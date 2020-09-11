package me.snikit;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RunabbleUtils {
//
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static Supplier<Item> itemProducer = () -> {
		RunabbleUtils.sleep(500);
		Item item = new Item(UUID.randomUUID().toString());
		System.out.println(item + " : produced by " + Thread.currentThread().getName());
		return item;
	};
	
	static Consumer<Item> itemConsumer= (item) -> {
		RunabbleUtils.sleep(2000);
		System.out.println(item + " : consumed " + Thread.currentThread().getName());
	};

}
