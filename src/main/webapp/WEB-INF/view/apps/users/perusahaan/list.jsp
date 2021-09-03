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
						      <div class="filteration-text" style="margin:20px 0px 30px 0px;">
						      		<div class="row col-lg-10">
						      			<div class="col-lg-12 col-md-12">
						            		<h1>User Perusahaan</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Manajemen data user perusahaan</div>
						      			</div>
						      		</div>
						      </div>
						</div>
					</div>
					<div class="grid-stack gs-area">
						<div class="grid-stack-item" data-gs-x="0" data-gs-y="10" data-gs-width="12" data-gs-height="23"  data-gs-min-height="7"  data-gs-min-width="6">
							<div class="console-panel grid-stack-item-content">
								<div class="console-panel-header wrap">
									<div class="cph-left">
									${roleActionTop}
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
										<table class="table table-striped datatable console-table" id="perusahaantable">
											<thead class="sorticon-pos">
												<tr>
													<th></th>
													<th>Nama Perusahaan</th>
													<th>NIB</th>
													<th>Alamat</th>
													<th>Email</th>
													<th>Status</th>
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
		<script src="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/jquery.dataTables.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colReorder.min.js"></script>
		<script src="${baseUrl}/assets/apps/js/plugins/datatable/dataTables.colVis.min.js"></script>
		<script>
		var perusahaantable;
		$(window).on("load", function () {		
			perusahaantable = $('#perusahaantable').DataTable({
		            "ajax": '/users/perusahaan/table',
		            "dom": '<"top"<"dt-filters"f>>rFt<"dt-bottom"<"dt-information"li><"dt-pagination"p>>',
		            "columns": [{
		                "data": "user_id",
		                "visible": false,
		                "searchable": false
		            },{
		            	"data":"company_name"
		            },{
		            	"data":"company_nib"
		            },{
		            	"data":"company_address"
		            },{
		            	"data":"company_email"
		            },{
		                "data": "user_status"
		            }],
		            "columnDefs": [ 
	            	{
		                "targets": 1,
		                "width": "250px"
		            },
	            	{
		                "targets": 2,
		                "width": "150px",
		                "render": function ( data, type, row, meta ) {
		                	return row.company_nib+'<br><span style="font-size:10px !important;">NPWP : '+row.company_taxno+'</span>';
		                }
		            },
	            	{
		                "targets": 2,
		                "width": "120px"
		            },
	            	{
		                "targets": 4,
		                "width": "150px"
		            },
		            {
		                "targets": 5,
		                "data": "user_status",
		                "render": function ( data, type, row, meta ) {
		                	if(row.user_status=='1'){
		                		return '<span style="font-size:12px;" class="badge badge-success badge-pill">Aktif</span>';
		                	}
		                	if(row.user_status=='2'){
		                		return '<span style="font-size:12px;" class="badge badge-warning badge-pill">Belum Diaktifasi</span>';
		                	}
		                	if(row.user_status=='0' || row.user_status=='9'){
		                		return '<span style="font-size:12px;" class="badge badge-danger badge-pill">Tidak Aktif</span>';
		                	}
		                }
		            }],
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
		                  $("#perusahaantable_wrapper .dataTables_filter input").appendTo(_container);
		                  $("#perusahaantable_wrapper  .dt-filters").css("display", "none");
		                  $(_container).find("input").attr('placeholder', 'Pencarian');
		                  $("#perusahaantable_wrapper  .dt-bottom").appendTo(_bottom_container);
		                  dashboardFilters();
		                  $(colvis.button()).insertAfter(_container);
		            }
		      });
			  
		      function format (d) {
		    	  	var id = d.user_name;
		    	    return '${roleActionTable}';
		      }
		      	      
		      $('#perusahaantable tbody').on('click', 'tr', function () {
		          var tr = $(this).closest('tr');
		          var row = perusahaantable.row(tr);
		   
		          if ( row.child.isShown() ) {
		              row.child.hide();
		              tr.removeClass('shown');
		          }
		          else {
		              row.child( format(row.data()) ).show();
		              tr.addClass('shown');
		          }
		      } );
			
		      function dashboardFilters() {
		            $(".filterhead").each(function (i) {
		                  var select = $('<select multiple class="multiselect"></select>')
		                  .appendTo($(this).empty())
		                  .on('change', function () {
		                        var term = $(this).val() || [];
		                        regExSearch = '^(' + term.join('|') + ')';
		                        perusahaantable.column(i).search(regExSearch, true, false).draw();
		                  });
		                  perusahaantable.column(i).data().unique().sort().each(function (d, j) {
		                        d = '<span>' + d + '</span>';
		                        d = $.parseHTML(d);
		                        d = $(d).text();
		                        if (!$(select).find("option:contains('" + d + "')").length) {
		                              select.append('<option value="' + d + '">' + d + '</option>')
		                        }
		                  });
		            });
		            $(".multiselect").SumoSelect({search: true, searchText: 'Enter here.'});
		      }

		      var colvis = new $.fn.dataTable.ColVis(perusahaantable);
	
		});
		
		function previewPerusahaan(id){
			document.location = '/users/perusahaan/form/'+id;
		}
		
		</script>
	</body>
</html>
