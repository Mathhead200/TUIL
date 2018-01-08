package com.mathhead200.tuil.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mathhead200.tuil.lexer.CloseParenthesisToken;
import com.mathhead200.tuil.lexer.FunctionToken;
import com.mathhead200.tuil.lexer.OpenParenthesisToken;
import com.mathhead200.tuil.lexer.OperatorToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class Expression implements Argument
{
	public final Token operator;
	public final List<Argument> arguments;
	
	private Expression(Token operator, List<Argument> arguments) {
		this.operator = operator;
		this.arguments = arguments;
	}
	
	public static Expression extract(TuilLexer lexer) {
		Token operator = null;
		List<Argument> arguments = new ArrayList<Argument>(2);
		while(true) {
			if( !lexer.hasNext() )
				throw new ParseException("no matching close parenthesis token \")\" for this expression");
			Token token = lexer.next();
			if( token instanceof CloseParenthesisToken )
				break;
			
			if( token instanceof FunctionToken  ) {
				if( arguments.size() != 0 )
					throw new ParseException("function token \"" + token.getLexeme() + "\" must be the first token in expression");
				if( operator != null )
					throw new ParseException("unexpected token \"" + token.getLexeme() + "\" in expression");
				operator = token;
			} else if( token instanceof OperatorToken ) {
				if( arguments.size() != 1 )
					throw new ParseException("operator token \"" + token.getLexeme() + "\" must be the second token in expression");
				if( operator != null )
					throw new ParseException("unexpected token \"" + token.getLexeme() + "\" in expression");
				operator = token;
			} else if( token instanceof Argument ) {
				if( (operator != null && !(operator instanceof FunctionToken) && arguments.size() == 2) || (operator == null && arguments.size() == 1) )
					throw new ParseException("unexpected token \"" + token.getLexeme() + "\" in expression");
				arguments.add((Argument) token);
			} else if( token instanceof OpenParenthesisToken ) {
				if( (operator != null && !(operator instanceof FunctionToken) && arguments.size() == 2) || (operator == null && arguments.size() == 1) )
					throw new ParseException("unexpected token \"" + token.getLexeme() + "\" in expression");
				arguments.add( Expression.extract(lexer) );
			} else {
				throw new ParseException("unexpected token \"" + token.getLexeme() + "\" in expression");
			}
		}
		return new Expression( operator, Collections.unmodifiableList(arguments) );
	}
}
