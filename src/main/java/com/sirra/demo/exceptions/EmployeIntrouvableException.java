package com.sirra.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeIntrouvableException extends RuntimeException {
    public EmployeIntrouvableException(String s) {
        super(s);
    }
}
