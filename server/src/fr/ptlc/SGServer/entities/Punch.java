package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.DoubleShape;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public class Punch extends Bump {
		
	private static final Hitbox hitbox = new DoubleShape(new Rectangle(-48, -48, 96, 96), new Circle(0, 32, 12));
	
	private static final String imageName = "punch";
	
	private static final float range = 96; // pixel
	
	private static final int speed = 6; // pixel/frame
	
	private final Playable owner;
	
	private final int damage;
	
	public Punch(GameManager gm, float direction, Playable owner, int damage) {
		super(gm, owner, direction, range, speed, 0, 0, hitbox, new Image(imageName, (float)(direction/Math.PI)));
		this.owner = owner;
		this.damage = damage;
	}
	
	protected void onDestroy() {
		
	}
	
	@Override
	protected void onPlayerReached(Playable reached) {
		reached.takeDamage(owner, damage, Type.PHYSIC);
	}
	
	@Override
	protected void onTangibleReached(Tangible reached) {
		reached.takeDamage(owner, damage);
	}
	
}
