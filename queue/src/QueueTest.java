import com.michael.api.IO.IO;
import com.michael.api.math.ApiMath;

import java.util.Scanner;

public class QueueTest {
	public static void main( String[] args ) {
		Queue<Integer> intQueue = new Queue<>();
		Queue<Integer> primes = new Queue<>();
		Scanner scanner = new Scanner( System.in );
		IO.print( "Input n: " ); //input n
		int n = scanner.nextInt();

		//fill the int queue
		for ( int i = 2; i <= n; i++ ) {
			intQueue.enqueue( i );
		}

		IO.print( "Content of: queueOfIntegers: " );
		intQueue.display();
		IO.println();

		do {
			int p = intQueue.dequeue();
			primes.enqueue( p );
			//take the size so we can use 2 queues
			int size = intQueue.size(), i = 0;
			while ( i < size ) {
				int testing = intQueue.dequeue();
				if ( testing % p != 0 ) {
					intQueue.enqueue( testing );
				}
				i++;
			}
			//print out the steps
			IO.print( "Primes: " );
			primes.display();
			IO.println();
			IO.print( "Content of: queueOfIntegers: " );
			intQueue.display();
			IO.println();
			IO.println();

		} while ( primes.back() < ApiMath.isqrt( n ) );

		while ( !intQueue.isEmpty() ) {
			primes.enqueue( intQueue.dequeue() );
		}

		IO.print( "primes: " );
		primes.display();

	}
}

//when you do this for real think about enqueuing it back to the list so you can do it with only 2 queues