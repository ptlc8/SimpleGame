package fr.ptlc.SGServer;

import fr.ptlc.SGServer.entities.Entity;
import fr.ptlc.SGServer.entities.Ephemeral;
import fr.ptlc.SGServer.entities.Playable;
import fr.ptlc.SGServer.entities.Tangible;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class Explosion extends Ephemeral {
	
	private static final Hitbox hitbox = new Circle(0, 0, 32);
	
	private static final String imageName = "explosion";
	
	public static final int duration = 60*1; // en frames
	
	private static final Class<?>[] focus = {Playable.class, Tangible.class};
	
	private final Playable sender;
	
	public Explosion(GameManager gm, float x, float y, Playable sender, int damage) {
		super(gm, x, y, hitbox, new Image(imageName), duration);
		this.sender = sender;
		for (Entity entity : getCollidedEntities(focus)) {
			if (entity instanceof Playable)
				((Playable)entity).takeDamage(sender, damage, Type.EXPLOSIVE);
			else if (entity instanceof Tangible)
				((Tangible)entity).takeDamage(sender, damage);
		}
			
	}

	@Override
	public void move() {
		// do nothing
	}

	@Override
	protected void onDestroy() {
		// do nothing
	}
	
	public Playable getSender() {
		return sender;
	}
}
