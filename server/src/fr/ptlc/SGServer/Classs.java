package fr.ptlc.SGServer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import fr.ptlc.SGServer.game.Capacity;
import fr.ptlc.SGServer.game.Type;

public enum Classs {
	
	guerrier("Guerrier", Type.PHYSIC, 120, 4f, 1200, 800, "guerrier", Capacity.coupFort, Capacity.coupDeBouclier, Capacity.parade, Capacity.berserker),
	archer("Archer", Type.DISTANCE, 60, 4f, 1200, 800, "archer", Capacity.flecheEmpoisonnee, Capacity.sniping, Capacity.tracking, Capacity.piege),
	mage("Mage", Type.MAGIC, 80, 4f, 2400, 800, "mage", Capacity.bouleDeFeu, Capacity.bouleDeGlace, Capacity.merDeFlammes, Capacity.terreGlacee),
	assassin("Assassin", Type.BLADE, 80, 6f, 800, 500, "assassin", Capacity.capeDInvisibilite, Capacity.intangibilite, Capacity.guillotine, Capacity.lameEmpoisonnee),
	golem("Golem", Type.NATURAL, 180, 4f, 1800, 1200, "golem", Capacity.corpsDAdamantium, Capacity.murDePierre, Capacity.seisme, Capacity.regenerationDeGaia),
	necromancien("Nécromancien", Type.NATURAL, 60, 4f, 4800, 4800, "necromancien", Capacity.invocSqueletteArcher, Capacity.invocGolemDeChair, Capacity.invocZombieOrcGuerrier, Capacity.invocEspritFrappeurVengeur), 
	alchimiste("Alchimiste", Type.CHIMIC, 70, 4f, 1200, 800, "alchimiste", new Capacity[0]);
	
	public static final Set<String> names = getNames();
	
	public static Classs getClasssByName(String name) {
		if (name.equals("random")) return values()[(new Random().nextInt(values().length))];
		return valueOf(name);
	}
	
	public static String getJSONClasssStats(String name) {
		if (name.equals("random")) return "{\"name\":\"Aléatoire\", \"health\":\"???\", \"damage\":\"???\", \"speed\":\"???\", \"energy\":\"???\", \"attackCost\":\"???\", \"imageName\":\"random\", \"capacities\":[]}";
		return getClasssByName(name).getJSONStats();
	}
	
	public static Set<String> getNames() {
		Set<String> names = new HashSet<>();
		for (Classs c : values())
			names.add(c.name());
		names.add("random");
		return names;
	}
	
	public final String name;
	public final Type type;
	public final int health;
	public final float speed; // en pixel / frame (tps)
	public final int energy; // en 10 * frames (tps)
	public final int attackCost;
	public final String imageName;
	private final Capacity[] capacities;
	private final transient String JSONStats;
	
	Classs(String name, Type type, int health, float speed, int energy, int attackCost, String imageName, Capacity... capacities) {
		this.name = name;
		this.type = type;
		this.health = health;
		this.speed = speed;
		this.energy = energy;
		this.attackCost = attackCost;
		this.imageName = imageName;
		this.capacities = capacities;
		String JSONCapacities = "[";
		for (Capacity capa : capacities)
			JSONCapacities += capa.getJSONStats() + ",";
		if (capacities.length != 0)
			JSONCapacities = JSONCapacities.substring(0, JSONCapacities.length() - 1);
		JSONCapacities += "]";
		this.JSONStats = "{\"name\":\""+name+"\", \"type\":\""+type+"\", \"health\":\""+health+"\", \"speed\":\""+speed+"\", \"energy\":\""+energy+"\", \"attackCost\":\""+attackCost+"\", \"imageName\":\""+imageName+"\", \"capacities\":"+JSONCapacities+"}";

	}
	
	public Capacity getCapacity(int n) {
		if (0 <= n && n < capacities.length)
			return capacities[n];
		return Capacity.nullCapacity;
	}
	
	public String getJSONStats() {
		return JSONStats;
	}
	
}
