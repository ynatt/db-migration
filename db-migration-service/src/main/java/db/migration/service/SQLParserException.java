package db.migration.service;

public class SQLParserException extends Exception{

    private Exception exception;

    public SQLParserException(String message){
        super(message);
    }

    public SQLParserException(Exception e){
        exception=e;
    }

    @Override
    public String getMessage() {
        if(exception!=null) {
            return exception.getMessage();
        }else {
            return super.getMessage();
        }
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
