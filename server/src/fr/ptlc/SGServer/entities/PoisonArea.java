package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.effects.Poison;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class PoisonArea extends EffectArea {
	
	private static final Hitbox hitbox = new Circle(0, 0, 96);
	
	private static final String imageName = "poison-area";
	
	private static final int duration = 60*4;

	public PoisonArea(GameManager gm, float x, float y, Playable owner) {
		super(gm, x, y, hitbox, imageName, duration, new Poison(owner, duration));
	}

}
