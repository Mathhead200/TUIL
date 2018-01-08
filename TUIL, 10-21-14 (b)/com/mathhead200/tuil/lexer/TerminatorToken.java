package com.mathhead200.tuil.lexer;


public class TerminatorToken implements Token
{
	private TerminatorToken() {
	}
	
	public static final TerminatorToken instance = new TerminatorToken();
	
	public String getLexeme() {
		return ";";
	}
}
