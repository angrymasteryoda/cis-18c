package michael.tk;

import com.michael.api.IO.IO;
import com.michael.api.math.EquationSolver;

/**
 * Created by michael on 5/25/2016.
 */
public class FinalMain {
	public static void main( String[] args ) {
//		IO.println( EquationSolver.solveEquation( "3 * (4 + 5)" ) );
//		convertToPostFix( "ab");
//		convertToPostFix( "3 * ( 4 + 5 )");
//		convertToPostFix( "2 * ( ( 3 + 5 ) * ( 3 + 2 ) )");
//		convertToPostFix( "6 * (3+(7*8)*(5+2))");
//		convertToPostFix( "-2*(-3+-5)" );
//		convertToPostFix( "/2" );
//		convertToPostFix( "(2+8" );
//		convertToPostFix( "(2+/8)" );
//		convertToPostFix( "(2!+8)" );
		convertToPostFix( "(5 + 3) * 12 / 3" );
	}

	public static void convertToPostFix( String equation ) {
		if ( !isSolvable( equation ) ) {
			IO.printlnErr( equation + " Is Not Solvable!" );
			return;
		}

		//remove all the spaces
		equation = equation.replaceAll( " ", "" );

		Tokenizer token = new Tokenizer( equation ); //create token
		StringBuilder postfix = new StringBuilder();
		Stack<Character> operations = new Stack<>();
		CharPattern nums = new CharPattern( "[0-9]" );
		CharPattern ops = new CharPattern( "[\\+\\-\\*\\/\\^\\!\\(\\)]" );

		for ( int i = 0; i < equation.length(); i++ ) {
			boolean skip = false;
			//IO.println( token.get() );
			if ( nums.matches( token.get() ) ) { //if matches a number add to the string
				if ( nums.matches( token.forward() ) || token.forward() == '!' ) {
					//if the next char is a number or factorial put it in without space
					postfix.append( token.get() );
				} else {
					postfix.append( token.get() + " " );
				}
			} else if ( ops.matches( token.get() ) ) { //if its an operation add to op stack
				//check if its a subtraction or negative
				if ( token.get() == '-' ) {
					//check if the one before even exists
					if ( token.behind() == 0 ) {
						skip = true;
						postfix.append( token.get() );
					} else if ( ops.matches( token.behind() ) ) {
						//if there is an op beforehand it is a negative +-4
						skip = true;
						postfix.append( token.get() );
					}
				}
				//check if a close parenthesise
				if ( token.get() == ')' ) {
					//pop off an operation to the string
					postfix.append( operations.pop() + " " );
					if ( operations.peek().equals( '(' ) ) { //if its a opening parenthesise pop it off
						skip = true;
						operations.pop();
					}
				}

				if ( token.get() == '!' ) { //if there is a factorial put it in now
					postfix.append( token.get() + " " );
					skip = true;
				}

				if ( !skip ) {
					operations.push( token.get() );
				}
			}
			token.next();
		}
		if ( operations.size() != 0 ) {
			//pop off the rest of the operations
			while ( operations.size() != 0 ) {
				if ( operations.peek().equals( ')' ) || operations.peek().equals( '(' ) ) {
					operations.pop();
				} else {
					postfix.append( operations.pop() + " " );
				}
			}
			//postfix.append( operations.pop() );
		}

		IO.println( postfix.toString() );
	}

	public static boolean isSolvable( String equation ) {
		//remove all the spaces
		equation = equation.replaceAll( " ", "" );
		boolean error = false;

		Tokenizer token = new Tokenizer( equation ); //create token
		CharPattern ops = new CharPattern( "[\\+\\-\\*\\/\\^\\!\\(\\)]" );
		CharPattern opsOnly = new CharPattern( "[\\+\\-\\*\\/\\^\\!]" );
		int oprands = 0;
		int parenthesise = 0;
		for ( int i = 0; i < equation.length(); i++ ) {
			if ( ops.matches( token.get() ) ) {
				if ( token.get() == '(' ) {
					parenthesise++;
				} else if ( token.get() == ')' ) {
					parenthesise--;
				} else {
					if ( token.get() == '-' ) {
						if ( ops.matches( token.behind() ) && ops.matches( token.forward() ) ) { //is looks like this +-* something is wrong
							error = true;
						}
					} else {
						if ( token.behind() == 0 ) {
							error = true;
						}

						if ( opsOnly.matches( token.forward() ) && token.forward() != '-' && token.get() != '!' ) {
							error = true;
						}
					}
				}
			}
			token.next();
		}

		if ( parenthesise != 0 ) {
			error = true;
		}
		return !error;
	}
}
