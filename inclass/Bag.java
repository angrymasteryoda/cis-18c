public class Bag<T> implements BagInterface<T> {
	private T[] bagItems;
	private int numItems;

	public bag(){
		bagItems = (T[])new Oject[10];
		numItems = 0;
	}

	public bag( int s ){
		bagItems = (T[])new Object[s];
		numItems = 0;
	}

	public int getsize(){return numItems;}
	public boolean isEMpty(){return numItems == 0;}
	public boolean add( T item ){
		if( numItems >= bagItems.length ){ return false;}
		bagItems[numItems++] = item;
		return true
	}

	public boolean remove( T item ){
		for( int i = 0; i < numItems; i++){
			if( bagItems[i] == item){
				for( int j = i+1; j < numItems; j++ ){
					bagItems[j-1] = bagItems[j];
				}
				numItems--;
				return true;
			}
		}
		return false;
	}

	public  void clear(){
		numItems = 0;
	}

	public contains( T item ){
		for( int i =0; i < numItems; i++) {
			if( bagItems[i] == item ){ return true;}
		}
		return false;
	}

	public T[] toArray(){
		T[] arr = (t[])new Object[numItems];
		for( int i =o; i < numItems; i++ ){
			arr[i] = bagItems[i];
		}
		return arr;
	}
}
