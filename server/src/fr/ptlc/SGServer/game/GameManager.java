package fr.ptlc.SGServer.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import fr.ptlc.SGServer.Classs;
import fr.ptlc.SGServer.Loot;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.entities.Entity;
import fr.ptlc.SGServer.entities.Minion;
import fr.ptlc.SGServer.entities.Playable;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public abstract class GameManager {
	
	private final Game game;
	protected final GameMap map;
	protected final Set<Player> allPlayers;
	protected final Set<Team> teams;
	
	protected final Map<Player, Playable> players;
	protected final Map<Player, Playable> deadPlayers;
	
	protected final Set<Entity> entities;
	protected final Set<Entity> entitiesToRemove;
	protected final Set<Entity> entitiesToAdd;
	
	private final Map<Player, Playable> spectators;
	
	private final Set<Runnable> runAfterMove;
	
	private final boolean haveDisplayer;
	private final GameDisplayer displayer;
	
	private final Timer timer;
	private TimerTask timerTask;
	public final int frameRate = 16;
	private boolean running = false;
		
	protected final Team DRAW = Team.Draw();
	
	public GameManager(Game game, GameMap map, Set<Player> players, Set<Team> teams, boolean haveDisplayer) {
		this.game = game;
		this.map = map;
		this.allPlayers = Collections.unmodifiableSet(players);
		this.teams = Collections.unmodifiableSet(teams);
		this.players = Collections.synchronizedMap(new HashMap<Player, Playable>());
		this.deadPlayers = Collections.synchronizedMap(new HashMap<Player, Playable>());
		entities = Collections.synchronizedSet(new HashSet<Entity>());
		entitiesToRemove = Collections.synchronizedSet(new HashSet<Entity>());
		entitiesToAdd = Collections.synchronizedSet(new HashSet<Entity>());
		runAfterMove = Collections.synchronizedSet(new HashSet<Runnable>());
		spectators = Collections.synchronizedMap(new HashMap<Player, Playable>());
		this.haveDisplayer = haveDisplayer;
		if (haveDisplayer) displayer = new GameDisplayer(game.getName(), map.getPattern());
		else displayer = null;
		timer = new Timer();
	}
	
	public void start() {
		running = true;
		onStart();
		timer.scheduleAtFixedRate(timerTask = new TimerTask() {
			@Override
			public void run() {
				GameManager.this.run();
			}
		}, 0, frameRate);
	}
	
	public void stop() {
		timerTask.cancel();
		timer.purge();
		//running = false;
	}
	
	public void run() {
		synchronized (entities) {
			Iterator<Entity> it = entities.iterator();
			while (it.hasNext()) {
				it.next().move();
			}
		}
		synchronized (entitiesToAdd) {
			synchronized (entities) {
				Iterator<Entity> it = entitiesToAdd.iterator();
				while (it.hasNext()) {
					entities.add(it.next());
					it.remove();
				}
			}
		}
		synchronized (runAfterMove) {
			for (Iterator<Runnable> it = runAfterMove.iterator(); it.hasNext();) {
				it.next().run();
				it.remove();
			}
		}
		synchronized (entitiesToRemove) {
			synchronized (entities) {
				Iterator<Entity> it = entitiesToRemove.iterator();
				while (it.hasNext()) {
					entities.remove(it.next());
					it.remove();
				}
			}
		}
		if (haveDisplayer) displayer.refresh(map.getPattern(), entities);
	}
	
	public abstract void onStart();
	
	public abstract void onDeath(Player deadPlayer);
	
	public abstract Loot getLoot(Player player, Team team);
	
	public abstract void onEnd();
	
	
	
	
	protected void summon(Player player) {
		Playable playable = new Playable(player, this, player.getTeam().getSpawn(), Classs.getClasssByName(player.getClassName()), player.getCapacityN(), player.getEquipement().getModifiers(), player.getEquipement().getHandItem());
		synchronized (players) {
			players.put(player, playable);
		}
		synchronized (entities) {
			entities.add(playable);
		}
	}
	
	protected void summonAllPlayers() {
		synchronized (players) {
			for (Player player : allPlayers)
				players.put(player, new Playable(player, this, player.getTeam().getSpawn(), Classs.getClasssByName(player.getClassName()), player.getCapacityN(), player.getEquipement().getModifiers(), player.getEquipement().getHandItem()));
		}
		synchronized (entities) {
			entities.addAll(players.values());
		}
	}
	
	public void add(Entity entity) {
		if (running)
			synchronized (entitiesToAdd) {
				entitiesToAdd.add(entity);
			}
		else
			synchronized (entities) {
				entities.add(entity);
			}
	}
	
	public void remove(Entity entity) {
		if (running)
			synchronized (entitiesToRemove) {
				entitiesToRemove.add(entity);
			}
		else
			synchronized (entities) {
				entities.remove(entity);
			}
	}
	
	protected void clear() {
		synchronized(entitiesToAdd) {entitiesToAdd.clear();}
		synchronized(entities) {entities.clear();}
		synchronized(entitiesToRemove) {entitiesToRemove.clear();}
		synchronized(players) {players.clear();}
		synchronized(deadPlayers) {deadPlayers.clear();}
	}
	
	public void runAfterMove(Runnable run) {
		synchronized (runAfterMove) {
			runAfterMove.add(run);
		}
	}
	
	protected void broadcast(String message) {
		game.broadcast(message);
	}
	
	protected void send(Player player, String message) {
		game.send(player, message);
	}
	
	protected void end(Team winningTeam) {
		stop();
		game.onEnd(winningTeam);
		if (displayer != null) displayer.close();
		onEnd();
	}
	
	public void onPlayableDie(Playable killer, Playable dead) {
		if (dead instanceof Minion) return;
		while (killer instanceof Minion) killer = ((Minion)killer).getOwner();
		Player deadPlayer = dead.getPlayer();
		Player playerKiller = killer.getPlayer();
		broadcast("kill {\"killer\":\"" + playerKiller.getName().replace("\"", "\\\"") + "\","
				+ "\"killed\":\"" + deadPlayer.getName().replace("\"", "\\\"") + "\","
				+ "\"className\":\"" + killer.getClasss().name() + "\"}");
		onDeath(deadPlayer);
	}
	
	protected void tranfertToDeath(Player deadPlayer) {
		send(deadPlayer, "youredead");
		/*Playable dead = players.get(deadPlayer);
		for (Entry<Player, Playable> otherDeadPlayer : deadPlayers.entrySet())
			if (otherDeadPlayer.getValue() == dead)
				synchronized (deadPlayers) {
					deadPlayers.put(otherDeadPlayer.getKey(), null);
				}
		for (Entry<Player, Playable> spec : spectators.entrySet())
			if (spec.getValue() == dead)
				synchronized (spectators) {
					spectators.put(spec.getKey(), null);
				}*/
		synchronized (players) {
			players.remove(deadPlayer);
		}
		synchronized (deadPlayers) {
			deadPlayers.put(deadPlayer, null);
		}
	}
	
	public void respawn(Player player) {
		send(player, "yourealive");
		synchronized (deadPlayers) {
			deadPlayers.remove(player);
		}
		summon(player);
	}
	
	
	
	
	
	public Game getGame() {
		return game;
	}
	
	public boolean setDeadPlayerFollow(Player dead, String playerName) {
		Playable followTarget = null;
		for (Entry<Player, Playable> player : players.entrySet())
			if (player.getKey().getName().equals(playerName) && player.getValue().getTeam().equals(dead.getTeam()))
				followTarget = player.getValue();
		if (followTarget == null) return false;
		deadPlayers.put(dead, followTarget);
		return true;
	}
	
	public boolean setSpectatorFollow(Player spec, String playerName) {
		Playable followTarget = null;
		for (Entry<Player, Playable> player : players.entrySet())
			if (player.getKey().getName().equals(playerName) && player.getValue().getTeam().equals(spec.getTeam()))
				followTarget = player.getValue();
		if (followTarget == null) return false;
		spectators.put(spec, followTarget);
		return true;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public Set<Entity> getEntities() {
		return Collections.unmodifiableSet(entities);
	}
	
	public String getATH(Player player) {
		Playable p = players.get(player);
		if (p==null) return "";
		return p.getHealth()+" "+p.getHealthMax()
			+" "+p.getEnergy()+" "+p.getEnergyMax()
			+" "+p.getSpecial()+" "+p.getSpecialMax();
	}
	
	public Set<Entity> getEntitiesAsPlayer(Player player) {
		Playable playable = players.get(player);
		if (playable == null) deadPlayers.get(player);
		if (playable == null) return null;
		return getEntitiesAsPlayable(players.get(player));
	}
	
	public Set<Entity> getEntitiesAsSpectator(Player spec) {
		if (spectators.get(spec) == null)
			return null;
		return getEntitiesAsPlayable(spectators.get(spec));
	}
	
	public Set<Entity> getEntitiesAsPlayable(Playable playable) {
		Set<Entity> visibleEntities = new HashSet<Entity>();
		Rectangle vision = playable.getSightRectangle();
		synchronized (entities) {
			for (Entity entity : entities) {
				if (vision.intersects(0, 0, entity.getRectangle(), 0, 0) && (entity.isVisible() || entity==playable))
					visibleEntities.add(entity);
			}
		}
		return visibleEntities;
	}
	
	public int getEntityId(Player player) {
		Playable p = players.containsKey(player) ? players.get(player) :
			deadPlayers.containsKey(player) ? deadPlayers.get(player) :
			spectators.get(player);
		if (p == null)
			return -1;
		return p.getId();
	}
	
	public void onInput(Player player, Input input) {
		synchronized (players) {
			Playable playable = players.get(player);
			if (playable == null) return;
			playable.setMove(input.move);
			if (input.move)
				playable.setDirection(input.direction);
		}
	}
	
	public void onAttack(Player player, float direction) {
		synchronized (entities) {
			if (players.get(player) != null)
				players.get(player).attack(direction);
		}
	}
	
	public void onSpecialAttack(Player player, float direction) {
		synchronized (entities) {
			if (players.get(player) != null)
				players.get(player).specialAttack(direction);
		}
	}
	
	public void send(Playable playable, String message) {
		send(playable.getPlayer(), message);
	}
	
	public void sendHealth(Playable playable, int health) {
		send(playable, "health " + health);
	}
	
	public void sendEnergy(Playable playable, int energy) {
		send(playable, "energy " + energy);
	}
	
	public void sendSpecial(Playable playable, int special) {
		send(playable, "special " + special);
	}

	public void onAddingEffect(Playable playable, Effect effect) {
		send(playable, "effect {\"name\":\""+effect.getName()+"\", \"duration\":"+effect.getDuration()+"}");
	}
	
	public void onDamageGiven(Playable attacker, Entity victim, int damage) {
		send(attacker, "damagegiven {\"entityId\":"+victim.getId()+", \"damage\":"+damage+"}");
	}
	
}
