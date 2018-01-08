package com.mathhead200.tuil.lexer;

import java.io.IOException;
import java.io.Reader;

import com.mathhead200.BigRational;
import com.mathhead200.tuil.ComplexRational;
import com.mathhead200.tuil.parser.Argument;


public class NumberToken implements Token, Argument
{
	private String lexeme;
	
	private NumberToken(String lexeme) {
		this.lexeme = lexeme;
	}
	
	public static NumberToken extract(Reader reader) throws IOException {
		StringBuilder lexeme = new StringBuilder();
		boolean hasDecimalPoint = false;
		int c;
		while(true) {
			reader.mark(1);
			c = reader.read();
			if( c < 0 )
				return null;
			if( c == 'i' ) {
				lexeme.append((char) c);
				break;
			}
			if( c == '.' ) {
				if( hasDecimalPoint ) {
					reader.reset();
					break;
				} else
					hasDecimalPoint = true;
			} else if( !Character.isDigit(c) ) {
				reader.reset();
				break;
			}
			lexeme.append((char) c);
		}
		return new NumberToken( lexeme.toString() );
	}
	
	public String getLexeme() {
		return lexeme;
	}
	
	public ComplexRational parseValue() {
		if( lexeme.charAt(lexeme.length() - 1) == 'i' ) {
			// imaginary number
			return new ComplexRational( BigRational.ZERO,
					new BigRational(lexeme.substring(0, lexeme.length() - 1)) );
		} else {
			// real number
			return new ComplexRational( new BigRational(lexeme), BigRational.ZERO );
		}
	}
}
