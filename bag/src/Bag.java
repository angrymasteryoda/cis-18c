/**
 * Created by michael on 2/28/2016.
 */
public class Bag<T> implements BagInterface<T> {
	private T[] bagItems;
	private int numItems;

	@SuppressWarnings("unchecked")
	public Bag() {
		bagItems = (T[]) new Object[10];
		numItems = 0;
	}

	@SuppressWarnings("unchecked")
	public Bag( int s ) {
		bagItems = (T[]) new Object[s];
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
		T[] temp = (T[]) new Object[ this.bagItems.length * 2 ];
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
	public boolean contains( Object item ) {
		for ( int i = 0; i < numItems; i++ ) {
			if ( bagItems[i] == item ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T[] toArray() {
		T[] arr = (T[]) new Object[numItems];
		for ( int i = 0; i < numItems; i++ ) {
			arr[i] = bagItems[i];
		}
		return arr;
	}
}
