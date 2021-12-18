package fr.ptlc.SGServer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.ptlc.SGServer.Access.Ref;
import fr.ptlc.SGServer.game.Game;
import fr.ptlc.SGServer.game.Team;

public class Player {
	
	public static final File saveDirectory = new File("players");
	private static final Gson saveSerializer = new GsonBuilder().setExclusionStrategies(new AccessStrat(Ref.SAVE)).create();
	private static final Gson saveDeserializer = new Gson();
	
	@Access(refs=Ref.ALL)
	private final String name; // in save
	@Access(refs=Ref.SAVE)
	private final String password; // in save
	
	private transient int connId = -1;
	private transient State state = State.MAINMENU;
	
	@Access(refs={Ref.SAVE, Ref.SELF})
	private final Inventory inventory; // in save
	@Access(refs={Ref.SAVE, Ref.SELF, Ref.TEAMMATES})
	private String className; // in save
	@Access(refs={Ref.SAVE, Ref.SELF, Ref.TEAMMATES})
	private final Equipement equipement; // in save
	@Access(refs=Ref.ALL)
	private int xp = 0; // in save
	@Access(refs=Ref.ALL)
	private int elo = 0; // in save
	@Access(refs={Ref.SAVE, Ref.SELF})
	private List<String> friends = new ArrayList<String>();
	
	private transient Room room = null;
	private transient Game game = null;
	
	private transient Team team = null;
	private transient int capacityN = -1;
	
	public Player(String name, String password, int connId) {
		this.name = name;
		this.password = password;
		this.connId = connId;
		state = State.MAINMENU;
		Map<Item, Integer> starterPack = new HashMap<Item, Integer>();
		starterPack.put(Item.iron_helmet, 1);
		for (int i = 0; i < 6; i++)
			starterPack.put(Item.values()[new Random().nextInt(Item.values().length)], 1);
		inventory = new Inventory(starterPack);
		equipement = new Equipement();
		xp = 0;
		elo = 0;
		friends = new ArrayList<String>();
		game = null;
		team = null;
		className = "guerrier";
		capacityN = -1;
		save();
	}
	
	protected static boolean exists(String name) {
		if (!(saveDirectory.exists() && saveDirectory.isDirectory())) return false;
		return new File(saveDirectory.getPath() + "/" + name).exists();
	}
	
	protected static Player load(String name) {
		System.out.println("Chargement des données du joueur " + name + "...");
		File file = new File(saveDirectory.getPath() + "/" + name);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String json = "";
			for (String line; (line = br.readLine()) != null; json += line);
			br.close();
			System.out.println("Données du joueur " + name + " chargées");
			return saveDeserializer.fromJson(json, Player.class);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Impossible de charger les données du joueur " + name);
			return null;
		}
	}
	
	private void save() {
		if (!(saveDirectory.exists() && saveDirectory.isDirectory())) saveDirectory.mkdirs();
		File file = new File(saveDirectory.getPath() + "/" + name);
		try {
			if (!file.exists()) file.createNewFile();
			if (!file.canWrite()) System.err.println("Impossible de sauvegarder les données du joueur " + name);
			PrintWriter w = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
			w.append(saveSerializer.toJson(this));
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean testPassword(String password) {
		return password.equals(this.password);
	}
	
	public int getConnId() {
		return connId;
	}
	
	public State getState() {
		return state;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Equipement getEquipement() {
		return equipement;
	}
	
	public boolean equip(Item item) {
		boolean r = equipement.equip(item);
		save();
		return r;
	}
	
	public boolean disequip(String slotName) {
		boolean r = equipement.disequip(slotName);
		save();
		return r;
	}
	
	public Map<Item, Integer> openChest(int size) {
		Map<Item, Integer> loots = Item.getLoot(4, size*20);
		inventory.add(loots);
		save();
		return loots;
	}
	
	public boolean craft(Item result) {
		if (result.getCraft() == null) return false;
		for (Entry<Item, Integer> m : result.getCraft().entrySet())
			if (!inventory.has(m.getKey(), m.getValue()))
				return false;
		for (Entry<Item, Integer> m : result.getCraft().entrySet())
			inventory.remove(m.getKey(), m.getValue());
		inventory.add(result, 1);
		save();
		return true;
	}
	
	public void swallowLoot(Loot loot) {
		xp += loot.getXP();
		elo += loot.getElo();
		inventory.add(loot.getItems());
		save();
	}
	
	public void setConnId(int connId) {
		this.connId = connId;
		state = (connId != -1) ? state == State.OFF || state == null ? State.MAINMENU : state : State.MAINMENU;
	}
	
	public int getXP() {
		return xp;
	}
	
	public int getElo() {
		return elo;
	}
	
	public List<String> getFriends() {
		return friends;
	}
	
	public void searchingLadderGame() {
		state = State.SEARCHINGLADDER;
	}
	
	public void stopSearching() {
		state = room == null ? State.MAINMENU : State.INGROUP;
	}
	
	public void setRoom(Room room) {
		if (this.room != null) this.room.leave(this);
		state = room != null ? room instanceof GameRoom ? State.INCHATGAME : State.INGROUP : State.MAINMENU;
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public TeamRoom getTeamRoom() {
		return room instanceof TeamRoom ? (TeamRoom)room : null;
	}
	
	public GameRoom getGameRoom() {
		return room instanceof GameRoom ? (GameRoom)room : null;
	}
	
	public void joinGame(Game game) {
		state = State.PLAYING;
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void isNowChoosingTeam() {
		state = State.CHOOSINGTEAM;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void isNowChoosingClass() {
		state = State.CHOOSINGCLASS;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
		save();
	}
	
	public int getCapacityN() {
		return capacityN;
	}
	
	public void setCapacityN(int capacityN) {
		this.capacityN = capacityN;
	}
	
	public void isNowSpectating() {
		state = State.SPECTATING;
	}
	
	public void isNowEndingGame() {
		team = null;
		state = State.MAINMENU;
	}
	
}
