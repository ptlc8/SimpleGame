package fr.ptlc.SGServer;

import java.util.HashMap;
import java.util.Map;

public class Loot {
	
	private int xp;
	
	private int elo;
	
	private Map<Item, Integer> items;
	
	public Loot() {
		xp = 0;
		elo = 0;
		items = new HashMap<Item, Integer>();
	}
	
	public Loot(int xp, int elo, Map<Item, Integer> items) {
		this.xp = xp;
		this.elo = elo;
		this.items = items;
	}
	
	public int getXP() {
		return xp;
	}
	
	public void increaseXP(int xp) {
		this.xp += xp;
	}
	
	public int getElo() {
		return elo;
	}
	
	public void increaseElo(int elo) {
		this.elo += elo;
	}
	
	public void addItem(Item item, int amount) {
		items.put(item, amount);
	}
	
	public Map<Item, Integer> getItems() {
		return items;
	}
	
}
