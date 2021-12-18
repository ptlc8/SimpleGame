package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

import fr.ptlc.SGServer.game.CustomGame;
import fr.ptlc.SGServer.game.Game;
import fr.ptlc.SGServer.game.GameMap;
import fr.ptlc.SGServer.game.GameMode;
import fr.ptlc.SGServer.game.Team;

public class GameRoom extends Room {
	
	private final SG sg;
	private final String name;
	private RoomState state;
	private String creatorName;
	
	private final Map<Integer, Player> members;
	private final List<Message> chat;
	
	private GameMap map;
	private GameMode gameMode;
	
	private final Set<Team> teams;
	
	public GameRoom(SG sg, String name, Player creator, int creatorId) {
		this.sg = sg;
		this.name = name;
		state = RoomState.WAITING;
		creatorName = creator.getName();
		members = Collections.synchronizedMap(new HashMap<>());
		members.put(creatorId, creator);
		chat = Collections.synchronizedList(new ArrayList<Message>());
		map = null;
		gameMode = null;
		teams = Collections.synchronizedSet(new HashSet<Team>());
	}
	
	public String getName() {
		return name;
	}
	
	public RoomState getState() {
		return state;
	}
	
	public String getCreatorName() {
		return creatorName;
	}
	
	public Collection<Player> getMembers() {
		return Collections.unmodifiableCollection(members.values());
	}
	
	public void join(Player player, int connId) {
		synchronized (members) {
			members.put(connId, player);
		}
		broadcast("memberjoin " + player.getName());
	}
	
	@Override
	public void leave(Player player) {
		synchronized (members) {
		Iterator<Entry<Integer, Player>> it = members.entrySet().iterator();
		while (it.hasNext())
			if (it.next().getValue().equals(player)) {
				it.remove(); // sinon remove par key
				broadcast("memberleave " + player.getName());
			}
		}
		if (members.isEmpty() && state.equals(RoomState.WAITING))
			sg.removeGameRoom(this);
	}
	
	private void broadcast(String message) {
		for (int connId : members.keySet())
			sg.sendMessage(connId, message);
	}
	
	public List<Message> getChat() {
		return Collections.unmodifiableList(chat);
	}
	
	public void addMessageChat(String sender, String content) {
		Message message = new Message(sender, content);
		chat.add(message);
		if (chat.size() > 32)
			chat.remove(0);
		broadcast("newchat " + new Gson().toJson(message));
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
		broadcast("map " + new Gson().toJson(this.map));
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
		broadcast("gamemode " + new Gson().toJson(this.gameMode));
	}
	
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teams);
	}
	
	public Team getTeamByName(String teamName) {
		for (Team team : teams)
			if (team.getName().equals(teamName))
				return team;
		return null;
	}
	
	public Set<Player> getTeamMates(Player member) {
		Set<Player> teamMates = new HashSet<>();
		for (Player m : members.values())
			if (m.getTeam().equals(member.getTeam()))
				teamMates.add(m);
		return teamMates;
	}
	
	public void start() {
		for (int i = 0; i < gameMode.getTeamsNumber(); i++)
			teams.add(new Team("Équipe "+i, map.getSpawn(i)));
		state = RoomState.PREPARING;
		Thread t = new Thread(new Runnable() {
			public void run() {
				broadcast("gamestarted");
				allPlayersAreChoosingTeam();
				while (!allPlayersHaveTeam()) {
					//wait
				}
				broadcast("teamscompleted");
				allPlayersAreChoosingClass();
				while (!allPlayersHaveClass()) {
					//wait
				}
				broadcast("classeschoosen");
				state = RoomState.PLAYING;
				sg.addGame(new CustomGame(sg, GameRoom.this, gameMode, map, teams, members, chat));
			}
		}, "gamethread " + name);
		t.start();
	}
	
	public void allPlayersAreChoosingTeam() {
		for (Player member : members.values())
			member.isNowChoosingTeam();
	}
	
	public boolean allPlayersHaveTeam() {
		for (Player member: members.values())
			if (member.getTeam() == null)
				return false;
		return true;
	}
	
	public void allPlayersAreChoosingClass() {
		for (Player member : members.values())
			member.isNowChoosingClass();
	}
	
	public boolean allPlayersHaveClass() {
		for (Player member: members.values())
			if (member.getClassName() == null || member.getCapacityN() == -1)
				return false;
		return true;
	}
	
	public void allPlayersJoinGame(Game game) {
		for (Player member : members.values())
			member.joinGame(game);
	}
	
}
