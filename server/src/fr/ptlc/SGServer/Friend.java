package fr.ptlc.SGServer;

public class Friend {
	
	private String name;
	
	private boolean connected;
	
	private int xp;
	
	private int elo;
	
	public Friend(String name, boolean connected, int xp, int elo) {
		this.name = name;
		this.connected = connected;
		this.xp = xp;
		this.elo = elo;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public int getXp() {
		return xp;
	}
	
	public int getElo() {
		return elo;
	}
	
}
