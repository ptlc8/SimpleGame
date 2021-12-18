package fr.ptlc.SGServer.entities;

import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class Ephemeral extends Entity {
	
	public Ephemeral(GameManager gm, float x, float y, Hitbox hitbox, Image image, int duration) {
		super(gm, x, y, hitbox, image);
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				destroy();
			}
		}, duration*gm.frameRate);
	}
	
}
