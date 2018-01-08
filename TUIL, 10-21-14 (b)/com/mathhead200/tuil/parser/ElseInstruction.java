package com.mathhead200.tuil.parser;

import com.mathhead200.tuil.lexer.TuilLexer;


public class ElseInstruction implements Instruction
{
	public final Instruction instruction;
	
	private ElseInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	
	public static ElseInstruction extract(TuilLexer lexer) {
		return new ElseInstruction( Instruction.extract(lexer) );
	}
}
