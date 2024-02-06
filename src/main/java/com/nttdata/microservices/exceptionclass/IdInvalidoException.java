package com.nttdata.microservices.exceptionclass;

public class IdInvalidoException extends RuntimeException {

	public IdInvalidoException(String message) {
    super(message);
	}
}
