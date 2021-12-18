
package fr.ptlc.SGServer.game;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;

import fr.ptlc.SGServer.Message;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.SG;
import fr.ptlc.SGServer.entities.Entity;

public abstract class Game {
	
	protected transient final SG sg;
	
	protected GameMode gameMode;
	
	protected transient GameManager gameManager;
	
	protected GameMap map;
	
	protected transient Map<Integer, Player> players;
	
	protected List<Message> chat;
	
	protected Set<Team> teams;
	
	public abstract String getName();
	
	protected abstract void onEnd(Team winningTeam);
	
	public Game(SG sg) {
		this.sg = sg;
	}
	
	protected void broadcast(String message) {
		for (int id : players.keySet())
			sg.sendMessage(id, message);
	}
	
	protected void send(Player player, String message) {
		for (Entry<Integer, Player> p : players.entrySet())
			if (p.getValue() == player)
				sg.sendMessage(p.getKey(), message);
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teams);
	}
	
	public Collection<Player> getMembers() {
		return Collections.unmodifiableCollection(players.values());
	}
	
	public Set<Player> getTeamMates(Player player) {
		Set<Player> teamMates = new HashSet<>();
		for (Player p : players.values())
			if (p.getTeam().equals(player.getTeam()))
				teamMates.add(p);
		return teamMates;
	}
	
	public List<Message> getChat() {
		return chat;
	}
	
	protected void addMessageChat(String sender, String content) {
		Message message = new Message(sender, content);
		chat.add(message);
		if (chat.size() > 32)
			chat.remove(0);
		broadcast("newchat " + new Gson().toJson(message));
	}
	
	protected void allPlayersArePlaying() {
		for (Player player : players.values())
			player.joinGame(this);
	}
	
	// only for test
	public boolean queryEnd(Player player) {
		if (!gameMode.name().startsWith("TEST"))
			return false;
		player.getTeam().increaseScore();
		gameManager.end(player.getTeam());
		return true;
	}
	
	// to transfer to GameManager
	
	public void onInput(Player player, Input input) {
		if (gameManager != null) gameManager.onInput(player, input);
	}
	
	public void onAttack(Player player, float direction) {
		if (gameManager != null) gameManager.onAttack(player, direction);
	}
	
	public void onSpecialAttack(Player player, float direction) {
		if (gameManager != null) gameManager.onSpecialAttack(player, direction);
	}
	
	public boolean setDeadPlayerFollow(Player dead, String playerName) {
		if (gameManager != null) return gameManager.setDeadPlayerFollow(dead, playerName);
		else return false;
	}
	
	public boolean setSpectatorFollow(Player spec, String playerName) {
		if (gameManager != null) return gameManager.setSpectatorFollow(spec, playerName);
		return false;
	}
	
	public int getEntityId(Player player) {
		if (gameManager != null) return gameManager.getEntityId(player);
		return -1;
	}
	
	public Set<Entity> getEntitiesAsPlayer(Player player) {
		if (gameManager != null) return gameManager.getEntitiesAsPlayer(player);
		return null;
	}
	
	public Set<Entity> getEntitiesAsSpectator(Player spec) {
		if (gameManager != null) return gameManager.getEntitiesAsSpectator(spec);
		return null;
	}
	
	public String getATH(Player player) {
		if (gameManager != null) return gameManager.getATH(player);
		return "{}";
	}
	
}