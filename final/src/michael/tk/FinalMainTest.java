package michael.tk;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michael on 5/25/2016.
 */
public class FinalMainTest {

	@Test
	public void testConvertToPostFix() throws Exception {
		assertEquals( "20+3*(5-1)", "32",    FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "20+3*(5-1)" ) ) );
		assertEquals( "3+22*(1+8)", "201",   FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "3+22*(1+8)" ) ) );
		assertEquals( "(5*4)*2", "40",       FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "(5*4)*2" ) ) );
		assertEquals( "2*(3+5)-9", "7",      FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "2*(3+5)-9" ) ) );
		assertEquals( "2*(13-(1+6))", "12",  FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "2*(13-(1+6))" ) ) );
		assertEquals( "48/3+5", "21",        FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "48/3+5" ) ) );
		assertEquals( "3*(6+4)*(5-3)", "60",  FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "3*(6+4)*(5-3)" ) ) );
		assertEquals( "100-4*(7-4)*3", "64", FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "100-4*(7-4)*3" ) ) );
		assertEquals( "12+23+33", "68",      FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "12+23+33" ) ) );
		assertEquals( "1*(24-6)/2", "9",      FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "1*(24-6)/2" ) ) );
		assertEquals( "2*((3+5)*(3+2))", "80", FinalMain.evaluatePostFix( FinalMain.convertToPostFix( "2*((3+5)*(3+2))" ) ) );

	}
}