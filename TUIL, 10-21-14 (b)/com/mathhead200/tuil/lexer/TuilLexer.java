package com.mathhead200.tuil.lexer;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class TuilLexer implements Closeable, Iterator<Token>, Iterable<Token>
{	
	private Reader reader;
	private Token nextToken = null;
	
	public TuilLexer(Reader reader) {
		this.reader = reader;
	}
	
	public void close() throws IOException {
		reader.close();
	}
	
	public boolean hasNext() {
		if( nextToken != null )
			return true;
		
		try {
			int c;
			
			// skip leading whitespace and comments
			boolean inComment = false;
			do {
				reader.mark(1);
				c = reader.read();
				if( c < 0 )
					return false;
				if( inComment ) {
					if( c == '\n' )
						inComment = false;
				} else { // !inComment
					if( c == '#' )
						inComment = true;
				}
			} while( inComment || Character.isWhitespace(c) );
			
			// read next lexeme
			if( Character.isAlphabetic(c) || c == '_' ) {
				// identifier
				reader.reset();
				nextToken = IdentifierToken.extract(reader);
			} else if( Character.isDigit(c) || c == '.' ) {
				// number
				reader.reset();
				nextToken = NumberToken.extract(reader);
			} else if( c == '"' ) {
				// string
				nextToken = StringToken.extract(reader);
			} else if( c == '(' ) {
				// open parenthesis
				nextToken = OpenParenthesisToken.instance;
			} else if( c == ')' ) {
				// close parenthesis
				nextToken = CloseParenthesisToken.instance;
			} else if( c == '{' ) {
				// open brace
				nextToken = OpenBraceToken.instance;
			} else if( c == '}' ) {
				// close brace
				nextToken = CloseBraceToken.instance;
			} else if( c == '[' ) {
				// open bracket
				nextToken = OpenBracketToken.instance;
			} else if( c == ']' ) {
				// close bracket
				nextToken = CloseBracketToken.instance;
			} else if( c == '+' ) {
				// plus
				nextToken = PlusToken.instance;
			} else if( c == '-' ) {
				// minus
				nextToken = MinusToken.instance;
			} else if( c == '*' ) {
				// times
				nextToken = TimesToken.instance;
			} else if( c == '/' ) {
				// divide
				nextToken = DivideToken.instance;
			} else if( c == '=' ) {
				// equal to
				nextToken = EqualToToken.instance;
			} else if( c == '!' ) {
				if( reader.read() == '=' ) {
					// not equal to
					nextToken = NotEqualToToken.instance;
				} else
					throw new LexerException("'!' not followed by '='");
			} else if( c == '<' ) {
				reader.mark(1);
				if( reader.read() == '=' ) {
					// less than or equal to
					nextToken = LessThanOrEqualToToken.instance;
				} else {
					// (strictly) less than
					reader.reset();
					nextToken = LessThanToken.instance;
				}
			} else if( c == '>' ) {
				reader.mark(1);
				if( reader.read() == '=' ) {
					// greater than or equal to
					nextToken = GreaterThanOrEqualToToken.instance;
				} else {
					// (strictly) greater than
					reader.reset();
					nextToken = GreaterThanToken.instance;
				}
			} else if( c == '$' ) {
				// function
				nextToken = FunctionToken.extract(reader);
			} else if( c == ';' ) {
				// statement terminator
				nextToken = TerminatorToken.instance;
			} else {
				// Error: invalid token
				throw new LexerException("Not a valid token: " + c);
			}
			
			// was the lexer successful?
			if( nextToken == null )
				throw new LexerException("Unexpected end of file.");
			
			return true;
		} catch(IOException e) {
			throw new LexerException(e);
		}
	}

	public Token next() {
		if( !hasNext() )
			throw new NoSuchElementException();
		Token token = nextToken;
		nextToken = null;
		return token;
	}
	
	public Iterator<Token> iterator() {
		return this;
	}
}
