package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.effects.Stun;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.DoubleShape;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public class Shield extends Bump {
	
	private static final Hitbox hitbox = new DoubleShape(new Rectangle(-16, -16, 32, 32), new Circle(0, 32, 32));
	private static final String imageName = "shield";
	private static final float range = 96; // pixel
	private static final int speed = 6; // pixel/frame
	
	private final Effect effect;
	private final int damage;
	
	public Shield(GameManager gm, float direction, Playable owner, int duration, int damage) {
		super(gm, owner, direction, range, speed, 0, 0, hitbox, new Image(imageName, direction));
		effect = new Stun(owner, duration);
		this.damage = damage;
	}
	
	@Override
	protected void onDestroy() {
		effect.stop();
	}

	@Override
	protected void onPlayerReached(Playable reached) {
		reached.takeDamage(owner, damage, Type.PHYSIC);
		effect.apply(reached);
	}

	@Override
	protected void onTangibleReached(Tangible reached) {
		reached.takeDamage(owner, damage);
	}
	
}
