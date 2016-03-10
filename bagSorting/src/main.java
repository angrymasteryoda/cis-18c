import com.michael.api.IO.IO;

import java.util.Random;

/**
 * Created by michael on 3/9/2016.
 */
public class main {
	public static void main( String[] args ){
		Random random = new Random(  );
		Bag<String> bag = new Bag<>( );
		bag.add( "roy" );
		bag.add( "A" );
		bag.add( "apple" );
//		for ( int i = 0; i < 10; i++ ) {
//			bag.add( random.nextInt( 90 ) + 10 );
//		}
		IO.arrayPrinter( bag.toArray() );
		IO.println( "sorted" );
		bag.mergeSort( true );
		IO.arrayPrinter( bag.toArray() );
	}
}
