package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;

public class ExplosiveTrap extends Trap {
	
	protected final int damage;
	
	public ExplosiveTrap(GameManager gm, float x, float y, Playable owner, int damage) {
		super(gm, x, y, owner);
		this.damage = damage;
	}
	
	@Override
	public void onPlayableReached(Playable reached) {
		reached.takeDamage(owner, damage, Type.EXPLOSIVE);
	}
	
	@Override
	public void onTangibleReached(Tangible reached) {
		reached.takeDamage(owner, damage);
	}
	
}
