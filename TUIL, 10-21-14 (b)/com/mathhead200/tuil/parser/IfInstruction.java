package com.mathhead200.tuil.parser;

import com.mathhead200.tuil.lexer.OpenParenthesisToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class IfInstruction implements Instruction
{
	public final Argument predicate;
	public final Instruction instruction;
	
	private IfInstruction(Argument predicate, Instruction instruction) {
		this.predicate = predicate;
		this.instruction = instruction;
	}
	
	public static IfInstruction extract(TuilLexer lexer) {
		Argument predicate;
		if( !lexer.hasNext() )
			throw new ParseException("unexpected end of file");
		Token token = lexer.next();
		if( token instanceof Argument )
			predicate = (Argument) token;
		else if( token instanceof OpenParenthesisToken )
			predicate = Expression.extract(lexer);
		else
			throw new ParseException("unexpected token \"" + token.getLexeme() + "\"");
		Instruction instruction = Instruction.extract(lexer);
		return new IfInstruction(predicate, instruction);
	}
}
