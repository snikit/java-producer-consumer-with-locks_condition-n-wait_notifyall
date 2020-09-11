package me.snikit;

public class Main {

//	private static BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);
	
//	private static BlockingQueueUsingLocks<Item> queue = new BlockingQueueUsingLocks<>(10);
	
	private static BlockingQueueUsingWaitNotify<Item> queue = new BlockingQueueUsingWaitNotify<>(10);
	
	
	

	private static final Runnable producer = () -> {

		try {

			while (true)
				queue.put(RunabbleUtils.itemProducer.get());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	};

	private static final Runnable consumer = () -> {

		try {

			while (true)
				RunabbleUtils.itemConsumer.accept(queue.take());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	};

	public static void main(String[] args) throws InterruptedException {
		
		
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(consumer).start();

//		CompletableFuture.allOf(CompletableFuture.runAsync(producer), CompletableFuture.runAsync(consumer),
//				CompletableFuture.runAsync(consumer)).join();

	}

}
