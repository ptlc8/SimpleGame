package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class Invisibility extends Effect {
	
	public Invisibility(Playable sender, int duration) {
		super("invisibility", sender/*also target ?*/, duration);
	}

	public void onAdding(Playable target) {
		target.setVisibility(false);
	}

	public void onRemoving(Playable target) {
		target.setVisibility(true);
	}

	public void stop() {
		// do nothing
	}
	
}
