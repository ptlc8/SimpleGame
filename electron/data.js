const prebuiltMaps = [
	{teams:["Equipe bleue","Equipe rouge","Equipe verte","Equipe jaune"],pattern:[[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,1,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,2],[2,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,1,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]]},
	{teams:["Equipe jaune","Equipe magenta"],pattern:[[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,11,0,0,1,0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,9,2,8,0,0,9,2,2,2,8,0,0,0,0,0,0,9,8,0,0,9,2,2,2,2,8,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,2,2,8,0,0,9,2,2,7,0,0,10,2,2,2,2,11,0,0,2],[2,0,0,10,2,11,0,0,10,2,2,2,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,9,2,8,0,0,9,8,0,0,10,2,11,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,10,2,11,0,0,10,11,0,0,9,2,8,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,2,2,2,8,0,0,9,2,2,2,2,2,2],[2,0,0,9,2,8,0,0,9,2,2,2,2,2,2,0,0,10,2,2,2,2,11,0,0,10,2,2,2,2,2,2],[2,0,0,10,2,11,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,10,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,8,0,0,0,9,2,2,2,2,2,2,2,8,0,0,0,2],[2,2,2,8,0,0,0,0,0,0,0,0,2,2,2,11,0,0,0,10,2,2,2,2,2,2,2,2,0,0,0,2],[2,2,2,11,0,0,0,0,5,4,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,10,11,0,0,0,2],[2,0,0,0,0,0,0,0,6,7,0,0,10,2,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,2,8,0,0,5,4,0,0,0,0,0,0,0,2],[2,0,0,0,9,8,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,6,7,0,0,0,0,9,2,2,2],[2,0,0,0,2,2,2,2,2,2,2,2,8,0,0,0,9,2,2,2,0,0,0,0,0,0,0,0,10,2,2,2],[2,0,0,0,10,2,2,2,2,2,2,2,11,0,0,0,10,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,8,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,9,2,8,0,0,2],[2,2,2,2,2,2,8,0,0,9,2,2,2,2,8,0,0,2,2,2,2,2,2,11,0,0,10,2,11,0,0,2],[2,2,2,2,2,2,11,0,0,10,2,2,2,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,10,2,11,0,0,9,8,0,0,9,2,8,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,9,2,8,0,0,10,11,0,0,10,2,11,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,2,2,2,8,0,0,9,2,8,0,0,2],[2,0,0,9,2,2,2,2,8,0,0,9,2,2,11,0,0,10,2,2,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,10,2,2,2,2,11,0,0,10,11,0,0,0,0,0,0,10,2,2,2,11,0,0,10,2,11,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,2,2,2,2,2,2,2,2,2,2,2,8,0,0,0,1,0,0,9,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]]}
];

const CLASSES = {
	guerrier:{name:'Guerrier', type:'PHYSIC', stats:[{name:'Vie', value:'120'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Épée'}, {name:'Énergie', value:'1200'}, {name:'Énergie par attaque', value:'800'}]},
	archer:{name:'Archer', type:'DISTANCE', stats:[{name:'Vie', value:'60'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Arc'}, {name:'Énergie', value:'1200'}, {name:'Énergie par attaque', value:'800'}]},
	golem:{name:'Golem', type:'NATURAL', stats:[{name:'Vie', value:'180'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Poing'}, {name:'Énergie', value:'1800'}, {name:'Énergie par attaque', value:'1200'}]},
	mage:{name:'Mage', type:'MAGIC', stats:[{name:'Vie', value:'80'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Bâton magique'}, {name:'Énergie', value:'2400'}, {name:'Énergie par attaque', value:'800'}]},
	assassin:{name:'Assassin NE PAS UTILISER', type:'BLADE', stats:[{name:'Vie', value:'80'}, {name:'Vitesse', value:'6'}, {name:'Arme', value:'Dague'}, {name:'Énergie', value:'800'}, {name:'Énergie par attaque', value:'500'}]},
	necromancien:{name:'Nécromancien', type:'NECROMANTIC', stats:[{name:'Vie', value:'60'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Sbires'}, {name:'Énergie', value:'4800'}, {name:'Énergie par invocation', value:'4800'}]},
	alchimiste:{name:'Alchimiste NE PAS UTILISER', type:'CHIMIC', stats:[{name:'Vie', value:'70'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Soon ?'}, {name:'Énergie', value:'1200'}, {name:'Énergie par attaque', value:'800'}]}
}

const GAMEMODES = {
	CLASSIC:{name:'Mode classique', description:'{teamsNb} équipes de {membersNb} joueurs s\'affrontent dans une arène, le but : être la dernière équipe en vie.'},
	REBRAWL:{name:'Mode Rebrawl', description:'Votre équipe de {membersNb} joueurs a peur de perdre un round ? Ce mode de jeu vous permet de faire un match en 3 manches. La première équipe des {teamsNb} à 2 points gagne le match.'},
	CTP:{name:'Mode capture de point', description:''},
	INVASION:{name:'Mode invasion', description:'Une seule équipe de {membersNb} joueurs ! Affrontez des vagues d\'envahisseurs en coopération, combien de vagues tiendrez-vous ?'},
	TEST:{name:'Test', description:'Bah oui, il faut bien faire des tests pour être sûr que ça fonctionne. ¯\\_(ツ)_/¯ <br />{teamsNb} équipes de {membersNb} joueurs.'}
};

const ITEMS = {
	magic_powder:{name:'Poudre magique', description:'Vive la poudre magique ! Elle apporte bonheur, joie et snnnniiiiiiffffffff... *u*', isItem:false},
	amethyst:{name:'Améthyste', description:'♥u♥ !', isItem:false},
	ice:{name:'Glace', description:'Brrrrrrrrrrrrrrrrrrrrrrrr...', isItem:false},
	void_bottle:{name:'Fiole de vide', description:'Grande fiole qui contient le vide', isItem:false},
	gold:{name:'Or', description:'', isItem:false},
	
	invisibility_cloak:{name:'Cape d\'invisibilité', description:'Pour se rendre invisible de l\'ennemi', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Durée', value:'8sec'}]},
	shield:{name:'Bouclier', description:'', isItem:true, slot:'hand', type:'PHYSIC', stats:[{name:'Durée de stun', value:'2sec'}, {name:'Dégâts', value:'20'}]},
	//intangibility_ring:{name:'Anneau d\'intangibilité', description:'', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Durée', value:'?'}]},
	explosive_trap:{name:'Piège explosif', description:'', isItem:true, slot:'hand', type:'EXPLOSIVE', stats:[{name:'Dégâts', value:'30'}, {name:'Limite', value:'3'}]},
	poison_trap:{name:'Piège à poison', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Durée', value:'3sec'}, {name:'Dégâts par seconde', value:'10'}]},
	//applicable_poison:{name:'Poison applicable', description:'Marre de devoir jeter du poison ? Appliquez-le désormais sur votre arme principale !', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Durée', value:'3sec'}, {name:'Dégâts par seconde', value:'10'}]},
	throwable_poison:{name:'Poison jetable', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Durée', value:'3sec'}, {name:'Dégâts par seconde', value:'10'}]},
	//crystal_ball:{name:'Boule de cristal', description:'', isItem:true, slot:'hand', type:'MAGIC', stats:[]},
	tracking:{name:'Trackage', description:'', isItem:true, slot:'hand', type:'ELECTRIC', stats:[]},
	//throwable_frost:{name:'Gel jetable', description:'', isItem:true, slot:'hand', type:'FROST', stats:[]},
	//applicable_frost:{name:'Gel applicable', description:'', isItem:true, slot:'hand', type:'FROST', stats:[]},
	molotov_cocktail:{name:'Cocktail Molotov', description:'', isItem:true, slot:'hand', type:'FIRE', stats:[]},
	stone_wall:{name:'Mur de pierres', description:'', isItem:true, slot:'hand', type:'PHYSIC', stats:[]},
	healing_potion:{name:'Potion de soin', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Soin', value:'30'}]},
	dynamite:{name:'Dynamite', description:'', isItem:true, slot:'hand', type:'EXPLOSIVE', stats:[]},
	throwable_slowness:{name:'Ralentissement jetable', description:'', isItem:true, slot:'hand', type:'FROST', stats:[]},
	//applicable_slowness:{name:'Ralentissement applicable', description:'', isItem:true, slot:'hand', type:'FROST', stats:[]},
	healing_ring:{name:'Anneau de guérison', description:'', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Soin', value:'30'}]},
	//laser:{name:'Laser', description:'', isItem:true, slot:'hand', type:'ELECTRIC', stats:[]},
	teleportation:{name:'Téléportation', description:'', isItem:true, slot:'hand', type:'DISTANCE', stats:[]},
	taser:{name:'Taser', description:'', isItem:true, slot:'hand', type:'ELECTRIC', stats:[{name:'Dégâts', value:'30'}, {name:'Durée de stun', value:'1sec'}]},
	smoke:{name:'Fumigène', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[]},
	orange_juice:{name:'Jus d\'orange', description:'', isItem:true, slot:'hand', type:'NATURAL', stats:[{name:'Soin', value:'30'}]},
	sismic_glove:{name:'Gant sismique', description:'Permet de provoquer des séismes', isItem:true, slot:'hand', type:'PHYSIC', stats:[{name:'Durée', value:'4sec'}, {name:'Dégats par seconde', value:10}]},
	
	casque_radar:{name:'Casque radar', description:'', isItem:true, slot:'head', modifiers:[{id:'SIGHT_BONUS', value:0.2}]},
	horn:{name:'Corne de licorne', description:'', isItem:true, slot:'head', modifiers:[{id:'MAGIC_RESISTANCE', value:0.2}]},
	magic_hat:{name:'Chapeau magique', description:'', isItem:true, slot:'head', modifiers:[]},
	big_heart:{name:'Grand coeur', description:'', isItem:true, slot:'chest', modifiers:[{id:'HEALTH_BONUS', value:0.2}]},
	//jetpack:{name:'Jetpack', description:'', isItem:true, slot:'chest', modifiers:[]}, // Slot.hand ???
	dynamite_belt:{name:'Ceinture de dynamite', description:'', isItem:true, slot:'chest', modifiers:[{id:'FINAL_EXPLOSION'}]},
	energy_pump:{name:'Pompe à énergie', description:'', isItem:true, slot:'chest', modifiers:[{id:'ENERGY_BOOST', value:0.2}]},
	energy_tank:{name:'Réservoir à énergie', description:'', isItem:true, slot:'chest', modifiers:[{id:'ENERGY_SUPPLY', value:0.2}]},
	hot_water_chestplate:{name:'Plastron bouillote', description:'', isItem:true, slot:'chest', modifiers:[{id:'FROST_RESISTANCE', value:0.2}]},
	cape:{name:'Cape', description:'', isItem:true, slot:'chest', modifiers:[{id:'SPEED', value:0.1}]},
	celerity_boots:{name:'Bottes de célérité', description:'', isItem:true, slot:'foot', modifiers:[{id:'SPEED', value:0.2}]},
	//ice_boots:{name:'Bottes de glace', description:'', isItem:true, slot:'foot', modifiers:[{id:'GLIDE', value:0.4}]},
	amethyst_helmet:{name:'Casque en améthyste', description:'', isItem:true, slot:'head', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	amethyst_chestplate:{name:'Plastron en améthyste', description:'', isItem:true, slot:'chest', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	amethyst_leggings:{name:'Jambières en améthyste', description:'', isItem:true, slot:'legs', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.15}]},
	amethyst_boots:{name:'Bottes en améthyste', description:'', isItem:true, slot:'foot', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	iron_helmet:{name:'Casque en fer', description:'La base d\'un bon guerrier : un bon casque, résistant et protecteur', isItem:true, slot:'head', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	iron_chestplate:{name:'Plastron en fer', description:'', isItem:true, slot:'chest', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	iron_leggings:{name:'Jambières en fer', description:'', isItem:true, slot:'legs', modifiers:[{id:'SPEED', value:-0.08}, {id:'RESISTANCE', value:0.1}]},
	iron_boots:{name:'Bottes en fer', description:'', isItem:true, slot:'foot', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	aluminium_helmet:{name:'Casque en aluminium', description:'', isItem:true, slot:'head', modifiers:[{id:'RESISTANCE', value:0.05}]},
	aluminium_chestplate:{name:'Plastron en aluminium', description:'', isItem:true, slot:'chest', modifiers:[{id:'RESISTANCE', value:0.05}]},
	aluminium_leggings:{name:'Jambières en aluminium', description:'', isItem:true, slot:'legs', modifiers:[{id:'RESISTANCE', value:0.05}, {id:'SPEED', value:-0.02}]},
	aluminium_boots:{name:'Bottes en aluminium', description:'', isItem:true, slot:'foot', modifiers:[{id:'RESISTANCE', value:0.05}]},
	caou_helmet:{name:'Bonnet en caoutchouc', description:'', isItem:true, slot:'head', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_chestplate:{name:'Veste en caoutchouc', description:'', isItem:true, slot:'chest', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_leggings:{name:'Pantalon en caoutchouc', description:'', isItem:true, slot:'legs', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_boots:{name:'Bottes en caoutchouc', description:'', isItem:true, slot:'foot', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	thorny_helmet:{name:'Casctus', description:'', isItem:true, slot:'head', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_chestplate:{name:'Plastron épineux', description:'', isItem:true, slot:'chest', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_leggings:{name:'Pantalon épineux', description:'', isItem:true, slot:'legs', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_boots:{name:'Bottes épineuses', description:'', isItem:true, slot:'foot', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	//casque_du_vide:{name:'', description:'', isItem:true, slot:'head', modifiers:[]},
	//plastron_du_vide:{name:'', description:'', isItem:true, slot:'chest', modifiers:[]},
	//jambières_du_vide:{name:'', description:'', isItem:true, slot:'legs', modifiers:[]},
	//bottes_du_vide:{name:'', description:'', isItem:true, slot:'foot', modifiers:[]},
	miror_ball:{name:'Boule à facettes', description:'', isItem:true, slot:'head', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.1}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	miror_chestplate:{name:'Plastron miroir', description:'', isItem:true, slot:'chest', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.1}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	miror_leggings:{name:'Jambières miroir', description:'', isItem:true, slot:'legs', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.05}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	miror_boots:{name:'Bottes miroir', description:'', isItem:true, slot:'foot', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.1}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	foam_helmet:{name:'Casque en mousse', description:'', isItem:true, slot:'head', modifiers:[{id:'PROJECTILE_RESISTANCE', value:0.3}, {id:'FIRE_RESISTANCE', value:-0.1}]},
	foam_chestplate:{name:'Plastron en mousse', description:'', isItem:true, slot:'chest', modifiers:[{id:'PROJECTILE_RESISTANCE', value:0.3}, {id:'FIRE_RESISTANCE', value:-0.1}]},
	foam_leggings:{name:'Jambes de mousse', description:'', isItem:true, slot:'legs', modifiers:[{id:'PROJECTILE_RESISTANCE', value:0.25}, {id:'FIRE_RESISTANCE', value:-0.1}]},
	foam_boots:{name:'Bottes en mousse', description:'', isItem:true, slot:'foot', modifiers:[{id:'PROJECTILE_RESISTANCE', value:0.3}, {id:'FIRE_RESISTANCE', value:-0.1}]},
	exoskeleton_arms:{name:'Bras exo-squelette', description:'', isItem:true, slot:'chest', modifiers:[{id:'ANTI_STUN_ATTACK'}]},
	exoskeleton_legs:{name:'Jambes exo-squelette (bas)', description:'', isItem:true, slot:'legs', modifiers:[{id:'ANTI_STUN_MOVE'}]}
	
};

const TYPES = {
	PHYSIC:{name:'Physique', color:'magenta'},
	DISTANCE:{name:'Projectile', color:'lime'},
	FROST:{name:'Froid', color:'lightblue'},
	FIRE:{name:'Feu', color:'orange'},
	ELECTRIC:{name:'Éléctrique', color:'yellow'},
	CHIMIC:{name:'Chimique', color:'purple'},
	EXPLOSIVE:{name:'Explosif', color:'red'},
	MAGIC:{name:'Magique', color:'cyan'},
	NATURAL:{name:'Naturel', color:'green'},
	NECROMANTIC:{name:'Nécromancie', color:'darkblue'},
	BLADE:{name:'Lame', color:'white'}
};

const MODIFIERS = {
	HEALTH_BONUS:{name:'Santé'},
	ENERGY_SUPPLY:{name:'Quantité d\'énergie'},
	ENERGY_BOOST:{name:'Recharge d\'énergie'},
	RESISTANCE:{name:'Résistance'},
	ELECTRIC_RESISTANCE:{name:'Résistance éléctrique'},
	FIRE_RESISTANCE:{name:'Résistance au feu'},
	FROST_RESISTANCE:{name:'Résistance au froid'},
	PROJECTILE_RESISTANCE:{name:'Résistance aux projectiles'},
	CHIMIC_RESISTANCE:{name:'Résistance chimique'},
	PHYSIC_RESISTANCE:{name:'Résistance physique'},
	MAGIC_RESISTANCE:{name:'Résistance magique'},
	SIGHT_BONUS:{name:'Visibilité'},
	SPEED:{name:'Vitesse'},
	PHYSIC_DAMAGE_RETURN:{name:'Dégâts physiques retournés'},
	MAGIC_DAMAGE_RETURN:{name:'Dégâts magiques retournés'},
	GLIDE:{name:'Glisse'},
	// ----
	ANTI_STUN_ATTACK:{name:'Anti-stun d\'attaque'},
	ANTI_STUN_MOVE:{name:'Anti-stun de déplacement'},
	FINAL_EXPLOSION:{name:'Explosion finale'}
};
