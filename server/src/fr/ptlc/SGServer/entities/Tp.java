package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class Tp extends Projectile {
	
	private static final Hitbox hitbox =  new Circle(0, 32, 16);
	
	private static final String imageName = "tp";
	
	private static final int speed = 8;
	
	public Tp(GameManager gm, float x, float y, float direction, Playable owner, int damage) {
		super(gm, x, y, speed, direction, owner, damage, Type.DISTANCE, hitbox, imageName);
	}

	@Override
	public void onPlayableReached(Playable reached) {
		getOwner().x = this.x;
		getOwner().y = this.y;
	}

	@Override
	public void onWallReached(float speedX, float speedY) {
		getOwner().x = this.x-speedX;
		getOwner().y = this.y-speedY;
	}

	@Override
	public void onTangibleReached(Tangible entity) {
		getOwner().x = this.x;
		getOwner().y = this.y;
	}
	
}
