package fr.ptlc.SGServer.entities;

import java.util.Set;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class Trap extends Entity {
	
	private static final Hitbox hitbox = new Circle(0, 0, 22);
	
	private static final String imageName = "trap";
	
	protected final Playable owner;
	
	private final Class<?>[] focus = {Playable.class, Tangible.class};
	
	public Trap(GameManager gm, float x, float y, Playable owner) {
		super(gm, x, y, hitbox, new Image(imageName));
		this.owner = owner;
	}

	@Override
	public void move() {
		Set<Entity> collidedEntities = getCollidedEntities(focus);
		for (Entity entity : collidedEntities) {
			if (entity instanceof Playable && (Playable)entity != owner) {
				onPlayableReached((Playable)entity);
				getGM().remove(this);
				return;
			} else if (entity instanceof Tangible) {
				onTangibleReached((Tangible)entity);
				getGM().remove(this);
			}
		}
	}

	@Override
	protected void onDestroy() {
		
	}
	
	public abstract void onPlayableReached(Playable reached);
	
	public abstract void onTangibleReached(Tangible reached);
	
	public Playable getOwner() {
		return owner;
	}
	
}
