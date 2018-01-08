package com.mathhead200.tuil.parser;

import com.mathhead200.tuil.lexer.Token;


public class ParseException extends RuntimeException
{
	private static final long serialVersionUID = 1791116564631088364L;

	public ParseException() {
		super();
	}
	
	public ParseException(String message) {
		super(message);
	}
	
	public ParseException(Throwable cause) {
		super(cause);
	}
	
	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ParseException(Token token) {
		this("unexpected token \"" + token.getLexeme() + "\"");
	}
}
