// PTLC 2019 CC BY-NC-SA

//~ const GAME = Object.freeze({NATIVE: 1; WEBGL: 2});

class Game {

	constructor(id, load, create, update, delta=16) {
		
		this.id = id;
		this.canvas = document.getElementById(id);
		
		this.ctx = this.canvas.getContext('2d');
		
		this.load = load || function() {};
		this.create = create || function() {};
		this.update = update || function() {};
		
		this.sprites = [];
		
		canvas.addEventListener('load', function() {
			this.create();
			setInterval(function() {
				update();
				for (let i = 0; i < this.sprites.length; i++) {
					let sprite = this.sprites[i];
					
				}
			}, delta);
		});
		
		this.load();
		
	}
	
	
	
}
