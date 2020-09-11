package me.snikit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class BlockingQueueUsingLocks<E> {

	int capacity;
	private Queue<E> queue;
	private ReentrantLock queueLock = new ReentrantLock(true);
	private Condition readCondition = queueLock.newCondition();
	private Condition writeCondition = queueLock.newCondition();

	BlockingQueueUsingLocks(int capacity) {
		this.capacity = capacity;
		this.queue = new LinkedList<E>();
	}

	void put(E element) throws InterruptedException {
		try {
			queueLock.lock();

			while (capacity == queue.size()) {
				writeCondition.await();
			}

			this.queue.add(element);
			readCondition.signalAll();
		} finally {
			queueLock.unlock();
		}

	}

	E take() throws InterruptedException {
		try {
			queueLock.lock();

			while (queue.size() == 0) {
				readCondition.await();
			}

			E item = queue.remove();
			writeCondition.signalAll();
			return item;
		} finally {
			queueLock.unlock();
		}

	}

}