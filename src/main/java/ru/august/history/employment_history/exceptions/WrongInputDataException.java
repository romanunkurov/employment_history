package ru.august.history.employment_history.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongInputDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongInputDataException(String message) {
        super(message);
    }
}
