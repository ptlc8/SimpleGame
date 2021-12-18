package fr.ptlc.SGServer.game;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.Classs;
import fr.ptlc.SGServer.Item;
import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.entities.Invader;

public class Survive extends GameManager {
	
	public static final int[] teamNumber = {1};
	
	private final Team invadersTeam;
	private final Player invasion;
	
	private final Timer timer;
	private int wave;
	
	public Survive(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		super(game, map, players, teams, haveDisplayer);
		invadersTeam = Team.SpecialTeam("", null);
		invasion = new Player("L'invasion", "inaccessible", -1);
		invasion.joinGame(game);
		invasion.setTeam(invadersTeam);
		timer = new Timer();
		wave = 0;
		summonAllPlayers();
	}
	
	@Override
	public void onStart() {
		startWave(++wave);
	}
	
	private void startWave(int level) {
		broadcast("wave "+level);
		for (int i = 0; i < level; i++)
			add(new Invader(invasion, this, Classs.getClasssByName("random"), level/6f+0.5f));
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				startWave(++wave);
			}
		}, 20000);
	}
	
	@Override
	public void onDeath(Player deadPlayer) {
		tranfertToDeath(deadPlayer);
		if (players.isEmpty()) {
			end(invadersTeam);
		}
	}
	
	@Override
	public Loot getLoot(Player player, Team team) {
		Loot loot = new Loot();
		if (wave > 10) loot.addItem(Item.getRandom(), 1);
		loot.increaseXP(2*wave);
		return loot;
	}
	
	@Override
	public void onEnd() {
		
	}
	
}
