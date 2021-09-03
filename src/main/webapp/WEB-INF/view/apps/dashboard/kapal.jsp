<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang='en'>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${application_name} - ${application_description}</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="${application_name} - ${application_description}" />
	    <meta name="keywords" content="${application_name} - ${application_description}" />
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="-1" />
		<link rel="shortcut icon" href="${baseUrl}/assets/public/images/favicon/favicon.png">
		<link rel="stylesheet" href="${baseUrl}/assets/public/plugins/fontawesome/css/all.min.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/css/dripicon.css" type="text/css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/css/style.css" type="text/css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/css/responsive.css" type="text/css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/css/grid_stack.min.css" type="text/css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sumoselect/sumoselect.min.css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/jquery-upload/css/jquery.fileupload.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/colorpicker/bootstrap-colorpicker-plus.min.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/scrollbar/jquery.mCustomScrollbar.min.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/datepicker/datepicker.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/signature-pdf/assets/css/pdfcanvas.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/rangeslider/ion.rangeSlider.css">
	    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/pixioverlay/css/leaflet.css">
	    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.SlideMenu.css">
	    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.Window.css">
	    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/leaflet.fullscreen.css">	
	    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/jquery-confirm/jquery_confirm.css">	
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/datatable/style.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colVis.css" />	    
	</head>
	<body class="theme-body">
		<div class="theme-wrapper">
			<header class="style4"></header>
			<div class="theme-content">
				<aside class="console-sidemenu active gradient" id="sidemenu">
					${userMenu}
				</aside>
				<div class="content-area">
					<div id="toparea">
						<div class="page-level-filteration">
						      <div class="filteration-text" style="margin:20px 0px 30px 0px;">
						      		<div class="row col-lg-10">
						      			<div class="col-lg-10 col-md-10">
						            		<h1>Dashboard Kapal</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;"></div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="8" data-gs-min-height="19" data-gs-height="19" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmEsignDoc" name="frmEsignDoc" autocomplete="off">
								<input type="hidden" value="${data[0].user_id}" id="user_id" name="user_id" required>
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<h4>Lokasi Kapal</h4>		
										<span>Terakhir update 13 Mei 2020</span>							
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"  data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div>
								<div class="console-panel-body pl-0 pr-0" style="margin-top:-20px;">
									<div class="console-form-body row">
								        <div class="row" id="dashboard-map" style="height:470px; margin:0px 1px 0px 1px;">
										    <div class="legend geometry top center hide" style="display:none;"></div>
								        </div>										
									</div>
								</div>
							</form>						
						</div>	
						<div class="grid-stack-item" data-gs-x="8" data-gs-y="5" data-gs-width="3" data-gs-height="7" data-gs-min-height="5"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content  console-no-header">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<h4>Jumlah Kapal</h4>									
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div>
								<div class="console-panel-body-noscroll">
									<div class="console-stats text-center">
										<div class="row">
											<div class="col-lg-6">
												<div class="stats-num">
													<span onclick="bypemilik('1')" id="DsbPelabuhanJml1" style="font-size:30px; cursor:pointer;" class="badge badge-success"></span>
												</div>
												<div style="margin-top:10px;">Kapal Umum</div>											
											</div>
											<div class="col-lg-6">
												<div class="stats-num">
													<span onclick="bypemilik('3')" id="DsbPelabuhanJml2" style="font-size:30px; cursor:pointer;" class="badge badge-primary"></span>
												</div>
												<div style="margin-top:10px;">Kapal Negara</div>											
											</div>
										</div>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>
						<div class="grid-stack-item" data-gs-x="8" data-gs-y="8" data-gs-width="3" data-gs-height="10" data-gs-min-height="12"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content  console-no-header">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<h4>Jumlah Per Bendera</h4>	
										<span>Urut berdasarkan jumlah data</span>								
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div>
								<div class="console-panel-body no-padding">
									<table class="table" id="benderatable">
										<thead class="sorticon-pos">
											<tr>
												<th></th>
												<th>Bendera Asal</th>
												<th>Jumlah</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>								
								</div>
							</div><!-- Console Panel -->
						</div>
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="19" data-gs-width="12" data-gs-height="18" data-gs-min-height="10"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header">
									<div class="cph-left">
										<h5>Data Kapal Per Tahun Pembuatan</h5>
										<span>Data 10 tahun terakhir</span>
									</div>
									<div class="cph-right">
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="Expand Panel To Full Screen"    data-rel="tooltip"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body">
									<div class="chart-container">
										<canvas id="barchart1" width="100" height="450"></canvas>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="37" data-gs-width="4" data-gs-height="10" data-gs-min-height="10"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header">
									<div class="cph-left">
										<h5>Berdasarkan Jenis</h5>
									</div>
									<div class="cph-right">
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="Expand Panel To Full Screen"    data-rel="tooltip"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body no-padding">
									<div class="chart-container">
										<canvas id="donutchart1" width="100" height="50"></canvas>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>						
						<div class="grid-stack-item" data-gs-x="4" data-gs-y="37" data-gs-width="4" data-gs-height="10" data-gs-min-height="10"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header">
									<div class="cph-left">
										<h5>Berdasarkan Kategori</h5>
									</div>
									<div class="cph-right">
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="Expand Panel To Full Screen"    data-rel="tooltip"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body no-padding">
									<div class="chart-container">
										<canvas id="donutchart2" width="100" height="50"></canvas>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>						
						<div class="grid-stack-item" data-gs-x="8" data-gs-y="37" data-gs-width="4" data-gs-height="10" data-gs-min-height="10"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header">
									<div class="cph-left">
										<h5>Berdasarkan GT</h5>
									</div>
									<div class="cph-right">
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="Expand Panel To Full Screen"    data-rel="tooltip"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body no-padding">
									<div class="chart-container">
										<canvas id="donutchart3" width="100" height="50"></canvas>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>						
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="55" data-gs-width="8" data-gs-height="18" data-gs-min-height="10"  data-gs-min-width="4">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header">
									<div class="cph-left">
										<h5>Data Pelabuhan</h5>
										<span>Urut berdasarkan kode pelabuhan</span>
									</div>
									<div class="cph-right">
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="Expand Panel To Full Screen"    data-rel="tooltip"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li class="dropdown">
												<a href="#" data-toggle="dropdown" title="Panel Options"    data-rel="tooltip"> <i class="icon dripicons-dots-3"></i></a>
												<div class="dropdown-menu  dropdown-menu-right theme-media">
													<div class="dropdown-menu-header"> <div class="dmh-inner"> <h4>Pengaturan</h4> </div> </div>
													<a class="dropdown-item cp" href="#" title=""> Tampilkan di Halaman Depan</a>
												</div>
											</li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body no-padding">
									<table class="table" id="pelabuhantable">
										<thead class="sorticon-pos">
											<tr>
												<th>Pelabuhan</th>
												<th>Koordinat</th>
												<th>Satker</th>
												<th>Jml. Term</th>
												<th>Kedalaman</th>
												<th>Jml. Kapal</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>								
								</div>
							</div><!-- Console Panel -->
						</div>
					</div>
				</div>
				<aside class="more-opt-panel"></aside>
			</div>
		</div>
	    <script src="${baseUrl}/assets/apps/js/jquerymain_jquerytouch_jquerycookies.js"></script>
		<script src="${baseUrl}/assets/apps/js/bootstrap_popper.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/loader/loadingoverlay.min.js"></script>    
		<script src="${baseUrl}/assets/apps/js/plugins/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
		<script>var _tooltip = jQuery.fn.tooltip;</script>
		<script src="${baseUrl}/assets/apps/js/jquery_ui.min.js"></script>
		<script>jQuery.fn.tooltip = _tooltip;</script>
		<script src="${baseUrl}/assets/apps/js/grid_stack_pack.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/script.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/sumoselect/jquery.sumoselect.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.ui.widget.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.fileupload.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datepicker/datepicker.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/daterangepicker/moment.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/daterangepicker/daterangepicker.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/vendor/pdfjs/pdf.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/vendor/interact/interact.min.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/assets/js/ionicons.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/assets/js/pdf.config.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/assets/js/signature.config.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/signature-pdf/assets/js/app.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/rangeslider/ion.rangeSlider.min.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/pixioverlay/js/example.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/pixioverlay/js/tools.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/leaflet/esri-leaflet.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.SlideMenu.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.Window.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/leaflet.fullscreen.js"></script>   
		<script src="${baseUrl}/assets/apps/js/plugins/sparkline/jquery.sparkline.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/chartjs/Chart.bundle.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/jquery-confirm/jquery_confirm.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/jquery.dataTables.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colReorder.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colVis.min.js"></script>
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
			
		    var map = L.map('dashboard-map').setView([-1.789275,118], 4);
		    var rbi= L.esri.tiledMapLayer({url: "https://portal.ina-sdi.or.id/arcgis/rest/services/RBI/Basemap/MapServer"});
		    var osm=L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(map);
		    var imgesri=L.tileLayer('http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}',{maxZoom: 20,subdomains:['mt0','mt1','mt2','mt3']});
		    bm_on=osm;
		    
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
			map.zoomControl.setPosition('topleft');
			
			var LayerMenu = '<div style="padding:0px 20px 20px 30px; margin-top:-50px !important;">'+
		       '<h4 style="font-size:18px !important; color:#FFF;">Layer Dashboard</h4>'+
		       '<hr>'+
		       '<b style="font-size:14px !important; color:#FFF;">Peta Dasar</b><br>'+
		       '<select onchange="changeMap(this.value)" style="margin-top:5px; margin-bottom:5px;" class="form-control"><option value="rbi">Peta RBI</option><option value="osm">Open Street Map</option><option value="imgesri">ESRI Imagery</option></select>'+
		       '<hr>'+
		       '<b style="font-size:14px !important; color:#FFF;">Layer Data</b><br>'+
		       '<div class="console-checkbox-list" style="margin-top:10px;">'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" value="port" class="markerGroup cbxpelabuhan"> <img src="${baseUrl}/assets/public/images/icon/port.webp"> &nbsp; Pelabuhan'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" checked value="ship" class="markerGroup cbxkapal"> <img src="${baseUrl}/assets/public/images/icon/ship-green.webp"> &nbsp; Posisi Kapal'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" value="sbnp" class="markerGroup cbxsbnp"> <img src="${baseUrl}/assets/public/images/icon/sbnp.webp"> &nbsp; SBNP'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" value="srop" class="markerGroup cbxsrop"> <img src="${baseUrl}/assets/public/images/icon/srop.webp"> &nbsp; SROP'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" value="tersus" class="markerGroup cbxtersus"> <img src="${baseUrl}/assets/public/images/icon/tersus.webp"> &nbsp; TERSUS/TUKS'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		           '<label class="con-check" style="color:#FFF;">'+
		               '<input type="checkbox" onclick="toggleGroup()" value="vts" class="markerGroup cbxvts"> <img src="${baseUrl}/assets/public/images/icon/vts.webp"> &nbsp; VTS'+
		               '<span class="checkmark"></span>'+
		           '</label>'+
		       '</div>'+
		       '<hr>'+
		   '</div>';
			var slideMenu = L.control.slideMenu(LayerMenu, {position: 'topright', menuposition: 'topright', width: '30%', icon: 'fa-layer-group'}).addTo(map);
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
			var slideMenuSearch = L.control.slideMenu(LayerMenuSearch, {position: 'topright', menuposition: 'topright', width: '30%', icon: 'fa-search'}).addTo(map);
			map.addControl(new L.Control.Fullscreen({position: 'topleft'}));
			
			var loader = new PIXI.loaders.Loader();
			loader.add('ship', '${baseUrl}/assets/public/images/icon/ship-green.webp');
			
			var layerShip=1;
			
			function toggleGroup(){
			    $('.leaflet-pixi-overlay').hide();
			    $('.markerGroup').each(function(){
			        if($(this).prop('checked')){
			            $('.layer-'+$(this).val().toString()).show();
			            if($(this).val().toString()=='ship'){
			            	layerShip = 1;
			            }
			        }else{
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
		
			
			function openDetail(what, key){
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
			
			function loadGisPelabuhan(what,key){
				var url = '/gis/ship';
				if(what.length>0){
					url = '/gis/ship/'+what+'/'+key;
				}
				loader.load(function(loader, resources) {
					var textures = [resources.ship.texture];
					getJSON(url, function(markers) {
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
											if(layerShip==1){
												L.popup({className: 'pixi-popup'}).setLatLng(e.latlng).setContent('<div style="padding:5px 5px 5px 5px; width:200px; min-width:200px;"><b style="color:#6D6D6D;">KAPAL</b><br><div style="font-size:16px !important; color:#3B5874;"><b><span style="color:#2F4099;">'+marker.legend.toString()+'</span></b></div><div style="padding-bottom:10px;">'+marker.tipe_kapal.toString()+'</div><button onclick="openDetail(\'ship\', \''+marker.kapal_id+'\')" class="btn btn-sm btn-primary"><i class="fa fa-info-circle"></i> Informasi</button></div></div>').openOn(map);
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
				});	
			}
			
			setTimeout(function(){
				loadGisPelabuhan('','');
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
			
			//Widget Jumlah
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  var myObj = JSON.parse(this.responseText);
				  $('#DsbPelabuhanJml1').html(myObj.umum.toString());
				  $('#DsbPelabuhanJml2').html(myObj.negara.toString());
			  }
			};
			xmlhttp.open("GET", "/dashboard/kapal/jumlah", true);
			xmlhttp.send();						  
			
			function bypemilik(pemilik){
				loadGisPelabuhan('pemilik', pemilik);
				loadbenderatable('pemilik', pemilik)
			}
			
			//Widget Satker
			var benderatable;
			loadbenderatable('', '');
			
			function loadbenderatable(what, key){
				if(benderatable != null){
					benderatable.destroy();				
				}
				benderatable = $('#benderatable').DataTable({
			            "ajax": {
			                "url": "/dashboard/kapal/bendera",
			                "type": "POST",
			                "data": {what:what, key:key}
			            },
			            "columns": [{
			            	"data": "kode",
			                "visible": false,
			                "searchable": false
			            },{
			            	"data": "keterangan"
			            },{
			                "data": "nilai"
			            }],
			            "paging":   false,
			            "info":     false,
			            "searching": false,
			            "order": [[ 2, "desc" ], [ 1, "asc" ]],
			            "responsive":true,
			            "colReorder": {
			                  realtime: false
			            },
			            "columnDefs": [ 
				            {
				                "targets": 2,
				                "render": function ( data, type, row, meta ) {
				                	return '<span style="font-size:12px;" class="badge badge-success badge-pill">'+row.nilai+'</span>'
			                }
			            }],
			            "stateSave": false,
			            "createdRow": function(row, data, dataIndex) {
			            	$(row).css("cursor", "pointer");
			            }
			      });
				  
			      function format(d) {
			    	  	var id = d.kode;
			    	  	$.post("/dashboard/pelabuhan/jumlah",{what:'satker',key:id},function(data){
			    	  		$('#DsbPelabuhanJml').html(data);
						});		
			    	  	$('.layer-ship').remove();
			    	  	loadGisPelabuhan('bendera',id);
			    	  	loadpelabuhantable('satker',id);
			    	  	chartKapal('satker',id);
			      }
					
			      function formatreset(d) {
			    	  	$.post("/dashboard/pelabuhan/jumlah",{what:''},function(data){
			    	  		$('#DsbPelabuhanJml').html(data);
						});			 
			    	  	$('.layer-ship').remove();
			    	  	loadGisPelabuhan('','');	
			    	  	loadpelabuhantable('','');
			    	  	chartKapal('','')
			      }
			      
			      $('#benderatable tbody').on('click', 'tr', function () {
			          var tr = $(this).closest('tr');
			          var row = benderatable.row(tr);
			          $('#benderatable tbody tr').css('font-weight', 'normal');
			          if (tr.hasClass('selectedx')) {
			        	  tr.removeClass('selectedx');
			        	  formatreset(row.data());
			          }
			          else {
			        	  tr.css('font-weight','bold');
			              tr.addClass('selectedx');
			              format(row.data());
			          }			          
			          
			      });
			      
			      var colvis = new $.fn.dataTable.ColVis(benderatable);
		
			}
			
			var pelabuhantable;
			function loadpelabuhantable(what, key){
				if(pelabuhantable != null){
					pelabuhantable.destroy();				
				}
				pelabuhantable = $('#pelabuhantable').DataTable({
		            "ajax": {
		                "url": "/dashboard/pelabuhan/table",
		                "type": "POST",
		                "data": {what:what, key:key}
		            },
		            "columns": [{
		            	"data": "kode_pelabuhan"
		            },{
		                "data": "koordinat_lintang"
		            },{
		                "data": "satker"
		            },{
		                "data": "jml_terminal"
		            },{
		                "data": "kedalaman_min"
		            },{
		                "data": "jml_kapal"
		            }],
		            "columnDefs": [ 
		            	{
			                "targets": 0,
			                "render": function ( data, type, row, meta ) {
			                	return row.kode_pelabuhan+' - '+row.nama_pelabuhan
		               		}
			            },
		            	{
			                "targets": 3,
			                "width": "200px"
			            },
		            	{
			                "targets": 5,
			                "width": "200px"
			            },
			            {
			                "targets": 1,
			                "render": function ( data, type, row, meta ) {
			                	return row.koordinat_lintang+' '+row.koordinat_bujur
		               		}
		            	},
			            {
			                "targets": 4,
			                "render": function ( data, type, row, meta ) {
			                	return 'Min: '+row.kedalaman_min+'; Max: '+row.kedalaman_max
		               		}
		            	}
			        ],
		            "paging":   false,
		            "info":     false,
		            "searching": false,
		            "order": [[ 0, "asc" ]],
		            "responsive":true,
		            "colReorder": {
		                  realtime: false
		            },
		            "stateSave": false,
		            "createdRow": function(row, data, dataIndex) {
		            	$(row).css("cursor", "pointer");
		            }
		      });
			}
			$(window).on("load", function () {		
				  loadpelabuhantable('','');
			      function format (d) {
			    	  	var id = d.user_name;
			    	    return '${roleActionTable}';
			      }
					
			      var colvis = new $.fn.dataTable.ColVis(benderatable);
		
			});
			
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				     var myObj = JSON.parse(this.responseText);
				     var label = [];
				     var value = [];
				     for(var i=0; i<myObj.data.length; i++){
				    	 label.push(myObj.data[i].keterangan);
	                     value.push(myObj.data[i].nilai);
				     }
					 var ctx = document.getElementById('barchart1').getContext('2d');
				     window.myBar = new Chart(ctx, {
				            type: 'line',
				            data: {
							      labels: label,
							      datasets: [{
							            label: 'Kapal',
							            backgroundColor: 'rgba(33,134,253,0.3)',
							            data: value
							      }]
							},
				            options: {
				            	  onClick: graphClickEvent,
				                  responsive: true,
				                  maintainAspectRatio:false,
				                  title: {
				                        display: false
				                  },
				                  legend: {
				                      position: 'top',
				                      display: true,
				       
				                  },
				                  tooltips: {
				                        mode: 'index',
				                        intersect: true
				                  },
				                  scales: {
				                      xAxes: [{
				                          ticks: {
				                              autoSkip: false,
				                              maxRotation: 90,
				                              minRotation: 90
				                          }
				                      }]
				                  },
				                  plugins: {
			                      	datalabels: {
				                        color: 'black',
				                        font: {
				                          weight: 'normal'
				                        },
				                        formatter: function(value, context) {
				                          return Math.round(value);
				                        }
				                     }
			                   	  }
				            }
				      });
			  }
			};
			xmlhttp.open("GET", "/dashboard/kapal/tahun", true);
			xmlhttp.send();						  
			
			function graphClickEvent(event, array){
			    if(array[0]){
					var id = array[0]._model.label.toString();
				    if(array[0]){
			    	  	$.post("/dashboard/pelabuhan/jumlah",{what:'propinsi',key:id},function(data){
			    	  		$('#DsbPelabuhanJml').html(data);
						});		
			    	  	$('.layer-ship').remove();
			    	  	loadGisPelabuhan('propinsi',id);
			    	  	loadpelabuhantable('propinsi',id);
			    	  	loadbenderatable('propinsi', id)
			    	  	chartKapal('propinsi',id);			    	
				    }

			    }
			}
			
			var myChart1;
			var myChart2;
			var myChart3;
			chartKapal('','');
			
			function chartKapal(what,key){
				if(myChart1 != null){
					myChart1.destroy();				
				}				
	    	  	$.post("/dashboard/kapal/jenis",{what:what, key:key},function(myObj){
				    var labelkapal = [];
				    var valuekapal = [];
	    	  		for(var i=0; i<myObj.data.length; i++){
				    	 labelkapal.push(myObj.data[i].keterangan);
				    	 valuekapal.push(myObj.data[i].nilai);	    	  			
	    	  		}
	    	  		var ctx = document.getElementById("donutchart1").getContext('2d');
				    myChart1 = new Chart(ctx, {
				         type: 'doughnut',
				         data: {
				             labels: labelkapal,
				             datasets: [{
				                 label: '',
				                 data: valuekapal,
				                 backgroundColor: [
				                     '#afd6fa',
				                     '#82b1dd',
				                     '#6ea1cf',
				                     '#638db3'
				                 ]
				             }]
				         },
				         options: {
				             title: {
				               display: false,
				             },
				             tooltips: {
				               mode: 'nearest',
				               intersect: false,
				               position: 'nearest',
				               xPadding: 10,
				               yPadding: 10,
				               caretPadding: 10
				             },
				             legend: {
				               display: false
				             },
				             responsive: true,
				             maintainAspectRatio: true,
			                  plugins: {
			                      	datalabels: {
				                        color: 'white',
				                        font: {
				                          weight: 'normal'
				                        }
				                     }
			                   	  },
				             scales: {
				                 xAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: ''
				                   }
				                 }],
				                 yAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: 'Value'
				                   },
				                   ticks: {
				                     beginAtZero: true
				                   }
				                 }]
				             }
				         }
				     });
				},'json');
	    	  	
				if(myChart2 != null){
					myChart2.destroy();				
				}				
	    	  	$.post("/dashboard/kapal/kategori",{what:what, key:key},function(myObj){
				    var labelkapal = [];
				    var valuekapal = [];
	    	  		for(var i=0; i<myObj.data.length; i++){
				    	 labelkapal.push(myObj.data[i].keterangan);
				    	 valuekapal.push(myObj.data[i].nilai);	    	  			
	    	  		}
	    	  		var ctx = document.getElementById("donutchart2").getContext('2d');
				    myChart1 = new Chart(ctx, {
				         type: 'doughnut',
				         data: {
				             labels: labelkapal,
				             datasets: [{
				                 label: '',
				                 data: valuekapal,
				                 backgroundColor: [
				                     '#2b70af',
				                     '#3f6689',
				                     '#214769',
				                     '#ffa0bc'
				                 ]
				             }]
				         },
				         options: {
				             title: {
				               display: false,
				             },
				             tooltips: {
				               mode: 'nearest',
				               intersect: false,
				               position: 'nearest',
				               xPadding: 10,
				               yPadding: 10,
				               caretPadding: 10
				             },
				             legend: {
				               display: false
				             },
				             responsive: true,
				             maintainAspectRatio: true,
			                  plugins: {
			                      	datalabels: {
				                        color: 'white',
				                        font: {
				                          weight: 'normal'
				                        }
				                     }
			                   	  },
				             scales: {
				                 xAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: ''
				                   }
				                 }],
				                 yAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: 'Value'
				                   },
				                   ticks: {
				                     beginAtZero: true
				                   }
				                 }]
				             }
				         }
				     });
				},'json');
	    	  	
	    	  	if(myChart2 != null){
					myChart2.destroy();				
				}				
	    	  	$.post("/dashboard/kapal/kategori",{what:what, key:key},function(myObj){
				    var labelkapal = [];
				    var valuekapal = [];
		    	    labelkapal.push('< 100');
		    	    labelkapal.push('100 - 500');
		    	    labelkapal.push('500 - 1000');
		    	    labelkapal.push('> 1000');
		    	    valuekapal.push('57956');	    	  			
		    	    valuekapal.push('20301');	    	  			
		    	    valuekapal.push('3160');	    	  			
		    	    valuekapal.push('9820');	    	  			
	    	  		var ctx = document.getElementById("donutchart3").getContext('2d');
				    myChart1 = new Chart(ctx, {
				         type: 'doughnut',
				         data: {
				             labels: labelkapal,
				             datasets: [{
				                 label: '',
				                 data: valuekapal,
				                 backgroundColor: [
				                     '#2b70af',
				                     '#3f6689',
				                     '#214769',
				                     '#ffa0bc'
				                 ]
				             }]
				         },
				         options: {
				             title: {
				               display: false,
				             },
				             tooltips: {
				               mode: 'nearest',
				               intersect: false,
				               position: 'nearest',
				               xPadding: 10,
				               yPadding: 10,
				               caretPadding: 10
				             },
				             legend: {
				               display: false
				             },
				             responsive: true,
				             maintainAspectRatio: true,
			                  plugins: {
			                      	datalabels: {
				                        color: 'white',
				                        font: {
				                          weight: 'normal'
				                        }
				                     }
			                   	  },
				             scales: {
				                 xAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: ''
				                   }
				                 }],
				                 yAxes: [{
				                   display: false,
				                   gridLines: false,
				                   scaleLabel: {
				                     display: true,
				                     labelString: 'Value'
				                   },
				                   ticks: {
				                     beginAtZero: true
				                   }
				                 }]
				             }
				         }
				     });
				},'json');
			}
		</script>
	</body>
</html>
