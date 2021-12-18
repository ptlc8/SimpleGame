package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.game.GameManager;

public class EffectArrow extends Arrow {
	
	private final Effect effect;
	
	public EffectArrow(GameManager gm, float x, float y, float direction, Playable owner, int damage, Effect effect) {
		super(gm, x, y, direction, owner, damage);
		this.effect = effect;
	}
	
	@Override
	public void onPlayableReached(Playable reached) {
		effect.apply(reached, true);
		super.onPlayableReached(reached);
	}
	
}
