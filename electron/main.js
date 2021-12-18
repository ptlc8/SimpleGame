console.log('Start');

const {BrowserWindow, app} = require('electron');
//const path = require('path');

const dClient = require('discord-rich-presence')('615215122452971521');

var win;

function createWindow() {
	win = new BrowserWindow({frame:false, width:800, height:622, minWidth:412, minHeight:300, transparent:false, show: false, backgroundColor:'#111111', icon:'icon.ico', webPreferences:{nodeIntegration:true}});
	win.on('close', () => win = null);
	win.dClient = dClient;
	win.loadFile('index.html');
	win.once('ready-to-show', () => win.show());
}

app.on('ready', () =>
	createWindow()
);

app.on("activate", () =>
	win === null && createWindow()
);

app.on("window-all-closed", () =>
	process.platform !== "darwin" && app.quit()
);
