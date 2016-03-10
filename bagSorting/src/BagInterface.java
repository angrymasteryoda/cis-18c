/**
 * Created by michael on 2/28/2016.
 */
public interface BagInterface<T> {
	//get size
	public int getsize();

	//check if emtpy
	public boolean isEmpty();

	public boolean add( T item );

	public boolean remove( T item );

	public  void clear();

	public boolean contains( T item );

	public T[] toArray();
}
