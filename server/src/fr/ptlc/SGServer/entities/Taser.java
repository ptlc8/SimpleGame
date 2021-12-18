package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.effects.Stun;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.DoubleShape;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public class Taser extends Bump {
	
private static final Hitbox hitbox = new DoubleShape(new Rectangle(-48, -48+32, 96, 96), new Circle(0, 32, 8));
	
	private static final String imageName = "taser";
	private static final float range = 96; // pixel
	private static final int speed = 12; // pixel/frame
	
	private final int damage;
	private final Effect effect;
	
	public Taser(GameManager gm, float direction, Playable owner, int damage, int stunDuration) {
		super(gm, owner, direction, range, speed, 0, 0, hitbox, new Image(imageName, (float)(direction/Math.PI)));
		this.damage = damage;
		effect = new Stun(owner, stunDuration);
	}

	@Override
	protected void onPlayerReached(Playable reached) {
		reached.takeDamage(owner, damage, Type.ELECTRIC);
		effect.apply(reached);
	}

	@Override
	protected void onTangibleReached(Tangible reached) {
		reached.takeDamage(owner, damage);
	}

	@Override
	protected void onDestroy() {
		effect.stop();
	}
	
	
	
}
