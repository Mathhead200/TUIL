package com.mathhead200.tuil.lexer;

import java.io.IOException;
import java.io.Reader;

import com.mathhead200.tuil.parser.Argument;
import com.mathhead200.tuil.parser.ParseException;


public class StringToken implements Token, Argument
{
	private String text;
	
	private StringToken(String text) {
		this.text = text;
	}
	
	public static StringToken extract(Reader reader) throws IOException {
		StringBuilder text = new StringBuilder();
		int c;
		boolean escaped = false;
		while(true) {
			c = reader.read();
			if( c < 0 )
				return null;
			if( escaped )
				escaped = false;
			else { // !escaped
				if( c == '"' )
					break;
				if( c == '\\' )
					escaped = true;
			}
			text.append((char) c);
		}
		return new StringToken( text.toString() );
	}
	
	public String getLexeme() {
		return "\"" + text + "\"";
	}
	
	public String parseValue() {
		StringBuilder str = new StringBuilder(text.length());
		boolean escaped = false;
		for( int i = 0; i < text.length(); i++ ) {
			char c = text.charAt(i);
			if( escaped ) {
				if( c == '\\' )
					str.append('\\');
				else if( c == '"' )
					str.append('"');
				else if( c == '\b' )
					str.append('\b');
				else if( c == '\f' )
					str.append('\f');
				else if( c == '\n' )
					str.append('\n');
				else if( c == '\r' )
					str.append('\r');
				else if( c == '\t' )
					str.append('\t');
				else
					throw new ParseException("Illegal escape character: " + c);
				escaped = false;
			} else { // !escaped
				if( c == '\\' )
					escaped = true;
				else
					str.append((char) c);
			}
		}
		return str.toString();
	}
}
