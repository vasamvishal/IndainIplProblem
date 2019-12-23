package iplpackage;

public class IPLBatsmenException extends Exception {
    public enum IPLException{
        NO_SUCH_FILE, INPUT_FILE_EXCEPTION, CSV_BUILDER_EXCEPTION, LIST_IS_EMPTY, HEADER_ISSUE
    }
    public IPLException type;

    public IPLBatsmenException(String message,IPLException type ) {
        super(message);
        this.type=type;
    }

}
