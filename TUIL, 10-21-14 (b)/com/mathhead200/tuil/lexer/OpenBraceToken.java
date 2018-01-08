package com.mathhead200.tuil.lexer;


public class OpenBraceToken implements Token
{
	private OpenBraceToken() {
	}
	
	public static final OpenBraceToken instance = new OpenBraceToken();
	
	public String getLexeme() {
		return "{";
	}
}
