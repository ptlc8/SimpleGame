package fr.ptlc.SGServer.game;

import java.util.Set;

import fr.ptlc.SGServer.Player;

public enum GameMode {
	
	CLASSIC_3v3(Classic.class, 2, 3),
	CLASSIC_1v1v1v1(Classic.class, 4, 1),
	REBRAWL_3v3(Rebrawl.class, 2, 3),
	REBRAWL_2v2(Rebrawl.class, 2, 2),
	CTP_2v2(CTPRebrawl.class, 2, 2),
	CLASSIC_1v1v1(Classic.class, 3, 1),
	CLASSIC_2v2(CTPRebrawl.class, 2, 2),
	CTP_3v3(CTPRebrawl.class, 2, 3),
	INVASION_2(Survive.class, 1, 2),
	INVASION_5(Survive.class, 1, 5),
	TEST_1(CTPRebrawl.class, 1, 1),
	CLASSIC_1v1(Classic.class, 2, 1),
	TEST_1v1(CTPRebrawl.class, 2, 1);
	
	public static GameMode get(String name) {
		try {
			return valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	private final Class<? extends GameManager> gameManager;
	private final int teamsNumber;
	private final int playersPerTeam;
	
	private GameMode(Class<? extends GameManager> gameManager, int teamsNumber, int playersPerTeam) {
		this.gameManager = gameManager;
		this.teamsNumber = teamsNumber;
		this.playersPerTeam = playersPerTeam;
	}
	
	public GameManager getGameManager(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		if (gameManager.equals(Classic.class))
			return new Classic(game, map, players, teams, haveDisplayer);
		else if (gameManager.equals(Rebrawl.class))
			return new Rebrawl(game, map, players, teams, haveDisplayer);
		else if (gameManager.equals(CTPRebrawl.class))
			return new CTPRebrawl(game, map, players, teams, haveDisplayer);
		else if (gameManager.equals(Survive.class))
			return new Survive(game, map, players, teams, haveDisplayer);
		else {
			System.err.println(name() + " possède un gestionnaire de jeu non-référencé");
			return new Classic(game, map, players, teams, haveDisplayer);
		}
	}
	
	public int getTeamsNumber() {
		return teamsNumber;
	}
	
	public int getPlayersPerTeam() {
		return playersPerTeam;
	}
	
	public int getPlayersNumber() {
		return teamsNumber*playersPerTeam;
	}
	
}
