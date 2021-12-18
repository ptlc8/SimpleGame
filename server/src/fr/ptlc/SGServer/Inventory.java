package fr.ptlc.SGServer;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends HashMap<Item, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	public Inventory() {}
	
	public Inventory(Map<Item, Integer> starterPack) {
		putAll(starterPack);
	}
	
	@Override
	public Integer get(Object arg) {
		Integer n = super.get(arg);
		return n==null ? 0 : n;
	}
	
	public void add(Map<? extends Item, Integer> stockables) {
		for (Entry<? extends Item, Integer> e : stockables.entrySet())
			put(e.getKey(), get(e.getKey())+e.getValue());
	}
	
	public void add(Item stockable, Integer quantity) {
		put(stockable, get(stockable)+quantity);
	}
	
	public boolean remove(Item stockable, Integer quantity) {
		if (get(stockable) < quantity) return false;
		put(stockable, get(stockable)-quantity);
		return true;
	}
	
	public boolean has(Item stockable, Integer quantity) {
		return get(stockable) >= quantity;
	}
		
}
