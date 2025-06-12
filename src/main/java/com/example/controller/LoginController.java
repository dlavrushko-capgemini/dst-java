import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password cannot be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        AccountService accountService = new AccountService();
        User user = accountService.findUserByUsername(username);
        if (user != null && accountService.verifyPassword(user, password)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("userPage.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

@Controller
@RequestMapping("/soteria")
public class SoteriaController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView handleLogin(@RequestParam String username, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            modelAndView.addObject("errorMessage", "Username and password cannot be empty.");
            return modelAndView;
        }
        AccountService accountService = new AccountService();
        User user = accountService.findUserByUsername(username);
        try {
            if (user != null && accountService.verifyPassword(user, password)) {
                modelAndView.setViewName("redirect:/userPage.jsp");
                return modelAndView;
            } else {
                modelAndView.addObject("errorMessage", "Invalid username or password.");
                return modelAndView;
            }
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", "An unexpected error occurred. Please try again.");
            return modelAndView;
        }
    }
}