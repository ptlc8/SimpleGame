// SimpleGame for 0.1.8 servers, by PTLC_ (ptlcptlcptlc@gmail.com)

var config = {
	type: Phaser.AUTO,
	width: 800,
	height: 600,
	id: 'game',
	parent: 'game-div',
	title: 'SimpleGame',
	version: 'dèv',
	fullscreenTarget: 'game-div',
	pixelArt : true,
	scale: {
		/*mode: Phaser.Scale.FIT,*/
		parent: 'game-div',
		/*autoCenter: Phaser.Scale.CENTER_BOTH,*/
		width: 800,
		height: 600
	},
	physics: {
		default: 'arcade',
		arcade: {
			debug: false
		}
	},
	scene: {
		preload: preload,
		create: create,
		update: update
	}
};


var debug = false;
var isTactile = false;
var game;
var lums;
var multiplayerButton;
var connection;
var ping;
var leaveServerButton;
var searchGamesButton;
var createGamesButton;
var leaveGameButton;
var sendChatButton;
var me = {name:'', className:'', inventory:{}, equipement:{}};
var group = undefined; // {code, size, max}
var game_ = {gamemode:'', teams:[], map:{pattern:[[2,2,2,0,2,2,2,0,2,2,0,0,0,2,2,2,2],[2,2,2,0,0,2,2,0,2,0,2,2,2,0,2,2,2],[2,2,2,0,2,0,2,0,2,0,2,2,2,0,2,2,2],[2,2,2,0,2,2,0,0,2,0,2,2,2,0,2,2,2],[2,2,2,0,2,2,2,0,2,2,0,0,0,2,2,2,2],[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2],[0,2,2,2,0,2,2,0,0,0,2,2,0,0,0,0,2],[0,0,2,0,0,2,0,2,2,2,0,2,0,2,2,2,0],[0,2,0,2,0,2,0,0,0,0,0,2,0,0,0,0,2],[0,2,2,2,0,2,0,2,2,2,0,2,0,2,2,2,2],[0,2,2,2,0,2,0,2,2,2,0,2,0,2,2,2,2]]}};
var gamemodes = [];
var chat = [];
var players = [];
var isCreator = false;
var configGameButton;
var backToGameMenuButton;
var sendMapAndTeamsButton;
var startGameButton;
var soloButton;
var chooseClass;
var warriorClassChoose, archerClassChoose, golemClassChoose, mageClassChoose, assassinClassChoose, invocateurClassChoose, randomClassChoose, alchimisteClassChoose;
var capacity0Button, capacity1Button, capacity2Button, capacity3Button;
var isBindingKeys = false;
var keys = {up: false, right: false, down: false, left: false};
var lastMove = {move: false, direction: 0};
var frags;
var myEntityId = -1;
var entities = [];
var isPlaying = false;
var camera;
var healthBar;
var healthMax = 0;
var health = 0;
var energyBar;
var energyMax = 0;
var energy = 0;
var specialBar;
var special = 0
var specialMax = 0;
var isSpectator = false;
var teamMates = [];
var followingentityid;
var spectatorMenuButton;
var previewClass;
var classStats;
var chooseClassButton;
var selectedClassName = "random";
var selectedCapacityN = -1;
var capacityStats;
var capacities;
var runSound;
var joystick;
var joystickBack;
var specialButton;
var specialLoaded;
var nextIsSpecial = false;
var tactileGUI;

setDefaultAddContentDiv("#menu-div");
addImage('loading', 'assets/loading.gif', 31.25, 25, undefined, true, 37.5, 50)
new Phaser.Game(config);

function preload() {
	
	game = this;
	
	//----background
	this.load.image('lum', 'assets/lum.png');
	
	// ---- tactile mode
	this.load.image('joystick', 'assets/joystick.png');
	this.load.image('joystick-back', 'assets/joystick-back.png');
	this.load.image('special-button', 'assets/special-button.png');
	this.load.spritesheet('special-loaded', 'assets/loaded.png', {frameWidth:128, framHeight: 128});
	
	this.load.spritesheet('loading', 'assets/loaded.png', {frameWidth:128, framHeight: 128});
	
	// ---- nouveaux player sprites (objets puis classes)
	for (let item of Object.keys(ITEMS)) {
		if (ITEMS[item].isItem) {
			this.load.image(item, 'assets/players/'+item+'.png');
			if (ITEMS[item].slot == 'chest') this.load.image(item+'-back', 'assets/players/'+item+'-back.png');
			for (let ref of Object.keys(spritesReference)) {
				if (spritesReference[ref]['special'+ITEMS[item].slot.charAt(0).toUpperCase()+ITEMS[item].slot.substring(1)]) {
					this.load.image(item+'-'+ref, 'assets/players/'+item+'-'+ref+'.png');
					if (ITEMS[item].slot == 'chest') this.load.image(item+'-back-'+ref, 'assets/players/'+item+'-back-'+ref+'.png');
				}
			}
		}
	}
	for (let className of Object.keys(spritesReference)) {
		game.load.image(className+'-head', 'assets/players/'+className+'-head.png');
		game.load.image(className+'-body', 'assets/players/'+className+'-body.png');
		game.load.image(className+'-arms', 'assets/players/'+className+'-arms.png');
		if (spritesReference[className].haveOverLegs)
			game.load.image(className+'-overlegs', 'assets/players/'+className+'-overlegs.png');
	}
	
	//----buttons
	this.load.image('connect-server', 'assets/buttons/connect-server.png');
	this.load.image('create-game', 'assets/buttons/create-game.png');
	this.load.image('search', 'assets/buttons/search.png');
	this.load.image('confirm', 'assets/buttons/valid.png');
	/**/this.load.image('solo', 'assets/buttons/confirm.png');
	this.load.image('back', 'assets/buttons/back.png');
	this.load.image('send', 'assets/buttons/send.png');
	this.load.image('config', 'assets/buttons/parameters.png');
	/**/this.load.image('start', 'assets/buttons/connect-server.png');
	this.load.image('guerrier-button', 'assets/buttons/guerrier.png');
	this.load.image('archer-button', 'assets/buttons/guerrier.png');
	this.load.image('assassin-button', 'assets/buttons/guerrier.png');
	this.load.image('mage-button', 'assets/buttons/mage.png');
	this.load.image('golem-button', 'assets/buttons/golem.png');
	this.load.image('necromancien-button', 'assets/buttons/necromancien.png');
	this.load.image('alchimiste-button', 'assets/buttons/coming-soon.png');
	this.load.image('random-button', 'assets/buttons/random.png');
	this.load.image('spectatorMenu', 'assets/buttons/connect-server.png');
	this.load.image('fight', 'assets/buttons/fight.png');
	this.load.image('dungeon', 'assets/buttons/dungeon.png');
	this.load.image('custom', 'assets/buttons/training.png');
	this.load.image('inventory', 'assets/buttons/inventory.png');
	this.load.image('join-group', 'assets/buttons/join-group.png');
	this.load.image('create-group', 'assets/buttons/create-group.png');
	this.load.image('leave-group', 'assets/buttons/leave-group.png')
	//...
	this.load.image('coup-fort-button', 'assets/buttons/coming-soon.png');
	this.load.image('fleche-empoisonnee-button', 'assets/buttons/fleche-empoisonnee.png');
	this.load.image('boule-de-feu-button', 'assets/buttons/boule-de-feu.png');
	this.load.image('boule-de-glace-button', 'assets/buttons/boule-de-glace.png');
	this.load.image('mer-de-flammes-button', 'assets/buttons/coming-soon.png');
	this.load.image('terre-glacee-button', 'assets/buttons/terre-glacee.png');
	//...
	
	//----map
	this.load.image('map','assets/map3.png');
	this.load.image('capture-point', 'assets/capture-point.png');
	
	//----classes images
	this.load.image('guerrier', 'assets/classes/guerrier.png');
	this.load.image('guerrier-right', 'assets/classes/guerrier-right.png');
	this.load.image('guerrier-left', 'assets/classes/guerrier-left.png');
	this.load.image('archer', 'assets/classes/guerrier.png');
	this.load.image('archer-right', 'assets/classes/guerrier-right.png');
	this.load.image('archer-left', 'assets/classes/guerrier-left.png');
	this.load.image('assassin', 'assets/classes/guerrier.png');
	this.load.image('assassin-right', 'assets/classes/guerrier-right.png');
	this.load.image('assassin-left', 'assets/classes/guerrier-left.png');
	this.load.image('mage', 'assets/classes/mage.png');
	this.load.image('mage-right', 'assets/classes/mage.png');
	this.load.image('mage-left', 'assets/classes/mage.png');
	this.load.image('golem', 'assets/classes/golem.png');
	this.load.image('golem-right', 'assets/classes/golem.png');
	this.load.image('golem-left', 'assets/classes/golem.png');
	this.load.image('necromancien', 'assets/classes/necromancien.png');
	this.load.image('necromancien-right', 'assets/classes/necromancien.png');
	this.load.image('necromancien-left', 'assets/classes/necromancien.png');
	this.load.image('alchimiste', 'assets/classes/guerrier.png');
	this.load.image('alchimiste-right', 'assets/classes/guerrier-right.png');
	this.load.image('alchimiste-left', 'assets/classes/guerrier-left.png');
	
	//----armes
	this.load.image('arrow', 'assets/weapons/arrow.png');
	this.load.image('sword', 'assets/weapons/sword.png');
	this.load.image('dagger', 'assets/weapons/dagger.png');
	this.load.image('spell', 'assets/weapons/spell.png');
	this.load.image('punch', 'assets/weapons/punch.png');
	
	this.load.image('slowness-area', 'assets/weapons/slowness-area.png');
	this.load.image('fireball', 'assets/weapons/fireball.png');
	this.load.image('iceball', 'assets/weapons/iceball.png');
	this.load.image('trap', 'assets/weapons/trap.png');
	this.load.image('trap-explode', 'assets/weapons/trap-explode.png');
	this.load.image('earthquake', 'assets/weapons/earthquake.png');
	this.load.image('burn-area', 'assets/weapons/burn-area.png');
	this.load.image('stone-wall', 'assets/stone-wall.png');
	this.load.image('shield', 'assets/weapons/shield.png');
	this.load.spritesheet('explosion', 'assets/weapons/explosion.png', {frameWidth:64, framHeight: 64});
	this.load.image('tp', 'assets/weapons/tp.png');
	
	//----
	this.load.image('healthBar', 'assets/health-bar.png');
	this.load.image('energyBar', 'assets/energy-bar.png');
	this.load.image('specialBar', 'assets/special-bar.png');
	
	//----sons
	this.load.audio('run', 'assets/audio/run.mp3');
}

function create() {
	
	remove('loading');
	dispatchEvent(new CustomEvent('game-loaded', {detail:{}}));
	
	lums = this.physics.add.group();
	for (var i = 0; i < 22; i++)
		lums.create(Phaser.Math.Between(0, 800), Phaser.Math.Between(0, 600), 'lum');
	
	this.anims.create({key: 'special-loaded', frames: this.anims.generateFrameNumbers('special-loaded'), repeat: -1});
	this.anims.create({key: 'loading', frames: this.anims.generateFrameNumbers('loading'), repeat: -1});
	this.anims.create({key: 'explosion', frames: this.anims.generateFrameNumbers('explosion'), repeat: -1});
	
	multiplayerButton = this.add.sprite(400, 350, 'connect-server').setScale(2).setInteractive({cursor:'pointer'}).on('pointerdown', printConnectServerMenu);
	leaveServerButton = this.add.sprite(100+32, 50+32, 'back').setInteractive({cursor:'pointer'}).on('pointerdown', leaveServer); leaveServerButton.visible = false;
	
	backToMainMenuButton = this.add.sprite(100+32, 50+32, 'back').setInteractive({cursor:'pointer'}).on('pointerdown', backToMainMenu).setVisible(false);
	
	searchGamesButton = this.add.sprite(468, 70, 'search').setInteractive({cursor:'pointer'}).on('pointerdown', searchGames); searchGamesButton.visible = false;
	createGameButton = this.add.sprite(650, 313, 'create-game').setInteractive({cursor:'pointer'}).on('pointerdown', createGame); createGameButton.visible = false;
	leaveGameButton = this.add.sprite(100+32, 50+32, 'back').setInteractive({cursor:'pointer'}).on('pointerdown', leaveGame); leaveGameButton.visible = false;
	sendChatButton = this.add.sprite(500-32, 500-20, 'send').setInteractive({cursor:'pointer'}).on('pointerdown', sendChat); sendChatButton.visible = false;
	configGameButton = this.add.sprite(700-32, 500-20, 'config').setInteractive({cursor:'pointer'}).on('pointerdown', configGame);
		configGameButton.visible = false;
	backToGameMenuButton = this.add.sprite(100+32, 50+32, 'back').setInteractive({cursor:'pointer'}).on('pointerdown', backToGameMenu);
		backToGameMenuButton.visible = false;
	sendMapAndTeamsButton = this.add.sprite(500+30+32, 50+64+30+20, 'send').setInteractive({cursor:'pointer'}).on('pointerdown', sendMapAndTeams);
		sendMapAndTeamsButton.visible = false;
	startGameButton = this.add.sprite(500+30+32, 50+64+30+40+30+20, 'start').setInteractive({cursor:'pointer'}).on('pointerdown', startGame);
		startGameButton.visible = false;
	soloButton = this.add.sprite(200,300,'solo').setInteractive({cursor:'pointer'}).on('pointerdown', printSoloMenu);
		/**/soloButton.visible = false;
	spectatorMenuButton = this.add.sprite(200,300,'spectatorMenu').setInteractive({cursor:'pointer'}).on('pointerdown',printTeamMateSpectatingMenu);
		spectatorMenuButton.visible = false;
		spectatorMenuButton.setScrollFactor(0);
	returnToGameMenuButton = this.add.sprite(400,400,'back').setInteractive({cursor:'pointer'}).on('pointerdown',returnToGameMenu);
		returnToGameMenuButton.visible = false;
		spectatorMenuButton.setScrollFactor(0);
		
		//------bouton choix de classe
	warriorClassChoose = this.add.sprite(75, 75, 'guerrier-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("guerrier");});
		warriorClassChoose.visible = false;
	archerClassChoose = this.add.sprite(225, 75, 'archer-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("archer");});
		archerClassChoose.visible = false;
	golemClassChoose = this.add.sprite(75, 225, 'golem-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("golem");});
		golemClassChoose.visible = false;
	mageClassChoose = this.add.sprite(225, 225, 'mage-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("mage");});
		mageClassChoose.visible = false;
	assassinClassChoose = this.add.sprite(75, 375, 'assassin-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("assassin");});
		assassinClassChoose.visible = false;
	invocateurClassChoose = this.add.sprite(225, 375, 'necromancien-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("necromancien");});
		invocateurClassChoose.visible = false;
	randomClassChoose = this.add.sprite(75, 525, 'random-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("random");});
		randomClassChoose.visible = false;
	alchimisteClassChoose = this.add.sprite(225, 525, 'alchimiste-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {getClassStats("alchimiste");});
		alchimisteClassChoose.visible = false;
	previewClass = this.add.image(475, 128+32, "guerrier").setScale(4).setVisible(false);
	classStats = this.add.text(300+11, 96*3+11, "-STATS-").setColor("#eeeeee").setWordWrapWidth(328).setVisible(false);
	capacityStats = this.add.text(300+11, 407, "-CAPA STATS-").setColor("#eeeeee").setWordWrapWidth(328).setVisible(false);
	chooseClassButton = this.add.sprite(475, 600-64-11, "confirm").setInteractive({cursor:'pointer'}).on('pointerdown', function() {chooseClass(selectedClassName, selectedCapacityN);});
		chooseClassButton.visible = false;
	
	capacity0Button = this.add.image(725, 75, 'coup-fort-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {printCapacityStats(0); selectedCapacityN=0;}).setVisible(false);
	capacity1Button = this.add.image(725, 225, 'coup-fort-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {printCapacityStats(1); selectedCapacityN=1;}).setVisible(false);
	capacity2Button = this.add.image(725, 375, 'coup-fort-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {printCapacityStats(2); selectedCapacityN=2;}).setVisible(false);
	capacity3Button = this.add.image(725, 525, 'coup-fort-button').setInteractive({cursor:'pointer'}).on('pointerdown', function() {printCapacityStats(3); selectedCapacityN=3;}).setVisible(false);
	
	this.input.keyboard.on('keydown', onKeyDown);
	this.input.keyboard.on('keyup', onKeyUp);
	
	this.input.on('pointerdown', onClick, this);
	this.input.on('pointerup', unClick, this);
	this.input.mouse.disableContextMenu();
	
	entities = [];
	entities.get = function(property, value) {return this.find(function(entity) {return entity[property]==value;});};
	entities.remove = function(entity) {return this.splice(this.indexOf(entity), 1)[0];};
	
	camera = this.cameras.main;
	camera.zoomTo(1); // pour éviter un bug
	
	title = this.add.text(400, 160, "Brawl Arena").setOrigin(0.5).setFontSize(96).setStroke("black", 16).setShadow(2,2,"#333333",2,true,true).setFontFamily("Elected Office");
	//addText("title", 100, 96, 10, "SimpleGame", 600, "white");
	
	game.input.addPointer(2);
	joystick = game.add.image(100, 500, "joystick").setDepth(Infinity).setScrollFactor(0).setInteractive({cursor:'grab'}).setVisible(isTactile);
	joystickBack = game.add.image(100, 500, "joystick-back").setScale(2).setAlpha(0.25).setDepth(Infinity).setScrollFactor(0).setVisible(isTactile);
	specialButton = game.add.image(700, 500, "special-button").setDepth(Infinity).setScrollFactor(0).setVisible(isTactile).setInteractive({cursor:'pointer'}).on('pointerdown', onSpecialButton);
	specialLoaded = game.add.sprite(700, 500, "special-loaded").setDepth(Infinity).setScrollFactor(0).setVisible(isTactile && nextIsSpecial);
	specialLoaded.anims.play('special-loaded');
	tactileGUI = game.add.container(0,0,[joystick, joystickBack, specialButton, specialLoaded]).setDepth(Infinity);
	
}

function update(time, delta) {
	
	lums.children.iterate(function(lum) {
		lum.setVelocityX(Math.max(-10, Math.min(10, lum.body.velocity.x+Phaser.Math.Between(-1, 1))));
		lum.setVelocityY(Math.max(-10, Math.min(10, lum.body.velocity.y+Phaser.Math.Between(-1, 1))));
	});
	
	if (isPlaying) {
		connection.send("getentities");
		if (energy < energyMax)
			refreshEnergy(energy + 10);
		if (special < specialMax)
			refreshSpecial(special + 2);
	}
	
	if (joystick.followCursor) {
		let x = joystick.followCursor.x;
		let y = joystick.followCursor.y;
		let newX = x < joystickBack.x ? Math.max(x, Math.cos(Math.atan2(y-joystickBack.y, x-joystickBack.x))*64+joystickBack.x)
												: Math.min(x, Math.cos(Math.atan2(y-joystickBack.y, x-joystickBack.x))*64+joystickBack.x);
		let newY = y < joystickBack.y ? Math.max(y, Math.sin(Math.atan2(y-joystickBack.y, x-joystickBack.x))*64+joystickBack.y)
												: Math.min(y, Math.sin(Math.atan2(y-joystickBack.y, x-joystickBack.x))*64+joystickBack.y);
		if (joystick.x != newX || joystick.xy != newY) {
			joystick.setPosition(newX, newY);
			if (isBindingKeys)
				sendMove(getAngleInRadians({x:newX-joystickBack.x,y:newY-joystickBack.y}));
		}
	}
	
	if (isSpectator){
		connection.send("getentities");
	}
	
	if (!isSpectator && isPlaying) {
		if (camera._follow == null && entities.get("id", myEntityId) !== undefined)
			camera.startFollow(entities.get("id", myEntityId), true);
		
	}
	
	if (isSpectator){
		if (entities.get("id",followingentityid) !== undefined)
			camera.startFollow(entities.get("id",followingentityid), true);
	}
	
}

function printSoloMenu(){
	remove("title");
	multiplayerButton.visible = false;
	soloButton.visible = false ;
	//soon ?
}

function onMessage(command, arg) {
	switch (command) {
		case "pong":
			ping = Date.now() - parseInt(arg);
			console.log(`Ping : ${ping}ms`);
			break;
		// ---- Connexion et inscription
		case "youreloggedin":
		case "youreregistered":
			me = JSON.parse(arg);
			hideIdMenu();
			group = undefined;
			printMainMenu();
			break;
		case "playernamealreadyused":
			displayError("pseudo", "Ce pseudonyme est déjà utilisé");
			break;
		case "youare":
		case "youllbe":
			displayError("pseudo", "Aucun pseudonyme renseigné");
			break;
		case "playerdoesntexist":
			displayError("pseudo", "Ce joueur n'existe pas");
			break;
		case "invalidpassword":
			displayError("mdp", "Mot de passe invalide");
			break;
		case "alreadyconnected":
			displayError("pseudo", "Vous êtes déjà connecté(e) autrepart");
			break;
		// ---- Menu principal
		case "groupjoined":
			remove("join-group");
		case "groupcreated":
			group = {size:1, max:5, code:arg};
			printMainMenu();
			break;
		case "groupleaved":
			group = undefined;
			printMainMenu();
			break;
		case "needjoincode":
			displayError("join-code", "Aucun code renseigné");
			break;
		case "invalidjoincode":
			displayError("join-code", "Code inconnu");
			break;
		case "groupisplaying":
			displayError("join-code", "Le groupe est déjà en jeu");
			break;
		case "groupfull":
			displayError("join-code", "Le groupe est complet :/");
			break;
		case "gamemodes":
			gamemodes = JSON.parse(arg);
			if (exists("gamemodes"))
				refreshGameModesList();
			break;
		// ---- Inventaire
		case "inventory":
			me.inventory = JSON.parse(arg);
			if (exists("inventory"))
				refreshInventory();
			break;
		case "classsetted":
			me.className = arg;
			refreshPlayerView();
			break;
		case "equiped":
			if (!ITEMS[arg]) break;
			me.equipement[ITEMS[arg].slot] = arg;
			refreshPlayerView();
			break;
		case "disequiped":
			me.equipement[arg] = undefined;
			refreshPlayerView();
			break;
		// ---- Menu d'attente d'adversaires	
		case "searching":
			hideMainMenu();
			game_.gamemode = arg;
			printSearchingMenu();
			break;
		case "founded":
			game_.gamemode = arg;
			hideMainMenu();
			break;
		case "searchcanceled":
			hideSearchingMenu();
			printMainMenu();
			break;
		case "searchingplayersnumber":
			setSearchingPlayersNumber(arg);
			break;
		// ---- Menu de parties custom
		case "searchgameresults":
			var games = JSON.parse(arg);
			var gamesEls = [];
			for (var i = 0; i < games.length; i++)
				gamesEls.push(addDiv("game"+i, 0, i*12, 100, 12, [
						addText("game"+i+"name", 4,30, 3, games[i], 69, "black", true),
						addButton("game"+i+"joinbutton", 76, 10, 20, 80, () => joinGame(games[i]), "rejoindre", 3, "limegreen", true)
					], undefined, true));
			setDivContent("games", gamesEls);
			break;
		case "gamecreated":	
		case "gamejoined":
			hideGamesMenu();
			printGameMenu(arg);
			break;
		case  "gamenamealreadyused":
			displayError("creategame", "Ce nom de partie est déjà utilisé");
			break;
		case "gameneedaname":
			displayError("creategame", "Aucun nom de partie renseigné");
			break;
		case "gameisplaying":
			displayError("games", "Cette partie est déjà lancée");
			break;
		case "gamedoesntexist":
			displayError("games", "Cette partie n'existe plus");
			break;
		case "needagamename":
			displayError("games", "Erreur de dèv");
			break;
		case "gameleaved":
			hideGameMenu();
			printGamesMenu();
			break;
		case "chat":
			msgs = JSON.parse(arg);
			chat = [];
			for (var i = 0; i < msgs.length; i++)
				chat.push("<" + msgs[i].sender + "> " + msgs[i].content);
			addChat();
			break;
		case "memberleave":
			addChat(arg + " a quitté la partie");
			for (var i = 0; i < players.length; i++){ 
				if (players[i].name == arg) {
					players.splice(i, 1); 
					i--;
				}
			}
			refreshPlayers();
			break;
		case "memberjoin":
			addChat(arg + " a rejoint la partie");
			players.push({name:arg});
			refreshPlayers();
			break;
		case "creatoris":
			if (isCreator = me.name == arg)
				configGameButton.visible = true;
			else
				configGameButton.visible = false;
			break;
		case "players":
			players = JSON.parse(arg);
			refreshPlayers();
			break
		case "newchat":
			var message = JSON.parse(arg);
			addChat("<" + message.sender + "> " + message.content);
			break;
		case "messagereceived":
			//do nothing
			break;
		case "needmessagecontent":
			displayError("sendchat", "Le message ne peut être vide");
			break;
		/*case "mapandteams":
			let mapandteams = JSON.parse(arg);
			game_.map.pattern = mapandteams.pattern;
			game_.teams = mapandteams.teams;
			addChat("La carte de jeu et les équipes ont été mises à jour");
			refreshMapPreview("map", game_.map.pattern);
			break;*/
		case "map":
			game_.map = JSON.parse(arg);
			addChat("La carte de jeu a été mise à jour");
			refreshMapPreview("map", game_.map.pattern); 
			break;
		/*case "teams":
			game_.teams = JSON.parse(arg);
			addChat("Les équipes ont été mises à jour");
			break;*/
		case "mapandteamssetted":
			display("Carte de jeu et équipes correctement modifiées");
			break;
		case "youarentcreator":
			display("Vous n'êtes pas le créateur de cette partie");
			break;
		case "invalidmapandteams":
			displayError("mapJson", "Le nombre de points d'apparition de correspond pas au nombre d'équipes ou est nul");
			break;
		case "needjsonmapandteams":
			displayError("mapJson", "Une erreur de syntaxe JSON est présente");
			break;
		case "needmapandteams":
			displayError("mapJson", "Aucune carte de jeu n'a été renseignée");
			break;
		case "gamestarted":
			hideConfigGame();
			hideGameMenu();
			printTeamMenu();
			break;
		case "needmap":
			display("Aucune carte de jeu n'a été renseignée");
			break;
		case "needmoreplayers":
			display("Il y a plus d'équipes que de joueurs, actuellement : " + arg);
			break;
		case "playerswithteams":
			
			break;
		case "teamjoined":
			addText("waiting", 100, 520, 5, "En attente des autres joueurs...", 600, "whitesmoke");
			break;
		case "needexistingteamname":
			// do nothing 'cause can't happen
			break;
		case "needteamname":
			// do nothing 'cause can't happen
			break;
		case "teamleaved":
			remove("waiting");
			break;
		case "teamscompleted":
			hideTeamMenu();
			printClassMenu();
			break;
		/*case "classsetted":
			
			break;*/
		case "capacitysetted":
			if (classStats.visible)
				addText("waiting", 100, 520, 5, "En attente des autres joueurs...", 600	, "whitesmoke");
			break;
		case "capacityunsetted":
			remove("waiting");
			break;
		case "classeschoosen":
			hideClassMenu();
			break;
		// ---- en jeu
		case "prestart":
			hideSearchingMenu();
			if (arg != "") game_ = JSON.parse(arg);
			preStart();
			break;
		case "playingin":
			display(arg, 4, 1000, true);
			break;
		case "letsplay":
			display("C'est parti !", 5, 2000, true);
			start();
			break;
		case "newbrawl":
			display("Rebrawl !", 5, 2000, true);
			rebrawl();
			break;
		case "yourentityid":
			camera._follow = null;
			myEntityId = JSON.parse(arg);
			break;
		case "entities":
			var entitiesFromServer = JSON.parse(arg);
			 if (entitiesFromServer != null) refreshEntities(entitiesFromServer);
			break;
		case "unknowcommand":
			console.log("Dèvs ! On envoie des commandes qui n'existe pas encore : " + arg);
			break;
		case "health":
			refreshHealth(parseInt(arg));
			break;
		case "energy":
			refreshEnergy(parseInt(arg));
			break;
		case "special":
			refreshSpecial(parseInt(arg));
			break;
		case "ath":
			var args = arg.split(" ");
			healthMax = parseInt(args[1]);
			refreshHealth(health = parseInt(args[0]));
			energyMax = parseInt(args[3]);
			refreshEnergy (energy = parseInt(args[2]));
			specialMax = parseInt(args[5]);
			refreshSpecial(special = parseInt(args[4]));
			break;
		case "kill":
			var kill = JSON.parse(arg);
			
			break;
		case "youredead":
			isBindingKeys = false;
			isPlaying = false;
			isSpectator = true;
			tactileGUI.setVisible(false);
			hideATH()
			connection.send("getteammateswithclasses");
			break;
		case "teammateswithclasses":
			teamMates = JSON.parse(arg);
			printTeamMateSpectatingMenu();
			break;
		case "followingentityid":
			followingentityid = JSON.parse(arg);
			camera._follow = null;
			break;
		case "classstats":
			printClassStats(JSON.parse(arg));
			break;
		case "cantfollow":
			printTeamMateSpectatingMenu();
			displayError("teammates","Impossible de suivre se joueur");
			break;
		case "version":
			setText("version", `Version du serveur : ${arg}`);
			break;
		case "effect":
			onEffect(JSON.parse(arg));
			break;
		case "wave":
			display(`Vague ${arg} !`, 4, 2000, true);
			break;
		case "teams":
			game_.teams = JSON.parse(arg);
			if (exists("tab"))
				printTabMenu();
			break;
		case "capture":
			if (!isPlaying && !isSpectator) break;
			//if (!game_.capture) game_.capture = {};
			let capture = JSON.parse(arg);
			let color2 = '#' + invertHex(capture.team.color.substr(1));
			//if (game_.capture.team != capture.team) display(`${capture.team.name} prend le contôle du point`, 5, 1000, true);
			addDiv('capture-bar', 30, 85, 40, 5, [addDiv('capture-progress', 0, 0, capture.progress*100, 100, [addText('capture-team-name', 0, 15, 3, capture.team.name, 100, color2, true)], capture.team.color, true)], color2, true);
			break;
		case "damagegiven":
			let damagegiven = JSON.parse(arg);
			let target =  entities.get('id', damagegiven.entityId);
			if (target) printDamage(target.x, target.y, -damagegiven.damage, damagegiven.damage>=0?"red":"lime");
			break;
		// ---- end of a game
		case "end":
			hideGame();
			printResultMenu(arg);
			break;
		case "loot":
			setLoot(JSON.parse(arg));
			break;
		default:
			console.log("Dèvs ! Ya une commande non prise en compte : " + command);
			break;
	}
}



// ---- Style de textes Phaser
function getStyle(size, color="#fff") {
	return {fontFamily:"Elected Office",
		fontSize:16*size+"px",
		color:color,
		stroke:"black",
		strokeThickness:3*size,
		shadowOffsetX:1,
		shadowOffsetY:1,
		shadowColor:"#333333",
		shadowBlur:1,
		shadowStroke:true,
		shadowFill:true,
		align:'center'}
}

// ---- Gestion des boutons Phaser

var buttons = {
	list: {},
	get: function(name) {return this.list[name];},
	exists: function(name) {return this.list[name]!=undefined;},
	set: function(name, button) {this.remove(name); this.list[name] = button;},
	add: function(name, button) {if (!this.exists(name)) this.list[name] = button; else this.destroy(button);},
	remove: function(name) {let button = this.get(name); if (!button) return; this.destroy(button); this.list[name] = undefined;},
	removeAll: function(...names) {for (let name of names) this.remove(name);},
	destroy: function(button) {if (button && button.destroy) button.destroy();}
}



// ---- Importation des sprites de joueurs

/*function setPlayerSprite(key, onComplete, className, eqp={}) {
	getSprite(className, eqp.head, eqp.chest, eqp.legs, eqp.foot, eqp.hand).then((data) => {
		game.textures.remove(key);
		game.load.image(key, data);
		game.load.start();
		game.load.onLoadComplete = () => {
			onComplete()
			game.load.onLoadComplete = () => {}
		}
	});
}*/

function addPlayerSprite(x, y, className, equipement) {
	return game.add.container(x, y, getPlayerSprites(game, className, equipement));
}

function getPlayerSprites(scene, className, equipement={}) {
	let ref = spritesReference[className] || spritesReference['guerrier'];
	let sprites = [];
	if (equipement.chest) sprites.push({key: equipement.chest+'-back' + (ref.specialChest ? '-'+ref.name : ''), name:'chest-back', x:0, y:ref.shouldersY-24-16});
	sprites.push({key: ref.name+'-body',                                                                        name:'-body', x:0, y:0});
	if (equipement.legs) sprites.push({key: equipement.legs + (ref.specialLegs ? "-"+ref.name : ""),            name:'legs', x:0, y:ref.hipsY-16-16});
	if (equipement.foot) sprites.push({key: equipement.foot + (ref.specialFoot ? "-"+ref.name : ""),            name:'foot', x:0, y:32});
	if (ref.haveOverLegs) sprites.push({key: ref.name+'-overlegs',                                              name:'-overlegs', x:0, y:0});
	if (equipement.chest) sprites.push({key: equipement.chest + (ref.specialChest ? "-"+ref.name : ""),         name:'chest', x:0, y:ref.shouldersY-24-16});
	sprites.push({key: ref.name+'-arms',                                                                        name:'-arms', x:0, y:0});
	if (equipement.hand) sprites.push({key: equipement.hand,                                                    name:'hand', x:ref.handX-48, y:ref.handY-48});
	sprites.push({key: ref.name+'-head',                                                                        name:'-head', x:0, y:0});
	if (equipement.head) sprites.push({key: equipement.head + (ref.specialHead ? "-"+ref.name : ""),            name:'head', x:0, y:ref.eyesY-32-16});
	let playerSprites = [];
	for (let sprite of sprites)
		if (game.textures.exists(sprite.key))
			playerSprites.push(new Phaser.GameObjects.Sprite(scene, sprite.x, sprite.y, sprite.key).setName(sprite.name));
	return playerSprites;
}



// ---- Menu de connection

var connectServerButton;

function printConnectServerMenu() {
	title.setVisible(false);
	multiplayerButton.visible = false;
	tactileGUI.setVisible(false);
	addTextField("ip",243,300,200,20,"Adresse IP", false, connectServer).focus();
	connectServerButton = game.add.image(525, 310, 'connect-server').setInteractive({cursor:'pointer'}).on('pointerdown', connectServer);
}

function connectServer() {
	if (getText("ip") == "") {
		displayError("ip", "Aucune adresse IP renseignée");
		return;
	}
	if (connection) {
		connection.onerror = undefined;
		connection.close();
	}
	connection = new WebSocket('ws://'+getText("ip"));
	connection.onopen = function () {
		hideConnectServerMenu();
		console.log(`Connecté à ${this.url} !`);
		dispatchEvent(new CustomEvent('connected', {detail:{}}));
		connection.send('ping');
		printIdMenu();
		connection.onclose = function() {
			display("Connexion avec le serveur perdue");
			console.log(`Déconnecté de ${this.url} !`);
			dispatchEvent(new CustomEvent('disconnected', {detail:{}}));
		}
	};
	connection.onerror = function (error) {
		console.log(error);
		displayError("ip", "Erreur de connexion au serveur : " + error.target.url);
	};
	connection.onmessage = function (message) {
		if (debug) console.log('Server: ' + message.data);
		onMessage(message.data.split(" ")[0], message.data.replace(/\S* /, ""));
	};
}

function hideConnectServerMenu() {
	connectServerButton.destroy();
	remove("ip");
}



// ---- Menu d'identification

var idMode = "iam";
var loginModeButton, registerModeButton, confirmPseudo;

function printIdMenu() {
	leaveServerButton.visible = true;
	loginModeButton = game.add.text(350, 250, 'connexion|').setOrigin(0.5,0.5).setInteractive({cursor:'pointer'}).on('pointerdown', function(){switchIdMode("iam")});
	registerModeButton = game.add.text(450, 250, '|inscription').setOrigin(0.5,0.5).setInteractive({cursor:'pointer'}).on('pointerdown', function(){switchIdMode("illbe")});
	addTextField("pseudo",300,270,200,20,"Pseudonyme", false, function(){get("mdp").focus()}).focus();
	addTextField("mdp",300,300,200,20,"Mot de passe", false, sendPseudo);
	confirmPseudo = game.add.image(400, 350, 'confirm').setInteractive({cursor:'pointer'}).on('pointerdown', sendPseudo);
	switchIdMode(idMode);
}

function switchIdMode(mode) {
	idMode = mode;
	(mode=="iam"?registerModeButton:loginModeButton).setTint(0x444444);
	(mode=="iam"?loginModeButton:registerModeButton).clearTint();
}

function sendPseudo() {
	connection.send(idMode + " " + JSON.stringify({name:getText("pseudo"), password:getText("mdp")}));
}

function hideIdMenu() {
	loginModeButton.destroy();
	registerModeButton.destroy();
	remove("pseudo");
	remove("mdp")
	confirmPseudo.destroy();
}

function leaveServer() {
	hideIdMenu();
	hideMainMenu();
	connection.onclose = function(){};
	connection.close();
	printConnectServerMenu();
}



// ---- Menu principal

function printMainMenu() {
	//leaveServerButton.setVisible(true);
	buttons.set('title', game.add.text(400, 50, me.name+(group!==undefined?" (en groupe)":""), getStyle(4)).setOrigin(0.5));
	buttons.add('player', addPlayerSprite(400, 250, me.className, me.equipement).setScale(4.5));
	buttons.add('left1', game.add.image(116, 166, 'inventory').setInteractive({cursor:'pointer'}).on('pointerdown', function(){hideMainMenu();printInventoryMenu();}));
	buttons.add('left1text', game.add.text(116, 216, 'Inventaire', getStyle(1)).setOrigin(0.5));
	buttons.add('left3', game.add.image(216, 484, 'dungeon').setInteractive({cursor:'pointer'}).on('pointerdown', function(){display("Coming soon !");}));
	buttons.add('left3text', game.add.text(266, 485, 'Explorer', getStyle(3)).setOrigin(0.5));
	buttons.add('right3', game.add.image(584, 484, 'fight').setInteractive({cursor:'pointer'}).on('pointerdown', () => {hideMainMenu();printGameModesPanel();}));
	buttons.add('right3text', game.add.text(634, 485, 'Affronter', getStyle(3)).setOrigin(0.5));
	if (group === undefined) {
		buttons.set('left2', game.add.image(116, 333, 'custom').setInteractive({cursor:'pointer'}).on('pointerdown', function(){hideMainMenu();printGamesMenu();}));
		buttons.set('left2text', game.add.text(116, 384, 'Mode custom', getStyle(1)).setOrigin(0.5));
		buttons.set('right1', game.add.image(684, 166, 'join-group').setInteractive({cursor:'pointer'}).on('pointerdown', printJoinPanel));
		buttons.set('right1text', game.add.text(684, 216, 'Rejoindre', getStyle(1)).setOrigin(0.5));
		buttons.set('right2', game.add.image(684, 333, 'create-group').setInteractive({cursor:'pointer'}).on('pointerdown', function() {connection.send('creategroup');}));
		buttons.set('right2text', game.add.text(684, 384, 'Créer groupe', getStyle(1)).setOrigin(0.5));
		dispatchEvent(new CustomEvent('solo-menu', {detail:{className:me.className}}));
	} else {
		buttons.set('left2', game.add.image(116, 333, 'leave-group').setInteractive({cursor:'pointer'}).on('pointerdown', function() {connection.send('leavegroup');}));
		buttons.set('left2text', game.add.text(116, 384, 'Quitter', getStyle(1)).setOrigin(0.5));
		buttons.set('right1', undefined);
		buttons.set('right1text', game.add.text(684, 216, '', getStyle(1)).setOrigin(0.5));
		buttons.set('right2', undefined);
		buttons.set('right2text', game.add.text(684, 384, '', getStyle(1)).setOrigin(0.5));
		buttons.add('teammate0', addPlayerSprite(600, 150, teamMates[0].className, teamMates[0].equipement).setScale(2.25));
		buttons.add('teammate1', addPlayerSprite(600, 350, teamMates[1].className, teamMates[1].equipement).setScale(2.25));
		buttons.add('teammate2', addPlayerSprite(700, 150, teamMates[2].className, teamMates[2].equipement).setScale(2.25));
		buttons.add('teammate3', addPlayerSprite(700, 350, teamMates[3].className, teamMates[3].equipement).setScale(2.25));
		dispatchEvent(new CustomEvent('group-menu', {detail:{className:me.className, group:group}}));
	}
}

function printGameModesPanel() {
	connection.send('getgamemodes');
	addDiv('gamemodes', 50, 50, 700, 500, [
			addText('gamemodes-title', 0, 3, 8, 'Modes de jeu', 100, 'white', true),
			addDiv('gamemodes-list', 5, 18, 40, 74, [], '', true),
			addDiv('gamemode-preview', 50, 18, 45, 74, [], 'rgba(200,200,200,0.5)', true),
			addButton('close-gamemodes', 0, 0, 8, 6, () => {remove('gamemodes');printMainMenu();}, 'X', 4, 'red', true)
		], 'rgba(100,100,100,0.8)');
	refreshGameModesList();
}

function refreshGameModesList() {
	let gmsEls = [];
	let i = 0;
	for (let gamemode of gamemodes) {
		gmsEls.push(addButton('gamemode'+i, 0, i*10, 100, 8, () => setGameModePreview(gamemode), gamemode.replace('_', ' (')+')', 4.5, 'rgba(200,200,200,0.5)', true));
		i++;
	}
	setDivContent('gamemodes-list', gmsEls);
}

function setGameModePreview(gamemode) {
	let gm = GAMEMODES[gamemode.split('_')[0]] || {name:gamemode, description:'Mode de jeu inconnu :/'};
	let description
	let teams = gamemode.replace(/.*_/gm, '').match(/[0-9]+/gm);
	setDivContent('gamemode-preview', [
			addText('gamemode-title', 0, 5, 4, `${gm.name} (${teams.join('v')})`, 100, 'white', true),
			addText('gamemode-description', 10, 16, 2.5, gm.description.replace('{teamsNb}', teams.length).replace('{membersNb}', teams[0]), 80, 'white', true),
			addButton('gamemode-select', 25, 82, 50, 10, (e) => {connection.send('setgamemode '+gamemode);remove('gamemodes');connection.send('searchladdergame '+gamemode);printMainMenu();}, 'Sélectionner', 5, 'tomato', true)
		]);
}

function printJoinPanel() {
	var joinGroup = function(){connection.send('joingroup ' + getText('join-code').toUpperCase())};
	addDiv('join-group', 32, 45, 36, 10, [
			addTextField('join-code', 8, 30, 50, 40, "code d'équipe", false, joinGroup, true),
			addButton('join-code-button', 62, 30, 30, 40, joinGroup, 'Rejoindre', 3, 'limegreen', true),
			addButton('close-join-group', 0, 0, 7, 24, () => remove('join-group'), 'X', 2, 'red', true)
		], 'rgba(200,200,200,0.5)', true);
}

function hideMainMenu() {
	leaveServerButton.setVisible(false);
	buttons.removeAll('title', 'player', 'left1', 'left1text', 'left2', 'left2text', 'left3', 'left3text');
	buttons.removeAll('right1', 'right1text', 'right2', 'right2text', 'right3', 'right3text');
	remove('gamemodes');
}

function backToMainMenu() {
	hideGamesMenu();
	hideInventoryMenu();
	printMainMenu();
}



//----Menu d'inventaire

function printInventoryMenu() {
	backToMainMenuButton.setVisible(true);
	connection.send('getinventory');
	addDiv('inventory', 50, 50, 300, 500, [], 'rgba(200,200,200,0.5)');
	refreshInventory();
	refreshPlayerView();
	// ---- print classes
	let els = [];
	let i = 0;
	for (let className of Object.keys(CLASSES)) {
		els.push(addImage('class-'+className, 'assets/buttons/'+className+'.png', 1+1+14*i, 7, () => setClass(className), true, 12, 86));
		i++;
	}
	addDiv('classes', 400, 325, 350, 50, els, 'rgba(200, 200, 200, 0.5)');
	addDiv('class-info', 400, 422, 350, 128, [], 'rgba(200,200,200,0.5)');
	refreshClassInfo(me.className);
	dispatchEvent(new CustomEvent('inventory-menu', {detail:{className:me.className}}));
}

function refreshInventory() {
	let itemsEls = [addText('inventory-title', 0, 0, 7, 'Inventaire', 100, 'white', true)];
	let i = 0;
	for (let id of Object.keys(me.inventory)) {
		itemsEls.push(addDiv('item'+i, i%3*33.3+1, Math.floor(i/3)*20+11, 31.3, 18, [
				addImage('item'+i+'-image', 'assets/items/'+id+'.png', 0, 0, () => printItemView(id, me.inventory[id]), true, 100, 100, 'assets/items/null.png'),
				addText('item'+i+'-amount', 7, 50, 6.8, me.inventory[id], 10, 'white', true)
			], 'rgba(200,200,200,0.5)', true));
		i++;
	}
	setDivContent('inventory', itemsEls);
}

function printItemView(id, amount, equiped=undefined) {
	let els = [];
	let item = ITEMS[id];
	els.push(addImage('item-view-image', 'assets/items/'+id+'.png', 0, 0, () => {}, true, 100, 66, 'assets/items/null.png'));
	els.push(addText('item-view-title', 0, 5, 3, item ? item.name+(amount==1?'':` (${amount})`) : id, 100, 'white', true));
	els.push(addText('item-view-description', 0, 55, 2, item?item.description:'', 100, 'white', true));
	if (item && item.isItem) {
		if ((equiped || me.equipement[item.slot] == id) && equiped !== false)
			els.push(addButton('item-view-disequip', 7.5, 86, 85, 9, () => {connection.send('disequip '+item.slot);printItemView(id, amount, false);}, 'Déséquiper', 3, 'whitesmoke', true));
		else els.push(addButton('item-view-equip', 7.5, 86, 85, 9, () => {connection.send('equip '+id);printItemView(id, amount, true);}, 'Équiper', 3, 'whitesmoke', true));
	} else els.push(addText('item-view-material', 5, 86, 2, '(Matériel de fabrication)', 90, 'white', true));
	let i = 0;
	if (item && item.isItem && item.modifiers) for (let mod of item.modifiers) {
		els.push(addText('item-view-modifier'+i, 0, 73+i*5, 2, MODIFIERS[mod.id].name + (mod.value?' '+(mod.value>0?'+':'')+mod.value*100+'%':''), 100, (mod.value<0 ? 'lightcoral' : 'lightgreen'), true));
		i++;
	}
	if (item && item.isItem && item.stats) for (let stat of item.stats) {
		els.push(addText('item-view-stat'+i, 0, 73+i*5, 2, stat.name + (stat.value?' : '+stat.value:''), 100, 'lightgreen', true));
		i++;
	}
	if (item && item.isItem && item.type)
		els.push(addText('item-view-type', 0, 68, 2, TYPES[item.type]?TYPES[item.type].name:item.type, 100, TYPES[item.type]?TYPES[item.type].color:'white', true));
	addDiv('item-view', 600, 50, 150, 225, els, 'rgba(200,200,200,0.5)');	
}

function refreshClassInfo(className) {
	let els = [
		addImage('class-image', `assets/players/${className}.png`, 0, 0, undefined, true, undefined, 100, 'assets/items/null.png'),
		addText('class-name', 25, 0, 5, CLASSES[className].name, 75, 'whitesmoke', true),
		addText('class-type', 30, 22, 2.2, 'Type : '+TYPES[CLASSES[className].type].name, 65, TYPES[CLASSES[className].type].color, true)
	];
	let i = 0;
	for (let stat of CLASSES[className].stats) {
		els.push(addText('class-stat'+i, 30, 34+12*i, 2.5, `${stat.name} : ${stat.value}`, 65, 'whitesmoke', true));
		i++;
	}
	setDivContent('class-info', els);
}

function setClass(className) {
	connection.send("setclass "+ className);
	refreshClassInfo(className);
}

function refreshPlayerView() {
	let player;
	buttons.set('player', player = addPlayerSprite(475, 150, me.className, me.equipement).setScale(3));
	for (let part of player.list) {
		if (!part.name.startsWith('-')) {
			let item = part.texture.key.split('-')[0];
			part.setInteractive({cursor:'pointer', pixelPerfect:true});
			part.on('pointerover', function() {this.setTint(0x888888);});
			part.on('pointerout', function(){this.clearTint();});
			part.on('pointerdown', () => printItemView(item, me.inventory[item], true));
		}
	}
}

function hideInventoryMenu() {
	backToMainMenuButton.setVisible(false);
	remove('inventory');
	remove('item-view');
	buttons.remove('player');
	remove('classes');
	remove('class-info');
}



// ---- Menu de d'attente d'adversaires

function printSearchingMenu() {
	buttons.add('loading', game.add.sprite(400, 300, 'loading').play('loading'));
	addButton('cancel-search', 44, 70, 12, 6, () => connection.send('cancel'), 'Annuler', 4, 'red', true);
	addText('searching-players-number', 0, 30, 3, '', 100, 'white', true);
	connection.send('getsearchingplayersnumber');
	dispatchEvent(new CustomEvent('searching', {detail:{className:me.className, gamemode:game_.gamemode}}));
}

function setSearchingPlayersNumber(n) {
	setText('searching-players-number', `${n} joueurs en attente...`);
}

function hideSearchingMenu() {
	buttons.remove('loading');
	remove('cancel-search');
	remove('searching-players-number');
}



//----Menu parties custom

function printGamesMenu() {
	backToMainMenuButton.setVisible(true);
	addTextField("searchbar",100,50,318,40,"Rechercher des parties", false, searchGames).focus();
	searchGamesButton.visible = true;
	addDiv("games", 100,120,400,400, [], "whitesmoke");
	addTextField("creategame",550,223,200,40,"Nom de la partie à créer", false, createGame);
	createGameButton.visible = true;
	addText("version", 100, 520, 2, '', 400, "whitesmoke");
	searchGames();
	getVersion();
}

function searchGames() {
	connection.send("searchgame " + getText("searchbar"));
}

function joinGame(name) {
	connection.send("joingame " + name)
}

function createGame() {
	connection.send("creategame " + getText("creategame"));
}

function getVersion() {
	connection.send("getversion");
}

function hideGamesMenu() {
	backToMainMenuButton.visible = false;
	removeText("searchbar");
	searchGamesButton.visible = false;
	removeText("games");
	removeText("creategame");
	createGameButton.visible = false;
	remove("version");
}



//----Menu d'une partie

function printGameMenu(name) {
	leaveGameButton.visible = true;
	addText("gamename", 164, 40, 10, name, 472, "whitesmoke");
	addTextArea("chat", 100, 50+64+30, 400, 350-64, "Bienvenue dans cette nouvelle partie !", true);
	addTextField("sendchat", 100, 460, 400-32-50, 40, "Envoyez un chat pour communiquer", false, sendChat);
	sendChatButton.visible = true;
	connection.send("getchat");
	addTextArea("players", 550, 50+64+30, 150, 250-64, "", true);
	connection.send("getplayers");
	addMapPreview("map",[],550,50+30+250+30, 150, 70);
	connection.send("getmapandteams");
	connection.send("whoiscreator");
	dispatchEvent(new CustomEvent('custom-game', {detail:{className:me.className, gameName:name}}));
}

function leaveGame() {
	connection.send("leavegame");
}

function addChat(line) {
	if (line) chat.push(line);
	var text = chat.length > 0 ? chat[0] : "";
	for (var i = 1; i < chat.length; i++)
		text += "\n" + chat[i];
	setText("chat", text);
}

function refreshPlayers() {
	var text = players.length > 0 ? players[0].name : "";
	for (var i = 1; i < players.length; i++)
		text += "\n" + players[i].name;
	setText("players", text);
}

function sendChat() {
	connection.send("sendchat " + getText("sendchat"));
	setText("sendchat", "");
}

function hideGameMenu(toConfig) {
	leaveGameButton.visible = false;
	if (!toConfig) {
		remove("gamename");
		remove("map");
	}
	remove("chat");
	remove("sendchat");
	sendChatButton.visible = false;
	remove("players");
	configGameButton.visible = false;
}

// apres la fin d'une partie
function returnToGameMenu(){
	printGameMenu();
	remove("result");
}



//----Menu de configuration d'une partie

function configGame() {
	hideGameMenu(true);
	backToGameMenuButton.visible = true;
	addTextArea("mapJson", 100, 50+64+30, 400, 200, "Carte de jeu et équipes au format JSON", false, sendMapAndTeams);
	sendMapAndTeamsButton.visible = true;
	startGameButton.visible = true;
}

function sendMapAndTeams() {
	connection.send("setmapandteams " + getText("mapJson"));
}

function startGame() {
	connection.send("start");
}

function backToGameMenu() {
	hideConfigGame(true);
	printGameMenu();
}

function hideConfigGame(toGameMenu) {
	backToGameMenuButton.visible = false;
	if (!toGameMenu) {
		remove("gamename");
		remove("map");
	}
	remove("mapJson");
	sendMapAndTeamsButton.visible = false;
	startGameButton.visible = false;
}



//----Menu de choix d'équipe

function printTeamMenu() {
	var teamsEls = [];
	var w = (100-2*(game_.teams.length-1))/game_.teams.length;
	var h = 350;
	for (var i = 0; i < game_.teams.length; i++) {
		teamsEls.push(addDiv("team"+i, (w+2)*i, 0, w, 100, [addText("team"+i+"name", 5, 5, 3, game_.teams[i], 90, "black", true), addTextArea("team"+i+"members", 0, 15, 100, 55, "Aucun joueur", true, undefined, true), addButton("team"+i+"join", 0, 75, 100, 20, () => joinTeam(game_.teams[i]), "Rejoindre", 3, "limegreen", true)], "whitesmoke", true));
	}
	addDiv("teams", 50, 150, 700, h, teamsEls);
}

function joinTeam(name) {
	connection.send("jointeam " + name);
}

function hideTeamMenu() {
	remove("teams");
	remove("waiting");
}



//----Menu de choix de classe

function printClassMenu() {
	warriorClassChoose.visible = true;
	archerClassChoose.visible = true;
	golemClassChoose.visible = true;
	mageClassChoose.visible = true;
	assassinClassChoose.visible = true;
	invocateurClassChoose.visible = true;
	randomClassChoose.visible = true;
	alchimisteClassChoose.visible = true;
	previewClass.visible = true;
	classStats.visible = true;
	capacityStats.visible = true;
}

function getClassStats(className) {
	connection.send("getclassstats " + className);
	previewClass.visible = true;
	classStats.visible = true;
	selectedClassName = className;
	capacityStats.setText("");
	let showCapa = className != "random";
	capacity0Button.visible = showCapa;
	capacity1Button.visible = showCapa;
	capacity2Button.visible = showCapa;
	capacity3Button.visible = showCapa;
	chooseClassButton.visible = !showCapa;
}

function printClassStats(stats) {
	previewClass.setTexture(stats.imageName);
	classStats.setText([stats.name.toUpperCase(), "Vie : "+stats.health, "Vitesse : "+stats.speed, "Dégâts : "+stats.damage, "Energie : "+stats.energy, "Coût d'une attaque : "+stats.attackCost]);
	capacity0Button.setTexture(stats.capacities[0].imageName+"-button");
	capacity1Button.setTexture(stats.capacities[1].imageName+"-button");
	capacity2Button.setTexture(stats.capacities[2].imageName+"-button");
	capacity3Button.setTexture(stats.capacities[3].imageName+"-button");
	capacities = stats.capacities;
}

function printCapacityStats(n) {
	capacityStats.setText([capacities[n].name.toUpperCase(), capacities[n].description, "Coût : "+capacities[n].cost]);
	chooseClassButton.visible = true;
}

function chooseClass(className, capacityN) {
	className = className || "random";
	connection.send("setclass " + className);
	connection.send("setcapacity " + (className=="random" ? 0 : capacityN));
}

function hideClassMenu() {
	warriorClassChoose.visible = false;
	archerClassChoose.visible = false;
	golemClassChoose.visible = false;
	mageClassChoose.visible = false;
	assassinClassChoose.visible = false;
	invocateurClassChoose.visible = false;
	randomClassChoose.visible = false;
	alchimisteClassChoose.visible = false;
	previewClass.visible = false;
	classStats.visible = false;
	capacityStats.visible = false;
	chooseClassButton.visible = false;
	capacityStats.visible = false;
	capacity0Button.visible = false;
	capacity1Button.visible = false;
	capacity2Button.visible = false;
	capacity3Button.visible = false;
	remove("waiting");
}



//-------affichage d'une partie(jeu)

function preStart() {
	displayMap(game_.map.pattern);
	connection.send("getmyentityid");
	connection.send("getentities");
	healthBar = game.add.image(42, 8, "healthBar");
	healthBar.setDepth(Infinity);
	energyBar = game.add.image(42, 34, "energyBar");
	energyBar.setDepth(Infinity);
	specialBar = game.add.image(42, 60, "specialBar");
	specialBar.setDepth(Infinity);
	connection.send("getath");
	tactileGUI.setVisible(true);
	game.input.setDefaultCursor('crosshair');
	isPlaying = true;
	dispatchEvent(new CustomEvent('starting-game', {detail:{className:me.className, gamemode:game_.gamemode}}));
}

function start() {
	connection.send("getmyentityid");
	connection.send("getath");
	isBindingKeys = true;
	dispatchEvent(new CustomEvent('game-started', {detail:{className:me.className, gamemode:game_.gamemode}}));
}

function rebrawl() {
	isSpectator = false;
	hideTeamMateSpectating();
	remove('capture-bar');
	connection.send("getath");
	connection.send("getmyentityid");
	healthBar.setVisible(true);
	energyBar.setVisible(true);
	specialBar.setVisible(true);
	isBindingKeys = true;
	isPlaying = true;
	tactileGUI.setVisible(true);
}

function displayMap(pattern) {
	frags = game.add.group();
	var first = -12 // selon map.png
	for (var i = 0 ; i < pattern.length ; i++)
		for (var j = 0 ; j < pattern[i].length ; j++) {
			var frag = frags.create(j*64-(pattern[i][j]-first)%4*64, i*48-48-Math.floor((pattern[i][j]-first)/4)*96, 'map');
			frag.setOrigin(0,0);
			frag.setDepth(pattern[i][j] > 1 ? (i*64+32)*3/4 : 0);
			frag.setCrop((pattern[i][j]-first)%4*64,Math.floor((pattern[i][j]-first)/4)*96,64,96);
		}
	//camera.setBounds(0, 0, (pattern.length==0?0:pattern[0].length*64), pattern.length*48);
}

function refreshEntities(entitiesFromServer) {
	if (!isPlaying && !isSpectator) return;
	for (let entity of entities) {
		var toRemove = true;
		for (var i = 0; i < entitiesFromServer.length; i++)
			if (entity.id == entitiesFromServer[i].id)
				toRemove = false;
		if (toRemove) {
			if (entity) entity.stop();
			entities.remove(entity);
			entity.destroy();
			//console.log("entity removed "+entity.id)
		}
	}
	for (var i = 0; i < entitiesFromServer.length; i++) {
		//entitiesFromServer[i].y *= 3/4;
		if (entities.get("id", entitiesFromServer[i].id)) {
			var entity = entities.get("id", entitiesFromServer[i].id);
			entity.refresh(entitiesFromServer[i].x, entitiesFromServer[i].y, entitiesFromServer[i].image, entitiesFromServer[i].visible, entitiesFromServer[i].player);
			//console.log("entity edited "+entity);
		} else {
			var entity = new Entity(game, entitiesFromServer[i].x, entitiesFromServer[i].y, entitiesFromServer[i].image, entitiesFromServer[i].id, entitiesFromServer[i].visible, entitiesFromServer[i].player, entitiesFromServer[i].team);
			game.children.add(entity);
			entities.push(entity);
			//console.log("entity created "+entity);
		}
	}
	if (camera._follow) {
		healthBar.setPosition(camera._follow.x, camera._follow.y-40-8);
		energyBar.setPosition(camera._follow.x, camera._follow.y-40);
		specialBar.setPosition(camera._follow.x, camera._follow.y-40+8);
	}
	
}

function refreshHealth(newHealth) {
	if (newHealth !== health) {
		printDamage(camera._follow.x, camera._follow.y, newHealth-health, newHealth<health?"red":"lime");
	}
	health = newHealth;
	let cropWidth = (health*64/healthMax);
	healthBar.setCrop(0, 0, cropWidth, 8);
}

function refreshEnergy(newEnergy) {
	energy = newEnergy
	let cropWidth = (energy*64/energyMax);
	energyBar.setCrop(0, 0, cropWidth, 8);
}

function refreshSpecial(newSpecial) {
	special = newSpecial;
	let cropWidth = (special*64/specialMax);
	specialBar.setCrop(0, 0, cropWidth, 8);
}

function printDamage(x, y, value, color="white") {
	let damageInfo = game.add.text(x+Phaser.Math.Between(-48,48), y+Phaser.Math.Between(-48,48), value+"♥")
		.setFontFamily("Arial").setColor(color).setFontSize(32).setOrigin(0.5).setDepth(Infinity)
		.setRotation(Phaser.Math.FloatBetween(-0.2, 0.2));
		setTimeout(function() {
				damageInfo.destroy();
			}, 1000);
}

function addTint(id, color) {
	addDiv(id, 0, 0, 100, 100, [], color, true);
}

function onEffect(data) {
	switch (data.name) {
		case "tracking":
			camera.zoomTo(0.5, 200, true);
			break;
		case "stun":
			camera.shake(camera.shake(data.duration, 0.01));
			break;
		case "poison":
			//addTint('effect-'+data.name, 'rgba(200,0,200,0.2)');
			break;
		case "burn":
			//addTint('effect-'+data.name, 'rgba(200,0,0,0.2)');
			break;
		case "berserk":
		case "parry":
	}
	setTimeout(function() {
		switch (data.name) {
			case "tracking":
				camera.zoomTo(1, 200, true);
				break;
		}
		remove('effect-'+data.name);
	}, data.duration);
}

function hideATH() {
	healthBar.visible = false;
	energyBar.visible = false;
	specialBar.visible = false;
}

function printTabMenu() {
	let teamsEls = [addDiv("tab-title", 1, 1, 98, 8, [addText("tab-title-name", 2, 2, 4, "Équipes", 76, "white", true), addText("tab-title-score", 82, 2, 4, "Scores", 16, "white", true)], "rgba(200,200,200,0.5)", true)];
	for (let i = 0; i < game_.teams.length; i++)
		teamsEls.push(addDiv("tab-team"+i, 1, 11+10*i, 98, 8, [addText("tab-team"+i+"-name", 2, 2, 4, game_.teams[i].name, 76, game_.teams[i].color, true), addText("tab-team"+i+"-score", 82, 2, 4, game_.teams[i].score, 16, game_.teams[i].color, true)], "rgba(200,200,200,0.5)", true));
	addDiv("tab", 200, 100, 400, 400, teamsEls, "rgba(200,200,200,0.8)");
}

function hideTabMenu() {
	remove("tab");
}

function hideGame() {
	isBindingKeys = false;
	isPlaying = false;
	frags.destroy(true);
	for (let i = entities.length-1; i >= 0; i--) {
		if (entities[i]) {
			entities[i].stop();
			entities[i].destroy();
		}
		entities.remove(entities[i]);
	}
	remove('capture-bar');
	hideATH();
	tactileGUI.setVisible(false);
	hideTabMenu();
	game.input.setDefaultCursor('');
	hideTeamMateSpectating();
	isSpectator = false;
	camera._follow = undefined;
	camera.centerOn(400,300);
}



//----Spectator mode

function printTeamMateSpectatingMenu(){
	/*var teamMatesEls = [];
	for (var i = 0; i < teamMates.length; i++){
		teamMatesEls.push(
			addDiv("teammate"+i, i*55, 0, 50, 400, 
				[addText("teammateClass"+i, 0, 0, 4, teamMates[i].name, i*55, "", 50, 400),
				addButton("chooseWhoSpect"+i, 0, 30, 50, 40, () => spectatorView(teamMates[i].name), "Suivre ce joueur", 3, "", 50, 400)]
			,"",600,400 )
			);
	}
	addDiv("teammates", 100, 100, 600, 400, teamMatesEls,"white");
	spectatorMenuButton.visible = false;*/
}

function spectatorView(nom){
	connection.send("follow "+nom);
	connection.send("getfollowingentityid");
	hideTeamMateSpectating();
}

function hideTeamMateSpectating(){
	remove("teammate");
	remove("teammateClass");
	remove("chooseWhoSpect");
	remove("teammates");
	spectatorMenuButton.visible = false;
	
}



// ---- Menu de résultats

function printResultMenu(result) {
	let theme = {};
	if (result == 'draw') theme = {text:'Égalité ?', color:'gold'};
	if (result == 'win') theme = {text:'Victoire !', color:'dodgerblue'};
	if (result == 'lose') theme = {text:'Défaite...', color:'lightcoral'};
	addText('result', 0, 10, 22, theme.text, 100, theme.color, true);
	addButton('result-ok', 40, 78, 20, 10, () => {hideResultMenu();printMainMenu();}, 'Ok', 8, theme.color, true);
	dispatchEvent(new CustomEvent('game-ended', {detail:{className:me.className, result:theme.text}}));
}

function setLoot(loot={}) {
	let loots = [];
	if (loot.xp && loot.xp != 0) loots.push({name:'XP', amount:loot.xp, image:'assets/xp.png'});
	if (loot.elo && loot.elo != 0) loots.push({name:'Élo', amount:loot.elo, image:'assets/elo.png'});
	if (loot.items) for (let item of Object.keys(loot.items))
		loots.push({name:ITEMS[item]?ITEMS[item].name:item, amount:loot.items[item], image:`assets/items/${item}.png`});
	let lootEls = [addText('result-loot-text', 2, 4, 4, 'Vous avez obtenu :', 96, 'whitesmoke', true)];
	for (let i = 0; i < loots.length; i++)
		lootEls.push(addDiv(`result-loot${i}`, 2+20*(i+Math.max((5-loots.length)/2, 0)), 28, 16, 64, [
			addImage(`result-loot${i}-image`, loots[i].image, 0, 0, undefined, true, 100, 100, 'assets/items/null.png'),
			addText(`result-loot${i}-amount`, 7, 50, 6.5, loots[i].amount!=1?loots[i].amount:'', 10, 'whitesmoke', true)
		], 'rgba(200,200,200,0.5)', true));	
	addDiv('result-loot', 100, 260, 600, 150, lootEls, 'rgba(200,200,200,0.5)', false)
}

function hideResultMenu() {
	remove('result');
	remove('result-loot');
	remove('result-ok');
}



// ---- Gestion des entrées clavier, souris et tactile

function switchInputMode() {
	isTactile = !isTactile;
	if (isTactile) {
		joystick.setVisible(true);
		joystickBack.setVisible(true);
		specialButton.setVisible(true);
	} else {
		joystick.followCursor = false;
		joystick.setVisible(false);
		joystickBack.setVisible(false);
		specialButton.setVisible(false);
		specialLoaded.setVisible(false);
		nextIsSpecial = false;
	}
}

function onKeyDown(e) {
	if (!isBindingKeys) return;
	switch (e.key) {
		case 'z':
		case 'ArrowUp':
			keys.up = true;
			break;
		case 'q':
		case 'ArrowLeft':
			keys.left = true;
			break;
		case 's':
		case 'ArrowDown':
			keys.down = true;
			break;
		case 'd':
		case 'ArrowRight':
			keys.right = true;
			break;
		case 'Tab':
			if (!exists("tab")) connection.send("getteams");
			printTabMenu();
			break;
		default:
			return;
	}
	e.preventDefault();
	sendMove(getAngleInRadians(getKeyboardVector()));
}

function onKeyUp(e) {
	switch (e.key) {
		case 'z':
		case 'ArrowUp':
			keys.up = false;
			break;
		case 'q':
		case 'ArrowLeft':
			keys.left = false;
			break;
		case 's':
		case 'ArrowDown':
			keys.down = false;
			break;
		case 'd':
		case 'ArrowRight':
			keys.right = false;
			break;
		case 'Tab':
			hideTabMenu();
			break;
		default:
			return;
	}
	if (!isBindingKeys) return;
	e.preventDefault();
	sendMove(getAngleInRadians(getKeyboardVector()));
}

function getAngleInRadians(vector) {
	if (vector.x == 0 && vector.y == 0) return undefined;
	return Math.atan2(vector.y, vector.x);
}

function getKeyboardVector() {
	var v = {x:0, y:0};
	if (keys.up) v.y--;
	if (keys.down) v.y++;
	if (keys.right) v.x++;
	if (keys.left) v.x--;
	return v;
}

function sendMove(angle) {
	if (angle == lastMove) return;
	else lastMove = angle;
	if (angle == undefined) connection.send("stopmove");
	else connection.send("move " + angle/Math.PI);
}

function onClick(pointer) {
	if (isTactile) {
		if (pointer.x < 200 && pointer.y > 400) { // joystick
			joystick.setPosition(pointer.x, pointer.y);
			joystickBack.setPosition(pointer.x, pointer.y);
			joystick.followCursor = pointer;
		} else {
			if (!isBindingKeys) return;
			if (!nextIsSpecial)
				sendClick(pointer.x, pointer.y);
			else {
				sendLeft(pointer.x, pointer.y);
				nextIsSpecial = false;
				specialLoaded.setVisible(false);
			}
		}
	} else {
		if (!isBindingKeys) return;
		if (pointer.buttons == 1)
			sendClick(pointer.x, pointer.y);
		else if (pointer.buttons == 2)
			sendLeft(pointer.x, pointer.y);
	}
}

function unClick(pointer) {
	if (isTactile) {
		if (joystick.followCursor && pointer.id==joystick.followCursor.id) {
			joystick.followCursor = false;
			joystick.setPosition(joystickBack.x, joystickBack.y);
			if (isBindingKeys) sendMove(undefined);
		}
	}
}

function onSpecialButton(pointer, a, b, propa) {
	if (!isBindingKeys) return;
	nextIsSpecial = !nextIsSpecial && special>=specialMax-20;
	specialLoaded.setVisible(nextIsSpecial);
	propa.stopPropagation();
}

function sendClick(x, y) {
	connection.send("attack " + getAngleInRadians({x: x/camera.zoom-camera._follow.x+camera.worldView.x, y:(y/camera.zoom-camera._follow.y+camera.worldView.y)*4/3})/Math.PI);
}

function sendLeft(x, y) {
	connection.send("specialattack " + getAngleInRadians({x: x/camera.zoom-camera._follow.x+camera.worldView.x, y:(y/camera.zoom-camera._follow.y+camera.worldView.y)*4/3})/Math.PI);
}



//----Autres

function switchFullscreen() {
	if (game.scale.isFullscreen) {
		game.scale.stopFullscreen();
	} else {
		game.scale.startFullscreen();
	}
}



//----Entity class

class Entity extends Phaser.GameObjects.Container {
	
	constructor (scene, x, y, image, id, visible, player, team) {
		let sprite = player ? addPlayerSprite(0,0,image.name,player.equipement) : new Phaser.GameObjects.Sprite(scene, 0, 0, image.name+image.suffix);
		super(scene, x, y=y*3/4, sprite);
		this.sprite = sprite;
		if (!image.name) image.name = "";
		if (!image.suffix) image.suffix = "";
		if (!image.rotation) image.rotation = 0;
		if (!image.flip) image.flip = false;
		this.setDepth(image.onGround ? 0 : this.y);
		this.image = image;
		this.id = id;
		//this.sprite.setTexture(image.name+image.suffix);
		this.sprite.setRotation(image.rotation * Math.PI);
		//this.sprite.setFlipY(image.flip);
		this.setScale(1.33,1);
		if (!visible) this.setAlpha(0.2);
		this.isPlayable = (image.name=="guerrier"||image.name=="archer"||image.name=="golem"||image.name=="assassin"||image.name=="mage"||image.name=="necromancien"||image.name=="alchimiste");
		this.isMoving = false;
		if (this.isPlayable) this.moveSound = game.sound.add('run');
		if (player && id != myEntityId)
			this.add(new Phaser.GameObjects.Text(game, 0, -48, player.name+"\n"+(team?team.name:''), getStyle(1, team?team.color:"white")).setOrigin(0.5));
	}
	
	refresh(x, y, image, visible, player) {
		this.isMoving = (this.x != x || this.y != y*3/4);
		this.setPosition(x, y*3/4);
		this.setDepth(image.onGround ? 0 : this.y);
		//this.sprite.setTexture(image.name+image.suffix);
		this.sprite.setRotation(image.rotation * Math.PI);
		//this.sprite.setFlipY(image.flip);
		this.setAlpha(visible ? 1 : 0.2);
		if (this.moveSound) {
			this.moveSound.setVolume((1-(Math.sqrt((x-camera.worldView.x-400)*(x-camera.worldView.x-400)+(this.y-camera.worldView.y-300)*(this.y-camera.worldView.y-300)))/500)/4);
			//console.log((1-(Math.sqrt((x-camera.worldView.x-400)*(x-camera.worldView.x-400)+(y-camera.worldView.y-300)*(y-camera.worldView.y-300)))/500)/4);
			if (this.isMoving && !this.moveSound.isPlaying) {
				this.moveSound.resume();
				if (!this.moveSound.isPlaying) this.moveSound.play();
			}
			if (!this.isMoving && this.moveSound.isPlaying) this.moveSound.pause();
		}
		//if (this.name) this.name.setPosition(x, y-48).setDepth(y);
	}
	
	stop() {
		if (this.moveSound) this.moveSound.stop();
		if (this.name) this.name.destroy();
	}
	
	preUpdate(time, delta){
		super.preUpdate(time, delta);
	}
	
}



// ---- fonctions utiles diverses

function invertHex(hex) {
	return (Number(`0x1${hex}`) ^ 0xFFFFFF).toString(16).substr(1).toUpperCase();
}










