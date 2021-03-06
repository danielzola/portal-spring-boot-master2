'use strict';

var BASE64_MARKER = ';base64,';
function convertDataBase64ToBinary (dataBase64) {
	var base64Index = dataBase64.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
	var base64 = dataBase64.substring(base64Index);
	var raw = window.atob(base64);
	var rawLength = raw.length;
	var array = new Uint8Array(new ArrayBuffer(rawLength));

	for(var i = 0; i < rawLength; i++) {
		array[i] = raw.charCodeAt(i);
	}
	return array;
}

var nohal = 0;
function renderPDF (url, canvasContainer, options) {
	var pdfjs = window['pdfjs-dist/build/pdf']
	var options = options || { scale: 1 }

	function renderPage(page) {
		nohal++
        var viewport = page.getViewport(options.scale);
        var canvas = document.createElement('canvas');
        var contex = canvas.getContext('2d');
        var renderContext = {
			canvasContext: contex,
			viewport: viewport
        };
        canvas.id = 'pdfpage'+nohal;
        canvas.height = viewport.height;
        canvas.width = viewport.width;
        canvasContainer.appendChild(canvas);
        page.render(renderContext);
        $('#jmlHal').val(nohal.toString());
    }

    function pageNumber(pdfDoc) {
        for(var page = 1; page <= pdfDoc.numPages; page++){
            pdfDoc.getPage(page).then(renderPage);
        }
        document.getElementById('pageOf').innerHTML += pdfDoc.numPages
    }

	url = convertDataBase64ToBinary(url)
	var setPDF = pdfjs.getDocument(url);
	setPDF.then(pageNumber)
}