package com.mathhead200.tuil.lexer;


public class OpenParenthesisToken implements Token
{
	private OpenParenthesisToken() {
	}
	
	public static final OpenParenthesisToken instance = new OpenParenthesisToken();
	
	public String getLexeme() {
		return "(";
	}
}
