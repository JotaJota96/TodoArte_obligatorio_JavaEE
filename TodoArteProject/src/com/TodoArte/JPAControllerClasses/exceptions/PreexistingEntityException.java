package com.TodoArte.JPAControllerClasses.exceptions;

public class PreexistingEntityException extends Exception {
	private static final long serialVersionUID = 7125384833659581821L;

	public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
