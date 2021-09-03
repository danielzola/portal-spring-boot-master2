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
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/datatable/style.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.css">
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sumoselect/sumoselect.min.css" />
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
						            		<h1>Hak Akses</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Manajemen hak akses aplikasi</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-min-height="25" data-gs-height="25" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmRole" name="frmRole" autocomplete="off">
								<input type="hidden" value="${empty data[0].role_id ? '' : data[0].role_id}" id="role_id" name="role_id" required>
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
												<div class="form-group">
													<label>Nama Hak Akses <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${empty data[0].role_name ? '' : data[0].role_name}" name="role_name" id="role_name" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Deskripsi <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${empty data[0].role_desc ? '' : data[0].role_desc}" name="role_desc" id="role_desc" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Status <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="role_status" name="role_status" required>
											            <c:choose>
															<c:when test="${empty data[0].role_status}">
																<option value="1">Aktif</option>
																<option value="0">Tidak Aktif</option>
															</c:when>
															<c:otherwise>
																<option value="1" ${data[0].role_status == '1' ? 'selected' : ''}>Aktif</option>
																<option value="0" ${data[0].role_status == '0' ? 'selected' : ''}>Tidak Aktif</option>
															</c:otherwise>
														</c:choose>
													</select>
												</div>
											</div>
										</div>		
										<div class="row">								
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Hak Akses</label>
												</div>
											</div>
										</div>
										<div class="table-responsive">
											<table class="table table-bordered console-table">
												<thead>
													<tr>
														<th colspan="4">Menu</th>
														<th width="100" style="text-align:center !important;">Hak Akses</th>
														<th width="50%">Aksi</th>
													</tr>
												</thead>
												<tbody id="hakaksestable">
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="console-footer">
									<button type="submit" class="btn btn-sm btn-info">Simpan</button>
									<button type="button" onclick="history.back()" class="btn btn-sm btn-danger">Kembali</button>
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
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/jquery.dataTables.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colReorder.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colVis.min.js"></script>
		<script>
			$('#hakaksestable').load('/option/priviledge/${key}');	
			function showSubHakAkses(level, id, obj){
	    		var $this = obj;
	    		if($this.html().indexOf("caret")>=0){
		    		var txt = $this.html().replace('<i class="fa fa-caret-right">', '').replace('<i class="fa fa-caret-down">', '');	    			
		            $this.toggleClass("expanded");
		            if ($this.hasClass("expanded")) {
		                $this.html('<i class="fa fa-caret-down"> '+txt);
		            } else {
		                $this.html('<i class="fa fa-caret-right"> '+txt);
		            }
	    		}
	    		$('.menu_'+(parseInt(level)+1)+'_'+id).toggle()
		    }
			$('#frmRole').submit(function(){
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
						var parseData = $("#frmRole").serializeArray();
						$.post("/users/role/save",parseData,function(data){
							if(data.code=='01'){
								swal({
									title: "Hak Akses",
									text: data.message.toString(),
									type: "success",
									confirmButtonColor: "#60c84c",
									confirmButtonText: "OK",
									closeOnConfirm: false
								},function(isConfirm){
										document.location = '/users/role/list';					
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
