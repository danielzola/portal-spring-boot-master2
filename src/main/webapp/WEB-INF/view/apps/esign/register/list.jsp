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
						      			<div class="col-lg-12 col-md-12">
						            		<h1>Registrasi E-Sign</h1>
						            		<div style="font-weight:normal !important; margin-top:5px;">Manajemen data registrasi sertifikat elektronik</div>
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
										<table class="table table-striped datatable console-table" id="esigntable">
											<thead class="sorticon-pos">
												<tr>
													<th></th>
													<th>NIK</th>
													<th>Nama Pegawai</th>
													<th>Jabatan</th>
													<th>Tgl. Registrasi</th>
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
		var esigntable;
		$(window).on("load", function () {		
			esigntable = $('#esigntable').DataTable({
		            "ajax": '/esign/register/table',
		            "dom": '<"top"<"dt-filters"f>>rFt<"dt-bottom"<"dt-information"li><"dt-pagination"p>>',
		            "columns": [{
		                "data": "id",
		                "visible": false,
		                "searchable": false
		            },{
		            	"data":"esign_nik"
		            },{
		            	"data":"esign_nama"
		            },{
		                "data": "esign_jabatan"
		            },{
		                "data": "esign_tanggal"
		            },{
		                "data": "esign_status"
		            }],
		            "columnDefs": [ 
		            	{
			                "targets": 2,
			                "width": "250px"
			            },
			            {
			                "targets": 4,
			                "width": "150px"
			            },
			            {
			                "targets": 5,
			                "render": function ( data, type, row, meta ) {
			                	if(row.esign_status=='0'){
			                		return '<span style="font-size:12px;" class="badge badge-warning badge-pill">Verifikasi Admin</span>';
			                	}
			                	if(row.esign_status=='1'){
			                		return '<span style="font-size:12px;" class="badge badge-danger badge-pill">Ditolak</span>';		                		
			                	}
			                	if(row.esign_status=='2'){
			                		return '<span style="font-size:12px;" class="badge badge-warning badge-pill">Verifikasi BSrE</span>';		                		
			                	}
			                	if(row.esign_status=='3'){
			                		return '<span style="font-size:12px;" class="badge badge-warning badge-pill">Setting Passphrase</span>';		                		
			                	}
			                	if(row.esign_status=='4'){
			                		return '<span style="font-size:12px;" class="badge badge-success badge-pill">Berlaku</span>';		                		
			                	}
		                }
		            }],
		            "order": [[ 4, "desc" ]],
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
		                  $("#esigntable_wrapper .dataTables_filter input").appendTo(_container);
		                  $("#esigntable_wrapper  .dt-filters").css("display", "none");
		                  $(_container).find("input").attr('placeholder', 'Pencarian');
		                  $("#esigntable_wrapper  .dt-bottom").appendTo(_bottom_container);
		                  dashboardFilters();
		                  $(colvis.button()).insertAfter(_container);
		            }
		      });
			  
		      function format (d) {
		    	  	var id = d.user_id;
		    	    return '${roleActionTable}';
		      }
		      	      
		      $('#esigntable tbody').on('click', 'tr', function () {
		          var tr = $(this).closest('tr');
		          var row = esigntable.row(tr);
		   
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
		                        esigntable.column(i).search(regExSearch, true, false).draw();
		                  });
		                  esigntable.column(i).data().unique().sort().each(function (d, j) {
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

		      var colvis = new $.fn.dataTable.ColVis(esigntable);
	
		});
		
		function addEsign(){
			document.location = '/esign/register/form/${userData[0].user_id}';
		}
		
		function previewEsign(id){
			document.location = '/esign/register/form/'+id;
		}
		
		function cekEsign(id){
			swal({title: "Status E-Sign", text: "Silahkan tunggu...", showCancelButton: false, showConfirmButton: false });
			$.post("/esign/register/cek",{id:id},function(data){
				if(data.code=='01'){
					var tipe = "info";
					if(data.message.toString()=='User telah terdaftar dan memiliki sertifikat elektronik'){
						tipe = "success";
					}
					swal({
						title: "Status E-Sign",
						text: data.message.toString(),
						type: tipe,
						confirmButtonColor: "#60c84c",
						confirmButtonText: "OK",
						closeOnConfirm: true
					}); 						
				}
			},'json');
			
		}
		</script>
	</body>
</html>
