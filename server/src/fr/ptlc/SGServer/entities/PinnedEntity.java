package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class PinnedEntity extends Entity {
	
	private final Entity pinTarget;
	
	protected float decalX;
	
	protected float decalY;
	
	public PinnedEntity(GameManager gm, Entity pinTarget, int decalX, int decalY, Hitbox hitbox, Image image) {
		super(gm, pinTarget.x+decalX, pinTarget.y+decalY, hitbox, image);
		this.pinTarget = pinTarget;
		this.decalX = decalX;
		this.decalY = decalY;
	}

	public void move() {
		x = pinTarget.x + decalX;
		y = pinTarget.y + decalY;
	}
	
	public Entity getPinTarget() {
		return pinTarget;
	}
	
}
