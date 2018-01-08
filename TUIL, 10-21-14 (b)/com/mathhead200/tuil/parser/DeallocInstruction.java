package com.mathhead200.tuil.parser;

import java.util.NoSuchElementException;

import com.mathhead200.tuil.lexer.IdentifierToken;
import com.mathhead200.tuil.lexer.TerminatorToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class DeallocInstruction implements Instruction
{
	public final IdentifierToken variable;
	
	private DeallocInstruction(IdentifierToken variable) {
		this.variable = variable;
	}
	
	public static DeallocInstruction extract(TuilLexer lexer) {
		IdentifierToken variable;
		try {
			// parse variable
			Token token = lexer.next();
			if( token instanceof IdentifierToken )
				variable = (IdentifierToken) token;
			else
				throw new ParseException(token);
			// parse statement terminator ;
			token = lexer.next();
			if( !(token instanceof TerminatorToken) )
				throw new ParseException(token);
		} catch(NoSuchElementException e) {
			throw new ParseException("unexpected end of file", e);
		}
		return new DeallocInstruction(variable);
	}
}
