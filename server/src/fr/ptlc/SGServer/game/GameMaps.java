package fr.ptlc.SGServer.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

public class GameMaps {
	
	private static final Map<Integer, List<GameMap>> maps = loadMaps("maps.json");
	
	private static Map<Integer, List<GameMap>> loadMaps(String fileName) {
		System.out.println("Chargement des cartes de jeu depuis " + fileName + "...");
		try {
			HashMap<Integer, List<GameMap>> maps = new HashMap<Integer, List<GameMap>>();
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String json = "";
			int n = 0;
			for (String line; (line = br.readLine()) != null;) {
				if (line.startsWith("#")) continue;
				if (line.startsWith("<-")) {
					json = line.substring(2);
				} else if (line.startsWith("->")) {
					GameMap map = new GameMap(new Gson().fromJson(json, short[][].class));
					int spawnNumber = map.getSpawnNumber();
					if (spawnNumber == 0) continue;
					if (!maps.containsKey(spawnNumber)) maps.put(spawnNumber, new ArrayList<GameMap>());
					maps.get(spawnNumber).add(map);
					n++;
				}
				else json += line;
			}
			br.close();
			System.out.println(n + " cartes de jeu ont été importées");
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void preload() {}
	
	public static GameMap getRandomMap(int spawnNumber) {
		if (!maps.containsKey(spawnNumber) || maps.get(spawnNumber).size() == 0) return null;
		return maps.get(spawnNumber).get(new Random().nextInt(maps.get(spawnNumber).size()));
	}
	
}
