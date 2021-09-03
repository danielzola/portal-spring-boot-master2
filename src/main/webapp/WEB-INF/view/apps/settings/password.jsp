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
						      			<div class="col-lg-12 col-md-12">
						            		<h1>Edit Password</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Form edit password pengguna</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-height="15"  data-gs-min-height="7"  data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmPassword" name="frmPassword" autocomplete="off">
								<input type="hidden" name="user_name" id="user_name" value="${userData[0].user_name}">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<span>Data dengan tanda bintang (<span style="color:#ff0000;">*</span>) wajib diisi<br>Minimal 8 karakter terdiri dari huruf besar, huruf kecil dan angka</span>									
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
												<div class="form-group">
													<label>Password Lama <span style="color:#ff0000;">*</span></label>
													<input type="password" class="form-control" id="txt_password" name="txt_password" required autocomplete="new-password">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Password Baru <span style="color:#ff0000;">*</span></label>
													<input type="password" class="form-control" id="txt_password_baru" name="txt_password_baru" required autocomplete="new-password">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Konfirmasi Password Baru <span style="color:#ff0000;">*</span></label>
													<input type="password" class="form-control" id="txt_password_konfirm" name="txt_password_konfirm" required autocomplete="new-password">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="console-footer">
									<button type="submit" class="btn btn-sm btn-info">Simpan</button>
									<button type="button" class="btn btn-sm btn-secondary">Batal</button>
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
		<script src="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
		<script>var _tooltip = jQuery.fn.tooltip;</script>
		<script src="${baseUrl}/assets/apps/js/jquery_ui.min.js"></script>
		<script>jQuery.fn.tooltip = _tooltip;</script>
		<script src="${baseUrl}/assets/apps/js/grid_stack_pack.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/script.js"></script>
		<script>
		$("#frmPassword").submit(function(){
			swal({
				title: "Konfirmasi",
				text: "Apakah Anda Yakin Ingin Menyimpan Data Ini?",
				type: "info",
				showCancelButton: true,
				confirmButtonColor: "#8CD4F5",
				confirmButtonText: "Simpan",
				cancelButtonText: "Batal",
				closeOnConfirm: false
			},function(isConfirm){
				if(isConfirm){
					$('.cancel').hide();
					$('.confirm').attr('disabled', 'disabled');
					var parseData = $("#frmPassword").serializeArray();
					$.post("/settings/password/save",parseData,function(data){
						if(data.code=='01'){
							swal({
								title: "Edit Password",
								text: "Edit password berhasil",
								type: "success",
								confirmButtonColor: "#60c84c",
								confirmButtonText: "OK",
								closeOnConfirm: false
							},function(isConfirm){
								if(isConfirm){
									document.location = '/';
								}
							}); 						
						}else{
							swal('Perhatian', data.message.toString(), 'warning');
						}
					},'json');
				}
			}); 
			return false;
		});		
		</script>
	</body>
</html>
