package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.DoubleShape;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public class Wall extends Ephemeral implements Tangible {
	
	private static final Hitbox hitbox = new DoubleShape(new Rectangle(-32, -48, 64, 96), new Rectangle(-32, 0, 64, 48));
	
	private static final String imageName = "stone-wall";
	
	private final Playable owner;
	
	private int health;
	
	public Wall(GameManager gm, float x, float y, Playable owner, int duration, int health) {
		super(gm, x, y, hitbox, new Image(imageName), duration);
		this.owner = owner;
		this.health = health;
	}
	
	@Override
	public void move() {
		
	}
	
	@Override
	protected void onDestroy() {
		
	}

	@Override
	public void takeDamage(Playable attacker, int damage) {
		if (attacker.getTeam().equals(owner.getTeam())) return; // si pas de friendly fire, psk les amis ne nous font pas mal exprès
		getGM().onDamageGiven(attacker, this, damage);
		health -= damage;
		if (health <= 0) destroy();
	}
	
}
