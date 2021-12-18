package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.ShapesGroup;

public class Dagger extends Slash {
	
	private static final int duration = 20;
	
	private static final Hitbox hitbox = ShapesGroup.getFromFile("dagger.json");
	
	private static final String imageName = "dagger";
	
	private static final int decalX = 0, decalY = 0;
	
	public Dagger(GameManager gm, Playable owner, float direction, int damage) {
		super(gm, owner, decalX, decalY, direction, damage, duration, hitbox, imageName);
	}

	@Override
	public void onPlayerReached(Playable reached) {
		
	}
	
}
