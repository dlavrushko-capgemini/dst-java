<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseLayout>
    <div class="col-md-12">
        <h1 class="alert alert-success">Thank you ${account.username}, for your registration!</h1>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="./user" class="btn btn-primary btn-block">Continue &raquo;</a>
        </div>
    </div>
</t:baseLayout>