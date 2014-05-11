package com.dor.ws.jsonobjs.outputs;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 29.04.2014.
 */
public class ExceptionOutput {

    private String exceptionCode;

    private String exceptionDescription;

    public ExceptionOutput(String exceptionCode, String exceptionDescription) {
        this.exceptionCode = exceptionCode;
        this.exceptionDescription = exceptionDescription;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }
}
