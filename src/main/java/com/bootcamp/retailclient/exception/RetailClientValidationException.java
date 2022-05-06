package com.bootcamp.retailclient.exception;

public class RetailClientValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  public RetailClientValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public RetailClientValidationException(String message) {
    super(message);
  }

}
