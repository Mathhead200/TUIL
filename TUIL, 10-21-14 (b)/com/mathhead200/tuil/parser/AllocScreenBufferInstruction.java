package com.mathhead200.tuil.parser;

import java.util.NoSuchElementException;

import com.mathhead200.tuil.lexer.IdentifierToken;
import com.mathhead200.tuil.lexer.TerminatorToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class AllocScreenBufferInstruction implements Instruction
{
	public final IdentifierToken variable;
	public final Argument rows;
	public final Argument columns;
	
	private AllocScreenBufferInstruction(IdentifierToken variable, Argument rows, Argument columns) {
		this.variable = variable;
		this.rows = rows;
		this.columns = columns;
	}
	
	public static AllocScreenBufferInstruction extract(TuilLexer lexer) {
		IdentifierToken variable;
		Argument rows;
		Argument columns;
		try {
			// parse variable
			Token token = lexer.next();
			if( token instanceof IdentifierToken )
				variable = (IdentifierToken) token;
			else
				throw new ParseException(token);
			// parse rows
			token = lexer.next();
			if( token instanceof Argument )
				rows = (Argument) token;
			else
				throw new ParseException(token);
			// parse columns
			token = lexer.next();
			if( token instanceof Argument )
				columns = (Argument) token;
			else
				throw new ParseException(token);
			// parse statement terminator ;
			token = lexer.next();
			if( !(token instanceof TerminatorToken) )
				throw new ParseException(token);
		} catch(NoSuchElementException e) {
			throw new ParseException("unexpected end of file", e);
		}
		return new AllocScreenBufferInstruction(variable, rows, columns);
	}
}
