package com.workintech.movie.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MovieException extends RuntimeException {
    private HttpStatus httpStatus;

    public MovieException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
