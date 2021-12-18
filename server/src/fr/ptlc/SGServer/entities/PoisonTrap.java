package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.effects.Poison;
import fr.ptlc.SGServer.game.GameManager;

public class PoisonTrap extends Trap {
	
	public final Effect effect;
	
	public PoisonTrap(GameManager gm, float x, float y, Playable owner, int duration) {
		super(gm, x, y, owner);
		this.effect = new Poison(owner, duration);
	}
	
	@Override
	public void onPlayableReached(Playable reached) {
		effect.apply(reached);
	}
	
	@Override
	public void onTangibleReached(Tangible reached) {
		// do nothing
	}
	
	@Override
	protected void onDestroy() {
		effect.stop();
		super.onDestroy();
	}
	
}
