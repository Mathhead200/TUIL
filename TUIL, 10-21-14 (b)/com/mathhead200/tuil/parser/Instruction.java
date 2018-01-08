package com.mathhead200.tuil.parser;

import java.util.NoSuchElementException;

import com.mathhead200.tuil.lexer.CloseBraceToken;
import com.mathhead200.tuil.lexer.IdentifierToken;
import com.mathhead200.tuil.lexer.OpenBraceToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public interface Instruction
{
	// private:
	static Instruction _extractInstruction(Token token, TuilLexer lexer) {
		String lexeme = token.getLexeme().toUpperCase();
		if( lexeme.equals("IF") )
			return IfInstruction.extract(lexer);
		if( lexeme.equals("ELSE") )
			return ElseInstruction.extract(lexer);
		if( lexeme.equals("WHILE") )
			return WhileInstruction.extract(lexer);
		if( lexeme.equals("ALLOCSCREENBUFFER") )
			return AllocScreenBufferInstruction.extract(lexer);
		if( lexeme.equals("CALC") )
			return CalcInstruction.extract(lexer);
		if( lexeme.equals("DEALLOC") )
			return DeallocInstruction.extract(lexer);
		// TODO: ...
		throw new ParseException("invalid instruction: \"" + token.getLexeme() + "\"");
	}
	
	static InstructionBlock _extractInstructionBlock(TuilLexer lexer) {
		InstructionBlock instrBlock = new InstructionBlock();
		Instruction instr;
		while( (instr = _extract(lexer)) != null )
			instrBlock.add(instr);
		return instrBlock;
	}
	
	static Instruction _extract(TuilLexer lexer) {
		try {
			Token token = lexer.next();
			if( token instanceof IdentifierToken ) {
				// instruction ...;
				return _extractInstruction(token, lexer);
			} else if( token instanceof OpenBraceToken ) {
				// instruction block { ... }
				return _extractInstructionBlock(lexer);
			} else if( token instanceof CloseBraceToken ) {
				// end of instruction block
				return null; // this indicates the recursion should stop
			} else {
				throw new ParseException("unexpected token \"" + token.getLexeme() + "\"");
			}
		} catch(NoSuchElementException e) {
			throw new ParseException("unexpected end of file");
		}
	}
	
	
	// public:
	public static Instruction extract(TuilLexer lexer) {
		if( !lexer.hasNext() )
			return null;
		Instruction instr = _extract(lexer);
		if( instr == null )
			throw new ParseException("unmatched close brace token \"}\"");
		return instr;
	}
}
