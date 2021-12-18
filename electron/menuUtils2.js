var _defaultDiv = document.body;
function setDefaultAddContentDiv(sel) {
	_defaultDiv = document.querySelector(sel);
}
// a value to convert in % and an addio(n)nal value to add in px
// for value with difference caused by padding, margin, border...
function _getCSSValueW(v, a, percent) {
	ref = percent ? 100 : 800;
	if (!a) return (v*100/ref)+'%';
	if (!v) return (a)+'px';
	return 'calc('+(v*100/ref)+'% + '+(a)+'px)';
}
function _getCSSValueH(v, a, percent) {
	ref = percent ? 100 : 600;
	if (!a) return (v*100/ref)+'%';
	if (!v) return (a)+'px';
	return 'calc('+(v*100/ref)+'% + '+(a)+'px)';
}
function addTextField(id, x=0, y=0, width=160, height=20, hint='', disabled=false, onEnter=false, percent=false) {
	remove(id);
	var textField = document.createElement('input');
	textField.setAttribute('type', 'text');
	textField.setAttribute('id', id);
	textField.setAttribute('placeholder', hint);
	textField.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+';width:'+_getCSSValueW(width, -6, percent)+';height:'+_getCSSValueH(height, -6, percent)+';border-width:2px');
	if (disabled) textField.disabled = 'disabled';
	if (onEnter) textField.addEventListener('keyup', function(event) {
			event.preventDefault();
			if (event.keyCode === 13) onEnter();
		 });
	_defaultDiv.appendChild(textField);
	return textField;
}
function addTextArea(id, x=0, y=0, width=240, height=180, hint='', disabled=false, onEnter=false, percent=false) {
	remove(id);
	var textArea = document.createElement('textarea');
	textArea.setAttribute('id', id);
	textArea.setAttribute('placeholder', hint);
	textArea.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+';width:'+_getCSSValueW(width, -6, percent)+';height:'+_getCSSValueH(height, -6, percent)+'; resize: none;border-width:1px');
	if (disabled) textArea.disabled = disabled;
	if (onEnter) textArea.addEventListener('keyup', function(event) {
			event.preventDefault();
			if (event.keyCode === 13) onEnter();
		 });
	_defaultDiv.appendChild(textArea);
	return textArea;
}
function getText(id) {
	var text = document.getElementById(id);
	if (text !== null) return text.value;
	else return '';
}
function setText(id, value) {
	var text = document.getElementById(id);
	if (text !== null) text.textContent = value;
}
function remove(id) {
	var el = document.getElementById(id);
	if (el !== null) {
		el.parentNode.removeChild(el);
		removeText('_error_' + id);
	}
}
//deprecated
function removeText(id) {
	var text = document.getElementById(id);
	if (text !== null) {
		text.parentNode.removeChild(text);
		removeText('_error_' + id);
	}
}
//deprecated
function getTextFieldText(id) {
	return getText(id);
}
//deprecated
function removeTextField(id) {
	removeText(id);
}
var _kId = 0;
function display(text='', size=1, time=4000, allWidth=false) {
	var id = '_display'+_kId++;
	var textToD = document.createElement('span');
	textToD.setAttribute('id', id);
	textToD.setAttribute('style','position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);font-size:'+size*5+'%;font-family: Elected Office, Arial;'+(allWidth?'width:100%':''));
	textToD.className = 'display';
	textToD.innerHTML = text;
	_defaultDiv.appendChild(textToD);
	setTimeout(function() {
		remove(id);
	}, time);
	return textToD;
}
function displayError(fieldId, error) {
	var field = document.getElementById(fieldId);
	var id = '_error_'+fieldId;
	remove(id);
	var err = document.createElement('span');
	err.setAttribute('id', id);
	err.style.position = 'absolute';
	if (getComputedStyle)
		err.style.top = _getCSSValueH(0, parseFloat(getComputedStyle(field, null).top)+parseFloat(getComputedStyle(field, null).height)+10);
	else
		err.style.top = _getCSSValueH(parseFloat(field.style.top), -20);
	err.style.left = field.style.left;
	err.style.fontSize = '2%';
	err.style.color = 'red';
	err.textContent = error;
	field.parentElement.append(err);
	setTimeout(function() {
		remove(id);
	}, 5000);
	return err;
}
function addMapPreview(id, pattern, x=0, y=0, width=200, height=200, percent=false) {
	remove(id);
	var mapPreview = document.createElement('canvas');
	mapPreview.setAttribute('id', id);
	mapPreview.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+';height:'+_getCSSValueH(height, 0, percent)+';width:'+_getCSSValueW(width, 0, percent)+';image-rendering:pixelated;');
	_defaultDiv.appendChild(mapPreview);
	refreshMapPreview(id, pattern);
	return mapPreview;
}
function refreshMapPreview(id, pattern) {
	var mapPreview = document.getElementById(id);
	let w = pattern.length===0 ? 0 : pattern[0].length;
	let h = pattern.length;
	let decalY = decalX = 0;
	let cStyle = getComputedStyle(mapPreview, null);
	let ratio = parseInt(cStyle.width)/parseInt(cStyle.height)
	if (w/ratio < h*ratio) {
		decalX = Math.round((h*ratio-w)/2);
	} else {
		decalY = Math.round((w/ratio-h)/2);
	}
	mapPreview.height = h+decalY*2;
	mapPreview.width = w+decalX*2;
	var ctx = mapPreview.getContext('2d');
	for (var i = 0; i < pattern.length; i++)
		for (var j = 0; j < pattern[i].length; j++) {
			ctx.fillStyle = pattern[i][j]<=0 ? 'white' : pattern[i][j]==1 ? 'red' : 'black';
			ctx.fillRect(j+decalX,i+decalY,1,1);
		}
}
function addDiv(id, x=0, y=0, width=400, height=300, elements=[], color="", percent=false) {
	remove(id);
	var div = document.createElement('div');
	div.setAttribute('id', id);
	div.setAttribute('style','position:absolute;top:'+_getCSSValueH(y,0,percent)+';left:'+_getCSSValueW(x,0,percent)+';width:'+_getCSSValueW(width,0,percent)+';height:'+_getCSSValueH(height,0,percent)+';'+(color?'background-color:'+color+';':'')+'overflow:auto;');
	for (let el of elements) {
		el.parentElement.removeChild(el);
		div.appendChild(el);
	}
	_defaultDiv.appendChild(div);
	return div;
}
function setDivContent(id, elements=[]) {
	var div = document.getElementById(id);
	div.innerHTML = '';
	for (let el of elements) {
		el.parentElement.removeChild(el);
		div.appendChild(el);
	}
	return div;
}
function addText(id, x=0, y=0, fontHeight=2, content='', width=20, color='black', percent=false) {
	remove(id);
	var text = document.createElement('span');
	text.setAttribute('id', id);
	text.setAttribute('style', 'position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+`;font-size:${fontHeight}%;color:${color};`+(width ? 'display:inline-block;width:'+_getCSSValueW(width, 0, percent)+';' : ''));
	text.innerHTML = content;
	_defaultDiv.appendChild(text);
	return text;
}
function addButton(id, x=0, y=0, width=80, height=40, action=function(){}, content='', textHeight=4, color='tomato', percent=false) {
	remove(id);
	var button = document.createElement('span');
	button.setAttribute('id', id);
	button.setAttribute('style', 'position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+';width:'+_getCSSValueW(width, 0, percent)+';height:'+_getCSSValueH(height, 0, percent)+';background-color:'+color+';text-align:center;font-size:'+textHeight+'%;');
	button.innerHTML = content;
	button.style.cursor = 'pointer';
	button.addEventListener('click', action);
	_defaultDiv.appendChild(button);
	return button;
}
function exists(id) {
	return document.getElementById(id) != null;
}
function addImage(id, src, x=0, y=0, onclick=undefined, percent=false, width=undefined, height=undefined, defaultImage=undefined) {
	remove(id);
	var image = document.createElement('img');
	image.setAttribute('id', id);
	image.setAttribute('src', src);
	image.setAttribute('style', 'position:absolute;top:'+_getCSSValueH(y, 0, percent)+';left:'+_getCSSValueW(x, 0, percent)+(width ? ';width:'+_getCSSValueW(width, 0, percent) : '')+(height ? ';height:'+_getCSSValueH(height, 0, percent) : '')+';image-rendering:pixelated;');
	if (onclick) {
		image.addEventListener('click', onclick);
		image.style.cursor = 'pointer';
	}
	if (defaultImage) image.onerror = function() {this.src = defaultImage; this.onerror = '';};
	_defaultDiv.appendChild(image);
	return image;
}
function preloadImages(onload, srcs) {
	i = new Image();
	i.src = srcs[0];
	i.onload = i.onerror = function() {
		srcs.shift();
		if (srcs.length != 0)
			preloadImages(onload, srcs);
		else
			onload();
	}
}
function setVisible(id, visible) {
	let el = document.getElementById(id).style.display = visible ? "block" : "none";
}
function get(id) {
	return document.getElementById(id);
}
