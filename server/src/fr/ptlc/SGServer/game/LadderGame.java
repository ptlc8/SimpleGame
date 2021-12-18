package fr.ptlc.SGServer.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.SG;

public class LadderGame extends Game {
	
	public LadderGame(SG sg, GameMode gameMode, GameMap map, Map<Integer, Player> players, Set<Team> teams) {
		super(sg);
		this.gameMode = gameMode;
		this.map = map;
		this.players = players;
		this.teams = teams;
		chat = new ArrayList<>();
		broadcast("prestart " + new Gson().toJson(this));
		//----start game without move
		gameManager = gameMode.getGameManager(this, map, new HashSet<Player>(players.values()), teams, true);
		allPlayersArePlaying();
		Thread t = new Thread(new Runnable() {
			public void run() {
				for (int i = 3; i > 0; i--) {
					broadcast("playingin " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//----start game with move
				gameManager.start();
				broadcast("letsplay");
			}
		});
		t.start();
	}
	
	@Override
	public String getName() {
		return "Ladder game " + hashCode() + " - " + gameMode.name();
	}
	
	@Override
	protected void onEnd(Team winningTeam) {
		for (Player p : players.values()) {
			send(p, "end " + (winningTeam.isDraw() ? "draw" : (winningTeam == p.getTeam() ? "win" : "lose")));
		}
		// calcul des puissances des deux equipes
		for (Team t : teams) {
			int elo = 0;
			for (Player p : players.values())
				if (p.getTeam() == t)
					elo += p.getElo();
			t.setElo(elo);
		}
		// attribution des gains
		for (Player p : players.values()) {
			Loot loot = gameManager.getLoot(p, p.getTeam());
			p.swallowLoot(loot);
			send(p, "loot "+new Gson().toJson(loot));
			p.isNowEndingGame();
		}
	}
	
}
