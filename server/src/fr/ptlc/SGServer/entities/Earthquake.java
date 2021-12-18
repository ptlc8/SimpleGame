package fr.ptlc.SGServer.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public class Earthquake extends Ephemeral {
	
	private static final Hitbox hitbox = new Circle(0, 0, 224);
	
	private static final String imageName = "earthquake";
	
	private static final Class<Playable> focus = Playable.class;
	
	private final Timer timer = new Timer();;
	
	private final Playable sender;
	
	private Set<Playable> targets;
	
	public final int damage;
	
	public Earthquake(GameManager gm, float x, float y, Playable sender, int duration, int interval, int damage) {
		super(gm, x, y, hitbox, new Image(imageName).setOnGround(true), duration);
		this.sender = sender;
		this.damage = damage;
		targets = new HashSet<Playable>();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				for (Playable target : targets)
					target.takeDamage(sender, damage, Type.PHYSIC);
			}
		}, interval/2*sender.getGM().frameRate, interval*sender.getGM().frameRate);
	}

	@Override
	public void move() {
		targets = getCollidedEntities(focus);
	}

	@Override
	protected void onDestroy() {
		timer.cancel();
	}
	
	public Playable getSender() {
		return sender;
	}

}
