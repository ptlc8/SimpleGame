package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class Spell extends Projectile {
	
	private static final float speed = 10; //pixel/frame
	
	private static final Hitbox hitbox = new Circle(0, 32, 8);
	
	private static final String imageName = "spell";
	
	public Spell(GameManager gm, float x, float y, float direction, Playable owner, int damage) {
		super(gm, x, y, speed, direction, owner, damage, Type.MAGIC, hitbox, imageName);
	}
	
	@Override
	public void onPlayableReached(Playable reached) {
		
	}
	
	@Override
	public void onWallReached(float speedX, float speedY) {
		
	}
	
	@Override
	public void onTangibleReached(Tangible entity) {
		
	}
	
}
