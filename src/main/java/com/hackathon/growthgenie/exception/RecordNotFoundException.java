package com.hackathon.growthgenie.exception;

import lombok.ToString;

@ToString
public class RecordNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private final String message;
  
  public RecordNotFoundException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
