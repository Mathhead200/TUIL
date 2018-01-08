package com.mathhead200.tuil.lexer;


public class OpenBracketToken implements Token
{
	private OpenBracketToken() {
	}
	
	public static final OpenBracketToken instance = new OpenBracketToken();
	
	public String getLexeme() {
		return "[";
	}
}
