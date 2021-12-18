package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class Tracking extends Effect {
	
	private static final float sightM = 2f;
	
	public Tracking(Playable sender, int duration) {
		super("tracking", sender, duration);
	}
	
	@Override
	public void onAdding(Playable target) {
		target.tracking(sightM);
	}
	
	@Override
	public void onRemoving(Playable target) {
		target.blindness(sightM);
	}
	
	@Override
	public void stop() {
		// do nothing
	}

}
