package michael.tk;

/**
 * Created by michael on 5/25/2016.
 */
public class Tokenizer {

	private String string;
	private int current;
	private int lookAhead;
	private int lookBehind;

	public Tokenizer( String string ) {
		this.string = string;
		this.current = 0;
		this.lookAhead = 1;
		this.lookBehind = -1;
	}

	public char get(){
		return string.charAt( current );
	}

	public char behind(){
		return ( ( lookBehind < 0 ) ? ( '\0' ) : ( string.charAt( lookBehind ) ) );
	}

	public char forward(){
		return ( ( lookAhead >= string.length() ) ? ( '\0' ) : ( string.charAt( lookAhead ) ) );
	}

	public void next(){
		if ( current < string.length() ) {
			current++;
			lookAhead++;
			lookBehind++;
		}
	}

}
