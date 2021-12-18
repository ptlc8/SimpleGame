package fr.ptlc.SGServer.effects;

import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.entities.Playable;

public abstract class Effect {
	
	private final String name;
	
	private final Playable sender;
	
	private final int duration;
	
	public Effect(String name, Playable sender, int duration) {
		this.name = name;
		this.sender = sender;
		this.duration = duration;
	}
	
	public boolean apply(Playable target) {return apply(target, false);}
	public boolean apply(Playable target, boolean andStop) {
		if (!target.addEffect(this)) return false;
		onAdding(target);
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				target.removeEffect(Effect.this);
				onRemoving(target);
				if (andStop) stop();
			}
		}, duration*target.getGM().frameRate);
		return true;
	}
	
	public abstract void onAdding(Playable target);
	
	public abstract void onRemoving(Playable target);
	
	public abstract void stop();
	
	public void stop(Playable target) {
		onRemoving(target);
	}
	
	public String getName() {
		return name;
	}
	
	public Playable getSender() {
		return sender;
	}
	/*
	 * duration multiplié par 16 pour être converti en à-peu-près-secondes
	 */
	public int getDuration() {
		return (int) (duration*16);
	}
	
}
