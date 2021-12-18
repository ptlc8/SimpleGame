package fr.ptlc.SGServer.game;

import java.awt.Point;
import java.util.Random;

public class Team {
	
	private static final String[] colors = {"#FFFFFF", "#FF0000", "#FFFF00", "#00FF00", "#00FFFF", "#0000FF", "#FF00FF", "#000000"};
	
	private final String name;
	private final String color;
	
	private int score;
	private transient Point spawn;
	
	private transient int elo = 0;
	
	private boolean isDraw = false;
	private boolean isSpecial = false;
	
	public Team(String name, Point spawn) {
		this.name = name;
		this.color = colors[new Random().nextInt(colors.length)];
		this.spawn = spawn;
		score = 0;
	}
	
	public static Team SpecialTeam(String name, Point spawn) {
		Team spTeam = new Team(name, spawn);
		spTeam.isSpecial = true;
		return spTeam;
	}
	
	public static Team Draw() {
		Team draw = new Team("", null);
		draw.isDraw = true;
		return draw;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public Point getSpawn() {
		return spawn;
	}
	
	public int getScore() {
		return score;
	}
	
	public void increaseScore() {
		score++;
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public int getElo() {
		return elo;
	}
	
	public void setElo(int elo) {
		this.elo = elo;
	}
	
	public boolean equals(String name) {
		return !isSpecial && this.name.equals(name);
	}
	
	public boolean isDraw() {
		return isDraw;
	}
	
	public boolean isSpecial() {
		return isSpecial;
	}
	
}
