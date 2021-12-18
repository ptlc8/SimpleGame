package fr.ptlc.SGServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ptlc.SGServer.Access.Ref;
import fr.ptlc.SGServer.game.Game;
import fr.ptlc.SGServer.game.GameMap;
import fr.ptlc.SGServer.game.GameMode;
import fr.ptlc.SGServer.game.Input;

public class SG {
	
	private WebsocketServer wss;
	
	// private final Map<String, Player> players;
	private final Map<Integer, Player> connectedPlayers;
	private final Map<String, TeamRoom> teamRooms;
	private final Map<String, GameRoom> gameRooms;
	private final Map<String, Game> games;
	private final LadderGamesManager lgm;
	
	public SG() {
		wss = null;
		// players = Collections.synchronizedMap(new HashMap<>());
		connectedPlayers = Collections.synchronizedMap(new HashMap<>());
		teamRooms = Collections.synchronizedMap(new HashMap<>());
		gameRooms = Collections.synchronizedMap(new HashMap<>());
		games = Collections.synchronizedMap(new HashMap<>());
		lgm = new LadderGamesManager(this);
	}
	
	public void setWebsocketServer(WebsocketServer wss) {
		this.wss = wss;
	}
	
	public String onPlayerMessage(int connId, String message) {
		String command = message.split(" ").length > 0 ? message.split(" ")[0] : "";
		String arg = message.replaceFirst(command, "").replaceFirst(" ", "");
		if (command.equals("ping"))
			return "pong " + new Date().getTime();
		if (!connectedPlayers.containsKey(connId)) {
			Player player;
			switch (command) {
			case "illbe":
				Identification rId = new Gson().fromJson(arg, Identification.class);
				if (rId.name.equals("")) return "youllbe";
				if (rId.password.equals("")) return "needpassword";
				if (playerNameIsUsed(rId.name)) return "playernamealreadyused";
				player = new Player(rId.name, rId.password, connId);
				/*synchronized (players) {
					players.put(rId.name, player);
				}*/
				synchronized (connectedPlayers) {
					connectedPlayers.put(connId, player);
				}
				return "youreregistered " + new Gson().toJson(player);
			case "iam":
				Identification cId = new Gson().fromJson(arg, Identification.class);
				if (cId.name.equals("")) return "youare";
				if (cId.password.equals("")) return "needpassword";
				if (!playerNameIsUsed(cId.name)) return "playerdoesntexist";
				player = Player.load(cId.name);//players.get(cId.name);
				if (!player.testPassword(cId.password)) return "invalidpassword";
				if (playerIsConnected(cId.name)) return "alreadyconnected";
				player.setConnId(connId);
				synchronized (connectedPlayers) {
					connectedPlayers.put(connId, player);
				}
				return "youreloggedin " + new Gson().toJson(player);
			default:
				return "youneedaname";
			}
		}
		Player player;
		synchronized (connectedPlayers) {
			player = connectedPlayers.get(connId);
		}
		TeamRoom teamRoom = player.getTeamRoom();
		GameRoom gameRoom = player.getGameRoom();
		Game game = player.getGame();
		switch (player.getState()) {
		case MAINMENU:
			switch (command) {
			case "getversion":
				return "version " + Main.version;
			case "searchgame":
				List<String> roomsName = new ArrayList<>();
				for (GameRoom r : gameRooms.values())
					if (r.getName().contains(arg))
						roomsName.add(r.getName());
				return "searchgameresults " + new Gson().toJson(roomsName);
			case "creategame":
				if (arg.equals("")) return "gameneedaname";
				if (roomNameIsUsed(arg)) return "gamenamealreadyused";
				GameRoom newGameRoom = new GameRoom(this, arg, player, connId);
				addGameRoom(newGameRoom);
				player.setRoom(newGameRoom);
				return "gamecreated " + arg;
			case "joingame":
				if (arg.equals("")) return "needagamename";
				GameRoom toJoin = getGameRoom(arg);
				if (toJoin == null) return "gamedoesntexist";
				if (!toJoin.getState().equals(RoomState.WAITING)) return "gameisplaying";
				toJoin.join(player, connId);
				player.setRoom(toJoin);
				return "gamejoined " + arg;
			case "setclass":
				if (arg.equals("")) return "needclassname";
				if (!Classs.names.contains(arg)) return "needexistingclassname";
				player.setClassName(arg);
				return "classsetted " + arg;
			case "getgamemodes":
				return "gamemodes " + new Gson().toJson(GameMode.values());
			case "searchladdergame":
				if (arg == "") return "needgamemode";
				GameMode gameMode = GameMode.get(arg);
				if (gameMode==null) return "invalidgamemode";
				TeamRoom soloRoom = TeamRoom.newSoloRoom(this, player, connId, gameMode);
				player.setRoom(soloRoom);
				player.searchingLadderGame();
				if (!lgm.addTeamRoom(soloRoom)) return "searching "+arg;
				return "founded "+arg;
			case "creategroup":
				player.setRoom(new TeamRoom(this, player, connId, GameMode.CLASSIC_3v3));
				return "groupcreated " + player.getTeamRoom().getJoinCode();
			case "joingroup":
				if (arg.equals("")) return "needjoincode";
				TeamRoom toJoin_ = getTeamRoom(arg);
				if (toJoin_ == null) return "invalidjoincode";
				if (!toJoin_.getState().equals(RoomState.WAITING)) return "groupisplaying";
				if (!toJoin_.join(player, connId)) return "groupfull";
				player.setRoom(toJoin_);
				return "groupjoined " + arg;
			case "openchest":
				return "chest " + new Gson().toJson(player.openChest(1));
			case "getinventory":
				return "inventory " + new Gson().toJson(player.getInventory());
			case "getequipement":
				return "equipement" + new Gson().toJson(player.getEquipement());
			case "equip":
				if (arg.equals("")) return "needitem";
				Item item = Item.get(arg);
				if (!player.getInventory().has(item, 1)) return "youhaven't";
				if (!player.equip(item)) return "cantequip";
				return "equiped " + arg;
			case "disequip":
				if (arg.equals("")) return "needslot";
				if (!player.disequip(arg)) return "unknowslot";
				return "disequiped " + arg;
			case "craft":
				if (arg.equals("")) return "needresult";
				Item result = Item.get(arg);
				if (result == null) return "needvalidresult";
				if (player.craft(result))
					return "craftsucceed";
				else return "cantcraft";
			case "getfriends":
				List <Friend> friends = new ArrayList<Friend>();
				for (String friendName : player.getFriends()) {
					Player friend = Player.load(friendName);
					friends.add(new Friend(friendName, playerIsConnected(friendName), friend.getXP(), friend.getElo()));
				}
				return "friends " + new Gson().toJson(friends);
			}
			break;
		case INGROUP:
			switch (command) {
			case "leavegroup":
				teamRoom.leave(player);
				player.setRoom(null);
				return "groupleaved";
			case "getmembers":
				return "members " + new GsonBuilder().setExclusionStrategies(new AccessStrat(Ref.TEAMMATES)).create().toJson(teamRoom.getMembers());
			case "whoisowner":
				return "owneris " + teamRoom.getOwnerName();
			case "sendchat":
				if (arg.equals("")) return "needmessagecontent";
				teamRoom.addMessageChat(player.getName(), arg);
				return "messagereceived";
			case "getchat":
				return "chat " + new Gson().toJson(teamRoom.getChat());
			case "setclass":
				if (arg.equals("")) return "needclassname";
				if (!Classs.names.contains(arg)) return "needexistingclassname";
				player.setClassName(arg);
				return "classsetted " + arg;
			case "getgamemodes":
				return "gamemodes " + new Gson().toJson(GameMode.values());
			case "setgamemode":
				if (!teamRoom.getOwnerName().equals(player.getName())) return "youarentowner";
				if (arg.equals("")) return "needgamemode";
				GameMode gameMode;
				try {
					gameMode = GameMode.valueOf(arg);
				} catch (Exception e) {
					return "invalidgamemode";
				}
				teamRoom.setGameMode(gameMode);
				return "gamemodesetted";
			case "getgamemode":
				return "gamemode " + new Gson().toJson(teamRoom.getGameMode());
			case "searchladdergame":
				if (teamRoom.getMembers().size() > teamRoom.getGameMode().getPlayersPerTeam()) return "toomuchmembers";
				teamRoom.searchingLadderGame();
				teamRoom.broadcast((lgm.addTeamRoom(teamRoom) ? "founded " : "searching ") + teamRoom.getGameMode().name());
				return "searchlaunched";
			}
			break;
		case SEARCHINGLADDER:
			switch (command) {
			case "cancel":
				if (!lgm.cancel(teamRoom))
					return "cantcancel";
				teamRoom.stopSearching();
				if (teamRoom.isSolo()) {
					teamRoom.leave(player);
					player.setRoom(null);
				}
				return "searchcanceled";
			case "getsearchingplayersnumber":
				return "searchingplayersnumber " + lgm.getSearchingPlayersNumber(player.getTeamRoom().getGameMode());
			}
			break;
		case INCHATGAME:
			switch (command) {
			case "leavegame":
				gameRoom.leave(player);
				player.setRoom(null);
				return "gameleaved";
			case "getplayers":
				return "players " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name")).create().toJson(gameRoom.getMembers());
			case "whoiscreator":
				return "creatoris " + gameRoom.getCreatorName();
			case "sendchat":
				if (arg.equals("")) return "needmessagecontent";
				gameRoom.addMessageChat(player.getName(), arg);
				return "messagereceived";
			case "getchat":
				return "chat " + new Gson().toJson(gameRoom.getChat());
			case "setmapandteams":
				if (!gameRoom.getCreatorName().equals(player.getName())) return "youarentcreator";
				if (arg.equals("")) return "needmapandteams";
				GameMap map = null;
				try {
					map = new Gson().fromJson(arg, GameMap.class);
				} catch (Exception e) {
					return "needjsonmapandteams";
				}
				if(map == null || !map.isValid()) return "invalidmapandteams";
				gameRoom.setMap(map);
				return "mapandteamssetted";
			case "getmapandteams":
				return "mapandteams " + new Gson().toJson(gameRoom.getMap());
			case "setgamemode":
				if (!gameRoom.getCreatorName().equals(player.getName())) return "youarentcreator";
				if (arg.equals("")) return "needgamemode";
				GameMode gameMode = GameMode.get(arg);
				if (gameMode==null) return "invalidgamemode";
				gameRoom.setGameMode(gameMode);
				return "gamemodesetted";
			case "getgamemode":
				return "gamemode " + new Gson().toJson(gameRoom.getGameMode());
			case "start":
				if (!gameRoom.getCreatorName().equals(player.getName())) return "youarentcreator";
				if (gameRoom.getMap() == null) return "needmap";
				if (gameRoom.getGameMode() == null) return "needgamemode";
				if (gameRoom.getMap().getSpawnNumber() == 0) return "needspawn";
				if (gameRoom.getGameMode().getTeamsNumber() > gameRoom.getMembers().size()) return "needmoreplayers " + gameRoom.getGameMode().getTeamsNumber();
				if (gameRoom.getGameMode().getTeamsNumber() != gameRoom.getMap().getSpawnNumber()) return "incompatiblemapandgamemode";
				gameRoom.start();
				return "gamestarted";
			}
			break;
		case CHOOSINGTEAM:
			switch (command) {
			case "getteams":
				return "teams " + new Gson().toJson(gameRoom.getTeams());
			case "getplayerswithteams":
				return "playerswithteams " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "team")).create().toJson(gameRoom.getMembers());
			case "jointeam":
				if (arg.equals("")) return "needteamname";
				if (gameRoom.getTeamByName(arg)==null) return "needexistingteamname";
				player.setTeam(gameRoom.getTeamByName(arg));
				return "teamjoined";
			case "leaveteam":
				player.setTeam(null);
				return "teamleaved";
			}
			break;
		case CHOOSINGCLASS:
			switch (command) {
			case "getclasses":
				return "classes " + new Gson().toJson(Classs.getNames());
			case "getclassstats":
				if (arg.equals("")) return "needclassname";
				if (!Classs.names.contains(arg)) return "needexistingclassname";
				return "classstats " + Classs.getJSONClasssStats(arg);
			case "getplayerswithteams":
				return "playerswithteams " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "team")).create().toJson(gameRoom.getMembers());
			case "getteammateswithclasses":
				return "teammateswithclasses " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "team", "className", "equipement")).create().toJson(gameRoom.getTeamMates(player));
			case "setclass":
				if (arg.equals("")) return "needclassname";
				if (!Classs.names.contains(arg)) return "needexistingclassname";
				player.setClassName(arg);
				return "classsetted";
			case "setcapacity":
				if (arg.equals("")) return "needcapacity";
				if (!isInt(arg)) return "needcapacityint";
				int n = Integer.parseInt(arg);
				if (n < -1 || n > 4) return "invalidcapacity";
				player.setCapacityN(n);
				if (n == -1) return "capacityunsetted";
				return "capacitysetted";
			}
			break;
		case PLAYING:
			switch (command) {
			case "getmapandteams":
				return "mapandteams " + new Gson().toJson(game.getMap());
			case "getmyentityid":	
				return "yourentityid " + game.getEntityId(player);
			case "getentities":
				/*Thread t = new Thread(new Runnable() {
					public void run() {
						try {
							String msg = "entities " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("x", "y", "image", "team", "id", "name", "color",  "suffix", "rotation", "flip", "visible", "player", "equipement", "className", "onGround", "hand", "head", "chest", "legs", "foot")).create().toJson(game.getEntitiesAsPlayer(player));
							Thread.sleep(1000);
							sendMessage(connId, msg);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}}});
				t.start();
				break;*/
				return "entities " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("x", "y", "image", "team", "id", "name", "color", "suffix", "rotation", "flip", "visible", "player", "equipement", "className", "onGround", "hand", "head", "chest", "legs", "foot")).create().toJson(game.getEntitiesAsPlayer(player));
			case "getath":
				return "ath " + game.getATH(player);
			case "getteammateswithclasses":
				return "teammateswithclasses " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "team", "className", "equipement")).create().toJson(game.getTeamMates(player));
			case "getteams":
				return "teams " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "color", "score")).create().toJson(game.getTeams());
			case "move":
				if (arg.equals("")) return "needdirection";
				if (!isFloat(arg)) return "needfloatdirection";
				game.onInput(player, new Input(Float.parseFloat(arg), true));
				return "moving";
			case "stopmove":
				game.onInput(player, new Input(0f, false));
				return "stopmoving";
			case "attack":
				if (arg.equals("")) return "needdirection";
				if (!isFloat(arg)) return "needfloatdirection";
				game.onAttack(player, Float.parseFloat(arg));
				return "attackreceived";
			case "specialattack":
				if (arg.equals("")) return "needdirectionsp";
				if (!isFloat(arg)) return "needfloatdirectionsp";
				game.onSpecialAttack(player, Float.parseFloat(arg));
				return "specialattackreceived";
			case "follow":
				if (arg.equals("")) return "needplayertofollow";
				if (!game.setDeadPlayerFollow(player, arg)) return "cantfollow " + arg;
				return "following " + arg;
			case "getfollowingentityid":
				return "followingentityid " + game.getEntityId(player);
			case "queryend": // only for test
				if (game.queryEnd(player)) return "ending";
				return "notatestgame";
			}
			break;
		case SPECTATING:
			switch(command) { 
			case "getmapandteams":
				return "mapandteams " + new Gson().toJson(game.getMap());
			case "getteammateswithclasses":
				return "teammateswithclasses " /*+ new GsonBuilder().setExclusionStrategies(new OnlyStrategy("name", "team", "className", "equipement")).create().toJson(game.getTeamMates(player))*/;
			case "follow":
				if (arg.equals("")) return "needplayertofollow";
				if (!game.setSpectatorFollow(player, arg)) return "cantfollow " + arg;
				return "following " + arg;
			case "getfollowingentityid":
				return "followingentityid " + game.getEntityId(player);
			case "getentities":
				return "entities " + new GsonBuilder().setExclusionStrategies(new OnlyStrategy("x", "y", "image", "team", "id", "name", "color", "suffix", "rotation", "flip", "visible", "player", "equipement", "className", "onGround", "hand", "head", "chest", "legs", "foot")).create().toJson(game.getEntitiesAsSpectator(player));
			}
			break;
		case OFF:
			break;
		}
		return "unknowcommand " + command;
	}
	
	public void onPlayerDisconnect(int id) {
		Player player = connectedPlayers.get(id);
		if (player == null) return;
		if (player.getRoom() != null) player.getRoom().leave(player);
		player.setConnId(-1);
		connectedPlayers.remove(id);
	}
	
	public void sendMessage(int connId, String message) {
		if (wss != null) wss.sendMessage(connId, message);
	}
	
	public boolean playerNameIsUsed(String name) {
		// return players.get(name) != null;
		return Player.exists(name);
	}
	
	public boolean playerIsConnected(String name) {
		for (Player p : connectedPlayers.values())
			if (p.getName().equals(name))
				return true;
		return false;
	}
	
	protected TeamRoom getTeamRoom(String joinCode) {
		return teamRooms.get(joinCode);
	}
	
	protected void addTeamRoom(TeamRoom teamRoom) {
		synchronized (teamRooms) {
			teamRooms.put(teamRoom.getJoinCode(), teamRoom);
		}
	}
	
	protected void removeTeamRoom(TeamRoom teamRoom) {
		synchronized (teamRooms) {
			teamRooms.remove(teamRoom.getJoinCode());
		}
	}
	
	public boolean roomJoinCodeIsUsed(String joinCode) {
		return teamRooms.get(joinCode) != null;
	}
	
	protected GameRoom getGameRoom(String roomName) {
		return gameRooms.get(roomName);
	}
	
	protected void addGameRoom(GameRoom gameRoom) {
		synchronized (gameRooms) {
			gameRooms.put(gameRoom.getName(), gameRoom);
		}
	}
	
	protected void removeGameRoom(GameRoom gameRoom) {
		synchronized (gameRooms) {
			gameRooms.remove(gameRoom.getName());
		}
	}
	
	public boolean roomNameIsUsed(String roomName) {
		return gameRooms.get(roomName) != null;
	}
	
	public Game getGame(String gameName) {
		return games.get(gameName);
	}
	
	protected void addGame(Game game) {
		synchronized (games) {
			games.put(game.getName(), game);
		}
	}
	
	protected void removeGame(Game game) {
		games.remove(game);
	}
	
	public boolean isFloat(String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
