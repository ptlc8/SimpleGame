
var dClient = require('electron').remote.getCurrentWindow().dClient;


addEventListener('game-loaded', (e) =>
	dClient.updatePresence({
		details: 'Menu de connexion',
		largeImageKey: 'bg',
		largeImageText: '^^',
		startTimestamp: Date.now()
	})
);
addEventListener('connected', (e) =>
	dClient.updatePresence({
		details: 'Menu de connexion',
		largeImageKey: 'bg',
		largeImageText: 'owo',
		startTimestamp: Date.now()
	})
);
addEventListener('disconnected', (e) =>
	dClient.updatePresence({
		details: 'Menu de connexion',
		state: 'Vient de se déconnecter',
		largeImageKey: 'bg',
		largeImageText: 'ono',
		startTimestamp: Date.now()
	})
);
addEventListener('solo-menu', (e) =>
	dClient.updatePresence({
		details: 'Menu principal',
		state: 'Seul',
		largeImageKey: 'main-menu',
		largeImageText: 'Menu principal',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now()
	})
);
addEventListener('group-menu', (e) =>
	dClient.updatePresence({
		details: 'Menu principal',
		state: 'En équipe',
		largeImageKey: 'main-menu',
		largeImageText: 'Menu principal',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		partyId: e.detail.group.code.substring(0,8),
		partySize: e.detail.group.size,
		partyMax: e.detail.group.max,
		startTimestamp: Date.now(),
		joinSecret: e.detail.group.code
	})
);
addEventListener('inventory-menu', (e) =>
	dClient.updatePresence({
		details: 'Inventaire',
		state: 'Inspection !',
		largeImageKey: 'inventory',
		largeImageText: 'Inventaire',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now()
	})
);
addEventListener('searching', (e) =>
	dClient.updatePresence({
		details: 'Recherche d\'adversaires',
		state: e.detail.gamemode,
		largeImageKey: 'in-game',
		largeImageText: 'Recherche...',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now()
	})
);
addEventListener('custom-game', (e) =>
	dClient.updatePresence({
		details: 'Partie custom',
		state: e.detail.gameName,
		largeImageKey: 'main-menu',
		largeImageText: 'Partie custom',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now()
	})
);
addEventListener('starting-game', (e) =>
	dClient.updatePresence({
		details: '3... 2... 1...',
		state: e.detail.gamemode,
		largeImageKey: 'in-game',
		largeImageText: 'En jeu !',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`
	})
);
addEventListener('game-started', (e) =>
	dClient.updatePresence({
		details: 'En jeu !',
		state: e.detail.gamemode,
		largeImageKey: 'in-game',
		largeImageText: 'En jeu !',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now(),
		//endTimestamp: Date.now() + 90*60*1000,
		instance: undefined
	})
);
addEventListener('game-ended', (e) =>
	dClient.updatePresence({
		details: 'Fin d\'une partie',
		state: e.detail.result,
		largeImageKey: 'in-game',
		largeImageText: 'Fin de partie',
		smallImageKey: e.detail.className,
		smallImageText: `joue ${e.detail.className}`,
		startTimestamp: Date.now()
	})
);

dClient.on('join', (secret) => {
	console.log('we should join with ', secret);
	connection.send(`joingroup ${secret}`);
});

dClient.on('spectate', (secret) => {
	console.log('we should spectate with ', secret);
	// not yet implemented
});

dClient.on('joinRequest', (user) => {
	addDiv('join-group', 30, 40, 40, 20, [
			addImage('joining-user-pp', `https://cdn.discordapp.com/avatars/${user.id}/${user.avatar}.png`, 6, 12, undefined, true, 13.5, 38),
			addText('joining-user', 25.5, 10, 3, `${user.username}#${user.discriminator} veut rejoindre ton équipe`, 68.5, "whitesmoke", true),
			addButton('joining-user-ignore', 6, 62, 26, 26, () => dClient.reply(user, 'IGNORE'), 'Ignorer', 3, 'darkgray', true),
			addButton('joining-user-yes', 37, 62, 26, 26, () => dClient.reply(user, 'YES'), 'Accepter', 3, 'limegreen', true),
			addButton('joining-user-no', 68, 62, 26, 26, () => dClient.reply(user, 'NO'), 'Refuser', 3, 'lightcoral', true)
		], 'rgba(200,200,200,0.5)', true);
	console.log('joinRequest ? ' + user);
});
