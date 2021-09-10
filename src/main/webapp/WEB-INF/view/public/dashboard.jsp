<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../assets/apps/js/plugins/pixioverlay/css/leaflet.css">
    <link rel="stylesheet" href="../assets/apps/js/plugins/leaflet/L.Control.SlideMenu.css">
    <link rel="stylesheet" href="../assets/apps/js/plugins/leaflet/L.Control.Window.css">
    <link rel="stylesheet" href="../assets/apps/js/plugins/leaflet/leaflet.fullscreen.css">
    <style>
	.loader {
	  position: relative;
	  border: 16px solid #f3f3f3;
	  border-radius: 50%;
	  border-top: 16px solid #3498db;
	  width: 70px;
	  height: 70px;
	  left:50%;
	  top:50%;
	  -webkit-animation: spin 2s linear infinite; /* Safari */
	  animation: spin 2s linear infinite;
	  z-index: 99999;
	}    
	#overlay{
	    position: absolute;
	    top:0px;
	    left:0px;
	    width: 100%;
	    height: 100%;
	    background: black;
	    opacity: .5;
	    z-index: 9999;
	}
	/* Safari */
	@-webkit-keyframes spin {
	  0% { -webkit-transform: rotate(0deg); }
	  100% { -webkit-transform: rotate(360deg); }
	}
	
	@keyframes spin {
	  0% { transform: rotate(0deg); }
	  100% { transform: rotate(360deg); }
	}
    </style>
</head>
<body>
    <div class="page-wrapper" style="position:relative;">
        <div class="row" id="dashboard-map" style="height:500px; margin:0px 1px 0px 1px;">
		    <div class="legend geometry top center hide" style="display:none;"></div>
        </div>
       	<div id="overlay">
		      <div class="loader"></div>
		</div>
    </div>
	<script src="../assets/apps/js/plugins/pixioverlay/js/example.min.js"></script>
	<script src="../assets/apps/js/plugins/pixioverlay/js/tools.min.js"></script>
	<script src="../assets/apps/js/plugins/leaflet/esri-leaflet.js"></script>
    <script src="../assets/apps/js/plugins/leaflet/L.Control.SlideMenu.js"></script>
    <script src="../assets/apps/js/plugins/leaflet/L.Control.Window.js"></script>
    <script src="../assets/apps/js/plugins/leaflet/leaflet.fullscreen.js"></script>   
    <script>
    var getJSON = function(url, successHandler, errorHandler) {
		var xhr = typeof XMLHttpRequest != 'undefined'
			? new XMLHttpRequest()
			: new ActiveXObject('Microsoft.XMLHTTP');
		xhr.open('get', url, true);
		xhr.onreadystatechange = function() {
			var status;
			var data;
			if (xhr.readyState == 4) {
				status = xhr.status;
				if (status == 200) {
					data = JSON.parse(xhr.responseText);
					successHandler && successHandler(data);
				} else {
					errorHandler && errorHandler(status);
				}
			}
		};
		xhr.send();
	};
	
    var map = L.map('dashboard-map').setView([-1.789275,118], 6);
    
    var rbi= L.esri.tiledMapLayer({url: "https://portal.ina-sdi.or.id/arcgis/rest/services/RBI/Basemap/MapServer"}).addTo(map);
    var osm=L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png');
    var imgesri=L.tileLayer('http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}',{maxZoom: 20,subdomains:['mt0','mt1','mt2','mt3']});
    bm_on=rbi;
    
    function changeMap(bm){
        if (bm=="rbi"){
            map.removeLayer(bm_on);
            rbi.addTo(map);
            bm_on=rbi;
        }
        else if (bm=="osm"){
            map.removeLayer(bm_on);
            osm.addTo(map);
            bm_on=osm;
        }
        else if (bm=="imgesri"){
            map.removeLayer(bm_on);
            imgesri.addTo(map);
            bm_on=imgesri;
        }
    }
    
	map.attributionControl.setPosition('bottomright');
	map.zoomControl.setPosition('topright');
	
	var LayerMenu = '<div style="padding:0px 20px 20px 30px; margin-top:-50px !important;">'+
       '<h4 style="font-size:18px !important; color:#FFF;">Layer Dashboard</h4>'+
       '<hr>'+
       '<b style="font-size:14px !important; color:#FFF;">Peta Dasar</b><br>'+
       '<select onchange="changeMap(this.value)" style="margin-top:5px; margin-bottom:5px;" class="form-control"><option value="rbi">Peta RBI</option><option value="osm">Open Street Map</option><option value="imgesri">ESRI Imagery</option></select>'+
       '<hr>'+
       '<b style="font-size:14px !important; color:#FFF;">Layer Data</b><br>'+
       '<div class="console-checkbox-list" style="margin-top:10px;">'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" checked value="port" class="markerGroup cbxpelabuhan"> <img src="../assets/public/images/icon/port.webp"> &nbsp; Pelabuhan'+
               '<span class="checkmark"></span>'+
           '</label>'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" value="ship" class="markerGroup cbxkapal"> <img src="../assets/public/images/icon/ship-green.webp"> &nbsp; Posisi Kapal'+
               '<span class="checkmark"></span>'+
           '</label>'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" value="sbnp" class="markerGroup cbxsbnp"> <img src="../assets/public/images/icon/sbnp.webp"> &nbsp; SBNP'+
               '<span class="checkmark"></span>'+
           '</label>'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" value="srop" class="markerGroup cbxsrop"> <img src="../assets/public/images/icon/srop.webp"> &nbsp; SROP'+
               '<span class="checkmark"></span>'+
           '</label>'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" value="tersus" class="markerGroup cbxtersus"> <img src="../assets/public/images/icon/tersus.webp"> &nbsp; TERSUS/TUKS'+
               '<span class="checkmark"></span>'+
           '</label>'+
           '<label class="con-check" style="color:#FFF;">'+
               '<input type="checkbox" onclick="toggleGroup()" value="vts" class="markerGroup cbxvts"> <img src="../assets/public/images/icon/vts.webp"> &nbsp; VTS'+
               '<span class="checkmark"></span>'+
           '</label>'+
       '</div>'+
       '<hr>'+
   '</div>';
	var slideMenu = L.control.slideMenu(LayerMenu, {position: 'topleft', menuposition: 'topleft', width: '30%', icon: 'fa-layer-group'}).addTo(map);
	
	var LayerMenuSearch = '<div style="padding:0px 20px 20px 30px; margin-top:-50px !important;">'+
					            '<h4 style="font-size:18px !important; color:#FFF;">Pencarian</h4>'+
					            '<hr>'+
					            '<b style="font-size:14px !important; color:#FFF;">Kata Kunci</b>'+
					            '<br>'+
					            '<input type="text" name="keywords" id="keywords" class="form-control">'+
					            '<br>'+
					            '<button onclick="searchMap()" type="button" id="btn-search" class="btn btn-warning">Cari</button>'+
					            '<br>'+
					            '<div id="search_result"></div>'+
					        '</div>';
	var slideMenuSearch = L.control.slideMenu(LayerMenuSearch, {position: 'topleft', menuposition: 'topleft', width: '30%', icon: 'fa-search'}).addTo(map);
	
	map.addControl(new L.Control.Fullscreen({position: 'topright'}));
	
	var loader = new PIXI.loaders.Loader();
	loader.add('port', '../assets/public/images/icon/port.webp')
		  .add('sbnp', '../assets/public/images/icon/sbnp.webp')
		  .add('srop', '../assets/public/images/icon/srop.webp')
		  .add('vts', '../assets/public/images/icon/vts.webp')
		  .add('tersus', '../assets/public/images/icon/tersus.webp')
		  .add('ship', '../assets/public/images/icon/ship-green.webp');
	
	var layerPort=1;
	var layerSbnp=0; var firstSbnp = 0;
	var layerSrop=0; var firstSrop = 0;
	var layerTersus=0; var firstTersus = 0;
	var layerVts=0;  var firstVts = 0;
	var layerShip=0;  var firstShip = 0;
	
	function runLoader(what){
		$('#overlay').show();
		loader.load(function(loader, resources) {
			var textures = [resources.port.texture,resources.sbnp.texture,resources.srop.texture,resources.vts.texture,resources.tersus.texture,resources.ship.texture];
			if(what=='sbnp'){
				getJSON('gis/sbnp', function(markers) {
					var legend = document.querySelector('div.legend.geometry');
					var legendContent = legend.querySelector('.content');
					var	pixiLayer = (function() {
						var firstDraw = true;
						var prevZoom;
						var markerSprites = [];
						var frame = null;
						var focus = null;
						var pixiContainer = new PIXI.Container();
						var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
						return L.pixiOverlay(function(utils) {
							var zoom = utils.getMap().getZoom();
							if (frame) {
								cancelAnimationFrame(frame);
								frame = null;
							}
							var container = utils.getContainer();
							var renderer = utils.getRenderer();
							var project = utils.latLngToLayerPoint;
							var scale = utils.getScale();
							var invScale = 1 / scale;
							if (firstDraw) {
								prevZoom = zoom;
								markers.forEach(function(marker) {
									var coords = project([marker.latitude, marker.longitude]);
									var markerSprite = new PIXI.Sprite(textures[1]);
									markerSprite.textureIndex = 1;
									markerSprite.x0 = coords.x;
									markerSprite.y0 = coords.y;
									markerSprite.anchor.set(0.5, 0.5);
									markerSprite.scale.set(invScale);
		
									container.addChild(markerSprite);
									markerSprites.push(markerSprite);
									markerSprite.legend = marker.nm_location;
									markerSprite.longitude = marker.longitude;
									markerSprite.latitude = marker.latitude;
									markerSprite.dsi_nr = marker.dsi_nr;
									markerSprite.id_suar = marker.id_suar;
								});
								var quadTrees = {};
								for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
									var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
									quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
								}
								function findMarkerSbnp(ll) {
									var layerPoint = project(ll);
									var quadTree = quadTrees[utils.getMap().getZoom()];
									var marker;
									var rMax = quadTree.rMax;
									var found = false;
									quadTree.visit(function(quad, x1, y1, x2, y2) {
										if (!quad.length) {
											var dx = quad.data.x - layerPoint.x;
											var dy = quad.data.y - layerPoint.y;
											var r = quad.data.scale.x * 16;
											if (dx * dx + dy * dy <= r * r) {
												marker = quad.data;
												found = true;
											}
										}
										return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
									});
									return marker;
								}
								map.on('click', function(e) {
									var redraw = false;
									if (focus) {
										focus.texture = textures[focus.textureIndex];
										focus = null;
										legendContent.innerHTML = '';
										redraw = true;
									}
									var marker;
										marker = findMarkerSbnp(e.latlng);
									if (marker) {
										focus = marker;
										if(layerSbnp==1){
											L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">SBNP</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">DSI NR '+marker.dsi_nr.toString()+'</div><button onclick="openDetail(\'sbnp\', \''+marker.id_suar+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
										}
										redraw = true;
									}
									if (redraw) utils.getRenderer().render(container);
								});
								var self = this;
								map.on('mousemove', L.Util.throttle(function(e) {
									var marker = findMarkerSbnp(e.latlng);
									if (marker) {
										L.DomUtil.addClass(self._container, 'leaflet-interactive');
									} else {
										L.DomUtil.removeClass(self._container, 'leaflet-interactive');
									}
								}, 32));
								L.DomUtil.addClass(self._container, 'layer-sbnp');
							}
							if (firstDraw || prevZoom !== zoom) {
								markerSprites.forEach(function(markerSprite) {
									var position = markerSprite.cache[zoom];
									if (firstDraw) {
										markerSprite.x = position.x;
										markerSprite.y = position.y;
									} else {
										markerSprite.currentX = markerSprite.x;
										markerSprite.currentY = markerSprite.y;
										markerSprite.targetX = position.x;
										markerSprite.targetY = position.y;
										markerSprite.currentScale = markerSprite.scale.x;
										markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
									}
								});
							}
							var start = null;
							var delta = 1;
							function animate(timestamp) {
							    var progress;
								var scale = utils.getScale();
								var invScale = 1 / scale;
		
							  if (start === null) start = timestamp;
							  progress = timestamp - start;
							  var lambda = progress / delta;
							  if (lambda > 1) lambda = 1;
							  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
							  markerSprites.forEach(function(markerSprite) {
								  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
								  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
								  markerSprite.scale.set(invScale);
								});
								renderer.render(container);
							  if (progress < delta) {
							    frame = requestAnimationFrame(animate);
							  }
							}
							if (!firstDraw && prevZoom !== zoom) {
								frame = requestAnimationFrame(animate);
							}
							firstDraw = false;
							prevZoom = zoom;
							renderer.render(container);
						}, pixiContainer, {
							doubleBuffering: doubleBuffering,
							destroyInteractionManager: true
						});
					})();
					pixiLayer.addTo(map);
					toggleGroup();
					$('#overlay').hide();
				});
			}
			if(what=='srop'){
			
				getJSON('gis/srop', function(markers) {
					var legend = document.querySelector('div.legend.geometry');
					var legendContent = legend.querySelector('.content');
					var	pixiLayer = (function() {
						var firstDraw = true;
						var prevZoom;
						var markerSprites = [];
						var frame = null;
						var focus = null;
						var pixiContainer = new PIXI.Container();
						var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
						return L.pixiOverlay(function(utils) {
							var zoom = utils.getMap().getZoom();
							if (frame) {
								cancelAnimationFrame(frame);
								frame = null;
							}
							var container = utils.getContainer();
							var renderer = utils.getRenderer();
							var project = utils.latLngToLayerPoint;
							var scale = utils.getScale();
							var invScale = 1 / scale;
							if (firstDraw) {
								prevZoom = zoom;
								markers.forEach(function(marker) {
									var coords = project([marker.latitude, marker.longitude]);
									var markerSprite = new PIXI.Sprite(textures[2]);
									markerSprite.textureIndex = 2;
									markerSprite.x0 = coords.x;
									markerSprite.y0 = coords.y;
									markerSprite.anchor.set(0.5, 0.5);
									markerSprite.scale.set(invScale);
		
									container.addChild(markerSprite);
									markerSprites.push(markerSprite);
									markerSprite.legend = marker.nama_srop;
									markerSprite.longitude = marker.longitude;
									markerSprite.latitude = marker.latitude;
									markerSprite.callsign = marker.call_sign_srop;
									markerSprite.id_srop = marker.id_srop;
								});
								var quadTrees = {};
								for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
									var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
									quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
								}
								function findMarkerSrop(ll) {
									var layerPoint = project(ll);
									var quadTree = quadTrees[utils.getMap().getZoom()];
									var marker;
									var rMax = quadTree.rMax;
									var found = false;
									quadTree.visit(function(quad, x1, y1, x2, y2) {
										if (!quad.length) {
											var dx = quad.data.x - layerPoint.x;
											var dy = quad.data.y - layerPoint.y;
											var r = quad.data.scale.x * 16;
											if (dx * dx + dy * dy <= r * r) {
												marker = quad.data;
												found = true;
											}
										}
										return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
									});
									return marker;
								}
								map.on('click', function(e) {
									var redraw = false;
									if (focus) {
										focus.texture = textures[focus.textureIndex];
										focus = null;
										legendContent.innerHTML = '';
										redraw = true;
									}
									var marker = findMarkerSrop(e.latlng);
									if (marker) {
										focus = marker;
										if(layerSrop==1){
											L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">SROP</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.callsign.toString()+'</div><button onclick="openDetail(\'srop\', \''+marker.id_srop+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
										}
										redraw = true;
									}
									if (redraw) utils.getRenderer().render(container);
								});
								var self = this;
								map.on('mousemove', L.Util.throttle(function(e) {
									var marker = findMarkerSrop(e.latlng);
									if (marker) {
										L.DomUtil.addClass(self._container, 'leaflet-interactive');
									} else {
										L.DomUtil.removeClass(self._container, 'leaflet-interactive');
									}
								}, 32));
								L.DomUtil.addClass(self._container, 'layer-srop');
							}
							if (firstDraw || prevZoom !== zoom) {
								markerSprites.forEach(function(markerSprite) {
									var position = markerSprite.cache[zoom];
									if (firstDraw) {
										markerSprite.x = position.x;
										markerSprite.y = position.y;
									} else {
										markerSprite.currentX = markerSprite.x;
										markerSprite.currentY = markerSprite.y;
										markerSprite.targetX = position.x;
										markerSprite.targetY = position.y;
										markerSprite.currentScale = markerSprite.scale.x;
										markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
									}
								});
							}
							var start = null;
							var delta = 1;
							function animate(timestamp) {
							    var progress;
								var scale = utils.getScale();
								var invScale = 1 / scale;
		
							  if (start === null) start = timestamp;
							  progress = timestamp - start;
							  var lambda = progress / delta;
							  if (lambda > 1) lambda = 1;
							  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
							  markerSprites.forEach(function(markerSprite) {
								  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
								  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
								  markerSprite.scale.set(invScale);
								});
								renderer.render(container);
							  if (progress < delta) {
							    frame = requestAnimationFrame(animate);
							  }
							}
							if (!firstDraw && prevZoom !== zoom) {
								frame = requestAnimationFrame(animate);
							}
							firstDraw = false;
							prevZoom = zoom;
							renderer.render(container);
						}, pixiContainer, {
							doubleBuffering: doubleBuffering,
							destroyInteractionManager: true
						});
					})();
					pixiLayer.addTo(map);
					toggleGroup();
					$('#overlay').hide();
				});
			}
			if(what=='tersus'){			
				getJSON('gis/tersus', function(markers) {
					var legend = document.querySelector('div.legend.geometry');
					var legendContent = legend.querySelector('.content');
					var	pixiLayer = (function() {
						var firstDraw = true;
						var prevZoom;
						var markerSprites = [];
						var frame = null;
						var focus = null;
						var pixiContainer = new PIXI.Container();
						var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
						return L.pixiOverlay(function(utils) {
							var zoom = utils.getMap().getZoom();
							if (frame) {
								cancelAnimationFrame(frame);
								frame = null;
							}
							var container = utils.getContainer();
							var renderer = utils.getRenderer();
							var project = utils.latLngToLayerPoint;
							var scale = utils.getScale();
							var invScale = 1 / scale;
							if (firstDraw) {
								prevZoom = zoom;
								markers.forEach(function(marker) {
									var coords = project([marker.latitude, marker.longitude]);
									var markerSprite = new PIXI.Sprite(textures[4]);
									markerSprite.textureIndex = 4;
									markerSprite.x0 = coords.x;
									markerSprite.y0 = coords.y;
									markerSprite.anchor.set(0.5, 0.5);
									markerSprite.scale.set(invScale);
		
									container.addChild(markerSprite);
									markerSprites.push(markerSprite);
									markerSprite.legend = marker.nama_tersus;
									markerSprite.longitude = marker.longitude;
									markerSprite.latitude = marker.latitude;
									markerSprite.tersus_tuks = marker.tersus_tuks;
									markerSprite.id_tersus = marker.id_tersus;
								});
								var quadTrees = {};
								for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
									var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
									quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
								}
								function findMarkerTersus(ll) {
									var layerPoint = project(ll);
									var quadTree = quadTrees[utils.getMap().getZoom()];
									var marker;
									var rMax = quadTree.rMax;
									var found = false;
									quadTree.visit(function(quad, x1, y1, x2, y2) {
										if (!quad.length) {
											var dx = quad.data.x - layerPoint.x;
											var dy = quad.data.y - layerPoint.y;
											var r = quad.data.scale.x * 16;
											if (dx * dx + dy * dy <= r * r) {
												marker = quad.data;
												found = true;
											}
										}
										return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
									});
									return marker;
								}
								map.on('click', function(e) {
									var redraw = false;
									if (focus) {
										focus.texture = textures[focus.textureIndex];
										focus = null;
										legendContent.innerHTML = '';
										redraw = true;
									}
									var marker = findMarkerTersus(e.latlng);
									if (marker) {
										focus = marker;
										if(layerTersus==1){
											L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">TERSUS/TUKS</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.tersus_tuks.toString()+'</div><button onclick="openDetail(\'tersus\', \''+marker.id_tersus+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
										}
										redraw = true;
									}
									if (redraw) utils.getRenderer().render(container);
								});
								var self = this;
								map.on('mousemove', L.Util.throttle(function(e) {
									var marker = findMarkerTersus(e.latlng);
									if (marker) {
										L.DomUtil.addClass(self._container, 'leaflet-interactive');
									} else {
										L.DomUtil.removeClass(self._container, 'leaflet-interactive');
									}
								}, 32));
								L.DomUtil.addClass(self._container, 'layer-tersus');
							}
							if (firstDraw || prevZoom !== zoom) {
								markerSprites.forEach(function(markerSprite) {
									var position = markerSprite.cache[zoom];
									if (firstDraw) {
										markerSprite.x = position.x;
										markerSprite.y = position.y;
									} else {
										markerSprite.currentX = markerSprite.x;
										markerSprite.currentY = markerSprite.y;
										markerSprite.targetX = position.x;
										markerSprite.targetY = position.y;
										markerSprite.currentScale = markerSprite.scale.x;
										markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
									}
								});
							}
							var start = null;
							var delta = 1;
							function animate(timestamp) {
							    var progress;
								var scale = utils.getScale();
								var invScale = 1 / scale;
		
							  if (start === null) start = timestamp;
							  progress = timestamp - start;
							  var lambda = progress / delta;
							  if (lambda > 1) lambda = 1;
							  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
							  markerSprites.forEach(function(markerSprite) {
								  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
								  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
								  markerSprite.scale.set(invScale);
								});
								renderer.render(container);
							  if (progress < delta) {
							    frame = requestAnimationFrame(animate);
							  }
							}
							if (!firstDraw && prevZoom !== zoom) {
								frame = requestAnimationFrame(animate);
							}
							firstDraw = false;
							prevZoom = zoom;
							renderer.render(container);
						}, pixiContainer, {
							doubleBuffering: doubleBuffering,
							destroyInteractionManager: true
						});
					})();
					pixiLayer.addTo(map);
					toggleGroup();
					$('#overlay').hide();
				});
			}
			if(what=='vts'){
				getJSON('gis/vts', function(markers) {
					var legend = document.querySelector('div.legend.geometry');
					var legendContent = legend.querySelector('.content');
					var	pixiLayer = (function() {
						var firstDraw = true;
						var prevZoom;
						var markerSprites = [];
						var frame = null;
						var focus = null;
						var pixiContainer = new PIXI.Container();
						var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
						return L.pixiOverlay(function(utils) {
							var zoom = utils.getMap().getZoom();
							if (frame) {
								cancelAnimationFrame(frame);
								frame = null;
							}
							var container = utils.getContainer();
							var renderer = utils.getRenderer();
							var project = utils.latLngToLayerPoint;
							var scale = utils.getScale();
							var invScale = 1 / scale;
							if (firstDraw) {
								prevZoom = zoom;
								markers.forEach(function(marker) {
									var coords = project([marker.latitude, marker.longitude]);
									var markerSprite = new PIXI.Sprite(textures[3]);
									markerSprite.textureIndex = 3;
									markerSprite.x0 = coords.x;
									markerSprite.y0 = coords.y;
									markerSprite.anchor.set(0.5, 0.5);
									markerSprite.scale.set(invScale);
		
									container.addChild(markerSprite);
									markerSprites.push(markerSprite);
									markerSprite.legend = marker.nama_vts;
									markerSprite.longitude = marker.longitude;
									markerSprite.latitude = marker.latitude;
									markerSprite.jns_lynn = marker.jns_lynn;
									markerSprite.id_vts = marker.id_vts;
								});
								var quadTrees = {};
								for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
									var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
									quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
								}
								function findMarkerVts(ll) {
									var layerPoint = project(ll);
									var quadTree = quadTrees[utils.getMap().getZoom()];
									var marker;
									var rMax = quadTree.rMax;
									var found = false;
									quadTree.visit(function(quad, x1, y1, x2, y2) {
										if (!quad.length) {
											var dx = quad.data.x - layerPoint.x;
											var dy = quad.data.y - layerPoint.y;
											var r = quad.data.scale.x * 16;
											if (dx * dx + dy * dy <= r * r) {
												marker = quad.data;
												found = true;
											}
										}
										return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
									});
									return marker;
								}
								map.on('click', function(e) {
									var redraw = false;
									if (focus) {
										focus.texture = textures[focus.textureIndex];
										focus = null;
										legendContent.innerHTML = '';
										redraw = true;
									}
									var marker = findMarkerVts(e.latlng);
									if (marker) {
										focus = marker;
										if(layerVts==1){
											L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">VTS</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.jns_lynn.toString()+'</div><button onclick="openDetail(\'vts\', \''+marker.id_vts+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
										}
										redraw = true;
									}
									if (redraw) utils.getRenderer().render(container);
								});
								var self = this;
								map.on('mousemove', L.Util.throttle(function(e) {
									var marker = findMarkerVts(e.latlng);
									if (marker) {
										L.DomUtil.addClass(self._container, 'leaflet-interactive');
									} else {
										L.DomUtil.removeClass(self._container, 'leaflet-interactive');
									}
								}, 32));
								L.DomUtil.addClass(self._container, 'layer-vts');
							}
							if (firstDraw || prevZoom !== zoom) {
								markerSprites.forEach(function(markerSprite) {
									var position = markerSprite.cache[zoom];
									if (firstDraw) {
										markerSprite.x = position.x;
										markerSprite.y = position.y;
									} else {
										markerSprite.currentX = markerSprite.x;
										markerSprite.currentY = markerSprite.y;
										markerSprite.targetX = position.x;
										markerSprite.targetY = position.y;
										markerSprite.currentScale = markerSprite.scale.x;
										markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
									}
								});
							}
							var start = null;
							var delta = 1;
							function animate(timestamp) {
							    var progress;
								var scale = utils.getScale();
								var invScale = 1 / scale;
		
							  if (start === null) start = timestamp;
							  progress = timestamp - start;
							  var lambda = progress / delta;
							  if (lambda > 1) lambda = 1;
							  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
							  markerSprites.forEach(function(markerSprite) {
								  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
								  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
								  markerSprite.scale.set(invScale);
								});
								renderer.render(container);
							  if (progress < delta) {
							    frame = requestAnimationFrame(animate);
							  }
							}
							if (!firstDraw && prevZoom !== zoom) {
								frame = requestAnimationFrame(animate);
							}
							firstDraw = false;
							prevZoom = zoom;
							renderer.render(container);
						}, pixiContainer, {
							doubleBuffering: doubleBuffering,
							destroyInteractionManager: true
						});
					})();
					pixiLayer.addTo(map);
					toggleGroup();
					$('#overlay').hide();
				});
			}
			if(what=='ship'){
				getJSON('gis/ship', function(markers) {
					var legend = document.querySelector('div.legend.geometry');
					var legendContent = legend.querySelector('.content');
					var	pixiLayer = (function() {
						var firstDraw = true;
						var prevZoom;
						var markerSprites = [];
						var frame = null;
						var focus = null;
						var pixiContainer = new PIXI.Container();
						var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
						return L.pixiOverlay(function(utils) {
							var zoom = utils.getMap().getZoom();
							if (frame) {
								cancelAnimationFrame(frame);
								frame = null;
							}
							var container = utils.getContainer();
							var renderer = utils.getRenderer();
							var project = utils.latLngToLayerPoint;
							var scale = utils.getScale();
							var invScale = 1 / scale;
							if (firstDraw) {
								prevZoom = zoom;
								markers.forEach(function(marker) {
									var coords = project([marker.latitude, marker.longitude]);
									var markerSprite = new PIXI.Sprite(textures[5]);
									markerSprite.textureIndex = 5;
									markerSprite.x0 = coords.x;
									markerSprite.y0 = coords.y;
									markerSprite.anchor.set(0.5, 0.5);
									markerSprite.scale.set(invScale);
									markerSprite.rotation = marker.true_heading-0.9;
		
									container.addChild(markerSprite);
									markerSprites.push(markerSprite);
									markerSprite.legend = marker.vessel_name;
									markerSprite.longitude = marker.longitude;
									markerSprite.latitude = marker.latitude;
									markerSprite.tipe_kapal = marker.tipe_kapal;
									markerSprite.kapal_id = marker.kapal_id;
								});
								var quadTrees = {};
								for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
									var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
									quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
								}
								function findMarkerShip(ll) {
									var layerPoint = project(ll);
									var quadTree = quadTrees[utils.getMap().getZoom()];
									var marker;
									var rMax = quadTree.rMax;
									var found = false;
									quadTree.visit(function(quad, x1, y1, x2, y2) {
										if (!quad.length) {
											var dx = quad.data.x - layerPoint.x;
											var dy = quad.data.y - layerPoint.y;
											var r = quad.data.scale.x * 16;
											if (dx * dx + dy * dy <= r * r) {
												marker = quad.data;
												found = true;
											}
										}
										return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
									});
									return marker;
								}
								map.on('click', function(e) {
									var redraw = false;
									if (focus) {
										focus.texture = textures[focus.textureIndex];
										focus = null;
										legendContent.innerHTML = '';
										redraw = true;
									}
									var marker = findMarkerShip(e.latlng);
									if (marker) {
										focus = marker;
										if(layerShip==1){
											L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">KAPAL</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.tipe_kapal.toString()+'</div><button onclick="openDetail(\'ship\', \''+marker.kapal_id+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
										}
										redraw = true;
									}
									if (redraw) utils.getRenderer().render(container);
								});
								var self = this;
								map.on('mousemove', L.Util.throttle(function(e) {
									var marker = findMarkerShip(e.latlng);
									if (marker) {
										L.DomUtil.addClass(self._container, 'leaflet-interactive');
									} else {
										L.DomUtil.removeClass(self._container, 'leaflet-interactive');
									}
								}, 32));
								L.DomUtil.addClass(self._container, 'layer-ship');
							}
							if (firstDraw || prevZoom !== zoom) {
								markerSprites.forEach(function(markerSprite) {
									var position = markerSprite.cache[zoom];
									if (firstDraw) {
										markerSprite.x = position.x;
										markerSprite.y = position.y;
									} else {
										markerSprite.currentX = markerSprite.x;
										markerSprite.currentY = markerSprite.y;
										markerSprite.targetX = position.x;
										markerSprite.targetY = position.y;
										markerSprite.currentScale = markerSprite.scale.x;
										markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
									}
								});
							}
							var start = null;
							var delta = 1;
							function animate(timestamp) {
							    var progress;
								var scale = utils.getScale();
								var invScale = 1 / scale;
		
							  if (start === null) start = timestamp;
							  progress = timestamp - start;
							  var lambda = progress / delta;
							  if (lambda > 1) lambda = 1;
							  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
							  markerSprites.forEach(function(markerSprite) {
								  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
								  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
								  markerSprite.scale.set(invScale);
								});
								renderer.render(container);
							  if (progress < delta) {
							    frame = requestAnimationFrame(animate);
							  }
							}
							if (!firstDraw && prevZoom !== zoom) {
								frame = requestAnimationFrame(animate);
							}
							firstDraw = false;
							prevZoom = zoom;
							renderer.render(container);
						}, pixiContainer, {
							doubleBuffering: doubleBuffering,
							destroyInteractionManager: true
						});
					})();
					pixiLayer.addTo(map);
					toggleGroup();
					$('#overlay').hide();
				});
			}
		});			
	}
	
	function toggleGroup(){
	    $('.leaflet-pixi-overlay').hide();
	    $('.markerGroup').each(function(){
	        if($(this).prop('checked')){
	            $('.layer-'+$(this).val().toString()).show();
	            if($(this).val().toString()=='port'){
	            	layerPort = 1;
	            }
	            if($(this).val().toString()=='sbnp'){
	            	layerSbnp = 1;
	            	if(firstSbnp == 0){
	            		runLoader('sbnp');
	            		firstSbnp = 1;
	            	}
	            }
	            if($(this).val().toString()=='srop'){
	            	layerSrop = 1;
	            	if(firstSrop == 0){
	            		runLoader('srop');
	            		firstSrop = 1;
	            	}
	            }
	            if($(this).val().toString()=='tersus'){
	            	layerTersus = 1;
	            	if(firstTersus == 0){
	            		runLoader('tersus');
	            		firstTersus = 1;
	            	}
	            }
	            if($(this).val().toString()=='vts'){
	            	layerVts = 1;
	            	if(firstVts == 0){
	            		runLoader('vts');
	            		firstVts = 1;
	            	}
	            }
	            if($(this).val().toString()=='ship'){
	            	layerShip = 1;
	            	if(firstShip == 0){
	            		runLoader('ship');
	            		firstShip = 1;
	            	}
	            }
	        }else{
	        	if($(this).val().toString()=='port'){
	            	layerPort = 0;
	            }
	        	if($(this).val().toString()=='sbnp'){
	            	layerSbnp = 0;
	            }
	            if($(this).val().toString()=='srop'){
	            	layerSrop = 0;
	            }
	            if($(this).val().toString()=='tersus'){
	            	layerTersus = 0;
	            }
	            if($(this).val().toString()=='vts'){
	            	layerVts = 0;
	            }
	            if($(this).val().toString()=='ship'){
	            	layerShip = 0;
	            }
	        }
	    });
	}
	
	function pointToMarker(lat,lng){
		map.setView([lat, lng], 14);
		setTimeout(function(){
			map.fire('click', {
			    latlng: L.latLng([lat, lng])
			});						
			map.fire('click', {
			    latlng: L.latLng([lat, lng])
			});						
		},100);
	}

	function searchMap(){
		$('#btn-search').html('Loading..');
		var keywords = $('#keywords').val().toString().replace(/ /g, "+");
			var xhr = new XMLHttpRequest();
			xhr.open("GET", 'gis/search/'+keywords, true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
			    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
			    	var myObj = JSON.parse(this.responseText);
			    	if(myObj.length>0){
			    		var search_result = '';
		  		    	for(var i=0; i<myObj.length; i++){
		  		    		if(layerPort==1 && myObj[i].tipe.toString()=='PELABUHAN'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    		if(layerSbnp==1 && myObj[i].tipe.toString()=='SBNP'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    		if(layerSrop==1 && myObj[i].tipe.toString()=='SROP'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    		if(layerTersus==1 && myObj[i].tipe.toString()=='TERSUS/TUKS'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    		if(layerVts==1 && myObj[i].tipe.toString()=='VTS'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    		if(layerShip==1 && myObj[i].tipe.toString()=='KAPAL'){
		  	  		    		search_result = search_result+'<a style="cursor:pointer; font-size:12px; color:#fff;" onclick="pointToMarker('+myObj[i].latitude+', '+myObj[i].longitude+')"><b>'+myObj[i].tipe+'</b> - '+myObj[i].nama+'</a><br>';  	  		    			
		  		    		}
		  		    	}
		  		    	if(search_result.length>0){
		  		    		$('#search_result').html('<br><strong style="color:#fff;">Hasil Pencarian: </strong><br><br>'+search_result);  	  		    		
		  		    	}else{
		  		    		$('#search_result').html('<br><strong style="color:#fff;">Hasil Pencarian: </strong><br><span style="color:#fff;"><br>Data tidak ditemukan</span>');  	  		    		
		  		    	}
			    	}
			    	else{
			    		$('#search_result').html('<br><strong style="color:#fff;">Hasil Pencarian: </strong><br><span style="color:#fff;"><br>Data tidak ditemukan</span>');
			    	}
					$('#btn-search').html('Cari');
	
			    }
			}
			xhr.send();				
	}
	
	function openDetail(what, key){
		if(what=='port'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA PELABUHAN</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama Pelabuhan</b></div>'+
							   '	<div>'+myObj[0].nama_pelabuhan.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Kode Pelabuhan</b></div>'+
							   '	<div>'+myObj[0].kode_pelabuhan.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Satuan Kerja</b></div>'+
							   '	<div>'+myObj[0].satker.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Alamat</b></div>'+
							   '	<div>'+myObj[0].kabkota.toString()+', '+myObj[0].provinsi.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+myObj[0].longitude.toString()+' '+myObj[0].latitude.toString()+'</div>'+
							   '	<br>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/port/detail/"+key, true);
			xmlhttp.send();
			
		}
		if(what=='ship'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA KAPAL</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; max-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama Kapal</b></div>'+
							   '	<div>'+myObj[0].vessel_name.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jenis</b></div>'+
							   '	<div>'+myObj[0].jenis_kapal.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Bendera</b></div>'+
							   '	<div>'+myObj[0].mid.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>IMO / MMSI</b></div>'+
							   '	<div>'+myObj[0].imo_number.toString()+' / '+myObj[0].mmsi.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Call Sign</b></div>'+
							   '	<div>'+myObj[0].call_sign.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Arah / Kecepatan</b></div>'+
							   '	<div>'+myObj[0].course_over_ground.toString()+' / '+myObj[0].speed_over_ground.toString()+'kn</div>'+
							   '	<div style="margin-top:5px;"><b>Tujuan</b></div>'+
							   '	<div>'+myObj[0].destination.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Waktu Laporan</b></div>'+
							   '	<div>'+myObj[0].received_time_utc_unix.toString()+' WIB</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+ConvertDDToDMS(myObj[0].latitude.toString())+' '+ConvertDDToDMS(myObj[0].longitude.toString())+'</div>'+
							   '	<div><hr></div>'+
							   '	<div style="margin-top:5px;"><b>Pemilik</b></div>'+
							   '	<div>'+myObj[0].nama_pemilik.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Alamat Pemilik</b></div>'+
							   '	<div>'+myObj[0].alamat_pemilik.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tempat Pembuatan</b></div>'+
							   '	<div>'+myObj[0].tempat_pembuatan.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tahun Pembuatan</b></div>'+
							   '	<div>'+myObj[0].tahun_pembuatan.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>No. Akta</b></div>'+
							   '	<div>'+myObj[0].no_akta.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tempat Pendaftaran</b></div>'+
							   '	<div>'+myObj[0].tempat_pendaftaran.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tanggal Pendaftaran</b></div>'+
							   '	<div>'+myObj[0].tgl_pendaftaran.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>No. Tanda Pendaftaran</b></div>'+
							   '	<div>'+myObj[0].no_tanda_pendaftaran.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>No. Surat Ukur</b></div>'+
							   '	<div>'+myObj[0].no_surat_ukur.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tanggal Surat Ukur</b></div>'+
							   '	<div>'+myObj[0].tgl_surat_ukur.toString()+'</div>'+
							   '	<div><hr></div>'+
							   '	<div style="margin-top:5px;"><b>GT</b></div>'+
							   '	<div>'+myObj[0].dimensi_isikotor.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Panjang / Lebar</b></div>'+
							   '	<div>'+myObj[0].dimensi_panjang.toString()+' / '+myObj[0].dimensi_lebar.toString()+' m</div>'+
							   '	<div style="margin-top:5px;"><b>Dimensi Dalam</b></div>'+
							   '	<div>'+myObj[0].dimensi_dalam.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Isi Bersih</b></div>'+
							   '	<div>'+myObj[0].dimensi_isibersih.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>LOA</b></div>'+
							   '	<div>'+myObj[0].dimensi_loa.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Bahan</b></div>'+
							   '	<div>'+myObj[0].bahan_ket.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Penggerak</b></div>'+
							   '	<div>'+myObj[0].penggerak_ket.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jml. Cerobong</b></div>'+
							   '	<div>'+myObj[0].jml_cerobong.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jml. Geladak</b></div>'+
							   '	<div>'+myObj[0].jml_geladak.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jml. Baling</b></div>'+
							   '	<div>'+myObj[0].jml_baling.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Merk Mesin</b></div>'+
							   '	<div>'+myObj[0].mesin_merk.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Daya</b></div>'+
							   '	<div>'+myObj[0].daya_mesin1.toString()+' '+myObj[0].satuan_mesin1.toString()+'</div>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/ship/detail/"+key, true);
			xmlhttp.send();			
		}
		if(what=='sbnp'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA SBNP</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama SBNP</b></div>'+
							   '	<div>'+myObj[0].nm_location.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>No. DSI</b></div>'+
							   '	<div>'+myObj[0].dsi_nr.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jenis</b></div>'+
							   '	<div>'+myObj[0].jenis.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+ConvertDDToDMS(myObj[0].longitude.toString())+' '+ConvertDDToDMS(myObj[0].latitude.toString())+'</div>'+
							   '	<div style="margin-top:5px;"><b>Daya</b></div>'+
							   '	<div>'+myObj[0].power.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Ketinggian</b></div>'+
							   '	<div>'+myObj[0].high.toString()+' / '+myObj[0].elevation.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jangkauan</b></div>'+
							   '	<div>'+myObj[0].jangkauan.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Pemilik</b></div>'+
							   '	<div>'+myObj[0].pemilik.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Deskripsi</b></div>'+
							   '	<div>'+myObj[0].description.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b><i>Remark</i></b></div>'+
							   '	<div>'+myObj[0].remark.toString()+'</div>'+
							   '	<br>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/sbnp/detail/"+key, true);
			xmlhttp.send();
			
		}
		if(what=='srop'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA SROP</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama SROP</b></div>'+
							   '	<div>'+myObj[0].nama_srop.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Kelas</b></div>'+
							   '	<div>'+myObj[0].kelas_srop.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Callsign</b></div>'+
							   '	<div>'+myObj[0].call_sign_srop.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+ConvertDDToDMS(myObj[0].koordinat_lintang.toString())+' '+ConvertDDToDMS(myObj[0].koordinat_bujur.toString())+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jangkauan Area</b></div>'+
							   '	<div>'+myObj[0].coverage_area.toString()+'</div>'+
							   '	<br>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/srop/detail/"+key, true);
			xmlhttp.send();
			
		}
		if(what=='vts'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA VTS</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama VTS</b></div>'+
							   '	<div>'+myObj[0].namobj.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Jenis Layanan</b></div>'+
							   '	<div>'+myObj[0].jns_lynn.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Frekuensi Kerja</b></div>'+
							   '	<div>'+myObj[0].frek_krj.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Alat</b></div>'+
							   '	<div>'+myObj[0].alat.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+ConvertDDToDMS(myObj[0].latitude.toString())+' '+ConvertDDToDMS(myObj[0].longitude.toString())+'</div>'+
							   '	<br>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/vts/detail/"+key, true);
			xmlhttp.send();
			
		}
		if(what=='tersus'){
			var titleTxt = '<div style="font-size:16px !important; margin-top:3px;">DATA TERSUS/TUKS</div>';
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    var myObj = JSON.parse(this.responseText);			    
				var content  = '<div style="min-width:300px; margin-top:-10px; font-size:12px !important;">'+
							   '	<div><hr></div>'+
							   '	<div><b>Nama Tersus/TUKS</b></div>'+
							   '	<div>'+myObj[0].nama_tersus.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Tipe</b></div>'+
							   '	<div>'+myObj[0].tersus_tuks.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Bidang Usaha</b></div>'+
							   '	<div>'+myObj[0].bidang_usaha.toString()+'</div>'+
							   '	<div style="margin-top:5px;"><b>Koordinat</b></div>'+
							   '	<div>'+myObj[0].koordinat.toString()+'</div>'+
							   '	<br>'+
				  			   '</div>';
				 $('.contentGisDetail').html(content);
			  }
			};
			xmlhttp.open("GET", "/gis/tersus/detail/"+key, true);
			xmlhttp.send();
			
		}
		L.control.window(map,{position:'topRight', modal: true, title:titleTxt, content:'<div class="contentGisDetail" style="font-size:12px !important; min-width:300px;">Loading...</div>'}).show();
	}
	
	setTimeout(function(){
		loader.load(function(loader, resources) {
			var textures = [resources.port.texture,resources.sbnp.texture,resources.srop.texture,resources.vts.texture,resources.tersus.texture,resources.ship.texture];
			getJSON('gis/port', function(markers) {
				var legend = document.querySelector('div.legend.geometry');
				var legendContent = legend.querySelector('.content');
				var	pixiLayer = (function() {
					var firstDraw = true;
					var prevZoom;
					var markerSprites = [];
					var frame = null;
					var focus = null;
					var pixiContainer = new PIXI.Container();
					var doubleBuffering = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
					return L.pixiOverlay(function(utils) {
						var zoom = utils.getMap().getZoom();
						if (frame) {
							cancelAnimationFrame(frame);
							frame = null;
						}
						var container = utils.getContainer();
						var renderer = utils.getRenderer();
						var project = utils.latLngToLayerPoint;
						var scale = utils.getScale();
						var invScale = 1 / scale;
						if (firstDraw) {
							prevZoom = zoom;
							markers.forEach(function(marker) {
								var coords = project([marker.latitude, marker.longitude]);
								var markerSprite = new PIXI.Sprite(textures[0]);
								markerSprite.textureIndex = 0;
								markerSprite.x0 = coords.x;
								markerSprite.y0 = coords.y;
								markerSprite.anchor.set(0.5, 0.5);
								markerSprite.scale.set(invScale);
	
								container.addChild(markerSprite);
								markerSprites.push(markerSprite);
								markerSprite.legend = marker.nama_pelabuhan;
								markerSprite.longitude = marker.longitude;
								markerSprite.latitude = marker.latitude;
								markerSprite.kabkota = marker.kabkota;
								markerSprite.kode = marker.kode_pelabuhan;
							});
							var quadTrees = {};
							for (var z = map.getMinZoom(); z <= map.getMaxZoom(); z++) {
								var rInit = ((z <= 7) ? 16 : 24) / utils.getScale(z);
								quadTrees[z] = window.solveCollision(markerSprites, {r0: rInit, zoom: z});
							}
							function findMarkerPort(ll) {
								var layerPoint = project(ll);
								var quadTree = quadTrees[utils.getMap().getZoom()];
								var marker;
								var rMax = quadTree.rMax;
								var found = false;
								quadTree.visit(function(quad, x1, y1, x2, y2) {
									if (!quad.length) {
										var dx = quad.data.x - layerPoint.x;
										var dy = quad.data.y - layerPoint.y;
										var r = quad.data.scale.x * 16;
										if (dx * dx + dy * dy <= r * r) {
											marker = quad.data;
											found = true;
										}
									}
									return found || x1 > layerPoint.x + rMax || x2 + rMax < layerPoint.x || y1 > layerPoint.y + rMax || y2 + rMax < layerPoint.y;
								});
								return marker;
							}
							map.on('click', function(e) {
								var redraw = false;
								if (focus) {
									focus.texture = textures[focus.textureIndex];
									focus = null;
									legendContent.innerHTML = '';
									redraw = true;
								}
								var marker = findMarkerPort(e.latlng);
								if (marker) {
									focus = marker;
									if(layerPort==1){
										L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">PELABUHAN</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.kabkota.toString()+'</div><button type="button" onclick="openDetail(\'port\', \''+marker.kode+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
									}
									redraw = true;
								}
								if (redraw) utils.getRenderer().render(container);
							});
							var self = this;
							map.on('mousemove', L.Util.throttle(function(e) {
								var marker = findMarkerPort(e.latlng);
								if (marker) {
									L.DomUtil.addClass(self._container, 'leaflet-interactive');
								} else {
									L.DomUtil.removeClass(self._container, 'leaflet-interactive');
								}
							}, 32));
							L.DomUtil.addClass(self._container, 'layer-port');
						}
						if (firstDraw || prevZoom !== zoom) {
							markerSprites.forEach(function(markerSprite) {
								var position = markerSprite.cache[zoom];
								if (firstDraw) {
									markerSprite.x = position.x;
									markerSprite.y = position.y;
								} else {
									markerSprite.currentX = markerSprite.x;
									markerSprite.currentY = markerSprite.y;
									markerSprite.targetX = position.x;
									markerSprite.targetY = position.y;
									markerSprite.currentScale = markerSprite.scale.x;
									markerSprite.targetScale = (position.r * scale < 16) ? position.r / 16 : invScale;
								}
							});
						}
						var start = null;
						var delta = 1;
						function animate(timestamp) {
						    var progress;
							var scale = utils.getScale();
							var invScale = 1 / scale;
	
						  if (start === null) start = timestamp;
						  progress = timestamp - start;
						  var lambda = progress / delta;
						  if (lambda > 1) lambda = 1;
						  lambda = lambda * (0.4 + lambda * (2.2 + lambda * -1.6));
						  markerSprites.forEach(function(markerSprite) {
							  markerSprite.x = markerSprite.currentX + lambda * (markerSprite.targetX - markerSprite.currentX);
							  markerSprite.y = markerSprite.currentY + lambda * (markerSprite.targetY - markerSprite.currentY);
							  markerSprite.scale.set(invScale);
							});
							renderer.render(container);
						  if (progress < delta) {
						    frame = requestAnimationFrame(animate);
						  }
						}
						if (!firstDraw && prevZoom !== zoom) {
							frame = requestAnimationFrame(animate);
						}
						firstDraw = false;
						prevZoom = zoom;
						renderer.render(container);
					}, pixiContainer, {
						doubleBuffering: doubleBuffering,
						destroyInteractionManager: true
					});
				})();
				pixiLayer.addTo(map);
				toggleGroup();
				$('#overlay').hide();
			});
		});		
	},2000);
	
	function ConvertDMSToDD(degrees, minutes, seconds, direction) {
		var dd = degrees + minutes/60 + seconds/(60*60);
	
		if (direction == "S" || direction == "W") {
			dd = dd * -1;
		}
		return dd;
	}
	
	function ConvertDDToDMS(deg) {
		   var d = Math.floor (deg);
		   var minfloat = (deg-d)*60;
		   var m = Math.floor(minfloat);
		   var secfloat = (minfloat-m)*60;
		   var s = Math.round(secfloat);
		   if (s==60) {
		     m++;
		     s=0;
		   }
		   if (m==60) {
		     d++;
		     m=0;
		   }
		   return (d + "&deg; " + m + '\"' + s+"\'");
		}
	</script>
</body>
</html>