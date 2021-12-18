package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.ptlc.SGServer.game.GameMap;
import fr.ptlc.SGServer.game.GameMaps;
import fr.ptlc.SGServer.game.GameMode;
import fr.ptlc.SGServer.game.LadderGame;
import fr.ptlc.SGServer.game.Team;

public class LadderGamesManager {
	
	private final Map<GameMode, List<TeamRoom>> waitingPlayers;
	private final SG sg;
	
	public LadderGamesManager(SG sg) {
		this.sg = sg;
		waitingPlayers = Collections.synchronizedMap(new HashMap<GameMode, List<TeamRoom>>());
		for (GameMode gm : GameMode.values())
			waitingPlayers.put(gm, Collections.synchronizedList(new ArrayList<TeamRoom>(){
				private static final long serialVersionUID = 1L;
				@Override
				public int size() { // actuellement lourd
					int size = 0;
					for (TeamRoom tR : this)
						size += tR.getMembers().size();
					return size;
				}
			}));
	}
	
	/**
	 * @param teamRoom
	 * @return true if game founded
	 */
	public boolean addTeamRoom(TeamRoom teamRoom) {
		GameMode gm = teamRoom.getGameMode();
		synchronized (waitingPlayers) {
			List<TeamRoom> waiting = waitingPlayers.get(gm);
			waiting.add(teamRoom);
			if (waiting.size() >= gm.getPlayersNumber()) {
				return launchGame(gm, waiting);
			}
		}
		return false;
	}

	public boolean cancel(TeamRoom teamRoom) {
		synchronized (waitingPlayers) {
			return waitingPlayers.get(teamRoom.getGameMode()).remove(teamRoom);
		}
	}
	
	private boolean launchGame(GameMode gm, List<TeamRoom> waitingTeamRooms) {
		List<TeamRoom> teamRooms = new ArrayList<TeamRoom>(waitingTeamRooms);
		List<List<Player>> repartition = new ArrayList<List<Player>>();
		for (int i = 0; i < gm.getTeamsNumber(); i++)
			repartition.add(new ArrayList<Player>());
		teamRooms.sort(new Comparator<TeamRoom>() {
			@Override
			public int compare(TeamRoom teamRoom0, TeamRoom teamRoom1) {
				return teamRoom1.getMembers().size() - teamRoom0.getMembers().size();
			}
		});
		for (TeamRoom teamRoom : teamRooms) {
			for (List<Player> team : repartition) {
				if (gm.getPlayersPerTeam() - team.size() >= teamRoom.getMembers().size()) {
					team.addAll(teamRoom.getMembers());
					break;
				}
			}
		}
		for (List<Player> team : repartition)
			if (team.size() != gm.getPlayersPerTeam())
				return false;
		
		GameMap map = GameMaps.getRandomMap(gm.getTeamsNumber());
		if (map == null) {
			System.err.println("Aucune carte de jeu disponible pour le mode de jeu " + gm.name() + " avec " + gm.getTeamsNumber() + " équipes");
			return false;
		}
		
		Map<Integer, Player> players = new HashMap<Integer, Player>();
		Set<Team> teams = new HashSet<Team>();
		for (int i = 0; i < repartition.size(); i++) {
			Team team = new Team("Équipe " + (i+1), map.getSpawn(i));
			teams.add(team);
			for (Player player : repartition.get(i)) {
				players.put(player.getConnId(), player);
				waitingTeamRooms.remove(player.getTeamRoom());
				if (player.getTeamRoom().isSolo()) {
					player.getTeamRoom().leave(player);
					player.setRoom(null);
				}
				player.setTeam(team);
			}
		}
		sg.addGame(new LadderGame(sg, gm, map, players, teams));
		System.out.println("Partie lancée ! (" + gm.name() + ")");
		return true;
	}
	
	public int getSearchingPlayersNumber(GameMode gameMode) {
		return waitingPlayers.get(gameMode).size();
	}
	
}
