package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class Slowness extends Effect {
	
	private static final float loss = 2f;
	
	public Slowness(Playable sender, int duration) {
		super("slowness", sender, duration);
	}

	public void onAdding(Playable target) {
		target.slowDown(loss);
	}

	public void onRemoving(Playable target) {
		target.speedUp(loss);
	}

	public void stop() {
		// do nothing
	}
	
}