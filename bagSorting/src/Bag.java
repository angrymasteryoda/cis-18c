import java.lang.reflect.Array;

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
					if ( bagItems[sIndex].compareTo( bagItems[j] ) > 0 ) {
						sIndex = j;
					}
				} else {
					if ( bagItems[sIndex].compareTo( bagItems[j] ) < 0 ) {
						sIndex = j;
					}
				}

			}
			if ( sIndex != i ) {
				swap( bagItems, i, sIndex );
			}
		}
	}

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
				if ( a[leftBegin].compareTo( a[rightBegin] ) <= 0 ) {
					temp[i] = a[leftBegin];
					leftBegin++;
				} else{
					temp[i] = a[rightBegin]; //take the the unsorted elem from right half
					rightBegin++;
				}
			} else {
				if ( a[leftBegin].compareTo( a[rightBegin] ) >= 0 ) {
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


	private void swap( T[] array, int i, int j ){
		T temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
