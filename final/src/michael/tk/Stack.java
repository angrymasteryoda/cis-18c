package michael.tk;

import com.michael.api.IO.IO;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by michael on 5/25/2016.
 */
public class Stack< T >  implements StackImplement<T>, Serializable {

	protected Vector<T> items;
	protected int topIndex;
	protected T top;

	public Stack() {
		top = null;
		topIndex = -1;
		items = new Vector<>( 10 );
	}

	@Override
	public T pop() {
		top = ( ( topIndex - 1 < 0 ) ? ( null ) : ( items.get( topIndex - 1 ) ) );
		return items.remove( topIndex-- );
	}

	@Override
	public T peek() {
		return top;
	}

	@Override
	public void push( T data ) {
		items.add( ++topIndex, data );
		top = data;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public int size(){
		return items.size();
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for( T item : items ){
			sb.append( item ).append( ", " );
		}
		return sb.toString();
	}


}
