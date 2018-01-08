package com.mathhead200.tuil.lexer;


public class TimesToken implements OperatorToken
{
	private TimesToken() {
	}
	
	public static final TimesToken instance = new TimesToken();
	
	public String getLexeme() {
		return "*";
	}
}
