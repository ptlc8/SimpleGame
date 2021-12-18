package fr.ptlc.SGServer.entities;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.Team;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;

public abstract class CapturePoint extends Entity {
	
	private static final Hitbox hitbox = new Circle(0, 0, 96);
	
	private static final String imageName = "capture-point";
	
	private final int pointsToCapture; // en frames (tps) * joueurs
	
	private Team capturingTeam;
	
	private int capturePoints;
	
	public CapturePoint(GameManager gm, float x, float y, int timeToCapture) {
		super(gm, x, y, hitbox, new Image(imageName).setOnGround(true));
		this.pointsToCapture = timeToCapture / gm.frameRate;
		capturingTeam = null;
		capturePoints = 0;
	}
	
	@Override
	public void move() {
		int captureEvolution = 0;
		for (Playable playable : getCollidedEntities(Playable.class)) {
			if (playable.getTeam().equals(capturingTeam)) {
				captureEvolution++;
			} else {
				captureEvolution--;
				if (capturePoints <= 0)
					capturingTeam = playable.getTeam();
			}
		}
		if (captureEvolution != 0) {
			capturePoints += captureEvolution;
			final int cE = captureEvolution;
			getGM().runAfterMove(() -> onCapturing(capturingTeam, cE, (float)capturePoints/pointsToCapture));
		}
		if (capturePoints > pointsToCapture)
			getGM().runAfterMove(() -> onCapture(capturingTeam));
	}
	
	public abstract void onCapturing(Team capturingTeam, int power, float progress);
	
	public abstract void onCapture(Team capturingTeam);
	
	@Override
	protected void onDestroy() {
		
	}
	
}
