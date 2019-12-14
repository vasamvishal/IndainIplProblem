package com.iplpackage;

public class IPLBatsmenException extends Exception {
    public IPLBatsmenException(IPLException type, String message) {
        super(message);
        this.type=type;
    }

    public enum IPLException{
        NO_SUCH_FILE,HEADER_ISSUE
    }
    public IPLException type;

}
