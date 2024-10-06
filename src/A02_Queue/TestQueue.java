package A02_Queue;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestQueue {

	@Test
	public void getCount() throws QueueEmptyException {
		Queue<Integer> s = new Queue<>();
		assertEquals("Leere Queue", 0, s.getCount());
		s.enqueue(1);
		assertEquals("Ein Element", 1, s.getCount());
		s.enqueue(2);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.enqueue(3);
		assertEquals("Drei Elemente", 3, s.getCount());
		s.dequeue();
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.dequeue();
		assertEquals("Ein Element", 1, s.getCount());
		s.enqueue(4);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.dequeue();
		s.dequeue();
		assertEquals("Leere Queue", 0, s.getCount());
	}

	@Test(expected=QueueEmptyException.class)
	public void stackEmptyException() throws QueueEmptyException {
		Queue<Integer> s = new Queue<>();
		s.dequeue();
	}

	@Test(expected=QueueEmptyException.class)
	public void stackEmptyAgainException() throws QueueEmptyException {
		Queue<Integer> s = new Queue<>();
		s.enqueue(1);
		s.enqueue(2);
		s.dequeue();
		s.dequeue();
		s.dequeue();
	}

	@Test
	public void enqueuedequeue() throws QueueEmptyException {
		Queue<Integer> s = new Queue<>();
		s.enqueue(1);
		s.enqueue(2);
		s.enqueue(3);
		assertEquals("Eins", 1,(int) s.dequeue());
		s.enqueue(4);
		assertEquals("Zwei", 2,(int) s.dequeue());
		s.enqueue(5);
		s.enqueue(6);
		assertEquals("Drei", 3, (int)s.dequeue());
		assertEquals("Vier", 4,(int) s.dequeue());
		assertEquals("Fünf", 5,(int) s.dequeue());
		assertEquals("Sechs", 6,(int) s.dequeue());
		s.enqueue(7);
		assertEquals("Sieben", 7, (int)s.dequeue());
		s.enqueue(8);
		s.enqueue(9);
		assertEquals("Acht", 8, (int)s.dequeue());
		assertEquals("Neun", 9,(int) s.dequeue());
	}

}