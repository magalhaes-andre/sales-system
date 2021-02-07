package com.magalhaes.andre.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class MissingInformationException extends ClientErrorException {

    private static final String DEFAULT_MESSAGE = "There is missing information in the received object: ";

    public MissingInformationException(Object o) {
        super(DEFAULT_MESSAGE.concat(o.toString()), Response.Status.BAD_REQUEST );
    }
}
