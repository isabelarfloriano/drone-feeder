package com.dronefeeder.controller;

import com.dronefeeder.exception.AlreadyExistsException;
import com.dronefeeder.exception.DataError;
import com.dronefeeder.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice class.
 */
@ControllerAdvice
public class ManagerAdvice {

  /**
   * handlerConflict method.
   **/
  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<DataError> handlerConflict(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new DataError(exception.getMessage()));
  }

  /**
   * handlerNotFound method.
   **/
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<DataError> handlerNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new DataError(exception.getMessage()));
  }

}
