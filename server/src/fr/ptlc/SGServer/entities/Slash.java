package fr.ptlc.SGServer.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.ShapesGroup;

public abstract class Slash extends PinnedEntity {
	
	private final Playable owner;
	
	private final int damage;
	
	private int duration; // en sec/60
	
	private final Class<?>[] focus = {Playable.class, Tangible.class};
	
	private List<Entity> hitted = new ArrayList<Entity>();
	
	public Slash(GameManager gm, Playable owner, int decalX, int decalY, float direction, int damage, int duration, Hitbox hitbox, String imageName) {
		super(gm, owner, decalX, decalY, (hitbox instanceof ShapesGroup ? ((ShapesGroup)hitbox).newWithRotation((float)(direction)) : hitbox), new Image(imageName, (float)(direction/Math.PI)));
		this.owner = owner;
		this.damage = damage;
		this.duration = duration;
		direction = (float)(direction % (2*Math.PI));
		if (direction < -1.5*Math.PI || (-0.5*Math.PI < direction && direction < 0.5*Math.PI) || 1.5*Math.PI < direction)
			image.flip = false;
		else
			image.flip = true;
	}
	
	public void move() {
		Set<Entity> collidedEntities = getCollidedEntities(focus);
		for (Entity entity : collidedEntities) {
			if (entity instanceof Playable && (Playable)entity != owner && !hitted.contains(entity)) {
				((Playable)entity).takeDamage(owner, damage, Type.PHYSIC);
				onPlayerReached((Playable)entity);
				hitted.add(entity);
			} else if (entity instanceof Tangible) {
				((Tangible)entity).takeDamage(owner, damage);
				//onTangibleReached((Tangible)entity);
				hitted.add(entity);
			}
		}
		duration--;
		if (duration <= 0) destroy();
		super.move();
	}
	
	public void onDestroy() {
		
	}
	
	public abstract void onPlayerReached(Playable reached);
	
	//public abstract void onTangibleReached(Tangible reached);
	
}
