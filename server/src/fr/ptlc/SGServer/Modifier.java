package fr.ptlc.SGServer;

public class Modifier {
	
	private final Modif modif;
	
	private final float value;
	
	/**
	 * @param modif
	 * @param value, a float superior to 0f
	 */
	public Modifier(Modif modif, float value) {
		this.modif = modif;
		this.value = value;
	}
	
	public Modif getModif() {
		return modif;
	}
	
	public float getValue() {
		return value;
	}
	
	public enum Modif {
		
		HEALTH_BONUS,
		ENERGY_SUPPLY,
		ENERGY_BOOST,
		RESISTANCE,
		ELECTRIC_RESISTANCE,
		FIRE_RESISTANCE,
		FROST_RESISTANCE,
		PROJECTILE_RESISTANCE,
		CHIMIC_RESISTANCE,
		PHYSIC_RESISTANCE,
		MAGIC_RESISTANCE,
		SIGHT_BONUS,
		SPEED,
		PHYSIC_DAMAGE_RETURN,
		MAGIC_DAMAGE_RETURN,
		GLIDE,
		// ----
		ANTI_STUN_ATTACK,
		ANTI_STUN_MOVE,
		FINAL_EXPLOSION;
	}

}
