package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class BerserkerEffect extends Effect {
	
	private static final float strengthM = 2f;
	private static final float speedM = 1.5f;
	private static final float fragilityM = 2f;
	
	public BerserkerEffect(Playable sender, int duration) {
		super("berserk", sender, duration);
	}

	public void onAdding(Playable target) {
		target.strength(strengthM);
		target.speedUp(speedM);
		target.fragility(fragilityM);
	}

	public void onRemoving(Playable target) {
		target.weakness(strengthM);
		target.slowDown(speedM);
		target.resistance(fragilityM);
	}

	public void stop() {
		// do nothing
	}
	
}
