package Ex9;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		ArrayDeque<Integer> fde = new ArrayDeque<Integer>();
		fde.add(1);
		fde.addFirst(2);
		fde.addLast(3);
		fde.add(4);
		System.out.println(fde.getFirst());
		System.out.println(fde.getLast());
		fde.remove();
		fde.removeLast();
		System.out.println();
		for(Integer gg : fde)
			System.out.println(gg);
		System.out.println();
		LinkedList<Integer> f = new LinkedList<Integer>();
		f.add(1);
		f.add(2);
		f.add(3);
		f.remove();
		for(Integer gg : f)
			System.out.println(gg);
		System.out.println();System.out.println();System.out.println();
		Deque<Integer> deque = new ArrayDeque<Integer>(7);
		 
        // Adding element to deque
        
       deque.add(5);
       deque.add(3);
       deque.add(2);
       deque.add(1);
        deque.add(4);
        for(Integer gg : deque)
			System.out.println(gg);
        
        // get queue from the deque
        // using asLifoQueue() method
        Queue<Integer> nq = Collections.asLifoQueue(deque);

        // printing the Queue
        System.out.println("View of the queue is: " + nq);
	}
}
