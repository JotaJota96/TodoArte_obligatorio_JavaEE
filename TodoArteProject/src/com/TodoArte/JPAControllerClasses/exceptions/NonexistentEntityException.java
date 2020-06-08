package com.TodoArte.JPAControllerClasses.exceptions;

public class NonexistentEntityException extends Exception {
	private static final long serialVersionUID = -8577906330731916005L;

	public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
