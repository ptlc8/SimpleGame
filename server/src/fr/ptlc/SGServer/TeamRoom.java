package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.gson.Gson;

import fr.ptlc.SGServer.game.GameMode;

public class TeamRoom extends Room {
	
	private final SG sg;
	private String ownerName;
	private String joinCode;
	private RoomState state;
	private boolean isSolo;
	
	private final Map<Integer, Player> members;
	private final List<Message> chat;
	
	private GameMode gameMode;
	
	public TeamRoom(SG sg, Player owner, int ownerId, GameMode gameMode) {
		this.sg = sg;
		ownerName = owner.getName();
		do {
			joinCode = newRandomString("AZERTYUIOPQSDFGHJKLMWXCVBN123456789", 12);
		} while(sg.roomJoinCodeIsUsed(joinCode));
		state = RoomState.WAITING;
		isSolo = false;
		members = new HashMap<Integer, Player>();
		members.put(ownerId, owner);
		chat = new ArrayList<Message>();
		this.gameMode = gameMode;
		sg.addTeamRoom(this);
	}
	
	public static TeamRoom newSoloRoom(SG sg, Player owner, int ownerId, GameMode gameMode) {
		TeamRoom soloRoom = new TeamRoom(sg, owner, ownerId, gameMode);
		soloRoom.isSolo = true;
		return soloRoom;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public String getJoinCode() {
		return joinCode;
	}
	
	public RoomState getState() {
		return state;
	}
	
	public boolean isSolo() {
		return isSolo;
	}
	
	public Collection<Player> getMembers() {
		return Collections.unmodifiableCollection(members.values());
	}
	
	public boolean join(Player player, int connId) {
		if (members.size() >= 5) return false;
		synchronized (members) {
			members.put(connId, player);
		}
		broadcast("memberjoin " + player.getName());
		return true;
	}
	
	@Override
	public void leave(Player player) {
		synchronized (members) {
		Iterator<Entry<Integer, Player>> it = members.entrySet().iterator();
		while (it.hasNext())
			if (it.next().getValue().equals(player)) {
				it.remove();
				broadcast("memberleave " + player.getName());
			}
		}
		if (!members.isEmpty()) {
			if (player.getName().equals(ownerName))
				for (Player m : members.values()) {
					ownerName = m.getName();
					break;
				}
		} else {
			sg.removeTeamRoom(this);
		}
	}
	
	protected void broadcast(String message) {
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
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
		broadcast("gamemode " + new Gson().toJson(this.gameMode));
	}

	public void searchingLadderGame() {
		state = RoomState.PLAYING;
		for (Player m : members.values())
			m.searchingLadderGame();
	}
	
	public void stopSearching() {
		state = RoomState.WAITING;
		for (Player m : members.values())
			m.stopSearching();
	}
	
	private static String newRandomString(String chars, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
			sb.append(chars.charAt(new Random().nextInt(chars.length())));
		return sb.toString();
	}
	
}
