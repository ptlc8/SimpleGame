package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.effects.Slowness;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class Iceball extends Projectile {
	
	private static final float speed = 8; //pixel/frame
	
	private static final Hitbox hitbox = new Circle(0, 32, 8);
	
	private static final String imageName = "iceball";
	
	private final int effectDuration;
	
	public Iceball(GameManager gm, float x, float y, float direction, Playable owner, int damage, int effectDuration) {
		super(gm, x, y, speed, direction, owner, damage, Type.FROST, hitbox, imageName);
		this.effectDuration = effectDuration;
	}
	
	@Override
	public void onPlayableReached(Playable reached) {
		new Slowness(getOwner(), effectDuration).apply(reached, true);
	}
	
	@Override
	public void onWallReached(float speedX, float speedY) {
		
	}
	
	@Override
	public void onTangibleReached(Tangible entity) {
		
	}
	
}
