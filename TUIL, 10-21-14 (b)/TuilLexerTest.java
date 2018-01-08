import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.mathhead200.tuil.lexer.CloseBraceToken;
import com.mathhead200.tuil.lexer.OpenBraceToken;
import com.mathhead200.tuil.lexer.TerminatorToken;
import com.mathhead200.tuil.lexer.Token;
import com.mathhead200.tuil.lexer.TuilLexer;


public class TuilLexerTest
{
	public static void main(String[] args) {

		if( args.length < 1 ) {
			System.out.println("Please supply a file (as argument) to Lex.");
			System.exit(0);
		}
		
		try( TuilLexer lexer = new TuilLexer(new BufferedReader(new FileReader(args[0]))) ) {
			for( Token token : lexer ) {
				System.out.printf( "[%s %s]", token.getClass().getSimpleName(), token.getLexeme() );
				if( token instanceof TerminatorToken || token instanceof OpenBraceToken || token instanceof CloseBraceToken )
					System.out.println();
				else
					System.out.print(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
