package com.dronefeeder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AlreadyExistsException class.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
@SuppressWarnings("serial")
public class AlreadyExistsException extends RuntimeException {
  public AlreadyExistsException(String message) {
    super(message);
  }
}
