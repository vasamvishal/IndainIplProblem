package com.iplpackage;

public class IPLBatsmenException extends Exception {
    public enum IPLException{
        NO_SUCH_FILE, INPUT_FILE_EXCEPTION, CSV_BUILDER_EXCEPTION, HEADER_ISSUE
    }
    public IPLException type;

    public IPLBatsmenException(IPLException type, String message) {
        super(message);
        this.type=type;
    }

}
