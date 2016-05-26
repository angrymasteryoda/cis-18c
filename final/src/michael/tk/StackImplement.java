package michael.tk;

/**
 * Created by michael on 5/25/2016.
 */
public interface StackImplement<T>{
	T pop();
	T peek();
	void push( T data );
	boolean isEmpty();

}
