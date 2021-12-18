package fr.ptlc.SGServer.game;

import java.util.HashSet;
import java.util.Set;

import fr.ptlc.SGServer.Item;
import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.entities.Entity;
import fr.ptlc.SGServer.entities.Playable;

public class Rebrawl extends GameManager {
	
	public static final int[] teamNumber = {2};
	private static final int scoreToWin = 2;
	
	public Rebrawl(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		super(game, map, players, teams, haveDisplayer);
		summonAllPlayers();
	}
	
	@Override
	public void onStart() {
	}
	
	//@Override
	public void onScore(Team scoringTeam) {
		if (scoringTeam.getScore() < scoreToWin) {
			clear();
			broadcast("newbrawl");
			summonAllPlayers();
		} else {
			end(scoringTeam);
		}
	}
	
	@Override
	public void onDeath(Player deadPlayer) {
		tranfertToDeath(deadPlayer);
		Set<Team> aliveTeams = new HashSet<Team>();
		for (Entity entity : entities)
			if (entity instanceof Playable && !entitiesToRemove.contains(entity))
				if (aliveTeams.add(((Playable)entity).getTeam()) && aliveTeams.size() >= 2 )
					return;
		for (Team team : aliveTeams) {
			team.increaseScore();
			onScore(team);
			return;
		}
		DRAW.increaseScore();
		onScore(DRAW);
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

}
