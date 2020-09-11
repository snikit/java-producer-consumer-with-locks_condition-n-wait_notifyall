package me.snikit;

import java.util.LinkedList;
import java.util.Queue;

public final class BlockingQueueUsingWaitNotify<E> {

	int capacity;
	private Queue<E> queue;

	BlockingQueueUsingWaitNotify(int capacity) {
		this.capacity = capacity;
		this.queue = new LinkedList<E>();
	}

	void put(E element) throws InterruptedException {

		synchronized (queue) {

			while (capacity == queue.size()) {
				queue.wait();
			}

			this.queue.add(element);
			queue.notifyAll();
		}

	}

	E take() throws InterruptedException {
		synchronized (queue) {

			while (queue.size() == 0) {
				queue.wait();
			}

			E item = queue.remove();
			queue.notifyAll();
			return item;
		}
	}

}