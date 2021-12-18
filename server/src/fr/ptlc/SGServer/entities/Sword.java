package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.ShapesGroup;

public class Sword extends Slash {
	
	private static final int duration = 30;
	
	private final static Hitbox hitbox = ShapesGroup.getFromFile("sword.json");
	
	private final static String imageName = "sword";
	
	private static final int decalX = 0, decalY = 0;
	
	public Sword(GameManager gm, Playable owner, float direction, int damage) {
		super(gm, owner, decalX, decalY, direction, damage, duration, hitbox, imageName);
	}

	@Override
	public void onPlayerReached(Playable reached) {
		
	}
	
}
