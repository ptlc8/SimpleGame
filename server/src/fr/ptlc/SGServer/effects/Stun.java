package fr.ptlc.SGServer.effects;

import fr.ptlc.SGServer.entities.Playable;

public class Stun extends Effect {
	
	public Stun(Playable sender, int duration) {
		super("stun", sender, duration);
	}
	
	@Override
	public void onAdding(Playable target) {
		target.setCanMove(false);
	}
	
	@Override
	public void onRemoving(Playable target) {
		target.setCanMove(true);
	}
	
	@Override
	public void stop() {
		
	}
	
}
