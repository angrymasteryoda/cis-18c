public class Queue<T> implements QueueInterface<T> {
	// linked list containing the items in the queue
	private Node<T> head = null, tail = null;

	// current number of items in queue
	private int numberOfItems;

	// Queue constructor that starts with empty queue.
	public Queue() {
		numberOfItems = 0; // no items yet!
	}

	// displays current contents of the queue
	public void display() {
		Node current = head;
		for ( int i = 0; i < numberOfItems; i++ ) {
			System.out.print( current.getValue() + " " );
			current = current.getNext();
			if ( current == null ) break;
		}
	}

	// ----- functions/methods in the interface that must be implemented -----

	@Override
	public boolean isEmpty() {
		return ( head == null );
	}

	@Override
	public int size() {
		return numberOfItems;
	}

	@Override
	public T front() {
		return head.value;
	}

	@Override
	public T back() {
		return tail.value;
	}

	/**
	 * push an element to the back of the queue
	 * @param newItem the element to be be pushed to the queue
	 */
	@Override
	public void push_back( T newItem ) {
		if ( head == null ) {
			//nothing set at the head make it now
			Node<T> node = new Node<>( newItem );
			head = node;
			tail = head;
			numberOfItems++;
		} else {
			Node<T> node = new Node<>( newItem );
			node.setPrevious( tail );
			tail.setNext( node );
			tail = node;
			numberOfItems++;
		}
	}

	/**
	 * same as push_back
	 * @param newItem item to be added to queue
	 */
	@Override
	public void enqueue( T newItem ) {
		push_back( newItem );
	}

	/**
	 * pop the first element off and returns it
	 * same as dequeue
	 * @return the first element value
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public T pop_front() {
		if( head != null ){
			if ( head.getNext() != null ) { //if head has a next
				( head.getNext() ).setPrevious( null );
			} else {
				tail = null;
			}
			T data = head.getValue();
			head = head.getNext();
			numberOfItems--;
			return data;
		}
		return null;
	}

	/**
	 * same as pop_front
	 * @return
	 */
	@Override
	public T dequeue() {
		return pop_front();
	}

	// -----------------------------------------------------------------------

	// Node Inner Class
	private class Node<T> {
		private T value;
		private Node _previous, _next;

		public Node( T data ) {
			value = data;
			_previous = null;
			_next = null;
		}

		protected Node( T data, Node previousNode, Node nextNode ) {
			value = data;
			_previous = previousNode;
			_next = nextNode;
		}

		public Node getNext() {
			return _next;
		}

		public Node getPrevious() {
			return _previous;
		}

		public void setValue( T newValue ) {
			value = newValue;
		}

		public T getValue() {
			return value;
		}

		public void setNext( Node newNextNode ) {
			_next = newNextNode;
		}

		public void setPrevious( Node newPreviousNode ) {
			_previous = newPreviousNode;
		}
	}
}