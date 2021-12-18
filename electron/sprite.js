const spritesReference = {
	guerrier: {name: 'guerrier', eyesY: 34, shouldersY:43, handX:37, handY:64, flipHand:true},
	archer: {name: 'archer', eyesY: 34, shouldersY:43, handX:64, handY:43, flipHand:false},
	assassin: {name: 'assassin', eyesY: 34, shouldersY:44, handX:64, handY:43, flipHand:false},
	mage: {name: 'mage', eyesY:30, shouldersY:39, chestScale:1.1, handX:34, handY:62, flipHand:true},
	golem: {name: 'golem', eyesY:29, specialChest:true, shouldersY:31, specialLegs:true, specialFoot: true, handX:26, handY:67, flipHand:true},
	necromancien: {name: 'necromancien', eyesY:33, shouldersY: 42, haveOverLegs: true, specialLegs:true, specialFoot: true, handX:72, handY:57},
	alchimiste: {name:'assassin', eyesY: 34, shouldersY:43, handX:64, handY:43, flipHand:false}
}
async function getSprite(className, hat='', chest='', legs='', foot='', hand='') {
	let promise = new Promise(async(resolve, reject) => {
		var ref = spritesReference[className] || spritesReference['guerrier'];
		canvas = document.createElement('canvas');
		canvas.width = 96;
		canvas.height = 96;
		var ctx = canvas.getContext('2d');
		//document.body.appendChild(canvas);
		
		//ctx.drawImage(await get(ref.name), 0, 0);
		let images = [
			{img:await getImage(chest+'-back' + (ref.specialChest ? '-'+ref.name : '')), x:16, y:ref.shouldersY-24},
			{img:await getImage(ref.name+'-body'), x:0, y:0},
			{img:await getImage(legs + (ref.specialLegs ? "-"+ref.name : "")), x:16, y:ref.Y-32},
			{img:await getImage(foot + (ref.specialFoot ? "-"+ref.name : "")), x:16, y:48},
			{img:await getImage(ref.name+'-overlegs'), x:0, y:0},
			{img:await getImage(chest + (ref.specialChest ? "-"+ref.name : "")), x:16, y:ref.shouldersY-24},
			{img:await getImage(ref.name+'-arms'), x:0, y:0},
			{img:await getImage(hand), x:ref.handX-32, y:ref.handY-32},
			{img:await getImage(ref.name+'-head'), x:0, y:0},
			{img:await getImage(hat + (ref.specialHead ? "-"+ref.name : "")), x:16, y:ref.eyesY-32}
		]
		for (image of images)
			if (image.img !== undefined)
				ctx.drawImage(image.img, image.x, image.y);
		
		resolve(canvas.toDataURL());
	});
	return promise;
}

async function getImage(name) {
	if (name == '' || name.startsWith('-')) return undefined;
	let promise = new Promise((resolve, reject) => {
		let image = new Image();
		image.src = 'assets/players/' + name + '.png';
			image.onload = () => {
				//console.log("loaded " + name);
				resolve(image);
			}
			image.onerror = () => {
				console.log("can't load " + name);
				resolve(undefined);
			}
		});
	return promise;
}
