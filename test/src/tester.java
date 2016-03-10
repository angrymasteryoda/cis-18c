import com.michael.api.IO.IO;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by michael on 3/9/2016.
 */
public class tester<T extends Comparable< ? super T > > {
	public static void main( String args[] ) {
//		Float a = 123.9f;
//		Float b = 85.2f;
//		IO.println( a.compareTo( b ));
		int a[] = { 8,5,7,6,3,1,2 };
		mergeSort( a );
		IO.arrayPrinter( a );
	}

	//starts the merger sort
	public static void mergeSort( int[] a ){
		int temp[] = new int[ a.length ]; //make the temp array we can play wtih
		mergeSort( a, temp, 0, a.length - 1 );
	}

	public static void mergeSort( int[] a, int[] temp, int left, int right ){
		if ( left < right ) { //if left is left than right to get down to 1 elem array
			int center = ( left + right ) / 2;
			mergeSort( a, temp, left, center );
			mergeSort( a, temp, center + 1, right );
			mergeCompare( a, temp, left, center, right );
		}
	}

	public static void mergeCompare( int[] a, int[] temp, int left, int center, int right ){
		int leftBegin = left;
		int leftEnd = center;
		int rightBegin = center + 1;
		int rightEnd = right;
		int i = 0;

		while( ( leftBegin <= leftEnd ) && ( rightBegin <= rightEnd ) ){ //have we finished reading the half array
			if ( a[leftBegin] <= a[rightBegin] ) {
				temp[i] = a[leftBegin];
				leftBegin++;
			} else{
				temp[i] = a[rightBegin]; //take the the unsorted elem from right half
				rightBegin++;
			}
			i++;
		}

		while( leftBegin <= leftEnd ){ //copy the missing elements from the a to temp
			temp[i] = a[leftBegin];
			i++;
			leftBegin++;
		}

		while( rightBegin <= rightEnd ){ //copy the missing elements from a to temp
			temp[i] = a[rightBegin];
			i++;
			rightBegin++;
		}

		//copy from where left started to the
		for ( int j = left; j <= rightEnd; j++ ) {
			a[j] = temp[ j - left];
		}

	}

}

/*

 */
