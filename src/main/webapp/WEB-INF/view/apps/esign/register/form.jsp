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
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sumoselect/sumoselect.min.css" />
		<link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/jquery-upload/css/jquery.fileupload.css">
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
						            		<h1>Registrasi E-Sign</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Form registrasi sertifikat elektronik</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-min-height="43" data-gs-height="43" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmEsign" name="frmEsign" autocomplete="off">
								<input type="hidden" value="${data[0].user_id}" id="user_id" name="user_id" required>
								<input type="hidden" id="action" name="action" required>
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
													<label>NIP <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" id="esign_nip" name="esign_nip" value="${empty data[0].esign_nip ? userData[0].emp_number : data[0].esign_nip}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nama Lengkap <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_nama" name="esign_nama" value="${empty data[0].esign_nama ? userData[0].user_fullname : data[0].esign_nama}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Jabatan <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_jabatan" name="esign_jabatan" value="${empty data[0].esign_jabatan ? userData[0].position_name : data[0].esign_jabatan}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Unit Kerja <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_unit_kerja" name="esign_unit_kerja" value="${empty data[0].esign_unit_kerja ? userData[0].office_name : data[0].esign_unit_kerja}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>NIK <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_nik" name="esign_nik" value="${empty data[0].esign_nik ? userData[0].user_nik : data[0].esign_nik}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Propinsi Kantor <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="esign_propinsi" name="esign_propinsi" required>
														<option value="">-- Pilih Propinsi --</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Kota / Kabupaten Kantor <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="esign_kota" name="esign_kota" required>
														<option value="">-- Pilih Kota/Kabupaten --</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Email <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_email" name="esign_email" value="${empty data[0].esign_email ? userData[0].user_email : data[0].esign_email}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>No. Handphone <span style="color:#ff0000;">*</span></label>
													<input readonly type="text" class="form-control" id="esign_telp" name="esign_telp" value="${empty data[0].esign_telp ? userData[0].user_hp : data[0].esign_telp}" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Scan KTP <span style="color:#ff0000;">*</span></label>
													<br>
													<input type="hidden" id="esign_ktp" name="esign_ktp" value="${data[0].esign_ktp}" required>
													<table>
														<tr>
												            <c:choose>
																<c:when test="${empty data[0].id || data[0].esign_status == '4'}">
																<td width="80" style="background:none;border:0px;">
																	<span class="btn btn-sm btn-primary fileinput-button">
																		<span>Pilih File</span>
																		<input id="file_esign_ktp" type="file" name="file" multiple accept=".jpg, .png">
																	</span>
																</td>
																</c:when>
															</c:choose>
															<td id="result_esign_ktp" style="background:none;border:0px;">
															<div id="progress_esign_ktp">
																<div id="progress_bar_esign_ktp">
														            <c:choose>
																		<c:when test="${!empty data[0].esign_ktp}">
												                    		<a class="btn btn-success btn-sm" href="/files/preview/${data[0].esign_ktp}" target="_blank">Preview</a>
																		</c:when>
																		<c:otherwise>
																            <c:choose>
																				<c:when test="${!empty data[0].id}">
														                    		<div><strong><span style="color:red;">Tidak ada file</span></strong></div>																		
																				</c:when>
																			</c:choose>
																		</c:otherwise>
																	</c:choose>
																</div>
															</div>         
															</td>    
														</tr>
														<tr>
															<td colspan="2"><br><i>Format jpg atau png berwana, ukuran file maksimal 30 MB.</i></td>
														</tr>
													</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Surat Rekomendasi <span style="color:#ff0000;">*</span></label>
													<br>
													<input type="hidden" id="esign_rekom" name="esign_rekom" value="${data[0].esign_rekom}" required>
													<table>
														<tr>
												            <c:choose>
																<c:when test="${empty data[0].id || data[0].esign_status == '4'}">
																<td width="80" style="background:none;border:0px;">
																	<span class="btn btn-sm btn-primary fileinput-button">
																		<span>Pilih File</span>
																		<input id="file_esign_rekom" type="file" name="file" multiple accept=".pdf">
																	</span>
																</td>
																</c:when>
															</c:choose>
															<td id="result_esign_rekom" style="background:none;border:0px;">
															<div id="progress_esign_rekom">
																<div id="progress_bar_esign_rekom">
														            <c:choose>
																		<c:when test="${!empty data[0].esign_rekom}">
												                    		<a class="btn btn-success btn-sm" href="/files/preview/${data[0].esign_rekom}" target="_blank">Preview</a>
																		</c:when>
																		<c:otherwise>
																            <c:choose>
																				<c:when test="${!empty data[0].id}">
														                    		<div><strong><span style="color:red;">Tidak ada file</span></strong></div>																		
																				</c:when>
																			</c:choose>
																		</c:otherwise>
																	</c:choose>
																</div>
															</div>         
															</td>    
														</tr>
														<tr>
															<td colspan="2"><br><i>Format pdf, ukuran file maksimal 30 MB.</i></td>
														</tr>
													</table>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Spesimen Tanda Tangan</label>
													<br>
													<input type="hidden" id="esign_ttd" name="esign_ttd" value="${data[0].esign_ttd}">
													<table>
														<tr>
												            <c:choose>
																<c:when test="${empty data[0].id || data[0].esign_status == '4'}">
																<td width="80" style="background:none;border:0px;">
																	<span class="btn btn-sm btn-primary fileinput-button">
																		<span>Pilih File</span>
																		<input id="file_esign_ttd" type="file" name="file" multiple accept=".jpg, .png">
																	</span>
																</td>
																</c:when>
															</c:choose>
															<td id="result_esign_ttd" style="background:none;border:0px;">
																<div id="progress_esign_ttd">
																	<div id="progress_bar_esign_ttd">
															            <c:choose>
																			<c:when test="${!empty data[0].esign_ttd}">
													                    		<a class="btn btn-success btn-sm" href="/files/preview/${data[0].esign_ttd}" target="_blank">Preview</a>
																			</c:when>
																			<c:otherwise>
																	            <c:choose>
																					<c:when test="${!empty data[0].id}">
															                    		<div><strong><span style="color:red;">Tidak ada file</span></strong></div>																		
																					</c:when>
																				</c:choose>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>         
															</td>    
														</tr>
														<tr>
															<td colspan="2"><br><i>Format jpg atau png, ukuran file maksimal 30 MB.</i></td>
														</tr>
													</table>
												</div>
											</div>
										</div>		
							            <c:choose>
											<c:when test="${fullaccess == '1' || data[0].esign_respon != ''}">
												<div class="row">
													<div class="col-lg-6 col-md-6">
														<div class="form-group">
															<label>Catatan</label>
															<textarea class="form-control" ${fullaccess == '1' ? '' : 'readonly'} id="esign_respon" name="esign_respon">${ data[0].esign_respon }</textarea>
														</div>
													</div>
												</div>																		
											</c:when>
										</c:choose>
									</div>
								</div>
								<div class="console-footer">
						            <c:choose>
										<c:when test="${fullaccess == '1' && data[0].esign_status == '0'}">
				                    		<button type="submit" onclick="$('#action').val('approve')" class="btn btn-sm btn-success">Setujui</button>																	
				                    		<button type="submit" onclick="$('#action').val('reject')" class="btn btn-sm btn-danger">Tolak</button>																	
										</c:when>
										<c:otherwise>
								            <c:choose>
												<c:when test="${empty data[0].id || data[0].esign_status=='4'}">
						                    		<button type="submit" onclick="$('#action').val('register')" class="btn btn-sm btn-primary">Registrasi</button>																	
												</c:when>
											</c:choose>
										</c:otherwise>										
									</c:choose>
									<button type="button" onclick="history.back()" class="btn btn-sm btn-warning">Kembali</button>
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
	    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.ui.widget.js"></script>
	    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.fileupload.js"></script>
		<script>
	        $('#esign_propinsi').SumoSelect({
	            search: true,
	            searchText: 'Pilih Propinsi'
	      	});
	        $('#esign_kota').SumoSelect({
	            search: true,
	            searchText: 'Pilih Kota/Kabupaten'
	      	});
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  $('#esign_propinsi').html(this.responseText);
				  $('#esign_propinsi')[0].sumo.reload();
				  $('#esign_propinsi').change(function(){
					var selectedprov = $(this).val();
					var xmlhttp = new XMLHttpRequest();
					xmlhttp.onreadystatechange = function() {
					  if (this.readyState == 4 && this.status == 200) {
						  $('#esign_kota').html(this.responseText);
						  $('#esign_kota')[0].sumo.reload();
					  }
					};
					xmlhttp.open("GET", "/option/kabkota/"+selectedprov+"/0", true);
					xmlhttp.send();						  
				  });
			  }
			};
			xmlhttp.open("GET", "/option/provinsi/${empty data[0].esign_propinsi ? 0 : data[0].esign_propinsi}", true);
			xmlhttp.send();	
			
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  $('#esign_kota').html(this.responseText);
				  $('#esign_kota')[0].sumo.reload();
			  }
			};
			xmlhttp.open("GET", "/option/kabkota/${empty data[0].esign_propinsi ? 0 : data[0].esign_propinsi}/${empty data[0].esign_kota ? 0 : data[0].esign_kota}", true);
			xmlhttp.send();						  
			
			$('#frmEsign').submit(function(){
				var action = $('#action').val();
				if(action=='register'){
					var msg = 'Apakah Anda yakin dengan data yang Anda isikan?';
					var btn = 'Registrasi';
				}
				if(action=='approve'){
					var msg = 'Apakah Anda yakin ingin menyetujui data ini?';
					var btn = 'Setujui';
				}
				if(action=='reject'){
					var msg = 'Apakah Anda yakin ingin menolak data ini?';
					var btn = 'Tolak';
				}
				swal({
					title: "Konfirmasi",
					text: msg,
					type: "info",
					showCancelButton: true,
					confirmButtonColor: "#8CD4F5",
					confirmButtonText: btn,
					cancelButtonText: "Batal",
					closeOnConfirm: false
				},function(isConfirm){
					if(isConfirm){
						$('.cancel').hide();
						$('.confirm').attr('disabled', 'disabled');
						var parseData = $("#frmEsign").serializeArray();
						$.post("/esign/register/save",parseData,function(data){
							if(data.code=='01'){
								swal({
									title: "Registrasi E-Sign",
									text: data.message.toString(),
									type: "success",
									confirmButtonColor: "#60c84c",
									confirmButtonText: "OK",
									closeOnConfirm: false
								},function(isConfirm){
										document.location = '/esign/register/list';					
								}); 						
							}else{
								swal('Mohon Maaf', data.message.toString(), 'warning');
							}
						},'json');		
					}
				}); 
				return false;
			});
			
			$('#file_esign_ktp').fileupload({
				url: '/files/upload/ktp',
				dataType: 'json',
				start: function (e) {
					$('#result_esign_ktp').html('<div id="progress_esign_ktp">Loading... 0%</div>');
				},
				done: function (e, data) {
					if(data.result.status=='001'){
						$('#esign_ktp').val(data.result.location);
						$('#progress_esign_ktp').fadeOut('fast', function(){
							$('#result_esign_ktp').html('<a class="btn btn-success btn-sm" href="/files/preview/'+data.result.location+'" target="_blank">Preview</a>');
						});
					}else{
						$('#esign_ktp').val('');
						$('#progress_esign_ktp').fadeOut('fast', function(){
							$('#result_esign_ktp').html('<span style="color:#FF0000; font-size:11px;"><b>'+data.result.message+'</b></span>');
						});                                
					}
				},
				progressall: function (e, data) {
					var progress = parseInt(data.loaded / data.total * 100, 10);
					$('#progress_esign_ktp').html('Loading... '+progress+'%');
				}
			}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
			
			$('#file_esign_rekom').fileupload({
				url: '/files/upload/rekom',
				dataType: 'json',
				start: function (e) {
					$('#result_esign_rekom').html('<div id="progress_esign_rekom">Loading... 0%</div>');
				},
				done: function (e, data) {
					if(data.result.status=='001'){
						$('#esign_rekom').val(data.result.location);
						$('#progress_esign_rekom').fadeOut('fast', function(){
							$('#result_esign_rekom').html('<a class="btn btn-success btn-sm" href="/files/preview/'+data.result.location+'" target="_blank">Preview</a>');
						});
					}else{
						$('#esign_rekom').val('');
						$('#progress_esign_rekom').fadeOut('fast', function(){
							$('#result_esign_rekom').html('<span style="color:#FF0000; font-size:11px;"><b>'+data.result.message+'</b></span>');
						});                                
					}
				},
				progressall: function (e, data) {
					var progress = parseInt(data.loaded / data.total * 100, 10);
					$('#progress_esign_rekom').html('Loading... '+progress+'%');
				}
			}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
		    
		    $('#file_esign_ttd').fileupload({
				url: '/files/upload/ttd',
				dataType: 'json',
				start: function (e) {
					$('#result_esign_ttd').html('<div id="progress_esign_ttd">Loading... 0%</div>');
				},
				done: function (e, data) {
					if(data.result.status=='001'){
						$('#esign_ttd').val(data.result.location);
						$('#progress_esign_ttd').fadeOut('fast', function(){
							$('#result_esign_ttd').html('<a class="btn btn-success btn-sm" href="/files/preview/'+data.result.location+'" target="_blank">Preview</a>');
						});
					}else{
						$('#esign_ttd').val('');
						$('#progress_esign_ttd').fadeOut('fast', function(){
							$('#result_esign_ttd').html('<span style="color:#FF0000; font-size:11px;"><b>'+data.result.message+'</b></span>');
						});                                
					}
				},
				progressall: function (e, data) {
					var progress = parseInt(data.loaded / data.total * 100, 10);
					$('#progress_esign_ttd').html('Loading... '+progress+'%');
				}
			}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
		</script>
	</body>
</html>
