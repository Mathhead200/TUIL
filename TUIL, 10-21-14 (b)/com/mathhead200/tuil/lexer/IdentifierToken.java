package com.mathhead200.tuil.lexer;

import java.io.IOException;
import java.io.Reader;

import com.mathhead200.tuil.parser.Argument;


public class IdentifierToken implements Token, Argument
{
	private String lexeme;
	
	private IdentifierToken(String lexeme) {
		this.lexeme = lexeme;
	}
	
	public static IdentifierToken extract(Reader reader) throws IOException {
		StringBuilder lexeme = new StringBuilder();
		int c;
		while(true) {
			reader.mark(1);
			c = reader.read();
			if( c < 0 )
				return null;
			if( !(Character.isLetterOrDigit(c) || c == '_') ) {
				reader.reset();
				break;
			}
			lexeme.append((char) c);
		}
		return new IdentifierToken( lexeme.toString() );
	}
	
	public String getLexeme() {
		return lexeme;
	}
}
