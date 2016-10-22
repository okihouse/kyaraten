package com.rest.error.exception;

public abstract class KyaratenException extends Exception{

	private static final long serialVersionUID = -1135602677055419438L;

	public abstract int errorCode();
	
	public abstract String errorMessage();
	
}
