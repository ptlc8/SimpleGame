package fr.ptlc.SGServer.game;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import fr.ptlc.SGServer.GameRoom;
import fr.ptlc.SGServer.Message;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.SG;

public class CustomGame extends Game {
	
	private transient final GameRoom gameRoom;
	
	public CustomGame(SG sg, GameRoom gameRoom, GameMode gameMode, GameMap map, Set<Team> teams, Map<Integer, Player> players, List<Message> chat) {
		super(sg);
		this.gameRoom = gameRoom;
		this.gameMode = gameMode;
		this.map = map;
		this.teams = teams;
		this.players = players;
		this.chat = chat;
		broadcast("classeschoosen");
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
		return gameRoom.getName() + gameMode.name();
	}
	
	@Override
	public void onEnd(Team winningTeam) {
		for (Player p : players.values()) {
			send(p, "end " + (winningTeam.isDraw() ? "draw" : (winningTeam == p.getTeam() ? "win" : "lose")));
			p.isNowEndingGame();
		}
		broadcast("loot {}");
	}
	
}
