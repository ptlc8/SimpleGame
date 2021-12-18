package fr.ptlc.SGServer.entities;

import java.util.ArrayList;
import java.util.List;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class Bump extends PinnedEntity {
	
	protected final Playable owner;
	
	private final float range;
	private final int speed;
	private float speedX, speedY;
	
	public float progress = 0;
	
	private List<Entity> hitted = new ArrayList<Entity>();
	private final Class<?>[] focus = {Playable.class, Tangible.class};
	
	public Bump(GameManager gm, Playable owner, float direction, float range, int speed, int decalX, int decalY, Hitbox hitbox, Image image) {
		super(gm, owner, decalX, decalY, hitbox, image);
		this.owner = owner;
		this.range = range;
		this.speed = speed;
		this.speedX = (float)(Math.cos(direction) * speed);
		this.speedY = (float)(Math.sin(direction) * speed);
	}
	
	@Override
	public void move() {
		decalX += speedX;
		decalY += speedY;
		progress += speed;
		for (Entity entity : getCollidedEntities(focus)) {
			if (entity instanceof Playable && (Playable)entity != getPinTarget() && !hitted.contains(entity)) {
				onPlayerReached((Playable)entity);
				hitted.add(entity);
			} else if (entity instanceof Tangible) {
				onTangibleReached((Tangible)entity);
				hitted.add(entity);
			}
		}	
		super.move();
		if (progress > range) destroy();
	};
	
	protected abstract void onPlayerReached(Playable reached);
	
	protected abstract void onTangibleReached(Tangible reached);
	
}
