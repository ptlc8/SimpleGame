package fr.ptlc.SGServer;

import java.util.HashMap;

public class Craft extends HashMap<Item, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	public Craft() {
		// do nothing
	}
	
	public Craft(Item m1, int a1) {
		put(m1, a1);
	}
	
	public Craft(Item m1, int a1, Item m2, int a2) {
		put(m1, a1);
		put(m2, a2);
	}
	
	public Craft(Item m1, int a1, Item m2, int a2, Item m3, int a3) {
		put(m1, a1);
		put(m2, a2);
		put(m3, a3);
	}
	
}
