var _defaultDiv = document.body;
var _referenceWidth = 800;
var _referenceHeight = 600;
function setDefaultAddContentDiv(sel) {
	_defaultDiv = document.querySelector(sel);
	//_defaultDiv.id = "";
}
function setReferenceDimensions(width, height) {
	_referenceWidth = width;
	_referenceHeight = height;
	return true;
}
// a value to convert in % and an addio(n)nal value to add in px
// for value with difference caused by padding, margin, border...
function _getCSSValueW(v, a, ref) {
	ref = ref || _referenceWidth;
	if (!a) return (v*100/ref)+'%';
	if (!v) return (a)+'px';
	return 'calc('+(v*100/ref)+'% + '+(a)+'px)';
}
function _getCSSValueH(v, a, ref) {
	ref = ref ||  _referenceHeight;
	if (!a) return (v*100/ref)+'%';
	if (!v) return (a)+'px';
	return 'calc('+(v*100/ref)+'% + '+(a)+'px)';
}
function addTextField(id, x, y, width, height, hint, disabled, onEnter, refW, refH) {
	x = x || 0;
	y = y || 0;
	width = width || 160;
	height = height || 20;
	hint = hint || '';
	disabled = disabled || false;
	removeText(id);
	var textField = document.createElement('input');
	textField.setAttribute('type', 'text');
	textField.setAttribute('id', id);
	textField.setAttribute('placeholder', hint);
	textField.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, refH)+';left:'+_getCSSValueW(x, 0, refW)+';width:'+_getCSSValueW(width, -6, refW)+';height:'+_getCSSValueH(height, -6, refH)+';border-width:2px');
	if (disabled) textField.disabled = 'disabled';
	if (onEnter) textField.addEventListener('keyup', function(event) {
			event.preventDefault();
			if (event.keyCode === 13) onEnter();
		 });
	_defaultDiv.appendChild(textField);
	return textField;
}
function addTextArea(id, x, y, width, height, hint, disabled, onEnter, refW, refH) {
	x = x || 0;
	y = y || 0;
	width = width || 240;
	height = height || 180;
	hint = hint || '';
	disabled = disabled || false;
	removeText(id);
	var textArea = document.createElement('textarea');
	textArea.setAttribute('id', id);
	textArea.setAttribute('placeholder', hint);
	textArea.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, refH)+';left:'+_getCSSValueW(x, 0, refW)+';width:'+_getCSSValueW(width, -6, refW)+';height:'+_getCSSValueH(height, -6, refH)+'; resize: none;border-width:1px');
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
var kId = 0;
function display(text, time) {
	text = text || '';
	time = time || 4000;
	var id = '_display'+kId++;
	var textToD = document.createElement('span');
	textToD.setAttribute('id', id);
	textToD.setAttribute('style','position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);font-size:5%;');
	textToD.className = 'display';
	textToD.innerHTML = text;
	_defaultDiv.appendChild(textToD);
	setTimeout(function() {
		removeText(id);
	}, time);
	return textToD;
}
function displayError(fieldId, error) {
	var field = document.getElementById(fieldId);
	var id = '_error_'+fieldId;
	removeText(id);
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
		removeText(id);
	}, 5000);
	return err;
}
function addMapPreview(id, pattern, x, y, height, refW, refH) {
	x = x || 0;
	y = y || 0;
	removeText(id);
	var mapPreview = document.createElement('canvas');
	mapPreview.setAttribute('id', id);
	mapPreview.setAttribute('style','position:absolute;top:'+_getCSSValueH(y, 0, refH)+';left:'+_getCSSValueW(x, 0, refW)+';height:'+_getCSSValueH(height)+';width:auto;');
	_defaultDiv.appendChild(mapPreview);
	refreshMapPreview(id, pattern);
	return mapPreview;
}
function refreshMapPreview(id, pattern) {
	var mapPreview = document.getElementById(id);
	var m = Math.ceil(parseFloat(getComputedStyle ? getComputedStyle(mapPreview, null).height : mapPreview.style.height)/pattern.length);
	mapPreview.height = pattern.length*m;
	mapPreview.width = pattern.length===0?0:pattern[0].length*m;
	var ctx = mapPreview.getContext('2d');
	for (var i = 0; i < pattern.length; i++)
		for (var j = 0; j < pattern[i].length; j++) {
			ctx.fillStyle = pattern[i][j]<=0 ? 'white' : pattern[i][j]==1 ? 'red' : 'black';
			ctx.fillRect(j*m,i*m,m,m);
		}
}
function addDiv(id, x, y, width, height, elements, color, refW, refH) {
	x = x || 0;
	y = y || 0;
	width = width || 400;
	height = height || 300;
	elements = elements || [];
	color = color || "";
	removeText(id);
	var div = document.createElement('div');
	div.setAttribute('id', id);
	div.setAttribute('style','position:absolute;top:'+_getCSSValueH(y,0,refH)+';left:'+_getCSSValueW(x,0,refW)+';width:'+_getCSSValueW(width,0,refW)+';height:'+_getCSSValueH(height,0,refH)+';'+(color?'background-color:'+color+';':'')+'overflow:auto;');
	for (let el of elements) {
		el.parentElement.removeChild(el);
		div.appendChild(el);
	}
	_defaultDiv.appendChild(div);
	return div;
}
function setDivContent(id, elements) {
	elements = elements || [];
	var div = document.getElementById(id);
	div.innerHTML = '';
	for (let el of elements) {
		el.parentElement.removeChild(el);
		div.appendChild(el);
	}
	return div;
}
function addText(id, x, y, fontHeight, content, width, color, refW, refH) {
	x = x || 0;
	y = y || 0;
	fontHeight = fontHeight || 2;
	content = content || '';
	width = width || 20;
	color = color || 'black';
	var text = document.createElement('span');
	text.setAttribute('id', id);
	text.setAttribute('style', 'position:absolute;top:'+_getCSSValueH(y, 0, refH)+';left:'+_getCSSValueW(x, 0, refW)+`;font-size:${fontHeight}%;color:${color};`+(width ? 'display:inline-block;width:'+_getCSSValueW(width, 0, refW)+';' : ''));
	text.innerHTML = content;
	_defaultDiv.appendChild(text);
	return text;
}
function addButton(id, x, y, width, height, action, args, content, textHeight, color, refW, refH) {
	x = x || 0;
	y = y || 0;
	width = width || 80;
	height = height || 40;
	action = action || function() {};
	content = content || '';
	textHeight = textHeight || 4;
	color = color || 'tomato';
	var button = document.createElement('span');
	button.setAttribute('id', id);
	button.setAttribute('style', 'position:absolute;top:'+_getCSSValueH(y, 0, refH)+';left:'+_getCSSValueW(x, 0, refW)+';width:'+_getCSSValueW(width, 0, refW)+';height:'+_getCSSValueH(height, 0, refH)+';background-color:'+color+';text-align:center;font-size:'+textHeight+'%;');
	button.innerHTML = content;
	button.args = args;
	button.addEventListener('click', function(e) {
		action(...e.target.args);
	});
	_defaultDiv.appendChild(button);
	return button;
}
