package com.hackathon.growthgenie.exception;


import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<?> handleApplicationException(
      final RecordNotFoundException exception, final HttpServletRequest request
) {

  return new ResponseEntity<>(exception, HttpStatus.NO_CONTENT);
}
}
