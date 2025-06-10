package utils;

public class ErrorHandler {

    public static String getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return (cause != null) ? cause.getMessage() : throwable.getMessage();
    }

    public static String generateErrorMessage(Throwable throwable) {
        String rootCause = getRootCause(throwable);
        return "An error occurred: " + rootCause;
    }
}