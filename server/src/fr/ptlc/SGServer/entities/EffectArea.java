package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class EffectArea extends Ephemeral {
	
	private final Class<Playable> focus = Playable.class;
	
	private final Effect effect;
	
	public EffectArea(GameManager gm, float x, float y, Hitbox hitbox, String imageName, int duration, Effect effect) {
		super(gm, x, y, hitbox, new Image(imageName).setOnGround(true), duration);
		this.effect = effect;
	}

	@Override
	public void move() {
		for (Entity entity : getCollidedEntities(focus))
			effect.apply((Playable)entity);
	}
	
	@Override
	public void onDestroy() {
		effect.stop();
	}
	
}
