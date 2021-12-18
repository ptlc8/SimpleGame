package fr.ptlc.SGServer.entities;

import java.awt.Point;
import java.util.Random;
import java.util.Set;

import fr.ptlc.SGServer.Classs;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.GameMap;

public class Invader extends Playable {
	
	private float diverge = 0f;
	
	public Invader(Player invasion, GameManager gm, Classs classs, float power) {
		super(invasion, gm, getRandomSpawn(gm.getMap()), classs, -1, null, null);
		this.slowDown(1.5f);
		this.tracking(4);
		this.speedUp(power);
		this.strength(power);
		this.resistance(power);
	}
	
	private static Point getRandomSpawn(GameMap map) {
		int x, y;
		boolean canSpawn;
		do {
		x = new Random().nextInt(map.getWidth()*64);
		y = new Random().nextInt(map.getHeight()*64);
		canSpawn = true;
		for (int i = x/64-1; i < x/64+1; i++)
			for(int j = y/64-1; j < y/64+1; j++)
				if (hitbox.intersects(x, y, map.getHitbox(i, j), i, j))
					canSpawn = false;
		} while (!canSpawn);
		return new Point(x, y);
	}
	
	@Override
	public void move() {
		Set<Entity> sight = getGM().getEntitiesAsPlayable(this);
		Playable nearest = null;
		float nearestD = 1000000;
		for (Entity entity : sight) {
			if (entity instanceof Playable) {
				if (!((Playable)entity).getTeam().equals(getTeam())) {
					float d = (float) Math.sqrt(Math.pow(x-entity.x, 2)+Math.pow(y-entity.y, 2));
					if (d < nearestD) {
						nearestD = d;
						nearest = (Playable)entity;
					}
				}
			}
		}
		diverge = Math.max(-0.1f, Math.min(diverge + new Random().nextFloat()/50-0.01f, 0.1f));
		if (nearest != null & energy >= attackCost) {
			if ((classs.equals(Classs.archer) || classs.equals(Classs.mage) && nearestD < 256) || (classs.equals(Classs.guerrier) && nearestD < 64)
					|| (classs.equals(Classs.golem) && nearestD < 48) || (classs.equals(Classs.assassin) && nearestD < 32)) {
				attack((float) (Math.atan2(nearest.y-y, nearest.x-x) / Math.PI) + diverge);
				setMove(false);
			} else {
				setDirection((float) (Math.atan2(nearest.y-y, nearest.x-x) / Math.PI) + diverge);
				setMove(true);
			}
		} else {
			setMove(false);
		}
		super.move();
	}
	
}
