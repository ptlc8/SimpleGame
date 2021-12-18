package fr.ptlc.SGServer.entities;

import java.util.Random;
import java.util.Set;

import fr.ptlc.SGServer.Classs;
import fr.ptlc.SGServer.game.GameManager;

public class Minion extends Playable {
	
	private Playable owner;
	
	private float diverge = 0f;
	
	public Minion(GameManager gm, float x, float y, Playable owner, Classs classs) {
		super(owner.getPlayer(), gm, x, y, classs, -1, null, null);
		this.owner = owner;
		setEnergyRestore(false);
	}
	
	public Playable getOwner() {
		return owner;
	}
	
	@Override
	public void move() {
		Set<Entity> sight = getGM().getEntitiesAsPlayable(this);
		Playable nearest = null;
		float nearestD = 1000000;
		float ownerD = 1000000;
		for (Entity entity : sight) {
			if (entity instanceof Playable) {
				if (!((Playable)entity).getTeam().equals(getTeam())) {
					float d = (float) Math.sqrt(Math.pow(x-entity.x, 2)+Math.pow(y-entity.y, 2));
					if (d < nearestD) {
						nearestD = d;
						nearest = (Playable) entity;
					}
				} else if (entity == owner) {
					ownerD = (float) Math.sqrt(Math.pow(x-entity.x, 2)+Math.pow(y-entity.y, 2));
				}
			}
		}
		if (ownerD > 1000) {
			destroy();
			return;
		}
		diverge = Math.max(-0.1f, Math.min(diverge + new Random().nextFloat()/50-0.01f, 0.1f));
		if (nearest != null & energy >= attackCost) {
			if ((classs.equals(Classs.archer) && nearestD < 256) || (classs.equals(Classs.guerrier) && nearestD < 64)
					|| (classs.equals(Classs.golem) && nearestD < 48) || (classs.equals(Classs.assassin) && nearestD < 32)) {
				attack((float) (Math.atan2(nearest.y-y, nearest.x-x) / Math.PI) + diverge);
				setMove(false);
			} else {
				setDirection((float) (Math.atan2(nearest.y-y, nearest.x-x) / Math.PI) + diverge);
				setMove(true);
			}
		} else if (ownerD > 128) {
			setDirection((float) (Math.atan2(owner.y-y, owner.x-x) / Math.PI) + diverge);
			setMove(true);
		} else {
			energy += 8;
			setMove(false);
		}
		super.move();
	}
	
}
