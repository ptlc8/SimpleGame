package fr.ptlc.SGServer.effects;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.entities.Playable;
import fr.ptlc.SGServer.game.Type;

public class Poison extends Effect {
	
	private static final int interval = 60; // en frames
	
	private static final int damage = 10;
	
	private final Timer timer = new Timer();
	
	private final Set<Playable> targets;
	
	public Poison(Playable poisoner, int duration) {
		super("poison", poisoner, duration);
		targets = new HashSet<Playable>();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				for (Playable target : targets)
					target.takeDamage(getSender(), damage, Type.CHIMIC);
			}
		}, interval/2*getSender().getGM().frameRate, interval*getSender().getGM().frameRate);
	}
	
	@Override
	public void onAdding(Playable target) {
		targets.add(target);
	}
	
	@Override
	public void onRemoving(Playable target) {
		targets.remove(target);
	}
	
	@Override
	public void stop() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(!targets.isEmpty());
				timer.cancel();
				Thread.currentThread().interrupt();
			}
		});
		t.run();
	}
	
}
