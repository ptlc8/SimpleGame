package fr.ptlc.SGServer.game;

public enum Capacity {
	
	//----Guerrier
	coupFort("Coup fort", "fait une attaque charg�, plus elle est charg�e plus elle inflige de d�g�ts", "coup-fort"), // charge + plus de d�gats +20%
	coupDeBouclier("Coup de bouclier", "donne un coup de bouclier qui �tourdit l'adversaire", "coup-de-bouclier"), // stun
	parade("Parade", "devient plus r�sistant mais plus faible pendant un certain temps", "parade"), // resistance*2 + faiblesse (+ vitesse diminue ?)
	berserker("Berserker", "devient plus fort et rapide mais plus fragile pendant un certain temps", "berserker"), // force*2 + vitesse*1.5 + fragilite*2 (+ stun ?)
	//----Archer
	flecheEmpoisonnee("Fl�che empoisonn�e", "envoie une fl�che donnant un effet de poison pendant un certain temps", "fleche-empoisonnee"), // effet de poison
	sniping("Sniping", "fait un tir chargeur qui inflige de gros d�g�ts", "sniping"), // plus de d�g�ts ?
	tracking("Trackage", "voit deux fois plus loin pendant un certain temps", "tracking"), // recherche de joueurs dans un  certain rayon
	piege("Pi�ge", "pose un pi�ge au sol qui explose en infligeant 3 d�g�ts (maximum : 3)", "piege"), // entit� persistente piege + d�g�ts (max: 3)
	// + fleche qui follow ?
	//----Assassin
	capeDInvisibilite("Cape d'invisibilit�", "devient invisible pendant un certain temps, toute attaque annule l'invisibilit�", "cape-d-invisibilite"), // invisibilit�
	intangibilite("Intangibilit�", "peut traverser les murs pendant un certain temps", "intangibilite"), // pas de collision avec les murs
	guillotine("Guillotine", "dash sur un adversaire et lui inflige de grands d�g�ts", "guillotine"), // dash + plus de d�gats
	lameEmpoisonnee("Lame empoisonn�e", "empoisonne sa lame donnant ainsi un effet de poison pendant un certain temps", "lame-empoisonnee"), // effet de poison
	//----Mage
	bouleDeFeu("Boule de feu", "envoie une boule de feu qui inflige de grands d�g�ts", "boule-de-feu"), // + de d�gats
	bouleDeGlace("Boule de glace", "envoie une boule de glace qui inflige des d�g�ts et ralenti", "boule-de-glace"), // ralentissement
	merDeFlammes("Mer de flammes", "fait appara�tre une zone enflamm�e qui inflige des d�g�ts", "mer-de-flammes"), // entit� ephemere + d�gats
	terreGlacee("Terre glac�e", "fait appara�tre une zone de froid qui ralenti", "terre-glacee"), // entit� ephemere + ralentissement
	//----Golem
	corpsDAdamantium("Corps d'''adamantium''", "", "corps-d-adamantium"), // aucun d�gats + ?
	murDePierre("Mur de pierre", "", "mur-de-pierre"), // blocage projectile ?
	seisme("S�isme", "", "seisme"), // zone de d�gats, entit� ephemere
	regenerationDeGaia("R�g�n�ration de Ga�a", "", "regeneration-de-gaia"), // regen + immobilit� ?
	//----N�cromancien
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
