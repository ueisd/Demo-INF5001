package com.sirra.demo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContactIntrouvableException extends RuntimeException {
    public ContactIntrouvableException(String s) {
        super(s);
    }

}
