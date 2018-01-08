package com.mathhead200.tuil.lexer;


public class LexerException extends RuntimeException
{
	private static final long serialVersionUID = 6570788881399049367L;

	public LexerException() {
		super();
	}
	
	public LexerException(String message) {
		super(message);
	}
	
	public LexerException(Throwable cause) {
		super(cause);
	}
	
	public LexerException(String message, Throwable cause) {
		super(message, cause);
	}
}
