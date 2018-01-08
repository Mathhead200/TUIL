package com.mathhead200.tuil.parser;

import java.util.NoSuchElementException;

import com.mathhead200.tuil.lexer.IdentifierToken;
import com.mathhead200.tuil.lexer.TerminatorToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class CalcInstruction implements Instruction
{
	public final IdentifierToken variable;
	public final Argument value;
	
	private CalcInstruction(IdentifierToken variable, Argument value) {
		this.variable = variable;
		this.value = value;
	}
	
	public static CalcInstruction extract(TuilLexer lexer) {
		IdentifierToken variable;
		Argument value;
		try {
			// parse variable
			Token token = lexer.next();
			if( token instanceof IdentifierToken )
				variable = (IdentifierToken) token;
			else
				throw new ParseException(token);
			// parse value
			token = lexer.next();
			if( token instanceof Argument )
				value = (Argument) token;
			else
				throw new ParseException(token);
			// parse statement terminator ;
			token = lexer.next();
			if( !(token instanceof TerminatorToken) )
				throw new ParseException(token);
		} catch(NoSuchElementException e) {
			throw new ParseException("unexpected end of file", e);
		}
		return new CalcInstruction(variable, value);
	}
}
