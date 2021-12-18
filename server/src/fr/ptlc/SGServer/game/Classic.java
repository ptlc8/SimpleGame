package fr.ptlc.SGServer.game;

import java.util.HashSet;
import java.util.Set;

import fr.ptlc.SGServer.Item;
import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.entities.Entity;
import fr.ptlc.SGServer.entities.Playable;

public class Classic extends GameManager {
	
	public static final int[] teamNumber = {2,3,4,5,6,7,8};
	
	public Classic(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		super(game, map, players, teams, haveDisplayer);
		summonAllPlayers();
	}
	
	@Override
	public void onStart() {
		
	}
	
	//@Override
	public void onScore(Team scoringTeam) {
		end(scoringTeam);
	}
	
	@Override
	public void onDeath(Player deadPlayer) {
		tranfertToDeath(deadPlayer);
		Set<Team> aliveTeams = new HashSet<Team>();
		for (Entity entity : entities)
			if (entity instanceof Playable && !entitiesToRemove.contains(entity) && ((Playable)entity).getPlayer() != deadPlayer)
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
		if (team.getScore() == 1) { // win
			loot.addItem(Item.getRandom(), 1);
			loot.increaseXP(20);
			loot.increaseElo(5*teams.size());
		} else if (DRAW.getScore() == 0) { // lose
			loot.increaseXP(10);
			loot.increaseElo(Math.max(-5, -player.getElo()));
		} else { // draw
			loot.increaseXP(5);
		}
		return loot;
	}
	
	@Override
	public void onEnd() {
		
	}
	
}
