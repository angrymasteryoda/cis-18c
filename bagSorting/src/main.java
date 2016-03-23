import com.michael.api.IO.IO;

import java.util.Random;

/**
 * Created by michael on 3/9/2016.
 */
public class main {
	public static void main( String[] args ){
		Random random = new Random(  );
		Bag<String> sBag = new Bag<>( );
		Bag<Float> fBag = new Bag<>( );
		Bag<Integer> iBag = new Bag<>( );
		int size = 10;
		IO.println( "selection sort\n");
		for ( int i = 0; i < size; i++ ) {
			sBag.addX( com.michael.api.math.Random.randomLetters( random.nextInt( 3 ) + 1 ) );
			iBag.add( ( random.nextInt( 99 ) + 1 ) );
			fBag.add( ( random.nextFloat() * 99 ) + 1 );
		}
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		IO.println( "sorted" );
		sBag.selectionSort( true );
		fBag.selectionSort( true );
		iBag.selectionSort( false );
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		sBag.clear();
		fBag.clear();
		iBag.clear();
		IO.println( "\nMerge sort\n");
		for ( int i = 0; i < size; i++ ) {
			sBag.addX( com.michael.api.math.Random.randomLetters( random.nextInt( 3 ) + 1 ) );
			iBag.add( ( random.nextInt( 99 ) + 1 ) );
			fBag.add( ( random.nextFloat() * 99 ) + 1 );
		}
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		IO.println( "sorted" );
		sBag.mergeSort( true );
		fBag.mergeSort( true );
		iBag.mergeSort( false );
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		sBag.clear();
		fBag.clear();
		iBag.clear();
		IO.println( "\nQuick sort\n");
		for ( int i = 0; i < size; i++ ) {
			sBag.addX( com.michael.api.math.Random.randomLetters( random.nextInt( 3 ) + 1 ) );
			iBag.add( ( random.nextInt( 99 ) + 1 ) );
			fBag.add( ( random.nextFloat() * 99 ) + 1 );
		}
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		IO.println( "sorted" );
		sBag.quickSort( true );
		fBag.quickSort( true );
		iBag.quickSort( false );
		IO.arrayPrinter( sBag.toArray() );
		IO.arrayPrinter( fBag.toArray() );
		IO.arrayPrinter( iBag.toArray() );
		sBag.clear();
		fBag.clear();
		iBag.clear();

	}
}
