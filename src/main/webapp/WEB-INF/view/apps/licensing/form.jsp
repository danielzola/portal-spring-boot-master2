<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/scrollbar/jquery.mCustomScrollbar.min.css">
	<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sumoselect/sumoselect.min.css">
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
					      			<div class="col-lg-12 col-md-12">
					            		<h1>Layanan Baru</h1>
					            		<div style="font-weight:normal !important; margin-top:5px;">Form pengajuan layanan baru</div>
					      			</div>
					      		</div>
					      </div>
					</div>
				</div>
				<div class="grid-stack gs-area">
					<div class="grid-stack-item" data-gs-x="0" data-gs-y="5" data-gs-width="12" data-gs-height="8" data-gs-min-height="8" data-gs-min-width="6">
						<form class="console-panel grid-stack-item-content" id="frmTTE" name="frmTTE">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<span>Data dengan tanda bintang (<span style="color:#ff0000;">*</span>) wajib diisi</span>
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li><a class="removeWidget" href="#" title="Remove Panel" data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div>
								<div class="console-form-body">
									<div class="row">
										<div class="col-lg-12 col-md-12">
											<div class="form-group" style="">
												<label class="block">Pilih Layanan <span style="color:#ff0000;">*</span></label>
												<select class="full" id="doc_code" name="doc_code" required>
													  <option value="">-- Pilih Layanan --</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="console-footer">
									<button type="submit" class="btn btn-sm btn-info">Proses</button>								
								</div>	
							</div>
						</form>				
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
	<script src="${baseUrl}/assets/apps/js/plugins/sumoselect/jquery.sumoselect.min.js"></script>
	<script src="${baseUrl}/assets/apps/js/script.js"></script>
    <script>
    $(window).on("load",function(){
        $('#doc_code').SumoSelect({
            search: true,
            searchText: 'Pilih Layanan'
      	});
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
			  $('#doc_code').html(this.responseText);
			  $('#doc_code')[0].sumo.reload();
		  }
		};
		xmlhttp.open("GET", "/option/layanan", true);
		xmlhttp.send();
		
        $('#frmTTE').submit(function(){
        	var proses = $('#doc_code').val();
        	document.location = '/layanan/process-start/'+proses;
        	return false;
        });    
  	});    
    </script>
</body>
</html>
