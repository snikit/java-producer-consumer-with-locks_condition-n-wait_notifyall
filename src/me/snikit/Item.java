package me.snikit;

public class Item {

	private String randomId;

	public Item(String randomId) {
		this.randomId = randomId;
	}

	@Override
	public String toString() {
		return randomId;
	}

}
