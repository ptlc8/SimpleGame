package fr.ptlc.SGServer.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.ptlc.SGServer.Classs;
import fr.ptlc.SGServer.Explosion;
import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.Item;
import fr.ptlc.SGServer.Modifier;
import fr.ptlc.SGServer.Player;
import fr.ptlc.SGServer.effects.BerserkerEffect;
import fr.ptlc.SGServer.effects.Effect;
import fr.ptlc.SGServer.effects.Invisibility;
import fr.ptlc.SGServer.effects.ParryEffect;
import fr.ptlc.SGServer.effects.Poison;
import fr.ptlc.SGServer.effects.Tracking;
import fr.ptlc.SGServer.game.LadderGame;
import fr.ptlc.SGServer.game.Type;
import fr.ptlc.SGServer.game.Capacity;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.game.Team;
import fr.ptlc.SGServer.hitboxes.Circle;
import fr.ptlc.SGServer.hitboxes.DoubleShape;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public class Playable extends Entity {
	
	public static final Hitbox hitbox = new DoubleShape(new Rectangle(-48,-48,96,96), new Circle(0, 32, 16));
	
	private final Player player;
	private final Team team;
	
	protected final Classs classs;
	private final Capacity capacity;
	private final Item handItem;
	
	private int health;
	private int healthMax;
	
	private float speed;
	private int sightDistance = 400;
	
	protected int energy;
	private int energyMax;
	protected final int attackCost;
	
	private int special;
	private final int specialMax;
	
	private boolean move;
	private float direction; //PI.rad
	
	private boolean canRestoreEnergy = true;
	
	//---- only for minions and traps
	private final List<Entity> possessions;
	
	//---- effects & modifiers
	private Set<Effect> effects = new HashSet<Effect>();
	private float strength = 1f;
	private float resistance = 1f;
	private Map<Type, Float> resistances = new HashMap<>();
	private Map<Type, Float> damageReturns = new HashMap<>();
	private boolean canMove = true;
	private boolean canAttack = true;
	private boolean ignoreStunMove = false;
	private boolean ignoreStunAttack = false;
	private boolean ignoreMapAndTangibles = false;
	private Effect applicableEffect = null;
	private boolean finalExplosion = false;
	
	public Playable(Player player, GameManager gm, float x, float y, Classs classs, int capacityN, List<Modifier> modifiers, Item handItem) {
		super(gm, x, y, hitbox, new Image(classs.imageName, "-right"));
		this.player = player;
		this.team = player.getTeam();
		this.classs = classs;
		this.capacity = classs.getCapacity(capacityN);
		this.handItem = handItem;
		this.healthMax = classs.health;
		this.speed = classs.speed;
		this.energyMax = classs.energy;
		this.attackCost = classs.attackCost;
		this.specialMax = capacity.cost;
		move = false;
		direction = 0f;
		possessions = new ArrayList<Entity>(3);
		
		for (Type type : Type.values()) {
			this.resistances.put(type, 1f);
			this.damageReturns.put(type, 0f);
		}
		if (modifiers != null) for (Modifier modifier : modifiers) {
			switch (modifier.getModif()) {
			case HEALTH_BONUS:
				healthMax += classs.health * modifier.getValue();
				break;
			case GLIDE:
				// possible ???
				break;
			case ENERGY_BOOST:
				
				break;
			case ENERGY_SUPPLY:
				energyMax += classs.energy * modifier.getValue();
				break;
			case RESISTANCE:
				resistance += 1 * modifier.getValue();
				break;
			case FIRE_RESISTANCE:
				resistances.put(Type.FIRE, resistances.get(Type.FIRE) + 1 * modifier.getValue());
				break;
			case FROST_RESISTANCE:
				resistances.put(Type.FROST, resistances.get(Type.FROST) + 1 * modifier.getValue());
				break;
			case ELECTRIC_RESISTANCE:
				resistances.put(Type.ELECTRIC, resistances.get(Type.ELECTRIC) + 1 * modifier.getValue());
				break;
			case SPEED:
				speed += classs.speed * modifier.getValue();
				break;
			case ANTI_STUN_ATTACK:
				ignoreStunAttack = true;
				break;
			case ANTI_STUN_MOVE:
				ignoreStunMove = true;
				break;
			case CHIMIC_RESISTANCE:
				resistances.put(Type.CHIMIC, resistances.get(Type.CHIMIC) + 1 * modifier.getValue());
				break;
			case FINAL_EXPLOSION:
				finalExplosion = true;
				break;
			case MAGIC_DAMAGE_RETURN:
				damageReturns.put(Type.MAGIC, damageReturns.get(Type.MAGIC) + 1 * modifier.getValue());
				break;
			case MAGIC_RESISTANCE:
				resistances.put(Type.MAGIC, resistances.get(Type.MAGIC) + 1 * modifier.getValue());
				break;
			case PHYSIC_DAMAGE_RETURN:
				damageReturns.put(Type.PHYSIC, damageReturns.get(Type.PHYSIC) + 1 * modifier.getValue());
				break;
			case PHYSIC_RESISTANCE:
				resistances.put(Type.MAGIC, resistances.get(Type.MAGIC) + 1 * modifier.getValue());
				break;
			case PROJECTILE_RESISTANCE:
				resistances.put(Type.DISTANCE, resistances.get(Type.DISTANCE) + 1 * modifier.getValue());
				break;
			case SIGHT_BONUS:
				sightDistance += 800 * modifier.getValue();
				break;
			}
		}
		this.health = this.healthMax;
		this.energy = this.energyMax;
		this.special = 0;
	}
	
	public Playable(Player player, GameManager gm, Point pos, Classs classs, int capacityN, List<Modifier> modifiers, Item handItem) {
		this(player, gm, pos.x, pos.y, classs, capacityN, modifiers, handItem);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public Classs getClasss() {
		return classs;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getHealthMax() {
		return healthMax;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getEnergyMax() {
		return energyMax;
	}
	
	public int getSpecial() {
		return special;
	}
	
	public int getSpecialMax() {
		return specialMax;
	}
	
	public void setMove(boolean move) {
		this.move = move;
	}
	
	public void setDirection(float direction) {
		this.direction = direction;
	}
	
	protected void setEnergyRestore(boolean canRestoreEnergy) {
		this.canRestoreEnergy = canRestoreEnergy;
	}
	
	public Rectangle getSightRectangle() {
		int sight = sightDistance;
		return new Rectangle((int)x-sight, (int)y-sight, sight*2, sight*2);
	}
	
	@Override
	public void move() {
		if (!canMove && !ignoreStunMove) return;
		if (canRestoreEnergy && energy < energyMax) energy+=10;
		if (!(this instanceof Minion) && special < specialMax) special+=2;
		if (!move) return;
		float speedX = (float) (Math.cos(direction * Math.PI) * speed);
		float speedY = (float) (Math.sin(direction * Math.PI) * speed);
		if (ignoreMapAndTangibles || !testCollisionMapAndTangibles(speedX, 0f))
			x += speedX;
		if (ignoreMapAndTangibles || !testCollisionMapAndTangibles(0f, speedY))
			y += speedY;
		if (speedX > 0)
			image.suffix = "-right";
		if (speedX < 0)
			image.suffix = "-left";
	}
	
	@Override
	public void onDestroy() {
		if (finalExplosion)
			getGM().add(new Explosion(getGM(), x, y, this, 50));
	}
	
	public void takeDamage(Playable attacker, int damage, Type type) {
		if (attacker.getTeam().equals(team)) return; // si pas de friendly fire, psk les amis ne nous font pas mal exprès
		//System.out.println("---- playable"+getId()+" subit "+damage+" dégâts de playable"+attacker.getId());
		int totalDamage = (int)(damage / (resistances.get(type) * resistance));
		health -= totalDamage;
		getGM().onDamageGiven(attacker, this, totalDamage);
		if (damageReturns.get(type) != 0 && damage != 0)
			attacker.takeDamage(this, (int)(damage*damageReturns.get(type)), type);
		if (health <= 0) {
			destroy();
			getGM().onPlayableDie(attacker, this);
		}
		if (!(this instanceof Minion)) getGM().sendHealth(this, health);
	}
	
	public void heal(Playable healer, int damage, Type healType) {
		if (!healer.getTeam().equals(team)) return; // si pas de friendly fire, psk les ennemis ne nous soigne pas exprès
		health += damage / (resistances.get(healType) * resistance);
		if (health > healthMax) health = healthMax;
		if (damageReturns.get(healType) != 0 && damage != 0)
			healer.heal(this, (int)(damage*damageReturns.get(healType)), healType);
		if (!(this instanceof Minion)) getGM().sendHealth(this, health);
	}
	
	public void attack(float direction) {
		if (!canAttack && !ignoreStunAttack) return;
		if (energy < attackCost) return;
		if (!visible) visible = true;
		energy -= attackCost;
		direction *= Math.PI;
		switch (classs) {
		case guerrier:
			getGM().add(new Sword(getGM(), this, direction, (int)(30*strength)));
			break;
		case archer:
			getGM().add(new Arrow(getGM(), x, y, direction, this, (int)(20*strength)));
			break;
		case mage:
			getGM().add(new Spell(getGM(), x, y, direction, this, (int)(10*strength)));
			break;
		case assassin:
			getGM().add(new Dagger(getGM(), this, direction, (int)(20*strength)));
			break;
		case golem:
			getGM().add(new Punch(getGM(), direction, this, (int)(20*strength)));
			break;
		case necromancien:
			Minion minion;
			getGM().add(minion = new Minion(getGM(), x+(float)Math.cos(direction)*64f, y+(float)Math.sin(direction)*64f, this, Classs.guerrier));
			addPossession(minion);
			break;
		case alchimiste:
			
			break;
		default:
			break;
		}
		if (!(this instanceof Minion)) getGM().sendEnergy(this, energy);
	}
	
	protected boolean addPossession(Entity entity) {
		possessions.add(entity);
		if (possessions.size() > 3)
			possessions.remove(0).destroy();
		return true;
	}
	
	public void specialAttack(float direction) {
		if (!canAttack && !ignoreStunAttack) return;
		//int damage = (int) (classs.damage * strength);
		if (special < specialMax) return;
		if (!visible) visible = true;
		special = 0;
		if (getGM().getGame() instanceof LadderGame) {
			direction *= Math.PI;
			Trap trap;
			if (handItem != null) {
				float m = classs.type.equals(handItem.getType()) ? 1.5f : 1;
				switch (handItem) {
				case invisibility_cloak:
					new Invisibility(this, (int)(8*60*m)).apply(this, true);
					break;
				case shield:
					getGM().add(new Shield(getGM(), direction, this, (int)(2*60*m), (int)(20*m)));
					break;
				case explosive_trap:
					trap = new ExplosiveTrap(getGM(), x+(float)Math.cos(direction)*64f, y+(float)Math.sin(direction)*64f, this, (int)(30*m));
					addPossession(trap);
					getGM().add(trap);
					break;
				case tracking:
					new Tracking(this, (int)(6*60*m)).apply(this, true);
					break;
				case throwable_slowness:
					getGM().add(new SlownessArea(getGM(), x+(float)Math.cos(direction)*192f, y+(float)Math.sin(direction)*192f, this));
					break;
				case throwable_poison:
					getGM().add(new PoisonArea(getGM(), x+(float)Math.cos(direction)*192f, y+(float)Math.sin(direction)*192f, this));
					break;
				case molotov_cocktail:
					getGM().add(new BurnArea(getGM(), x+(float)Math.cos(direction)*192f, y+(float)Math.sin(direction)*192f, this));
					break;
				case stone_wall:
					getGM().add(new Wall(getGM(), x+(float)Math.cos(direction)*64f, y+(float)Math.sin(direction)*64f, this, (int)(10*60*m), (int)(10*m)));
					break;
				case poison_trap:
					trap = new PoisonTrap(getGM(), x+(float)Math.cos(direction)*64f, y+(float)Math.sin(direction)*64f, this, (int)(3*60*m));
					addPossession(trap);
					getGM().add(trap);
					break;
				case healing_potion:
					heal(this, (int)(30*m), Type.CHIMIC);
					break;
				case healing_ring:
					heal(this, (int)(30*m), Type.MAGIC);
					break;
				case orange_juice:
					heal(this, (int)(30*m), Type.NATURAL);
					break;
				case taser:
					getGM().add(new Taser(getGM(), direction, this, (int)(30*m), (int)(1*60*m)));
					break;
				case sismic_glove:
					System.out.println("sismi !");
					getGM().add(new Earthquake(getGM(), x, y, this, (int)(4*60*m), 6, 1));
					break;
				case dynamite:
					getGM().add(new Explosion(getGM(), x+(float)Math.cos(direction)*128f, y+(float)Math.sin(direction)*128f, this, 25));
					break;
				case teleportation:
					getGM().add(new Tp(getGM(), x, y, direction, this, (int)(5*m)));
					break;
				default:
					// do nothing
				}
			}
		}
		
		else switch (capacity) {
		case berserker:
			new BerserkerEffect(this, 6*60).apply(this, true);
			break;
		case bouleDeFeu:
			getGM().add(new Fireball(getGM(), x, y, direction, this, (int)(1*2*strength)));
			break;
		case bouleDeGlace:
			getGM().add(new Iceball(getGM(), x, y, direction, this, (int)(1*strength), 4*60));
			break;
		case capeDInvisibilite:
			new Invisibility(this, 8*60).apply(this, true);
			break;
		case corpsDAdamantium:
			
			break;
		case coupDeBouclier:
			getGM().add(new Shield(getGM(), direction, this, (int)(2*60*strength), 2));
			break;
		case coupFort:
			
			break;
		case flecheEmpoisonnee:
			getGM().add(new EffectArrow(getGM(), x, y, direction, this, (int)(2*strength), new Poison(this, 2*60)));
			break;
		case guillotine:
			
			break;
		case intangibilite:
			
			break;
		case invocEspritFrappeurVengeur:
			Minion minion = new Minion(getGM(), x+(float)Math.cos(direction*Math.PI)*64f, y+(float)Math.sin(direction*Math.PI)*64f, this, Classs.assassin);
			getGM().add(minion);
			addPossession(minion);
			break;
		case invocGolemDeChair:
			Minion minion2 = new Minion(getGM(), x+(float)Math.cos(direction*Math.PI)*64f, y+(float)Math.sin(direction*Math.PI)*64f, this, Classs.golem);
			getGM().add(minion2);
			addPossession(minion2);
			break;
		case invocSqueletteArcher:
			Minion minion3 = new Minion(getGM(), x+(float)Math.cos(direction*Math.PI)*64f, y+(float)Math.sin(direction*Math.PI)*64f, this, Classs.archer);
			getGM().add(minion3);
			addPossession(minion3);
			break;
		case invocZombieOrcGuerrier:
			Minion minion4 = new Minion(getGM(), x+(float)Math.cos(direction*Math.PI)*64f, y+(float)Math.sin(direction*Math.PI)*64f, this, Classs.guerrier);
			getGM().add(minion4);
			addPossession(minion4);
			break;
		case lameEmpoisonnee:
			getGM().add(new EffectDagger(getGM(), this, direction, (int)(2*strength), new Poison(this, 2*60)));
			break;
		case merDeFlammes:
			getGM().add(new FlamesSea(getGM(), (float)(x+Math.cos(direction*Math.PI)*192), (float)(y+Math.sin(direction*Math.PI)*192), this));
			break;
		case murDePierre:
			getGM().add(new Wall(getGM(), x+(float)Math.cos(direction*Math.PI)*64, y+(float)Math.sin(direction*Math.PI)*64, this, 10*60, 10));
			break;
		case parade:
			new ParryEffect(this, 6*60).apply(this, true);
			break;
		case piege:
			Trap trap = new ExplosiveTrap(getGM(), x+(float)Math.cos(direction*Math.PI)*64f, y+(float)Math.sin(direction*Math.PI)*64f, this, 3);
			getGM().add(trap);
			addPossession(trap);
			break;
		case regenerationDeGaia:
			
			break;
		case seisme:
			//getGM().add(new Earthquake(getGM(), x, y, this));
			break;
		case sniping:
			
			break;
		case terreGlacee:
			getGM().add(new SlownessArea(getGM(), (float)(x+Math.cos(direction*Math.PI)*192), (float)(y+Math.sin(direction*Math.PI)*192), this));
			break;
		case tracking:
			new Tracking(this, 6*60).apply(this, true);
			break;
		default:
			break;
		}
		if (!(this instanceof Minion)) getGM().sendSpecial(this, special);
	}
	
	public boolean addEffect(Effect effect) {
		boolean a = effects.add(effect);
		//if (a) System.out.println("---- ajout d'un effet à playable"+getId());
		if (a) getGM().onAddingEffect(this, effect);
		return a;
	}
	
	public boolean removeEffect(Effect effect) {
		boolean a = effects.remove(effect);
		//System.out.println("---- suppr d'un effet de playable"+getId());
		return a;
	}
	
	public void slowDown(float m) {
		speed /= m;
	}
	
	public void speedUp(float m) {
		speed *= m;
	}
	
	public void strength(float m) {
		strength *= m;
	}
	
	public void weakness(float m) {
		strength /= m;
	}
	
	public void resistance(float m) {
		resistance *= m;
	}
	
	public void fragility(float m) {
		resistance /= m;
	}
	
	public void tracking(float m) {
		sightDistance *= m;
	}
	
	public void blindness(float m) {
		sightDistance /= m;
	}
	
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
		this.canAttack = canMove;
	}
	
	public void setIgnoreMapAndTangibles(boolean ignoreMAT) {
		this.ignoreMapAndTangibles = ignoreMAT;
	}
	
	public boolean hasApplicableEffect() {
		return applicableEffect != null;
	}
	
	public Effect swallowApplicableEffect() {
		Effect aE = applicableEffect;
		applicableEffect = null;
		return aE;
	}
	
}
