package fr.ptlc.SGServer.game;

public enum Capacity {
	
	//----Guerrier
	coupFort("Coup fort", "fait une attaque chargé, plus elle est chargée plus elle inflige de dégâts", "coup-fort"), // charge + plus de dégats +20%
	coupDeBouclier("Coup de bouclier", "donne un coup de bouclier qui étourdit l'adversaire", "coup-de-bouclier"), // stun
	parade("Parade", "devient plus résistant mais plus faible pendant un certain temps", "parade"), // resistance*2 + faiblesse (+ vitesse diminue ?)
	berserker("Berserker", "devient plus fort et rapide mais plus fragile pendant un certain temps", "berserker"), // force*2 + vitesse*1.5 + fragilite*2 (+ stun ?)
	//----Archer
	flecheEmpoisonnee("Flèche empoisonnée", "envoie une flèche donnant un effet de poison pendant un certain temps", "fleche-empoisonnee"), // effet de poison
	sniping("Sniping", "fait un tir chargeur qui inflige de gros dégâts", "sniping"), // plus de dégâts ?
	tracking("Trackage", "voit deux fois plus loin pendant un certain temps", "tracking"), // recherche de joueurs dans un  certain rayon
	piege("Piège", "pose un piège au sol qui explose en infligeant 3 dégâts (maximum : 3)", "piege"), // entité persistente piege + dégâts (max: 3)
	// + fleche qui follow ?
	//----Assassin
	capeDInvisibilite("Cape d'invisibilité", "devient invisible pendant un certain temps, toute attaque annule l'invisibilité", "cape-d-invisibilite"), // invisibilité
	intangibilite("Intangibilité", "peut traverser les murs pendant un certain temps", "intangibilite"), // pas de collision avec les murs
	guillotine("Guillotine", "dash sur un adversaire et lui inflige de grands dégâts", "guillotine"), // dash + plus de dégats
	lameEmpoisonnee("Lame empoisonnée", "empoisonne sa lame donnant ainsi un effet de poison pendant un certain temps", "lame-empoisonnee"), // effet de poison
	//----Mage
	bouleDeFeu("Boule de feu", "envoie une boule de feu qui inflige de grands dégâts", "boule-de-feu"), // + de dégats
	bouleDeGlace("Boule de glace", "envoie une boule de glace qui inflige des dégâts et ralenti", "boule-de-glace"), // ralentissement
	merDeFlammes("Mer de flammes", "fait apparaître une zone enflammée qui inflige des dégâts", "mer-de-flammes"), // entité ephemere + dégats
	terreGlacee("Terre glacée", "fait apparaître une zone de froid qui ralenti", "terre-glacee"), // entité ephemere + ralentissement
	//----Golem
	corpsDAdamantium("Corps d'''adamantium''", "", "corps-d-adamantium"), // aucun dégats + ?
	murDePierre("Mur de pierre", "", "mur-de-pierre"), // blocage projectile ?
	seisme("Séisme", "", "seisme"), // zone de dégats, entité ephemere
	regenerationDeGaia("Régénération de Gaïa", "", "regeneration-de-gaia"), // regen + immobilité ?
	//----Nécromancien
	invocSqueletteArcher("Invocation d'un squelette archer", "", "invoc-archer"), // invocation d'un Playable archer + IA
	invocGolemDeChair("Invocation d'un golem de chair", "", "invoc-golem"), // invocation d'un Playable golem + IA
	invocZombieOrcGuerrier("Invocation d'un zombie-orc guerrier", "", "invoc-guerrier"), // invocation d'un Playable guerrier + IA
	invocEspritFrappeurVengeur("Invocation d'un esprit frappeur/vengeur", "", "invoc-assassin"), // invocation d'un Playable assassin + IA
	//----Alchimiste
	
	
	
	
	//----
	nullCapacity("", "", "");
	
	public final String name;
	public final String description;
	public final int cost;
	public final String imageName;
	private final transient String JSONStats;
	
	Capacity(String name, String description, String imageName) {
		this.name = name;
		this.description = description;
		this.cost = 1000;
		this.imageName = imageName;
		this.JSONStats = "{\"name\":\""+name+"\", \"description\":\""+description.replace("\"", "\\\"")+"\", \"cost\":\""+cost+"\", \"imageName\":\""+imageName+"\"}";
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public String getJSONStats() {
		return JSONStats;
	}
	
}
