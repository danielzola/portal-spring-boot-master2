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
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/scrollbar/jquery.mCustomScrollbar.min.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sumoselect/sumoselect.min.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.css">
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
						            		<h1>Data Perusahaan</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Detil informasi data user perusahaan</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-min-height="25" data-gs-height="25" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmPerusahaan" name="frmPerusahaan" autocomplete="off">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<span><span style="color:#ff0000;">Perhatian!</span> Perubahan data hanya bisa dilakukan di OSS</span>									
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li><a class="removeWidget" href="#" title="Remove Panel"    data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div>
								<div class="console-panel-body pl-0 pr-0">
									<div class="console-form-body">
										<div class="row">
											<div class="col-lg-6 col-md-6">
											<h4>DATA PERUSAHAAN</h4>
											</div>
										</div>
										<div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nama Perusahaan <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].company_type_name} ${data[0].company_name}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>NIB <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].company_nib}" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>NPWP <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].company_taxno}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Alamat <span style="color:#ff0000;">*</span></label>
													<textarea class="form-control" readonly>${data[0].company_address}</textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Email <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].company_email}" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>No. Telp <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].company_phone}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Status <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="user_status" name="user_status" disabled>
														<option value="0" ${data[0].user_status == '0' ? 'selected' : ''}>Tidak Aktif</option>
														<option value="1" ${data[0].user_status == '1' ? 'selected' : ''}>Aktif</option>
														<option value="2" ${data[0].user_status == '2' ? 'selected' : ''}>Belum Diaktifasi</option>
													</select>
												</div>
											</div>
										</div>		
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
		<script src="${baseUrl}/assets/apps/js/script.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/sumoselect/jquery.sumoselect.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/jquery-alphanumeric/jquery.alphanumeric.pack.js"></script>
	</body>
</html>
