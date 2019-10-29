package com.sirra.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IndividuIntrouvableException extends RuntimeException {
    public IndividuIntrouvableException(String s) {
        super(s);
    }
}
