package fr.ptlc.SGServer.game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.ShapesGroup;

public class GameMap {
	
	private static final transient Hitbox[] hitboxes = getHitboxes("hitboxes/map.json");
	
	//0-: void, 1: spawn, 2: wall, 3+: diverse walls
	private short[][] pattern;
	
	public GameMap(short[][] pattern) {
		this.pattern = pattern;
	}
	
	public short[][] getPattern() {
		return pattern;
	}
	
	public int getWidth() {
		return pattern.length==0 ? 0 :pattern[0].length;
	}
	
	public int getHeight() {
		return pattern.length;
	}
	
	public int getSpawnNumber() {
		if (pattern == null)return 0;
		int n = 0; 
		for (short[] l : pattern)
			for (short c : l)
				if (c == 1)
					n++;
		return n;
	}
	
	public boolean isValid() {
		return pattern != null;
	}
	
	public Point getSpawn(int index) {
		for (int i = 0; i < pattern.length; i++)
			for (int j = 0; j < pattern[i].length; j++)
				if (pattern[i][j] == 1) // 1: spawn
					if (index-- == 0)
						return new Point(j*64+32, i*64+32);
		return new Point(0, 0);
	}
	
	public Hitbox getHitbox(int x, int y) {
		try {
			return hitboxes[pattern[y][x]];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private static Hitbox[] getHitboxes(String fileName) {
		try {
			System.out.println("Récupération des hitboxes de cartes de jeu...");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new Circle().getClass().getClassLoader().getResourceAsStream(fileName)));
			List<Hitbox> hitboxes = new ArrayList<Hitbox>();
			String line;
			hitboxes.add(new ShapesGroup());
			while ((line = br.readLine()) != null)
				hitboxes.add(ShapesGroup.getFromJSON(line));
			br.close();
			System.out.println("Hitboxes de cartes de jeu récupérées");
			return hitboxes.toArray(new Hitbox[0]);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
