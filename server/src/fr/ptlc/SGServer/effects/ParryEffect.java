package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class ParryEffect extends Effect{
	
	private static final float resistanceM = 2f;
	private static final float weaknessM = 2f;
	
	public ParryEffect(Playable sender, int duration) {
		super("parry", sender, duration);
	}

	public void onAdding(Playable target) {
		target.resistance(resistanceM);
		target.weakness(weaknessM);
	}

	public void onRemoving(Playable target) {
		target.fragility(resistanceM);
		target.strength(weaknessM);
	}

	public void stop() {
		// do nothing
	}
	
}
