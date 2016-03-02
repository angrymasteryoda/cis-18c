/**
 * Created by michael on 2/28/2016.
 */
public class BagMain {
	public static void main( String[] args ){
		Bag<Integer> bag = new Bag<>( 2 );
		bag.add( 1 );
		bag.add( 2 );
		bag.add( 3 );
		bag.add( 4 );
		bag.add( 5 );

		bag.remove( 2 );

		Object[] arr = bag.toArray();
		for( Object x : arr ){
			System.out.println( x );
		}
	}
}
