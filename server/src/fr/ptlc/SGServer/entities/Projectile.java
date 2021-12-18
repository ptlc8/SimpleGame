package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class Projectile extends Entity {
	
	private final float speedX;
	private final float speedY;
	
	private final Playable owner;
	
	private final int damage;
	private final Type type;
	
	private final Class<?>[] focus = {Playable.class, Tangible.class};
	
	public Projectile(GameManager gm, float x, float y, float speed, float direction, Playable owner, int damage, Type type, Hitbox hitbox, String imageName) {
		super(gm, x, y, hitbox, new Image(imageName, (float)(direction/Math.PI))); // direction plus prise en compte dans la Hitbox
		speedX = (float) (Math.cos(direction) * speed);
		speedY = (float) (Math.sin(direction) * speed);
		this.owner = owner;
		this.damage = damage;
		this.type = type;
	}
	
	public void move() {
		for (Entity entity : getCollidedEntities(focus)) {
			if (entity instanceof Playable && (Playable)entity != owner) {
				((Playable)entity).takeDamage(owner, damage, type);
				onPlayableReached((Playable)entity);
				destroy();
				return;
			} else if (entity instanceof Tangible) {
				((Tangible)entity).takeDamage(owner, damage);
				onTangibleReached((Tangible)entity);
				destroy();
			}
		}
		if (!testCollisionMap()) {
			x += speedX;		
			y += speedY;
		} else {
			onWallReached(speedX, speedY);
			destroy();
		}
	}
	
	public void onDestroy() {
		
	}
	
	public abstract void onPlayableReached(Playable reached);
	
	public abstract void onWallReached(float speedX, float speedY);
	
	public abstract void onTangibleReached(Tangible entity);
	
	public Playable getOwner() {
		return owner;
	}
	
}
