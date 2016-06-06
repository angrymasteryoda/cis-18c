package michael.tk;

import com.michael.api.IO.IO;
import com.michael.api.math.ApiMath;
import com.michael.api.math.EquationSolver;

import java.util.Scanner;

/**
 * Created by michael on 5/25/2016.
 */
public class FinalMain {
	public static void main( String[] args ) {
//		Scanner in = new Scanner( System.in );
//		IO.println( "Enter an equation: " );
//		String eq = in.nextLine();
//		String post = convertToPostFix( eq );
//		IO.println( post );
//		IO.println( evaluatePostFix( post ) );

//		IO.println( EquationSolver.solveEquation( "3 * (4 + 5)" ) );
//		IO.println( convertToPostFix( "3 * ( 4 + 5 )") );
//		IO.println( convertToPostFix( "2 * ( ( 3 + 5 ) * ( 3 + 2 ) )") );
//		IO.println( convertToPostFix( "6 * (3+(7*8)*(5+2))") );
//		IO.println( convertToPostFix( "(7 + 1) * 9 / 5" ) );
//		IO.println( convertToPostFix( "(34 + 12) * 5 / 23" ) );
//		IO.println( convertToPostFix( "-2*(-3+-5)" ) );
//		IO.println( convertToPostFix( "/2" ) );
//		IO.println( convertToPostFix( "(2+8" ) );
//		IO.println( convertToPostFix( "(2+/8)" ) );
//		IO.println( convertToPostFix( "(2!+8)" ) );
//		IO.println( convertToPostFix( "2^4 + 2" ) );
//		IO.println( convertToPostFix( "5.4 + 5.5" ) );
//		IO.println( convertToPostFix( "((3+5)*6) * ( (4+8)*-2)" ) );
//		IO.println( convertToPostFix( "(3+5)*6 ((4+8)*-2)" ) );
//		IO.println( convertToPostFix( "3 * ( 4 + 5 )" ) );
//		IO.println( convertToPostFix( "2 * ( ( 3 + 5 ) * ( 3 + 2 ) )" ) );
//		IO.println( convertToPostFix( "6 * (3+(7*8)*(5+2))" ) );
//		IO.println( convertToPostFix( "(5*4)2" ) );
//		IO.println( convertToPostFix( "-2*(3+5)" ) ) ;
//		IO.println( convertToPostFix( "2!+2" ) ) ;

//		IO.println( evaluatePostFix( convertToPostFix( "((3+5)*6) * ( (4+8)*-2)" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "3 * ( 4 + 5 )") ) );
//		IO.println( evaluatePostFix( convertToPostFix( "2 * ( ( 3 + 5 ) * ( 3 + 2 ) )") ) );
//		IO.println( evaluatePostFix( convertToPostFix( "6 * (3+(7*8)*(5+2))") ) );
//		IO.println( evaluatePostFix( convertToPostFix( "-2*(3+5)" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "2!+2") ) );
//		IO.println( evaluatePostFix( convertToPostFix( "(7 + 1) * 9 / 5" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "(34 + 12) * 5 / 23" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "/2" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "(2+8" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "(2+/8)" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "(2!+8)" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "2^4 + 2" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "5.4 + 5.5" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "3*(6+4)(5-3)" ) ) );
//		IO.println( evaluatePostFix( convertToPostFix( "2*(3+5)-9" ) ) );

	}

	public static String convertToPostFix( String infix ) {
		if ( !isSolvable( infix ) ) {
			IO.printlnErr( infix + " Is Not Solvable!" );
			return "";
		}

		//remove all the spaces
		infix = infix.replaceAll( " ", "" );

		Tokenizer token = new Tokenizer( infix ); //create token
		StringBuilder postfix = new StringBuilder();
		Stack<Character> ops = new Stack<>();
		CharPattern nums = new CharPattern( "[0-9\\.]" );//DONE allow decimals


		for ( int i = 0; i < infix.length(); i++ ) {
			if ( isOperator( token.get() ) ) {
				if ( ops.size() == 0 ) { //if empty stack just add it
					if ( token.get() == '-' ) {
						if ( token.behind() == 0 ) {//it is the first char so its negative
							postfix.append( token.get() );
							token.next();
							continue;
						} else if ( isOperator( token.behind(), false ) ) {
							postfix.append( token.get() );
							token.next();
							continue;
						} else{
							ops.push( token.get() );
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
					if ( token.get() == '-' ) {
						if ( token.behind() == 0 ) {//it is the first char so its negative
							postfix.append( token.get() );
							token.next();
							continue;
						} else if ( isOperator( token.behind(), false ) ) {
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
		while ( ops.size() > 0 ) {
			postfix.append( ops.pop() ).append( " " );
		}

		return ( postfix.toString() );
	}

	public static String evaluatePostFix( String expr ) {
		String[] equation = expr.split( " " );
		Stack<String> stack = new Stack<>();
		double result;

		for ( int i = 0; i < equation.length; i++ ) {
			if ( !isNumber( equation[i] ) && isOperator( equation[i].charAt( 0 ), false ) ) {
				String op2 = stack.pop();
				String op1 = stack.pop();
				String re = solveEquation( op1 + " " + equation[i] + " " + op2 ); //DONE fix this
				stack.push( re );
			} else {
				if ( equation[i].contains( "!" ) ) {
					String num = equation[i].replaceFirst( "!", "" );
					String re = Long.toString( ApiMath.factorial( Long.parseLong( num ) ) );
					stack.push( re );
				} else {
					stack.push( equation[i] );
				}
			}
		}
		return removeFPart( stack.pop() );
	}

	private static boolean isNumber( String str ) {
		try {
			double d = Double.parseDouble( str );
		} catch ( NumberFormatException nfe ) {
			return false;
		}
		return true;
	}

	private static String removeFPart( String test ) {
		double num = Double.parseDouble( test );
		long iPart = (long) num;
		if( ( num - iPart ) == 0.0 ){
			long l = (long) num;
			return Long.toString( l );
		}
		return test;
	}

	private static String solveEquation( String eq ) {
		String[] equation = eq.split( " " );

		double op1 = Double.parseDouble( equation[0] );
		double op2 = Double.parseDouble( equation[2] );
		String op = equation[1];

		Double result = 0d;
		if ( op.equals( "+" ) ) {
			result = op1 + op2;
		} else if ( op.equals( "-" ) ) {
			result = op1 - op2;
		} else if ( op.equals( "*" ) ) {
			result = op1 * op2;
		} else if ( op.equals( "/" ) ) {
			result = op1 / op2;
		} else if ( op.equals( "^" ) ) {
			result = Math.pow( op1, op2 );
		}
		return Double.toString( result );
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

	public static boolean isSolvable( String infix ) {
		//remove all the spaces
		infix = infix.replaceAll( " ", "" );
		boolean error = false;

		Tokenizer token = new Tokenizer( infix ); //create token
		int parenthesise = 0;
		for ( int i = 0; i < infix.length(); i++ ) {
			if ( isOperator( token.get() ) ) {
				if ( token.get() == '(' ) {
					if( token.behind() != 0 && !isOperator( token.behind() ) ){
						error = true;
					}
					parenthesise++;
				} else if ( token.get() == ')' ) {
					if( token.forward() != 0 && !isOperator( token.forward() ) ) {
						error = true;
					}
					parenthesise--;
				} else {
					if ( token.get() == '-' ) {
						if ( isOperator( token.behind(), false ) && isOperator( token.forward(), false ) ) { //is looks like this +-* something is wrong
							error = true;
						}
					} else {
						if ( token.behind() == 0 ) {
							error = true;
						}

						if ( isOperator( token.forward(), false ) && token.forward() != '-' && token.get() != '!' ) {
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
