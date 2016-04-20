import com.michael.api.IO.IO;

/**
 * Created by michael on 4/20/2016.
 */
public class Stack<T> {
	private T[] items;
	private int top;

	@SuppressWarnings("unchecked")
	public Stack() {
		items = (T[]) new Object[20];
		top = -1;
	}

	@SuppressWarnings("unchecked")
	public Stack( int InitialStackSize ) {
		items = (T[]) new Object[InitialStackSize];
		top = -1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == items.length - 1;
	}

	@SuppressWarnings( "unchecked" )
	public void push( T data ) {
		if ( !isFull() ) {
			items[++top] = data;
		} else {
			T[] temp = (T[]) new Object[ items.length * 2 ];
			for( int i = 0; i <= top; i++ ){
				temp[i] = items[i];
			}
			temp[++top] = data;
			items = temp;
		}
	}

	public T pop() {
		if ( isEmpty() ) return null;
		return items[top--];
	}

	public void display() {
		for ( int i = top; i >= 0; i-- ) {
			IO.print( items[i] + ", " );
		}
		IO.println();
	}
}
