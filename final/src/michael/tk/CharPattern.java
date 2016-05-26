package michael.tk;

import java.util.regex.Pattern;

/**
 * Created by michael on 5/25/2016.
 */
public class CharPattern {
	private char c;
	private String regex;

	public CharPattern( String regex ) {
		this.regex = regex;
	}

	public boolean matches( char c ){
		Pattern patt = Pattern.compile( regex );
		String str = c + "";
		return patt.matcher( str ).matches();
	}
}
