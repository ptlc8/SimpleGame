package fr.ptlc.SGServer.game;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import fr.ptlc.SGServer.Item;
import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;

public class CTPRebrawl extends GameManager {
	
	public static final int[] teamNumber = {2};
	private static final int scoreToWin = 2;
	private static final int respawnTime = 8000; // milliseconds
	private static final int timeToCapture = 10000; // milliseconds
	
	public CTPRebrawl(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		super(game, map, players, teams, haveDisplayer);
		summonAllPlayers();
	}
	
	@Override
	public void onStart() {
		add(new CapturePoint(this, map.getWidth()*64/2, map.getHeight()*64/2, timeToCapture));
	}
	
	@Override
	public void onDeath(Player deadPlayer) {
		tranfertToDeath(deadPlayer);
		send(deadPlayer, "respawnin "+respawnTime);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				respawn(deadPlayer);
			}
		}, respawnTime);
	}
	
	@Override
	public Loot getLoot(Player player, Team team) {
		Loot loot = new Loot();
		if (team.getScore() == scoreToWin) { // win
			loot.addItem(Item.getRandom(), 1);
			loot.increaseXP(20*team.getScore());
			loot.increaseElo(5*teams.size()*team.getScore());
		} else if (DRAW.getScore() == scoreToWin) { // lose
			loot.increaseXP(10+10*team.getScore());
			loot.increaseElo(Math.max(-5*(scoreToWin-team.getScore()), -player.getElo()));
		} else { // draw
			loot.increaseXP(5);
		}
		return loot;
	}
	
	@Override
	public void onEnd() {
		
	}
	
	public static class CapturePoint extends fr.ptlc.SGServer.entities.CapturePoint {
		
		private final CTPRebrawl gm;
		
		public CapturePoint(CTPRebrawl gm, float x, float y, int timeToCapture) {
			super(gm, x, y, timeToCapture);
			this.gm = gm;
		}
		
		@Override
		public void onCapturing(Team capturingTeam, int power, float progress) {
			gm.broadcast("capture {\"team\":"+new Gson().toJson(capturingTeam)+", \"power\":"+power+", \"progress\":"+progress+"}");
		}

		@Override
		public void onCapture(Team capturingTeam) {
			capturingTeam.increaseScore();
			if (capturingTeam.getScore() < scoreToWin) {
				//gm.stop();
				gm.clear();
				gm.broadcast("newbrawl");
				gm.summonAllPlayers();
				//gm.start();
				gm.onStart();
			} else {
				gm.end(capturingTeam);
			}
		}
		
	}
	
}
