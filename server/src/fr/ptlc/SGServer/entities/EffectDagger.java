package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.game.GameManager;

public class EffectDagger extends Dagger {
	
	private final Effect effect;
	
	public EffectDagger(GameManager gm, Playable owner, float direction, int damage, Effect effect) {
		super(gm, owner, direction, damage);
		this.effect = effect;
	}
	
	@Override
	public void onPlayerReached(Playable reached) {
		effect.apply(reached);
		super.onPlayerReached(reached);
	}

}
