import com.michael.api.IO.IO;
import com.michael.api.json.JSONObject;
import com.michael.api.math.Random;

/**
 * Created by michael on 4/20/2016.
 */
public class StackDemo {

	public static void main( String[] args ){
		Stack<Integer> iStack = new Stack(  );
		for( int i = 0; i < 21; i++ ){
			iStack.push( i );
		}
		IO.println( "Integer stack" );
		iStack.display();
		IO.println( "pop off an element" );
		IO.println( "popped off: " + iStack.pop() );
		iStack.display();
		IO.println( "\nfloat stack" );
		Stack<Float> fStack = new Stack(  );
		for( float i = 0; i < 21; i++ ){
			fStack.push( i );
		}
		fStack.display();
		IO.println( "\nString stack" );
		Stack<String> sStack = new Stack(  );
		for( int i = 0; i < 21; i++ ){
			sStack.push( Random.randomAlphaNumeric( 4 ) );
		}
		sStack.display();
		IO.println( "\nJSON stack" );
		Stack<JSONObject> JsonStack = new Stack(  );
		Random rand = new Random();
		for( int i = 0; i < 21; i++ ){
			JSONObject o = new JSONObject();
			o.put( Random.randomAlphaNumeric( 2 ), rand.nextInt() );
			o.put( "index", i );
			JsonStack.push( o );
		}
		JsonStack.display();
	}
}
