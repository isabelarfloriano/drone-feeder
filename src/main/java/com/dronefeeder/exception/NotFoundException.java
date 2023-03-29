package com.dronefeeder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AlreadyExistsException class.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}