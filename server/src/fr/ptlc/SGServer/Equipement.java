package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.List;

import fr.ptlc.SGServer.Access.Ref;

public class Equipement {
	
	@Access(refs={Ref.ALL})
	private Item hand, head, chest, legs, foot;
	
	public Equipement() {
		hand = chest = legs = foot = null;
		head = null;
	}
	
	public Item getHandItem() {
		return hand;
	}
	
	public List<Modifier> getModifiers() {
		List<Modifier> modifiers = new ArrayList<Modifier>();
		if (head != null) for (Modifier mod : head.getModifiers())
			modifiers.add(mod);
		if (chest != null) for (Modifier mod : chest.getModifiers())
			modifiers.add(mod);
		if (legs != null) for (Modifier mod : legs.getModifiers())
			modifiers.add(mod);
		if (foot != null) for (Modifier mod : foot.getModifiers())
			modifiers.add(mod);
		return modifiers;
	}
	
	protected boolean equip(Item item) {
		switch (item.getSlot()) {
		case hand:
			hand = item;
			break;
		case head:
			head = item;
			break;
		case chest:
			chest = item;
			break;
		case legs:
			legs = item;
			break;
		case foot:
			foot = item;
			break;
		case no_one:
			return false;
		}
		return true;
	}
	
	protected boolean disequip(String slotName) {
		switch (slotName) {
		case "hand":
			hand = null;
			break;
		case "head":
			head = null;
			break;
		case "chest":
			chest = null;
			break;
		case "legs":
			legs = null;
			break;
		case "foot":
			foot = null;
			break;
		default:
			return false;
		}
		return true;
	}
	
}
