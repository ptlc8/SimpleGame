package fr.ptlc.SGServer;

public interface Stockable {
	
	public Craft getCraft();
	
	public static Stockable get(String name) {
		try {
			return Material.valueOf(name);
		} catch (Exception e) {
			return Item.get(name);
		}
	}
	
}
