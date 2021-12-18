package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class Regeneration extends Effect {
	
	private static final float slownessM = 8f;
	private static final float fragilityM = 2f;
	
	public Regeneration(Playable sender, int duration) {
		super("regeneration", sender, duration);
	}
	
	@Override
	public void onAdding(Playable target) {
		target.slowDown(slownessM);
		target.fragility(fragilityM);
	}
	
	@Override
	public void onRemoving(Playable target) {
		target.speedUp(slownessM);
		target.resistance(fragilityM);
	}
	
	@Override
	public void stop() {
		
	}

}
