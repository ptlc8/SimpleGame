const prebuiltMaps = [
	{teams:["Equipe bleue","Equipe rouge","Equipe verte","Equipe jaune"],pattern:[[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,1,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,2],[2,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,0,0,0,0,0,0,2],[2,0,0,1,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]]},
	{teams:["Equipe jaune","Equipe magenta"],pattern:[[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,11,0,0,1,0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,9,2,8,0,0,9,2,2,2,8,0,0,0,0,0,0,9,8,0,0,9,2,2,2,2,8,0,0,2],[2,0,0,2,2,2,0,0,2,2,2,2,2,2,8,0,0,9,2,2,7,0,0,10,2,2,2,2,11,0,0,2],[2,0,0,10,2,11,0,0,10,2,2,2,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,9,2,8,0,0,9,8,0,0,10,2,11,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,10,2,11,0,0,10,11,0,0,9,2,8,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,2,2,2,2,2,8,0,0,9,2,2,2,2,2,2],[2,0,0,9,2,8,0,0,9,2,2,2,2,2,2,0,0,10,2,2,2,2,11,0,0,10,2,2,2,2,2,2],[2,0,0,10,2,11,0,0,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,10,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,2,2,2,8,0,0,0,9,2,2,2,2,2,2,2,8,0,0,0,2],[2,2,2,8,0,0,0,0,0,0,0,0,2,2,2,11,0,0,0,10,2,2,2,2,2,2,2,2,0,0,0,2],[2,2,2,11,0,0,0,0,5,4,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,10,11,0,0,0,2],[2,0,0,0,0,0,0,0,6,7,0,0,10,2,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,2,8,0,0,5,4,0,0,0,0,0,0,0,2],[2,0,0,0,9,8,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,6,7,0,0,0,0,9,2,2,2],[2,0,0,0,2,2,2,2,2,2,2,2,8,0,0,0,9,2,2,2,0,0,0,0,0,0,0,0,10,2,2,2],[2,0,0,0,10,2,2,2,2,2,2,2,11,0,0,0,10,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,8,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,9,2,8,0,0,2],[2,2,2,2,2,2,8,0,0,9,2,2,2,2,8,0,0,2,2,2,2,2,2,11,0,0,10,2,11,0,0,2],[2,2,2,2,2,2,11,0,0,10,2,2,2,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,10,2,11,0,0,9,8,0,0,9,2,8,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,9,2,8,0,0,10,11,0,0,10,2,11,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,2,2,2,2,8,0,0,9,2,8,0,0,2],[2,0,0,9,2,2,2,2,8,0,0,9,2,2,11,0,0,10,2,2,2,2,2,2,0,0,2,2,2,0,0,2],[2,0,0,10,2,2,2,2,11,0,0,10,11,0,0,0,0,0,0,10,2,2,2,11,0,0,10,2,11,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2],[2,2,2,2,2,2,2,2,2,2,2,2,8,0,0,0,1,0,0,9,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]]}
];

const CLASSES = {
	guerrier:{name:'Guerrier', type:'PHYSIC', stats:[{name:'Vie', value:'120'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'??p??e'}, {name:'??nergie', value:'1200'}, {name:'??nergie par attaque', value:'800'}]},
	archer:{name:'Archer', type:'DISTANCE', stats:[{name:'Vie', value:'60'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Arc'}, {name:'??nergie', value:'1200'}, {name:'??nergie par attaque', value:'800'}]},
	golem:{name:'Golem', type:'NATURAL', stats:[{name:'Vie', value:'180'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Poing'}, {name:'??nergie', value:'1800'}, {name:'??nergie par attaque', value:'1200'}]},
	mage:{name:'Mage', type:'MAGIC', stats:[{name:'Vie', value:'80'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'B??ton magique'}, {name:'??nergie', value:'2400'}, {name:'??nergie par attaque', value:'800'}]},
	assassin:{name:'Assassin NE PAS UTILISER', type:'BLADE', stats:[{name:'Vie', value:'80'}, {name:'Vitesse', value:'6'}, {name:'Arme', value:'Dague'}, {name:'??nergie', value:'800'}, {name:'??nergie par attaque', value:'500'}]},
	necromancien:{name:'N??cromancien', type:'NECROMANTIC', stats:[{name:'Vie', value:'60'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Sbires'}, {name:'??nergie', value:'4800'}, {name:'??nergie par invocation', value:'4800'}]},
	alchimiste:{name:'Alchimiste NE PAS UTILISER', type:'CHIMIC', stats:[{name:'Vie', value:'70'}, {name:'Vitesse', value:'4'}, {name:'Arme', value:'Soon ?'}, {name:'??nergie', value:'1200'}, {name:'??nergie par attaque', value:'800'}]}
}

const GAMEMODES = {
	CLASSIC:{name:'Mode classique', description:'{teamsNb} ??quipes de {membersNb} joueurs s\'affrontent dans une ar??ne, le but : ??tre la derni??re ??quipe en vie.'},
	REBRAWL:{name:'Mode Rebrawl', description:'Votre ??quipe de {membersNb} joueurs a peur de perdre un round ? Ce mode de jeu vous permet de faire un match en 3 manches. La premi??re ??quipe des {teamsNb} ?? 2 points gagne le match.'},
	CTP:{name:'Mode capture de point', description:''},
	INVASION:{name:'Mode invasion', description:'Une seule ??quipe de {membersNb} joueurs ! Affrontez des vagues d\'envahisseurs en coop??ration, combien de vagues tiendrez-vous ?'},
	TEST:{name:'Test', description:'Bah oui, il faut bien faire des tests pour ??tre s??r que ??a fonctionne. ??\\_(???)_/?? <br />{teamsNb} ??quipes de {membersNb} joueurs.'}
};

const ITEMS = {
	magic_powder:{name:'Poudre magique', description:'Vive la poudre magique ! Elle apporte bonheur, joie et snnnniiiiiiffffffff... *u*', isItem:false},
	amethyst:{name:'Am??thyste', description:'???u??? !', isItem:false},
	ice:{name:'Glace', description:'Brrrrrrrrrrrrrrrrrrrrrrrr...', isItem:false},
	void_bottle:{name:'Fiole de vide', description:'Grande fiole qui contient le vide', isItem:false},
	gold:{name:'Or', description:'', isItem:false},
	
	invisibility_cloak:{name:'Cape d\'invisibilit??', description:'Pour se rendre invisible de l\'ennemi', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Dur??e', value:'8sec'}]},
	shield:{name:'Bouclier', description:'', isItem:true, slot:'hand', type:'PHYSIC', stats:[{name:'Dur??e de stun', value:'2sec'}, {name:'D??g??ts', value:'20'}]},
	//intangibility_ring:{name:'Anneau d\'intangibilit??', description:'', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Dur??e', value:'?'}]},
	explosive_trap:{name:'Pi??ge explosif', description:'', isItem:true, slot:'hand', type:'EXPLOSIVE', stats:[{name:'D??g??ts', value:'30'}, {name:'Limite', value:'3'}]},
	poison_trap:{name:'Pi??ge ?? poison', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Dur??e', value:'3sec'}, {name:'D??g??ts par seconde', value:'10'}]},
	//applicable_poison:{name:'Poison applicable', description:'Marre de devoir jeter du poison ? Appliquez-le d??sormais sur votre arme principale !', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Dur??e', value:'3sec'}, {name:'D??g??ts par seconde', value:'10'}]},
	throwable_poison:{name:'Poison jetable', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[{name:'Dur??e', value:'3sec'}, {name:'D??g??ts par seconde', value:'10'}]},
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
	healing_ring:{name:'Anneau de gu??rison', description:'', isItem:true, slot:'hand', type:'MAGIC', stats:[{name:'Soin', value:'30'}]},
	//laser:{name:'Laser', description:'', isItem:true, slot:'hand', type:'ELECTRIC', stats:[]},
	teleportation:{name:'T??l??portation', description:'', isItem:true, slot:'hand', type:'DISTANCE', stats:[]},
	taser:{name:'Taser', description:'', isItem:true, slot:'hand', type:'ELECTRIC', stats:[{name:'D??g??ts', value:'30'}, {name:'Dur??e de stun', value:'1sec'}]},
	smoke:{name:'Fumig??ne', description:'', isItem:true, slot:'hand', type:'CHIMIC', stats:[]},
	orange_juice:{name:'Jus d\'orange', description:'', isItem:true, slot:'hand', type:'NATURAL', stats:[{name:'Soin', value:'30'}]},
	sismic_glove:{name:'Gant sismique', description:'Permet de provoquer des s??ismes', isItem:true, slot:'hand', type:'PHYSIC', stats:[{name:'Dur??e', value:'4sec'}, {name:'D??gats par seconde', value:10}]},
	
	casque_radar:{name:'Casque radar', description:'', isItem:true, slot:'head', modifiers:[{id:'SIGHT_BONUS', value:0.2}]},
	horn:{name:'Corne de licorne', description:'', isItem:true, slot:'head', modifiers:[{id:'MAGIC_RESISTANCE', value:0.2}]},
	magic_hat:{name:'Chapeau magique', description:'', isItem:true, slot:'head', modifiers:[]},
	big_heart:{name:'Grand coeur', description:'', isItem:true, slot:'chest', modifiers:[{id:'HEALTH_BONUS', value:0.2}]},
	//jetpack:{name:'Jetpack', description:'', isItem:true, slot:'chest', modifiers:[]}, // Slot.hand ???
	dynamite_belt:{name:'Ceinture de dynamite', description:'', isItem:true, slot:'chest', modifiers:[{id:'FINAL_EXPLOSION'}]},
	energy_pump:{name:'Pompe ?? ??nergie', description:'', isItem:true, slot:'chest', modifiers:[{id:'ENERGY_BOOST', value:0.2}]},
	energy_tank:{name:'R??servoir ?? ??nergie', description:'', isItem:true, slot:'chest', modifiers:[{id:'ENERGY_SUPPLY', value:0.2}]},
	hot_water_chestplate:{name:'Plastron bouillote', description:'', isItem:true, slot:'chest', modifiers:[{id:'FROST_RESISTANCE', value:0.2}]},
	cape:{name:'Cape', description:'', isItem:true, slot:'chest', modifiers:[{id:'SPEED', value:0.1}]},
	celerity_boots:{name:'Bottes de c??l??rit??', description:'', isItem:true, slot:'foot', modifiers:[{id:'SPEED', value:0.2}]},
	//ice_boots:{name:'Bottes de glace', description:'', isItem:true, slot:'foot', modifiers:[{id:'GLIDE', value:0.4}]},
	amethyst_helmet:{name:'Casque en am??thyste', description:'', isItem:true, slot:'head', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	amethyst_chestplate:{name:'Plastron en am??thyste', description:'', isItem:true, slot:'chest', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	amethyst_leggings:{name:'Jambi??res en am??thyste', description:'', isItem:true, slot:'legs', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.15}]},
	amethyst_boots:{name:'Bottes en am??thyste', description:'', isItem:true, slot:'foot', modifiers:[{id:'RESISTANCE', value:0.15}, {id:'MAGIC_RESISTANCE', value:-0.1}]},
	iron_helmet:{name:'Casque en fer', description:'La base d\'un bon guerrier : un bon casque, r??sistant et protecteur', isItem:true, slot:'head', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	iron_chestplate:{name:'Plastron en fer', description:'', isItem:true, slot:'chest', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	iron_leggings:{name:'Jambi??res en fer', description:'', isItem:true, slot:'legs', modifiers:[{id:'SPEED', value:-0.08}, {id:'RESISTANCE', value:0.1}]},
	iron_boots:{name:'Bottes en fer', description:'', isItem:true, slot:'foot', modifiers:[{id:'SPEED', value:-0.05}, {id:'RESISTANCE', value:0.1}]},
	aluminium_helmet:{name:'Casque en aluminium', description:'', isItem:true, slot:'head', modifiers:[{id:'RESISTANCE', value:0.05}]},
	aluminium_chestplate:{name:'Plastron en aluminium', description:'', isItem:true, slot:'chest', modifiers:[{id:'RESISTANCE', value:0.05}]},
	aluminium_leggings:{name:'Jambi??res en aluminium', description:'', isItem:true, slot:'legs', modifiers:[{id:'RESISTANCE', value:0.05}, {id:'SPEED', value:-0.02}]},
	aluminium_boots:{name:'Bottes en aluminium', description:'', isItem:true, slot:'foot', modifiers:[{id:'RESISTANCE', value:0.05}]},
	caou_helmet:{name:'Bonnet en caoutchouc', description:'', isItem:true, slot:'head', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_chestplate:{name:'Veste en caoutchouc', description:'', isItem:true, slot:'chest', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_leggings:{name:'Pantalon en caoutchouc', description:'', isItem:true, slot:'legs', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	caou_boots:{name:'Bottes en caoutchouc', description:'', isItem:true, slot:'foot', modifiers:[{id:'ELECTRIC_RESISTANCE', value:0.2}]},
	thorny_helmet:{name:'Casctus', description:'', isItem:true, slot:'head', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_chestplate:{name:'Plastron ??pineux', description:'', isItem:true, slot:'chest', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_leggings:{name:'Pantalon ??pineux', description:'', isItem:true, slot:'legs', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	thorny_boots:{name:'Bottes ??pineuses', description:'', isItem:true, slot:'foot', modifiers:[{id:'PHYSIC_DAMAGE_RETURN', value:0.2}, {id:'PHYSIC_RESISTANCE', value:0.1}]},
	//casque_du_vide:{name:'', description:'', isItem:true, slot:'head', modifiers:[]},
	//plastron_du_vide:{name:'', description:'', isItem:true, slot:'chest', modifiers:[]},
	//jambi??res_du_vide:{name:'', description:'', isItem:true, slot:'legs', modifiers:[]},
	//bottes_du_vide:{name:'', description:'', isItem:true, slot:'foot', modifiers:[]},
	miror_ball:{name:'Boule ?? facettes', description:'', isItem:true, slot:'head', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.1}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	miror_chestplate:{name:'Plastron miroir', description:'', isItem:true, slot:'chest', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.1}, {id:'MAGIC_RESISTANCE', value:0.2}]},
	miror_leggings:{name:'Jambi??res miroir', description:'', isItem:true, slot:'legs', modifiers:[{id:'MAGIC_DAMAGE_RETURN', value:0.1}, {id:'ELECTRIC_RESISTANCE', value:0.05}, {id:'MAGIC_RESISTANCE', value:0.2}]},
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
	ELECTRIC:{name:'??l??ctrique', color:'yellow'},
	CHIMIC:{name:'Chimique', color:'purple'},
	EXPLOSIVE:{name:'Explosif', color:'red'},
	MAGIC:{name:'Magique', color:'cyan'},
	NATURAL:{name:'Naturel', color:'green'},
	NECROMANTIC:{name:'N??cromancie', color:'darkblue'},
	BLADE:{name:'Lame', color:'white'}
};

const MODIFIERS = {
	HEALTH_BONUS:{name:'Sant??'},
	ENERGY_SUPPLY:{name:'Quantit?? d\'??nergie'},
	ENERGY_BOOST:{name:'Recharge d\'??nergie'},
	RESISTANCE:{name:'R??sistance'},
	ELECTRIC_RESISTANCE:{name:'R??sistance ??l??ctrique'},
	FIRE_RESISTANCE:{name:'R??sistance au feu'},
	FROST_RESISTANCE:{name:'R??sistance au froid'},
	PROJECTILE_RESISTANCE:{name:'R??sistance aux projectiles'},
	CHIMIC_RESISTANCE:{name:'R??sistance chimique'},
	PHYSIC_RESISTANCE:{name:'R??sistance physique'},
	MAGIC_RESISTANCE:{name:'R??sistance magique'},
	SIGHT_BONUS:{name:'Visibilit??'},
	SPEED:{name:'Vitesse'},
	PHYSIC_DAMAGE_RETURN:{name:'D??g??ts physiques retourn??s'},
	MAGIC_DAMAGE_RETURN:{name:'D??g??ts magiques retourn??s'},
	GLIDE:{name:'Glisse'},
	// ----
	ANTI_STUN_ATTACK:{name:'Anti-stun d\'attaque'},
	ANTI_STUN_MOVE:{name:'Anti-stun de d??placement'},
	FINAL_EXPLOSION:{name:'Explosion finale'}
};
