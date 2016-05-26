package michael.tk;

import com.michael.api.IO.IO;
import com.michael.api.math.EquationSolver;

/**
 * Created by michael on 5/25/2016.
 */
public class FinalMain {
	public static void main( String[] args ) {
//		IO.println( EquationSolver.solveEquation( "3 * (4 + 5)" ) );
//		convertToPostFix( "3 * ( 4 + 5 )");
//		convertToPostFix( "2 * ( ( 3 + 5 ) * ( 3 + 2 ) )");
//		convertToPostFix( "6 * (3+(7*8)*(5+2))");
//		convertToPostFix( "(7 + 1) * 9 / 5" );
//		convertToPostFix( "(34 + 12) * 5 / 23" );
//		convertToPostFix( "-2*(-3+-5)" );
//		convertToPostFix( "/2" );
//		convertToPostFix( "(2+8" );
//		convertToPostFix( "(2+/8)" );
//		convertToPostFix( "(2!+8)" );
//		convertToPostFix( "2^4 + 2" );
		convertToPostFix( "5.4 + 5.5" );
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
		Stack<Character> ops = new Stack<>();
		CharPattern nums = new CharPattern( "[0-9\\.]" );//DONE allow decimals


		for ( int i = 0; i < equation.length(); i++ ) {
			if ( isOperator( token.get() ) ) {
				if ( ops.size() == 0 ) { //if empty stack just add it
					if( token.get() == '-' ){
						if( token.behind() == 0 ) {//it is the first char so its negative
							postfix.append( token.get() );
							token.next();
							continue;
						} else if ( isOperator( token.behind() ) ) {
							postfix.append( token.get() );
							token.next();
							continue;
						}
					} else if ( token.get() == '!' ) { //if there is a factorial put it in now
						postfix.append( token.get() ).append( " " );
						token.next();
						continue;
					} else
						ops.push( token.get() );
				} else if ( token.get() == ')' ) { //if we are closing the parenthesise
					//pop off whatever is there until we hit the open parenthesise
					while ( ops.size() > 0 && ops.peek() != '(' ) {
						postfix.append( ops.pop() ).append( " " );
					}
					//remove the opening paren
					ops.pop();
				} else {
					if( token.get() == '-' ){
						if( token.behind() == 0 ) {//it is the first char so its negative
							postfix.append( token.get() );
							token.next();
							continue;
						} else if ( isOperator( token.behind() ) ) {
							postfix.append( token.get() );
							token.next();
							continue;
						}
					}
					if ( token.get() == '!' ) { //if there is a factorial put it in now
						postfix.append( token.get() ).append( " " );
						token.next();
						continue;
					}

					if ( ( token.get() == '(' && ops.peek().equals( '(' ) ) ||
							( pemdas( ops.peek() ) >= pemdas( token.get() ) ) ) { //if not an open paren and the stack is of higher or equal order pop into final
						while ( ops.size() > 0 && !ops.peek().equals( '(' ) && pemdas( ops.peek() ) >= pemdas( token.get() ) ) {
							postfix.append( ops.pop() ).append( " " );
						}
						ops.push( token.get() );
					} else {
						ops.push( token.get() );
					}
				}
			} else if ( nums.matches( token.get() ) ) {
				//DONE look for whole numbers, decimals, negatives
				if ( nums.matches( token.forward() ) || token.forward() == '!' ) {
					//if the next char is a number or factorial put it in without space
					postfix.append( token.get() );
				} else {
					postfix.append( token.get() ).append( " " );
				}
			}

			token.next();
		}

		//pop whatever is left in the stack off be atleast 1
		while( ops.size() > 0 ){
			postfix.append( ops.pop() );
		}

		IO.println( postfix.toString() );
	}

	private static int pemdas( char op ) {
		//factorial does not fall under this
		//http://mathforum.org/library/drmath/view/57493.html
		if ( op == '(' || op == ')' ) {
			return 4;
		} else if ( op == '^' ) {
			return 3;
		} else if ( op == '*' || op == '/' ) {
			return 2;
		} else if ( op == '-' || op == '+' ) {
			return 1;
		}
		IO.printlnErr( op + " is not in pemdas" );
		return -1; //should never happen but ya should check for it
	}

	public static boolean isSolvable( String equation ) {
		//remove all the spaces
		equation = equation.replaceAll( " ", "" );
		boolean error = false;

		Tokenizer token = new Tokenizer( equation ); //create token
		CharPattern ops = new CharPattern( "[\\+\\-\\*\\/\\^\\!\\(\\)]" );
		CharPattern opsOnly = new CharPattern( "[\\+\\-\\*\\/\\^\\!]" );
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

//						if( token.get() == '/' ){ //check divide by 0. will allow for now but will change in the calculation
//							error = true;
//						}
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

	/**
	 * Test if it is an operator or not
	 *
	 * @param test         char to test
	 * @param parenthesise if should allow parenthesise
	 * @return true if it is an operator
	 */
	private static boolean isOperator( char test, boolean parenthesise ) {
		CharPattern ops = ( ( parenthesise ) ? ( new CharPattern( "[\\+\\-\\*\\/\\^\\!\\(\\)]" ) ) : ( new CharPattern( "[\\+\\-\\*\\/\\^\\!]" ) ) );
		return ops.matches( test );
	}

	/**
	 * Test if it is an operator or not where parenthesise are allowed
	 *
	 * @param test char to test
	 * @return true if it is an operator
	 */
	private static boolean isOperator( char test ) {
		return isOperator( test, true );
	}

}
