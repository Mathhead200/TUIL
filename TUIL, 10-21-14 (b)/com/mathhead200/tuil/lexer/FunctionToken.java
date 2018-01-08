package com.mathhead200.tuil.lexer;

import java.io.IOException;
import java.io.Reader;


public class FunctionToken implements Token
{	
	private String name;
	
	private FunctionToken(String name) {
		this.name = name;
	}
	
	public static FunctionToken extract(Reader reader) throws IOException {
		return new FunctionToken( IdentifierToken.extract(reader).getLexeme() );
	}

	public String getLexeme() {
		return "$" + name;
	}
}
