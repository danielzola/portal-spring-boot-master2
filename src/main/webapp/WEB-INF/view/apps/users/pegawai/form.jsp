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
						      		<div class="row col-lg-8">
						      			<div class="col-lg-2 col-md-2 align-center">
							            	<div class="photo-holder">
									            <c:choose>
													<c:when test="${fn:length(data[0].user_photo) > 0}">
														<div class="photo-thumb" style="background-image: url(${data[0].user_photo}) !important;"></div>
													</c:when>
													<c:otherwise>
														<div class="photo-thumb" style="background-image: url(${baseUrl}/assets/apps/images/noprofile.png) !important;"></div>
													</c:otherwise>
												</c:choose>
											</div>
						      			</div>
						      			<div class="col-lg-10 col-md-10" style="padding-top:30px;">
						            		<h1>Data Pegawai</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Detil informasi data user pegawai</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-min-height="40" data-gs-height="40" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmPegawai" name="frmPegawai" autocomplete="off">
								<input type="hidden" value="${data[0].user_id}" id="user_id" name="user_id" required>
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<span>Data dengan tanda bintang (<span style="color:#ff0000;">*</span>) wajib diisi</span>									
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
											<h4>DATA PEGAWAI</h4>
											</div>
										</div>
										<div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nama Pegawai <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].emp_name}" id="emp_name" name="emp_name" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nomor Induk Pegawai <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].emp_number}" id="emp_number" name="emp_number" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nomor Induk Kependudukan <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${data[0].user_nik}" id="user_nik" name="user_nik" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Jenis Kelamin <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="emp_gender" name="emp_gender" required disabled>
														<option value="">-- Pilih Jenis Kelamin --</option>
														<option value="L" ${data[0].emp_gender == 'L' ? 'selected' : ''}>Pria</option>
														<option value="P" ${data[0].emp_gender == 'P' ? 'selected' : ''}>Wanita</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Jabatan <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].position_name}" id="position_name" name="position_name" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Unit Kerja <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].unit_name}" id="unit_name" name="unit_name" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>&nbsp; </label>
													<input type="text" class="form-control" readonly value="${data[0].office_name}" id="office_name" name="office_name" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Email <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" readonly value="${data[0].user_email}" id="user_email" name="user_email" placeholder="Email" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>No. Handphone Pribadi <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${data[0].user_hp}" id="user_hp" name="user_hp" placeholder="Contoh: +62812xxxxxxx" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>No. Handphone Dinas </label>
													<input type="text" class="form-control" readonly value="${data[0].emp_hp}" id="emp_hp" name="emp_hp" required>
												</div>
											</div>
										</div>
										<div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
											<h4>DATA LOGIN</h4>
											</div>
										</div>
										<div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Hak Akses <span style="color:#ff0000;">*</span></label>
													<select multiple="multiple" class="form-control" id="role_id" name="role_id" required></select>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Status <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="user_status" name="user_status">
														<option value="0" ${data[0].user_status == '0' ? 'selected' : ''}>Tidak Aktif</option>
														<option value="1" ${data[0].user_status == '1' ? 'selected' : ''}>Aktif</option>
														<option value="2" ${data[0].user_status == '2' ? 'selected' : ''}>Belum Diaktifasi</option>
													</select>
												</div>
											</div>
										</div>		
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Username <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${data[0].user_name}" readonly id="user_name" name="user_name" placeholder="Username" required>
												</div>
											</div>
										</div>					
										<div class="row btn_reset_password">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Password <span class="requirePass"></span></label><br>																						
													<button type="button" onclick="$('.btn_reset_password').hide(); $('.reset_password').show(); $('.password').prop('required',true)" class="btn btn-sm btn-primary">Reset Password</button>
												</div>
											</div>
										</div>
										<div class="row reset_password" style="display:none;">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Password <span style="color:#ff0000;">*</span></label>
													<input type="password" class="form-control password" id="user_password" name="user_password" autocomplete="new-password">
												</div>
											</div>
										</div>								
										<div class="row reset_password" style="display:none;">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Konfirmasi Password <span style="color:#ff0000;">*</span></label>
													<input type="password" class="form-control password" id="user_password2" name="user_password2" autocomplete="new-password">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="console-footer">
									<button type="submit" class="btn btn-sm btn-info">Simpan</button>
									<button type="button" onclick="history.back()" class="btn btn-sm btn-secondary">Batal</button>
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
		<script>
	        $('#role_id').SumoSelect({
	            search: true,
	            searchText: 'Pilih Hak Akses'
	      	});
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  $('#role_id').html(this.responseText);
				  $('#role_id')[0].sumo.reload();
			  }
			};
			xmlhttp.open("GET", "/option/role/${fn:replace(data[0].role_id,';',',')}", true);
			xmlhttp.send();	
			
			$('#user_nik').numeric();
			$('#user_hp').numeric({allow:"+"});
			
			$('#frmPegawai').submit(function(){
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
						var parseData = $("#frmPegawai").serializeArray();
						$.post("/users/pegawai/save",parseData,function(data){
							if(data.code=='01'){
								swal({
									title: "Data Pegawai",
									text: data.message.toString(),
									type: "success",
									confirmButtonColor: "#60c84c",
									confirmButtonText: "OK",
									closeOnConfirm: false
								},function(isConfirm){
										document.location = '/users/pegawai/list';					
								}); 						
							}else{
								swal('Mohon Maaf', data.message.toString(), 'warning');
							}
						},'json');
					}
				}); 
				return false;
			});
		</script>
	</body>
</html>
