import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by michael on 2/28/2016.
 */
public class Bag<T extends Comparable< ? super T > > implements BagInterface<T> {
	private T[] bagItems;
	private int numItems;

	@SuppressWarnings("unchecked")
	public Bag() {
		bagItems = (T[]) new Comparable[10];
		numItems = 0;
	}

	@SuppressWarnings("unchecked")
	public Bag( int s ) {
		bagItems = (T[]) new Comparable[s];
		numItems = 0;
	}

	@Override
	public int getsize() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public boolean add( T item ) {
		if ( numItems >= bagItems.length ) {
			resize( item );
		}
		bagItems[numItems++] = item;
		return true;
	}

	public void addX( T...items) {
		for( T x : items ){
			add( x );
		}
	}

	@SuppressWarnings( "unchecked" )
	private void resize( T item ){
		T[] temp = (T[]) new Comparable[ this.bagItems.length * 2 ];
		for ( int i = 0; i < numItems; i++ ) {
			temp[i] = bagItems[i];
		}
		bagItems = temp;
	}

	public boolean remove( T item, boolean keepOrder ){
		for( int i = 0; i < numItems; i++ ){
			if ( bagItems[i] == item ) {
				if ( !keepOrder ) {
					bagItems[i] = bagItems[numItems - 1];
					bagItems[--numItems] = null;
				} else{
					for ( int j = i + 1; j < numItems; j++ ) {
						bagItems[j - 1] = bagItems[j];
					}
					bagItems[ --numItems ] = null;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove( T item ) {
		return remove( item, false );
	}

	public boolean removeIndex( int i ) {
		try {
			if ( bagItems[i] != null ) {
				bagItems[i] = bagItems[numItems - 1];
				bagItems[--numItems] = null;
				return true;
			}
			return false;
		} catch ( Exception e ) {
			return false; //for null pointer and other stuff it might catch
		}
	}

	@Override
	public void clear() {
		numItems = 0;
	}

	@Override
	public boolean contains( T item ) {
		for ( int i = 0; i < numItems; i++ ) {
			if ( bagItems[i] == item ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T[] toArray() {
		T[] arr = (T[]) new Comparable[numItems];
		for ( int i = 0; i < numItems; i++ ) {
			arr[i] = bagItems[i];
		}
		return arr;
	}

	public void selectionSort( boolean isAscending ){
		//smallest index
		int sIndex;
		for ( int i  = 0; i < numItems; i++ ) {
			sIndex = i;
			for ( int j = i; j < numItems; j++ ) {

				if ( isAscending ) {
					//find smallest
					if (compare(  bagItems[sIndex], bagItems[j] ) > 0 ) {
						sIndex = j;
					}
				} else {
					if ( compare( bagItems[sIndex], bagItems[j] ) < 0 ) {
						sIndex = j;
					}
				}

			}
			if ( sIndex != i ) {
				swap( bagItems, i, sIndex );
			}
		}
	}

	/**
	 * Sorts the bag using the mergesort algorithm
	 * @param isAscending sort by ascending order
	 */
	public void mergeSort( boolean isAscending ){
		T temp[] = (T[]) new Comparable[ numItems ]; //make the temp array we can play wtih
		mergeSort( bagItems, temp, 0, numItems - 1, isAscending );
	}

	private void mergeSort( T[] a, T[] temp, int left, int right, boolean isAscending ){
		if ( left < right ) { //if left is left than right to get down to 1 elem array
			int center = ( left + right ) / 2;
			mergeSort( a, temp, left, center, isAscending );
			mergeSort( a, temp, center + 1, right, isAscending );
			mergeCompare( a, temp, left, center, right, isAscending );
		}
	}

	private void mergeCompare( T[] a, T[] temp, int left, int center, int right, boolean isAscending ){
		int leftBegin = left;
		int leftEnd = center;
		int rightBegin = center + 1;
		int rightEnd = right;
		int i = 0;

		while( ( leftBegin <= leftEnd ) && ( rightBegin <= rightEnd ) ){ //have we finished reading the half array
			if ( isAscending ) {
				if ( compare( a[leftBegin], a[rightBegin] ) <= 0 ) {
					temp[i] = a[leftBegin];
					leftBegin++;
				} else{
					temp[i] = a[rightBegin]; //take the the unsorted elem from right half
					rightBegin++;
				}
			} else {
				if ( compare( a[leftBegin], a[rightBegin] ) >= 0 ) {
					temp[i] = a[leftBegin];
					leftBegin++;
				} else{
					temp[i] = a[rightBegin]; //take the the unsorted elem from right half
					rightBegin++;
				}
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

	public void quickSort( boolean isAscending ){
		quickSort( bagItems, 0, numItems - 1, isAscending );
	}

	private void quickSort( T[] a, int left, int right, boolean isAscending ){
		int leftI = left; // left scan index
		int rightI = right; //right scan index
		Random rand = new Random( );
		T pivot = a[rand.nextInt(right)]; //using the a random point as the pivot
		while ( leftI < rightI ) { //if we havent scanned the left side all the way through yet
			if( isAscending ){
				while ( compare( a[leftI], pivot ) < 0 ) { //scan left side looking for index where larger than pivot
					leftI++;
				}

				while ( compare( a[rightI], pivot ) > 0 ) { //scan right side looking for index where smaller than pivot
					rightI--;
				}
			} else {
				while ( compare( a[leftI], pivot ) > 0 ) { //scan left side looking for index where larger than pivot
					leftI++;
				}

				while ( compare( a[rightI], pivot ) < 0 ) { //scan right side looking for index where smaller than pivot
					rightI--;
				}
			}


			if ( leftI <= rightI ) { //if the left index is smaller than we need to swap
				swap( a, leftI, rightI );
				leftI++;
				rightI--;
			}
		}
		if ( left < rightI ) {
			quickSort( a, left, rightI, isAscending );
		}
		if ( leftI < right ) {
			quickSort( a, leftI, right, isAscending );
		}
	}

	private int compare( T a, T b ){
		if ( a instanceof String ) {
			return ( (String) a ).compareToIgnoreCase( (String) b );
		} else{
			return a.compareTo( b );
		}
	}

	private void swap( T[] array, int i, int j ){
		T temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
