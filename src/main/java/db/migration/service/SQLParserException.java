package db.migration.service;

public class SQLParserException extends Exception{

    private Exception exception;

    public SQLParserException(Exception e){
        exception=e;
    }

    @Override
    public String getMessage() {
        return exception.getMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return exception.getStackTrace();
    }

    @Override
    public synchronized Throwable getCause() {
        return exception;
    }
}
