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
						      <div class="filteration-text">
						      		<div class="row col-lg-12" style="min-height:160px !important;">
						      			<div class="col-lg-2 col-md-2 align-center">
							            	<div class="photo-holder">
									            <c:choose>
													<c:when test="${fn:length(userData[0].user_photo) > 0}">
														<div class="photo-thumb" style="background-image: url(${baseUrl}/assets/apps/images/noprofile.png) !important;"></div>
													</c:when>
													<c:otherwise>
														<div class="photo-thumb" style="background-image: url(${baseUrl}/assets/apps/images/noprofile.png) !important;"></div>
													</c:otherwise>
												</c:choose>
											</div>
						      			</div>
						      			<div class="col-lg-10 col-md-10">
						            		<h1>Selamat Datang</h1>
								            <c:choose>
												<c:when test="${userData[0].user_type == '1'}">
								            		<div style="margin-top:5px;">${userData[0].user_fullname}</div>
							            			<div>${userData[0].office_name} </div>
							            			<div>${userData[0].unit_name} </div>
							            			<div>${userData[0].position_name} </div>
							            			<div>
							            				<i class="fas fa-envelope"></i> &nbsp; ${userData[0].user_email} &nbsp; <i class="fas fa-phone-square"></i> &nbsp; ${userData[0].user_hp} 
							            			</div>												
												</c:when>
												<c:otherwise>
							            			<div style="margin-top:5px;">${userData[0].company_name} </div>
							            			<div>${userData[0].company_address} </div>
							            			<div>
							            				<i class="fas fa-envelope"></i> &nbsp; ${userData[0].company_email} &nbsp; <i class="fas fa-phone-square"></i> &nbsp; ${userData[0].company_phone} 
							            			</div>												
												</c:otherwise>
											</c:choose>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="4" data-gs-height="5"  data-gs-min-height="5"  data-gs-min-width="3" data-gs-no-resize='true'>
							<div class="console-panel grid-stack-item-content console-no-header" style="cursor:pointer;" onclick="document.location = 'layanan/my-task/task-list'">
								<div class="console-panel-body-noscroll no-padding">
									<div class="status_box">
										<div class="status_box_left">
											<span class="badge bg-danger text-white">Tugas</span>
											<h3 class="mt-1 counter_tugas">0</h3>
											<p class="mb-0">Permohonan</p>
										</div>
										<div class="status_box_right">
											<span><i class="fas fa-inbox"></i></span>
											<svg viewBox="0 0 230 147" style="opacity: 0.4;" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="230px" height="147px" enable-background="new 0 0 230 147" xml:space="preserve">  <image id="image0" width="230" height="147" x="0" y="0" xlink:href="data:image/png;base64,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" /></svg>
										</div>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>
	
						<div class="grid-stack-item" data-gs-x="4" data-gs-y="0" data-gs-width="4" data-gs-height="5"  data-gs-min-height="5"  data-gs-min-width="3" data-gs-no-resize='true'>
							<div class="console-panel grid-stack-item-content console-no-header" style="cursor:pointer;" onclick="document.location = 'layanan/my-task/active-list'">
								<div class="console-panel-body-noscroll no-padding">
									<div class="status_box">
										<div class="status_box_left">
											<span class="badge bg-primary text-white">Proses Terbuka</span>
											<h3 class="mt-1 counter_terbuka">0</h3>
											<p class="mb-0">Permohonan</p>
										</div>
										<div class="status_box_right">
											<span><i class="fas fa-inbox"></i></span>
											<svg viewBox="0 0 230 147" style="opacity: 0.4;" id="Layer_2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="230px" height="147px" enable-background="new 0 0 230 147" xml:space="preserve">  <image id="image1" width="230" height="147" x="0" y="0" xlink:href="data:image/png;base64,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" /></svg>
										</div>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>
	
						<div class="grid-stack-item" data-gs-x="8" data-gs-y="0" data-gs-width="4" data-gs-height="5"  data-gs-min-height="5"  data-gs-min-width="3" data-gs-no-resize='true'>
							<div class="console-panel grid-stack-item-content console-no-header" style="cursor:pointer;" onclick="document.location = 'layanan/my-task/completed-list'">
								<div class="console-panel-body-noscroll no-padding">
									<div class="status_box">
										<div class="status_box_left">
											<span class="badge bg-success text-white">Proses Selesai</span>
											<h3 class="mt-1 counter_selesai">0</h3>
											<p class="mb-0">Permohonan</p>
										</div>
										<div class="status_box_right">
											<span><i class="fas fa-inbox"></i></span>
											<svg viewBox="0 0 230 147" style="opacity: 0.4;" id="Layer_3" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="230px" height="147px" enable-background="new 0 0 230 147" xml:space="preserve">  <image id="image2" width="230" height="147" x="0" y="0" xlink:href="data:image/png;base64,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" /></svg>
										</div>
									</div>
								</div>
							</div><!-- Console Panel -->
						</div>	
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-height="23"  data-gs-min-height="7"  data-gs-min-width="6">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header wrap">
									<div class="cph-left">
										<h5>Log Aktivitas</h5>
										<span>Terakhir Login : ${sessionLoginTime}
										</span>
									</div>
									<div class="cph-right wrap">
										<div class="get_dt_search"></div>
										<ul class="top-header-btns">
											<li><a class="switch-full" href="#" title="" data-rel="tooltip" data-original-title="Expand Panel To Full Screen"> <i class="icon dripicons-expand-2"></i></a></li>
											<li><a class="collapse-panel" href="#" title="Collapse/Uncollapse Panel"   data-rel="tooltip" > <i class="icon dripicons-chevron-up"></i></a></li>
											<li><a class="removeWidget" href="#" title="Remove Panel"    data-rel="tooltip"> <i class="icon dripicons-cross"></i> </a></li>
										</ul>
									</div>
								</div><!-- Panel Header -->
								<div class="console-panel-body no-padding">
									<div class="table-container">
										<table class="table table-striped datatable console-table" id="activitytable">
											<thead class="sorticon-pos">
												<tr>
													<th>Aktivitas</th>
													<th>Tanggal</th>
													<th>Alamat IP</th>
													<th></th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="console-footer">
									<div class="dt-bottom-container"></div>
								</div>
							</div>
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
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/jquery.dataTables.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colReorder.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colVis.min.js"></script>
		<script>
		
		var myObj = JSON.parse('${counter}');
	    $('.counter_tugas').html(myObj.counter_tugas.toString());
	    $('.counter_terbuka').html(myObj.counter_terbuka.toString());
	    $('.counter_selesai').html(myObj.counter_selesai.toString());
		  
		var activitytable;
		$(window).on("load", function () {		
			activitytable = $('#activitytable').DataTable({
		            "ajax": '/activity/data/${userData[0].user_name}',
		            "dom": '<"top"<"dt-filters"f>>rFt<"dt-bottom"<"dt-information"li><"dt-pagination"p>>',
		            "columns": [{
		            	"data":"log_desc"
		            },{
		            	"data":"log_date"
		            },{
		            	"data":"log_ip"
		            },{
		                "data": "log_time",
		                "visible": false,
		                "searchable": false
		            }],
		            "columnDefs": [
		                { "width": "200px", "targets": 1 },
		                { "width": "200px", "targets": 2 }
		            ],
		            "order": [[ 3, "desc" ]],
		            "responsive":true,
		            "colReorder": {
		                  realtime: false
		            },
		            "stateSave": false,
		            "createdRow": function(row, data, dataIndex) {
		            	$(row).css("cursor", "pointer");
		            },
		            initComplete: function () {
		                  let _container = $(this).parents('.console-panel').find('.get_dt_search')
		                  let _bottom_container = $(this).parents('.console-panel').find('.dt-bottom-container')
		                  $("#activitytable_wrapper .dataTables_filter input").appendTo(_container);
		                  $("#activitytable_wrapper  .dt-filters").css("display", "none");
		                  $(_container).find("input").attr('placeholder', 'Pencarian');
		                  $("#activitytable_wrapper  .dt-bottom").appendTo(_bottom_container);
		                  dashboardFilters();
		                  $(colvis.button()).insertAfter(_container);
		            }
		      });
			  
		      function dashboardFilters() {
		            $(".filterhead").each(function (i) {
		                  var select = $('<select multiple class="multiselect"></select>')
		                  .appendTo($(this).empty())
		                  .on('change', function () {
		                        var term = $(this).val() || [];
		                        regExSearch = '^(' + term.join('|') + ')';
		                        activitytable.column(i).search(regExSearch, true, false).draw();
		                  });
		                  activitytable.column(i).data().unique().sort().each(function (d, j) {
		                        d = '<span>' + d + '</span>';
		                        d = $.parseHTML(d);
		                        d = $(d).text();
		                        if (!$(select).find("option:contains('" + d + "')").length) {
		                              select.append('<option value="' + d + '">xx' + d + '</option>')
		                        }
		                  });
		            });
		            $(".multiselect").SumoSelect({search: true, searchText: 'Enter here.'});
		      }
	
		      var colvis = new $.fn.dataTable.ColVis(activitytable);
	
	
		});
		</script>
	</body>
</html>
