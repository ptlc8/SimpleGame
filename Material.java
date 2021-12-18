package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum Material implements Stockable {
	
	//nothing((byte)0),
	sand((byte)8),
	wood((byte)8),
	stone((byte)16),
	iron((byte)16),
	caou((byte)4),
	magic_powder((byte)4),
	explosive_powder((byte)2),
	rag((byte)8),
	amethyst((byte)2),
	venom((byte)4),
	gold((byte)4),
	ink((byte)8),
	paper((byte)16),
	ice((byte)8),
	lithium((byte)2),
	coal((byte)8),
	void_bottle((byte)1),
	aluminium((byte)8),
	leather((byte)16),
	water((byte)16),
	gasoline((byte)4),
	glass(new Craft(Material.sand, 3)),
	steel(new Craft(iron, 2, coal, 1)),
	poison_bottle(new Craft(venom, 4, glass, 6, water, 2)),
	battery(new Craft(lithium, 2, iron, 3)),
	frost_bottle(new Craft());
	
	private final byte drop;
	private final Craft craft;
	
	private Material(byte drop) {
		this.drop = drop;
		craft = null;
	}
	
	private Material(Craft craft) {
		drop = 0;
		this.craft = craft;
	}
	
	@Override
	public Craft getCraft() {
		return craft;
	}
	
	
	private static Material[] all = getAll();
	private static Material[] getAll() {
		List<Material> all = new ArrayList<Material>();
		for (Material m : values())
			for (int j = 0; j < m.drop; j++)
				all.add(m);
		return (Material[]) all.toArray(new Material[all.size()]);
	}
	
	public static Map<Material, Integer> getLoot(int diversity, int total) {
		Material[] materials = new Material[diversity];
		for (int i = 0; i < diversity; i++) {
			boolean isUnique;
			do {
				materials[i] = all[new Random().nextInt(all.length)];
				isUnique = true;
				for (int j = 0; j < i; j++)
					if (materials[i].equals(materials[j])) {
						isUnique = false;
						break;
					}
			} while (!isUnique);
			
		}
		List<Material> materialsSorted = new ArrayList<Material>();
		for (Material m : materials)
			materialsSorted.add(m);
		materialsSorted.sort(new Comparator<Material>() {
			@Override
			public int compare(Material m1, Material m2) {
				return m2.drop-m1.drop;
			}});
		Map<Material, Integer> loots = new HashMap<Material, Integer>();
		float i = 0.5f;
		for (Material m : materialsSorted) {
			loots.put(m, (int)Math.floor(i*total));
			i /= 2;
		}
		return loots;
	}
	
}
