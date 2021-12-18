package fr.ptlc.SGServer;

import java.util.Random;

import fr.ptlc.SGServer.Modifier.Modif;
import fr.ptlc.SGServer.game.Type;

public enum Item implements Stockable {
	
	invisibility_cloak(Slot.hand, new Craft(Material.rag, 22, Material.magic_powder, 5), Type.MAGIC),
	shield(Slot.hand, new Craft(Material.iron, 8, Material.wood, 16), Type.PHYSIC),
	//intangibility_ring(Slot.hand, new Craft(Material.gold, 6, Material.magic_powder, 3), Type.MAGIC),
	explosive_trap(Slot.hand, new Craft(Material.explosive_powder, 4, Material.steel, 7), Type.EXPLOSIVE),
	poison_trap(Slot.hand, new Craft(Material.poison_bottle, 1, Material.steel, 7), Type.CHIMIC),
	//applicable_poison(Slot.hand, new Craft(Material.poison_bottle, 1, Material.rag, 4), Type.CHIMIC),
	throwable_poison(Slot.hand, new Craft(Material.poison_bottle, 1, Material.sand, 4), Type.CHIMIC),
	//crystal_ball(Slot.hand, new Craft(Material.glass, 9, Material.magic_powder, 4), Type.MAGIC),
	tracking(Slot.hand, new Craft(Material.battery, 1, Material.steel, 5), Type.ELECTRIC),
	//throwable_frost(Slot.hand, new Craft(Material.frost_bottle, 1, Material.sand, 4), Type.FROST),
	//applicable_frost(Slot.hand, new Craft(Material.frost_bottle, 1, Material.rag, 4), Type.FROST),
	molotov_cocktail(Slot.hand, new Craft(Material.rag, 1, Material.glass, 2, Material.gasoline, 3), Type.FIRE),
	stone_wall(Slot.hand, new Craft(Material.stone, 32), Type.NATURAL),
	healing_potion(Slot.hand, new Craft(), Type.MAGIC),
	dynamite(Slot.hand, new Craft(Material.explosive_powder, 4, Material.paper, 8), Type.EXPLOSIVE),
	throwable_slowness(Slot.hand, new Craft(), Type.FROST),
	//applicable_slowness(Slot.hand, new Craft(), Type.FROST),
	healing_ring(Slot.hand, new Craft(), Type.MAGIC),
	//laser(Slot.hand, new Craft(Material.battery, 1, Material.glass, 1, Material.steel, 3), Type.ELECTRIC),
	teleportation(Slot.hand, new Craft(Material.void_bottle, 4), Type.DISTANCE),
	taser(Slot.hand, new Craft(Material.battery, 2, Material.caou, 4, Material.steel, 9), Type.ELECTRIC),
	smoke(Slot.hand, new Craft(Material.sand, 4, Material.paper, 2, Material.explosive_powder, 1), Type.CHIMIC),
	orange_juice(Slot.hand, new Craft(), Type.NATURAL),
	sismic_glove(Slot.hand, new Craft(), Type.PHYSIC),
	
	casque_radar(Slot.head, new Craft(Material.battery, 1, Material.steel, 4, Material.leather, 2), new Modifier(Modif.SIGHT_BONUS, 0.2f)),
	horn(Slot.head, new Craft(Material.amethyst, 1, Material.magic_powder, 3), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	magic_hat(Slot.head, new Craft(Material.rag, 9, Material.magic_powder, 1)),
	big_heart(Slot.chest, new Craft(), new Modifier(Modif.HEALTH_BONUS, 0.2f)),
	//jetpack(Slot.chest, new Craft(Material.battery, 4, Material.aluminium, 22)), // Slot.hand ???
	dynamite_belt(Slot.chest, new Craft(Material.leather, 5, Material.explosive_powder, 3), new Modifier(Modif.FINAL_EXPLOSION, 1f)),
	energy_pump(Slot.chest, new Craft(Material.void_bottle, 1, Material.caou, 6, Material.wood, 18), new Modifier(Modif.ENERGY_BOOST, 0.2f)),
	energy_tank(Slot.chest, new Craft(), new Modifier(Modif.ENERGY_SUPPLY, 0.2f)),
	hot_water_chestplate(Slot.chest, new Craft(Material.caou, 7, Material.water, 5), new Modifier(Modif.FROST_RESISTANCE, 0.2f)),
	cape(Slot.chest, new Craft(), new Modifier(Modif.SPEED, 0.1f)),
	celerity_boots(Slot.foot, new Craft(Material.leather, 14, Material.magic_powder, 6), new Modifier(Modif.SPEED, 0.2f)),
	//ice_boots(Slot.foot, new Craft(Material.ice, 12, Material.leather, 12), new Modifier(Modif.GLIDE, 0.4f)),
	amethyst_helmet(Slot.head, new Craft(), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	amethyst_chestplate(Slot.chest, new Craft(), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	amethyst_leggings(Slot.legs, new Craft(), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.15f)),
	amethyst_boots(Slot.foot, new Craft(), new Modifier(Modif.RESISTANCE, 0.15f), new Modifier(Modif.MAGIC_RESISTANCE, -0.1f)),
	iron_helmet(Slot.head, new Craft(), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_chestplate(Slot.chest, new Craft(), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_leggings(Slot.legs, new Craft(), new Modifier(Modif.SPEED, -0.08f), new Modifier(Modif.RESISTANCE, 0.1f)),
	iron_boots(Slot.foot, new Craft(), new Modifier(Modif.SPEED, -0.05f), new Modifier(Modif.RESISTANCE, 0.1f)),
	aluminium_helmet(Slot.head, new Craft(), new Modifier(Modif.RESISTANCE, 0.05f)),
	aluminium_chestplate(Slot.chest, new Craft(), new Modifier(Modif.RESISTANCE, 0.05f)),
	aluminium_leggings(Slot.legs, new Craft(), new Modifier(Modif.RESISTANCE, 0.05f), new Modifier(Modif.SPEED, -0.02f)),
	aluminium_boots(Slot.foot, new Craft(), new Modifier(Modif.RESISTANCE, 0.05f)),
	caou_helmet(Slot.head, new Craft(), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_chestplate(Slot.chest, new Craft(), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_leggings(Slot.legs, new Craft(), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	caou_boots(Slot.foot, new Craft(), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.2f)),
	thorny_helmet(Slot.head, new Craft(), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_chestplate(Slot.chest, new Craft(), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_leggings(Slot.legs, new Craft(), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	thorny_boots(Slot.foot, new Craft(), new Modifier(Modif.PHYSIC_DAMAGE_RETURN, 0.2f), new Modifier(Modif.PHYSIC_RESISTANCE, 0.1f)),
	//casque_du_vide(Slot.head, new Craft()),
	//plastron_du_vide(Slot.chest, new Craft()),
	//jambières_du_vide(Slot.legs, new Craft()),
	//bottes_du_vide(Slot.foot, new Craft()),
	miror_ball(Slot.head, new Craft(), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_chestplate(Slot.chest, new Craft(), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_leggings(Slot.legs, new Craft(), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.05f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	miror_boots(Slot.foot, new Craft(), new Modifier(Modif.MAGIC_DAMAGE_RETURN, 0.1f), new Modifier(Modif.ELECTRIC_RESISTANCE, 0.1f), new Modifier(Modif.MAGIC_RESISTANCE, 0.2f)),
	foam_helmet(Slot.head, new Craft(), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_chestplate(Slot.chest, new Craft(), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_leggings(Slot.legs, new Craft(), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.25f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	foam_boots(Slot.foot, new Craft(), new Modifier(Modif.PROJECTILE_RESISTANCE, 0.3f), new Modifier(Modif.FIRE_RESISTANCE, -0.1f)),
	exoskeleton_arms(Slot.chest, new Craft(), new Modifier(Modif.ANTI_STUN_ATTACK, 1f)),
	exoskeleton_legs(Slot.legs, new Craft(), new Modifier(Modif.ANTI_STUN_MOVE, 1f)),
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
	
	private final Slot slot;
	private final Craft craft;
	private final Type type;
	private final Modifier[] modifiers;
	
	private Item(Slot slot, Craft craft, Type type) {
		this.slot = slot;
		this.craft = craft;
		this.type = type;
		this.modifiers = new Modifier[0];
	}
	
	private Item(Slot slot, Craft craft, Modifier... modifiers) {
		this.slot = slot;
		this.craft = craft;
		this.type = null;
		this.modifiers = modifiers;
	}
	
	public Slot getSlot() {
		return slot;
	}
	
	@Override
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
	
}
