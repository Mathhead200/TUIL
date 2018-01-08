package com.mathhead200.tuil.parser;

import java.io.Closeable;
import java.util.Iterator;

import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class TuilParser implements Closeable, Iterator<?>, Iterable<?>
{
	private TuilLexer lexer;
	
	public TuilParser(TuilLexer lexer) {
		this.lexer = lexer;
	}
	
	
}
