package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.ptlc.SGServer.Modifier.Modif;
import fr.ptlc.SGServer.game.Type;

public enum Item {
	
	/*sand((byte)8),
	wood((byte)8),
	stone((byte)16),
	iron((byte)16),
	caou((byte)4),*/
	magic_powder((byte)4),
	/*explosive_powder((byte)2),
	rag((byte)8),*/
	amethyst((byte)2),
	/*venom((byte)4),
	gold((byte)4),
	ink((byte)8),
	paper((byte)16),*/
	ice((byte)8),
	/*lithium((byte)2),
	coal((byte)8),
	void_bottle((byte)1),
	aluminium((byte)8),
	leather((byte)16),
	water((byte)16),
	gasoline((byte)4),
	glass(new Craft(sand, 3)),
	steel(new Craft(iron, 2, coal, 1)),
	poison_bottle(new Craft(venom, 4, glass, 6, water, 2)),
	battery(new Craft(lithium, 2, iron, 3)),
	frost_bottle(new Craft()),*/
	
	invisibility_cloak(Slot.hand, new Craft(/*rag, 22, magic_powder, 5*/), Type.MAGIC),
	shield(Slot.hand, new Craft(/*iron, 8, wood, 16*/), Type.PHYSIC),
	//intangibility_ring(Slot.hand, new Craft(/*gold, 6, magic_powder, 3*/), Type.MAGIC),
	explosive_trap(Slot.hand, new Craft(/*explosive_powder, 4, steel, 7*/), Type.EXPLOSIVE),
	poison_trap(Slot.hand, new Craft(/*poison_bottle, 1, steel, 7*/), Type.CHIMIC),
	//applicable_poison(Slot.hand, new Craft(/*poison_bottle, 1, rag, 4*/), Type.CHIMIC),
	throwable_poison(Slot.hand, new Craft(/*poison_bottle, 1, sand, 4*/), Type.CHIMIC),
	//crystal_ball(Slot.hand, new Craft(/*glass, 9, magic_powder, 4*/), Type.MAGIC),
	tracking(Slot.hand, new Craft(/*battery, 1, steel, 5*/), Type.ELECTRIC),
	//throwable_frost(Slot.hand, new Craft(/*frost_bottle, 1, sand, 4*/), Type.FROST),
	//applicable_frost(Slot.hand, new Craft(/*frost_bottle, 1, rag, 4*/), Type.FROST),
	molotov_cocktail(Slot.hand, new Craft(/*rag, 1, glass, 2, gasoline, 3*/), Type.FIRE),
	stone_wall(Slot.hand, new Craft(/*stone, 32*/), Type.NATURAL),
	healing_potion(Slot.hand, new Craft(/**/), Type.MAGIC),
	dynamite(Slot.hand, new Craft(/*explosive_powder, 4, paper, 8*/), Type.EXPLOSIVE),
	throwable_slowness(Slot.hand, new Craft(/**/), Type.FROST),
	//applicable_slowness(Slot.hand, new Craft(/**/), Type.FROST),
	healing_ring(Slot.hand, new Craft(/**/), Type.MAGIC),
	//laser(Slot.hand, new Craft(/*battery, 1, glass, 1, steel, 3*/), Type.ELECTRIC),
	teleportation(Slot.hand, new Craft(/*void_bottle, 4*/), Type.DISTANCE),
	taser(Slot.hand, new Craft(/*battery, 2, caou, 4, steel, 9*/), Type.ELECTRIC),
	smoke(Slot.hand, new Craft(/*sand, 4, paper, 2, explosive_powder, 1*/), Type.CHIMIC),
	orange_juice(Slot.hand, new Craft(/**/), Type.NATURAL),
	sismic_glove(Slot.hand, new Craft(/**/), Type.PHYSIC),
	
	casque_radar(Slot.head, new Craft(/*battery, 1, steel, 4, leather, 2*/), new Modifier(Modif.SIGHT_BONUS, 0.2f)),
	horn(Slot.head, new Craft(/*amethyst, 1, magic_powder, 3*/), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	magic_hat(Slot.head, new Craft(/*rag, 9, magic_powder, 1*/)),
	big_heart(Slot.chest, new Craft(/**/), new Modifier(Modif.HEALTH_BONUS, 0.2f)),
	//jetpack(Slot.chest, new Craft(/*battery, 4, aluminium, 22)), // Slot.hand ???
	dynamite_belt(Slot.chest, new Craft(/*leather, 5, explosive_powder, 3*/), new Modifier(Modif.FINAL_EXPLOSION, 1f)),
	energy_pump(Slot.chest, new Craft(/*void_bottle, 1, caou, 6, wood, 18*/), new Modifier(Modif.ENERGY_BOOST, 0.2f)),
	energy_tank(Slot.chest, new Craft(/**/), new Modifier(Modif.ENERGY_SUPPLY, 0.2f)),
	hot_water_chestplate(Slot.chest, new Craft(/*caou, 7, water, 5*/), new Modifier(Modif.FROST_RESISTANCE, 0.2f)),
	cape(Slot.chest, new Craft(/**/), new Modifier(Modif.SPEED, 0.1f)),
	celerity_boots(Slot.foot, new Craft(/*leather, 14, magic_powder, 6*/), new Modifier(Modif.SPEED, 0.2f)),
	//ice_boots(Slot.foot, new Craft(/*ice, 12, leather, 12*/), new Modifier(Modif.GLIDE, 0.4f)),
	amethyst_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	amethyst_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	amethyst_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.15f)),
	amethyst_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	iron_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.SPEED, -0.08f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	aluminium_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.05f)),
	aluminium_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.05f)),
	aluminium_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.05f), new Modifier(Modif.SPEED, -0.02f)),
	aluminium_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.RESISTANCE, 0.05f)),
	caou_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	thorny_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	//casque_du_vide(Slot.head, new Craft(/*)),
	//plastron_du_vide(Slot.chest, new Craft(/*)),
	//jambières_du_vide(Slot.legs, new Craft(/*)),
	//bottes_du_vide(Slot.foot, new Craft(/*)),
	miror_ball(Slot.head, new Craft(/**/), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.05f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	foam_helmet(Slot.head, new Craft(/**/), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_chestplate(Slot.chest, new Craft(/**/), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_leggings(Slot.legs, new Craft(/**/), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.25f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_boots(Slot.foot, new Craft(/**/), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	exoskeleton_arms(Slot.chest, new Craft(/**/), new Modifier(Modif.ANTI_STUN_ATTACK, 1f)),
	exoskeleton_legs(Slot.legs, new Craft(/**/), new Modifier(Modif.ANTI_STUN_MOVE, 1f)),
	;
	
	
	
	public static Item get(String name) {
		try {
			return valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static final Item getRandom() {
		return values()[new Random().nextInt(values().length)];
	}
	
	private Slot slot = Slot.no_one;
	private byte drop = 0;
	private Craft craft = null;
	private Type type = null;
	private Modifier[] modifiers = new Modifier[0];
	
	// hand item constructor
	private Item(Slot slot, Craft craft, Type type) {
		this.slot = slot;
		this.craft = craft;
		this.type = type;
	}
	
	// armor item constructor
	private Item(Slot slot, Craft craft, Modifier... modifiers) {
		this.slot = slot;
		this.craft = craft;
		this.modifiers = modifiers;
	}
	
	// primary material constructor
	private Item(byte drop) {
		this.drop = drop;
	}
	
	// craftable material constructor
	private Item(Craft craft) {
		this.craft = craft;
	}
	
	public Slot getSlot() {
		return slot;
	}
	
	public Craft getCraft() {
		return craft;
	}
	
	public boolean isSlot(Slot slot) {
		return this.slot.equals(slot);
	}
	
	public Type getType() {
		return type;
	}
	
	public Modifier[] getModifiers() {
		return modifiers;
	}
	
	private static Item[] all = getAll();
	private static Item[] getAll() {
		List<Item> all = new ArrayList<Item>();
		for (Item m : values())
			for (int j = 0; j < m.drop; j++)
				all.add(m);
		return (Item[]) all.toArray(new Item[all.size()]);
	}
	
	public static Map<Item, Integer> getLoot(int diversity, int total) {
		Item[] materials = new Item[diversity];
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
		List<Item> materialsSorted = new ArrayList<Item>();
		for (Item m : materials)
			materialsSorted.add(m);
		materialsSorted.sort(new Comparator<Item>() {
			@Override
			public int compare(Item m1, Item m2) {
				return m2.drop-m1.drop;
			}});
		Map<Item, Integer> loots = new HashMap<Item, Integer>();
		float i = 0.5f;
		for (Item m : materialsSorted) {
			loots.put(m, (int)Math.floor(i*total));
			i /= 2;
		}
		return loots;
	}
	
}
