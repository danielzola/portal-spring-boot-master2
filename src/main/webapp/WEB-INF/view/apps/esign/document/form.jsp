<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:choose>
	<c:when test="${empty data[0].doc_status}">
		<c:set var="height" scope = "session" value = "${30}"/>
	</c:when>
	<c:otherwise>
		<c:set var="height" scope = "session" value = "${20}"/>
	</c:otherwise>
</c:choose>
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
	</head>
	<body class="theme-body">
		<a id="modalPrintPreview" style="display:none;" data-toggle="modal" data-target=".bd-example-modal-xl"></a>
		<div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Print Preview</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
					<iframe id="framePrintPreview" width="100%" height="600"></iframe>
					</div>
				</div>
			</div>
		</div>
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
						            		<h1>Dokumen Digital Signature</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Form dokumen digital signature</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-min-height="${height}" data-gs-height="${height}" data-gs-min-width="6">
							<form class="console-panel grid-stack-item-content" id="frmEsignDoc" name="frmEsignDoc" autocomplete="off">
								<input type="hidden" value="${data[0].doc_id}" id="doc_id" name="doc_id" required>
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
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Nama Dokumen <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="doc_code" name="doc_code" required>
														<option value="">-- Pilih Dokumen --</option>
		                                                 <optgroup label="Lain-lain">
		                                                     <option ${data[0].doc_code=='003' ? 'selected' : ''} value="003">Dokumen Administrasi</option>
		                                                 </optgroup>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-md-12">
												<div class="form-group">
													<label>Perihal <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${data[0].doc_desc}" id="doc_desc" name="doc_desc" placeholder="Perihal" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Nomor Dokumen <span style="color:#ff0000;">*</span></label>
													<input type="text" class="form-control" value="${data[0].doc_number}" id="doc_number" name="doc_number" placeholder="Nomor Dokumen" required>
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Jenis Dokumen <span style="color:#ff0000;">*</span></label>
													<select class="form-control" id="doc_type" name="doc_type"  required>
														<option ${data[0].doc_type=='' ? 'selected' : ''} value="">-- Pilih Jenis --</option>
														<option ${data[0].doc_type=='003' ? 'selected' : ''} value="003">Nota Dinas</option>
														<option ${data[0].doc_type=='004' ? 'selected' : ''} value="004">Surat</option>
														<option ${data[0].doc_type=='005' ? 'selected' : ''} value="005">Undangan</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Tanggal Dokumen <span style="color:#ff0000;">*</span></label>
													<input type="text" readonly value="${data[0].doc_date_start}" id="doc_date_start" name="doc_date_start" required class="form-control datepicker-heres" data-language="en" data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy">
												</div>
											</div>
											<div class="col-lg-6 col-md-6">
												<div class="form-group">
													<label>Tanggal Berlaku</label>
													<input type="text" readonly value="${data[0].doc_date_end}" id="doc_date_end" name="doc_date_end" class="form-control datepicker-heres" data-language="en" data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy">
												</div>
											</div>
										</div>
							            <c:choose>
											<c:when test="${empty data[0].doc_status}">
												<div class="row">
													<div class="col-lg-12 col-md-12">
														<div class="form-group">
															<label>Pejabat Penandatangan <span style="color:#ff0000;">*</span></label>
															<select class="form-control doc_by" id="doc_by" name="doc_by" required></select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-12 col-md-12">
														<div class="form-group">
															<label>File Dokumen <span style="color:#ff0000;">*</span></label>
															<input type="hidden" id="frmFilePath" name="frmFilePath" value="${data[0].doc_file_path}" required>
															<input type="hidden" id="frmFileName" name="frmFileName" value="${data[0].doc_file}" >
															<input type="hidden" id="doc_file_page" name="doc_file_page" value="">
															<table>
																<tr>
																	<td width="80" style="background:none;border:0px;">
																		<span class="btn btn-sm btn-primary fileinput-button">
																			<span>Pilih File</span>
																			<input id="fileuploads" type="file" name="file" multiple accept=".pdf">
																		</span>
																	</td>
																	<td id="resultDok" style="background:none;border:0px;">
																	<div id="progress">
																		<div id="progress_bar">
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
												<div class="col-lg-12 col-md-12">
													<div class="form-group">
														<input type="hidden" class="jmlHal" name="jmlHal" id="jmlHal">
													</div>
												</div>
												<div class="row">
													<div class="col-lg-3 col-md-3">
														<div class="form-group">
															<label>Posisi QRCode <span style="color:#ff0000;">*</span></label>
															<select class="form-control" id="doc_qr_position" name="doc_qr_position">
																<option ${data[0].doc_qr_position=='' ? 'selected' : ''} value="">-- Pilih --</option>
																<option ${data[0].doc_qr_position=='custom' ? 'selected' : ''} value="custom">Custom</option>
																<option ${data[0].doc_qr_position=='bottomright' ? 'selected' : ''} value="bottomright">Kanan Bawah</option>
																<option ${data[0].doc_qr_position=='bottomleft' ? 'selected' : ''} value="bottomleft">Kiri Bawah</option>
																<option ${data[0].doc_qr_position=='topright' ? 'selected' : ''} value="topright">Kanan Atas</option>
																<option ${data[0].doc_qr_position=='topleft' ? 'selected' : ''} value="topleft">Kiri Atas</option>
																<option ${data[0].doc_qr_position=='topmid' ? 'selected' : ''} value="topmid">Tengah Atas</option>
																<option ${data[0].doc_qr_position=='bottommid' ? 'selected' : ''} value="bottommid">Tengah Bawah</option>
																<option ${data[0].doc_qr_position=='coordinate' ? 'selected' : ''} value="coordinate">Koordinat</option>
															</select>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 qr_custom" style="display:none;">
														<div class="form-group" style="padding-top:30px;">
															<button onclick="addDragQR(55, 0)" type="button" class="btn btn-sm btn-success">Tambah QRCode</button>
															<button onclick="resetDragQR()" type="button" class="btn btn-sm btn-danger">Reset</button>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 qr_coord qr_page" style="display:none;">
														<div class="form-group">
															<label>Halaman QRCode</label>
						                                    <div class="input-group">
						                                        <input type="text" onchange="setQRPosition()" name="doc_qr_page" id="doc_qr_page" value="${empty data[0].doc_qr_page ? 1 : data[0].doc_qr_page}" class="form-control" placeholder="">
						                                        <div class="input-group-append" data-rel="tooltip" data-original-title="Kosongkan form untuk semua halaman; Pisahkan dengan tanda koma untuk halaman tertentu (contoh: 1,2,3); Pisahkan dengan tanda strip (-) untuk rentang halaman (contoh: 1-3)" style="cursor:pointer;">
						                                        	<span class="input-group-text"><i class="fas fa-question-circle"></i></span>
						                                        </div>
						                                    </div>
														</div>
													</div>
													<div class="col-lg-6 col-md-12 qr_custom qr_size" style="display:none;">
														<div class="form-group">
															<label class="block">Ukuran QRCode</label>
															<input class="range theme3" id="doc_qr_size" name="doc_qr_size" data-min="50" data-max="100" data-from="${empty data[0].doc_qr_size ? 100 : data[0].doc_qr_size}"   />
														</div>
													</div>
												</div>
												<div class="row qr_coord" style="display:none;">
													<div class="col-lg-12 col-md-12"><div class="form-group"></div></div>
													<div class="col-lg-3 col-md-3">
														<div class="form-group">
															<label>Koordinat QRCode (Y) <span style="color:#ff0000;">*</span></label>
															<input onchange="setQRPosition()" type="text" class="form-control" id="doc_qr_y" name="doc_qr_y" value="${empty data[0].doc_qr_y ? 0 : data[0].doc_qr_y}">											
														</div>
													</div>
													<div class="col-lg-3 col-md-3">
														<div class="form-group">
															<label>Koordinat QRCode (X) <span style="color:#ff0000;">*</span></label>
															<input onchange="setQRPosition()" type="text" class="form-control" id="doc_qr_x" name="doc_qr_x" value="${empty data[0].doc_qr_x ? 55 : data[0].doc_qr_x}">											
														</div>
													</div>
												</div>
												<div class="row col-lg-3 col-md-3 qr_sign" style="display:none;">
													<div class="form-group">
														<label>Halaman TTD</label>
					                                    <div class="input-group">
					                                        <input type="text" name="doc_sign_page" id="doc_sign_page" class="form-control">
					                                        <div class="input-group-append" data-rel="tooltip" data-original-title="Kosongkan form untuk semua halaman; Pisahkan dengan tanda koma untuk halaman tertentu (contoh: 1,2,3); Pisahkan dengan tanda stip (-) untuk rentang halaman (contoh: 1-3)" style="cursor:pointer;">
					                                        	<span class="input-group-text"><i class="fas fa-question-circle"></i></span>
					                                        </div>
					                                    </div>
													</div>
												</div>
												<div class="row col-lg-12 col-md-12 qr_sign" style="display:none;"><div class="form-group"></div></div>
												<div class="row col-lg-3 col-md-3 qr_sign" style="display:none;">
													<div class="form-group">
														<label>Ukuran TTD (%) <span style="color:#ff0000;">*</span></label>
														<input type="text" class="form-control" id="doc_sign_size" name="doc_sign_size">											
													</div>
												</div>
												<div class="col-lg-3 col-md-3 qr_sign" style="display:none;">
													<div class="form-group">
														<label>Koordinat TTD (X) <span style="color:#ff0000;">*</span></label>
														<input type="text" class="form-control" id="doc_sign_x" name="doc_sign_x">											
													</div>
												</div>
												<div class="col-lg-3 col-md-3 qr_sign" style="display:none;">
													<div class="form-group">
														<label>Koordinat TTD (Y) <span style="color:#ff0000;">*</span></label>
														<input type="text" class="form-control" id="doc_sign_y" name="doc_sign_y">											
													</div>
												</div>
												<div class="row pdfcanvascontent" style="display:none;">
													<div class="col-lg-3 col-md-3">
														<div class="form-group">
															<label>Print Preview</label>
														</div>
													</div>
												</div>
												<!--
												<div class="row pdfcanvascontent" style="display:none;">
													<div class="col-lg-3 col-md-3">
														<div class="form-group">
															<button type="button" onclick="showCoordinates()" class="btn btn-primary">Show Coordinates</button>
														</div>
													</div>
												</div>
												-->
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${data[0].doc_status=='2'}">
														<div class="row">
															<div class="col-lg-12 col-md-12">
																<div class="form-group">
																	<label>File Dokumen <span style="color:#ff0000;">*</span></label>
																	<input type="hidden" id="frmFilePath" name="frmFilePath" value="${data[0].doc_file_path}" required>
																	<input type="hidden" id="frmFileName" name="frmFileName" value="${data[0].doc_file}" >
																	<input type="hidden" id="doc_file_page" name="doc_file_page" value="">
																	<table>
																		<tr>
																			<td width="80" style="background:none;border:0px;">
																				<a class="btn btn-success btn-sm" href="/files/preview/digisign/${data[0].doc_file_sign}" target="_blank">Digital Signature</a>
																			</td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<div class="row">
															<div class="col-lg-12 col-md-12">
																<div class="form-group">
																	<label>File Dokumen <span style="color:#ff0000;">*</span></label>
																	<input type="hidden" id="frmFilePath" name="frmFilePath" value="${data[0].doc_file_path}" required>
																	<input type="hidden" id="frmFileName" name="frmFileName" value="${data[0].doc_file}" >
																	<input type="hidden" id="doc_file_page" name="doc_file_page" value="">
																	<table>
																		<tr>
																			<td width="80" style="background:none;border:0px;">
																				<a class="btn btn-success btn-sm" href="/esign/doc/preview/${data[0].doc_id}" target="_blank">Print Preview</a>
																			</td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>	
														<c:choose>
															<c:when test="${data[0].doc_status=='1' && userData[0].user_id == data[0].doc_by}">														
															<div class="row">																										
																<div class="col-lg-6 col-md-6">
																	<div class="form-group">
																		<label>Passphrase <span style="color:#ff0000;">*</span></label>
																		<input type="password" class="form-control" id="passphrase" name="passphrase" placeholder="Passphrase" required>
																	</div>
																</div>	
															</div>		
															</c:when>
														</c:choose>						
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										<div class="container-fluid pdfcanvascontent" style="margin-bottom:70px; display:none; width:1120px; min-width:1120px; max-width:1120px;">
									        <div class="row">
									            <div class="col-lg-12">
									              <div class="document-container">
									                <div class="document-render" id="documentRender">
									                </div>
									              </div>
									            </div>
									        </div>
								        </div>
									</div>
								</div>
								<div class="console-footer">
						            <c:choose>
										<c:when test="${empty data[0].doc_status}">
				                    		<button type="submit" class="btn btn-sm btn-primary">Simpan</button>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${data[0].doc_status=='1' && userData[0].user_id == data[0].doc_by}">	
													<button type="submit" class="btn btn-sm btn-primary">Tanda Tangani</button>										
												</c:when>
											</c:choose>
										</c:otherwise>
									</c:choose>
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
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
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
		<script>			
			function showCoordinates(){
				var maxHTMLx   = $('#pdfpage1').width();
			  	var maxHTMLy = $('#pdfpage1').height();
			  	$('.digital-signature').each(function( index ) {
			  		var x = parseFloat($(this).data("x"));
			  		var y = parseFloat($(this).data("y"));
			  		var paramContainerWidth = parseFloat($(this).width());
			  		var scaleqr = $('#doc_qr_size').val();
			  		var sqr = parseFloat(100)-parseFloat(scaleqr);
			  		var minx = (sqr*200)/100;
			  		var pdfY = y * maxPDFy / maxHTMLy;
			  		var posizioneY = (maxPDFy - offsetY - pdfY - parseFloat(scaleqr)).toFixed(2);	  
			  		var posizioneX = ((x * maxPDFx / maxHTMLx)  - paramContainerWidth + 135 - minx).toFixed(2);
			  		
			  		console.log(posizioneX+':'+posizioneY);
			  	});
			}
			var maxPDFx = 595;
			var maxPDFy = 842;
			var offsetY = 6;						
			$('#frmEsignDoc').submit(function(){
				swal({
					title: "Konfirmasi",
					text: "Apakah Anda Yakin Ingin Memproses Data Ini?",
					type: "info",
					showCancelButton: true,
					confirmButtonColor: "#8CD4F5",
					confirmButtonText: "Proses",
					cancelButtonText: "Batal",
					closeOnConfirm: false
				},function(isConfirm){
					var xaxi = '';
					var yaxi = '';
					var hp = $('#pdfpage1').attr('height');
					var p = ''
					var maxHTMLx   = $('#pdfpage1').width();
				  	var maxHTMLy = $('#pdfpage1').height();
				  	$('.digital-signature').each(function( index ) {
				  		var x = parseFloat($(this).attr("data-x"));
				  		var y = parseFloat($(this).attr("data-y"));
				  		var paramContainerWidth = parseFloat($(this).width());
				  		var scaleqr = $('#doc_qr_size').val();
				  		var sqr = parseFloat(100)-parseFloat(scaleqr);
				  		var minx = (sqr*200)/100;
				  		var pdfY = y * maxPDFy / maxHTMLy;
				  		var posizioneY = (maxPDFy - offsetY - pdfY - parseFloat(scaleqr)).toFixed(2);	
				  		yaxi = posizioneY+','+yaxi;
				  		var posizioneX = ((x * maxPDFx / maxHTMLx)  - paramContainerWidth + 135 - minx).toFixed(2);
				  		xaxi = posizioneX+','+xaxi;
						var cy = $(this).attr('data-y')==0 ? 1 : $(this).attr('data-y');
						p  = ''+Math.ceil(cy/hp)+','+p;				  		
				  	});
					$('#doc_qr_x').val(xaxi.slice(0, -1));
					$('#doc_qr_y').val(yaxi.slice(0, -1));
					$('#doc_qr_page').val(p.slice(0, -1));
					
					if(isConfirm){
						$('.cancel').hide();
						$('.confirm').attr('disabled', 'disabled');	
						var parseData = $("#frmEsignDoc").serializeArray();
						$.post("/esign/doc/${empty data[0].doc_status ? 'save' : 'sign'}",parseData,function(data){
							if(data.code=='01'){
								swal({
									title: "Digital Signature",
									text: data.message.toString(),
									type: "success",
									confirmButtonColor: "#60c84c",
									confirmButtonText: "OK",
									closeOnConfirm: false
								},function(isConfirm){
										document.location = '/esign/doc/list';					
								}); 						
							}else{
								swal('Mohon Maaf', data.message.toString(), 'warning');
							}
						},'json');
					}
				}); 
				return false;
			});
			
		    $('.datepicker-heres').datepicker({
		        autoclose: true,
		        lang: "en"
		    }).data("datepicker");
		    
		    $('#fileuploads').fileupload({
		    	url: '/files/upload/digisign',
				dataType: 'json',
				start: function (e) {
					$('#resultDok').html('<div id="progress">Loading... 0%</div>');
				},
				done: function (e, data) {
					if(data.result.status=='001'){
						$('#frmFilePath').val(data.result.location);
						$('#frmFileName').val(data.result.name);
						$('#progress').fadeOut('fast', function(){
							$('#resultDok').html('<a class="btn btn-success btn-sm" href="/files/preview/'+data.result.location+'" target="_blank">Preview</a>');
							$.post("/files/upload/encodepdf",{path: data.result.location.toString()},function(response){
								var pdfData = 'data:application/pdf;base64,'+response.toString();
								nohal = 0;
								$('#documentRender').html('');
								renderPDF(pdfData, document.getElementById('documentRender'), { scale: 1.55 });
							});
						});
					}else{
						$('#frmFilePath').val('');
						$('#frmFileName').val('');
						$('#progress').fadeOut('fast', function(){
							$('#resultDok').html('<span style="color:#FF0000; font-size:11px;"><b>'+data.result.message+'</b></span>');
						});                                
					}
				},
				progressall: function (e, data) {
					var progress = parseInt(data.loaded / data.total * 100, 10);
					$('#progress').html('Loading... '+progress+'%');
				}
			}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
		    
		    $(".range").ionRangeSlider({
		    	onChange: function (data) {
		            var size = parseInt(200)*(parseInt(data.from)/parseInt(100));
    				var breaks = 0;
    				var breaks_array = [0];
    				var jmlHal = $('#jmlHal').val();
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
    					breaks = parseInt(breaks)+parseInt(20); 
    					if(i>1){
        					breaks_array.push(breaks);    						
    					}
	    			}
	    			console.log(breaks_array);
	            	var position = $('#doc_qr_position').val();
	            	
		            $('.digital-signature').each(function(){
		            	$(this).css('width', size+'px');	
		            	var qrpage = $(this).attr('data-page');
		            	if(position=='topright'){
		            		var ph = $('#pdfpage'+qrpage).attr('height')
		            		var pw = $('#pdfpage'+qrpage).attr('width')
		            		var dif = parseInt(200)-parseInt(size);
		            		var x = parseInt(pw)-parseInt(140)+parseInt(dif);
		            		var min = 0;
		            		if(qrpage>1){
		            			min = 20;
		            		}		            		
		            		var y = (parseInt(ph)*(qrpage-1))+parseInt(breaks_array[qrpage-1])-min;
		            		console.log(qrpage-1);
		            		$(this).css('transform', 'translate('+(x)+'px, '+(y)+'px)');
		            		$(this).attr('data-x', x);
		            		$(this).attr('data-y', y);
		            	}
		            	if(position=='bottomright'){
		            		var ph = $('#pdfpage'+qrpage).attr('height')
		            		var pw = $('#pdfpage'+qrpage).attr('width')
		            		var dif = parseInt(200)-parseInt(size);		            		
		            		var x = parseInt(pw)-parseInt(140)+parseInt(dif);
		            		var min = 0;
		            		if(qrpage>1){
		            			min = 20;
		            		}
		            		var y = (parseInt(ph)*(qrpage))-(200-parseInt(breaks_array[qrpage-1]))+parseInt(dif)-min;
		            		$(this).css('transform', 'translate('+(x)+'px, '+(y)+'px)');
		            		$(this).attr('data-x', x);
		            		$(this).attr('data-y', y);
		            	}
		            });
		        }
		    });
		    $('#doc_qr_position').change(function(){
		    	resetDragQR();
		    	if($(this).val()=='coordinate'){
	    			$('.pdfcanvascontent').show();
		    		$('.qr_custom').hide();
		    		$('.qr_coord').show();
		    		$('.qr_page').show();
		    		$('.qr_size').show();
		    		setQRPosition($(this).val().toString());
		    	}else{
		    		if($(this).val()=='custom'){
		    			$('.qr_custom').show();
		    			$('.qr_coord').hide();	
		    			$('.qr_page').hide();
		    			$('.pdfcanvascontent').show();
			    		$('.qr_size').show();
		    		}else{
		    			$('.qr_custom').hide();	
			    		$('.qr_coord').hide();		 
			    		$('.qr_page').show();
		    			$('.pdfcanvascontent').show();
			    		$('.qr_size').show();
			    		setQRPosition();
		    		}
		    	}
		    });
		    
		    function setQRPosition(){
		    	resetDragQR();
		    	var what = $('#doc_qr_position').val();
		    	var jmlHal = $('#jmlHal').val();
		    	var qrpage = $('#doc_qr_page').val();
		    	var x = 0;
		    	var qr_page = [];
		    	if(qrpage.length<1){
			    	for(var i = 1; i<=parseInt(jmlHal); i++){
			    		qr_page.push(i.toString());
			    	}
		    	}
		    	else{
			    	if(qrpage.indexOf(',')>=0){
			    		var qr_page = qrpage.split(",");
			    	}else{
				    	if(qrpage.indexOf('-')>=0){
				    		var qrpagearr = qrpage.split("-");
				    		for(var i=parseInt(qrpagearr[0]); i<=parseInt(qrpagearr[1]); i++){
				    			qr_page.push(i.toString());
				    		}
				    	}else{
				    		qr_page.push(qrpage);
				    	}
			    	}
		    	}		    	
	    		if(what=='topleft'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(55,0);	    						
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR(55,(parseInt(hpage)*(i-1))+(parseInt(breaks)));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='topright'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(parseInt(wpage)-parseInt(140),0);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR(parseInt(wpage)-parseInt(140),(parseInt(hpage)*(i-1))+parseInt(breaks));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='topmid'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR((parseInt(wpage)/2)-52,0);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR((parseInt(wpage)/2)-52,(parseInt(hpage)*(i-1))+parseInt(breaks));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='bottomleft'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(55,parseInt(hpage)-200);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR(55,(parseInt(hpage)*(i))-(200-parseInt(breaks)));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='bottomright'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(parseInt(wpage)-parseInt(140),parseInt(hpage)-200);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR(parseInt(wpage)-parseInt(140),(parseInt(hpage)*(i))-(200-parseInt(breaks)));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='bottommid'){
    				var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR((parseInt(wpage)/2)-52,parseInt(hpage)-200);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
	    						addDragQR((parseInt(wpage)/2)-52,(parseInt(hpage)*(i))-(200-parseInt(breaks)));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}
	    		}
	    		if(what=='coordinate'){
			    	var x = $('#doc_qr_x').val().length<1 ? 0 : $('#doc_qr_x').val();
			    	var y = $('#doc_qr_y').val().length<1 ? 0 : $('#doc_qr_y').val();
			    	var jmlHal = $('#jmlHal').val();
			    	var breaks = 20;
	    			for(var i = 1; i<=parseInt(jmlHal); i++){
	    				var hpage = $('#pdfpage'+i).attr('height');
	    				var wpage = $('#pdfpage'+i).attr('width');
	    				if(i==1){
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(x,y);
	    					}
	    				}else{
	    					if(parseInt(qr_page.indexOf(i.toString()))>=0){
		    					addDragQR(x,(parseInt(hpage)*(i-1)+parseInt(breaks))+parseInt(y));
	    					}
	    					breaks = parseInt(breaks)+parseInt(20);
	    				}
	    			}		    		    			
	    		}
		    }
		    		    
		    function addDragQR(x,y){
		    	var size = parseInt(200)*(parseInt($("#doc_qr_size").val())/parseInt(100));
	            var hp = $('#pdfpage1').attr('height');
            	var cy = y==0 ? 1 : y;
            	var page = Math.ceil(cy/hp);
		    	
		    	var qr = '<img src="${baseUrl}/assets/apps/images/qr.png" width="'+size+'" class="digital-signature" style="cursor:pointer; transform: translate('+x+'px, '+y+'px);" data-x="'+x+'" data-y="'+y+'" data-page="'+page+'">';
		    	$('#documentRender').prepend(qr);
		    }
		    
		    function resetDragQR(){
		    	$('.digital-signature').remove();
		    }
	        $('#doc_by').SumoSelect({
	            search: true,
	            searchText: 'Pilih Penandatangan'
	      	});
	        
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  $('#doc_by').html(this.responseText);
				  $('#doc_by')[0].sumo.reload();
			  }
			};
			xmlhttp.open("GET", "/option/esignuser/${userData[0].office_code}/${empty data[0].doc_by ? '0' : data[0].doc_by}", true);
			xmlhttp.send();						  
		    
			function previewFiles(){
		    	$('#framePrintPreview').prop('src', '/esign/doc/preview/7226');
				$('#modalPrintPreview').click();    	
		    }
		</script>
	</body>
</html>
