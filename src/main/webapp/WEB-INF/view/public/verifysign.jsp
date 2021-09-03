<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>${application_name} - ${application_description}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="${application_name} - ${application_description}" />
    <meta name="keywords" content="${application_name} - ${application_description}" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="-1" />
    <link rel="shortcut icon" href="${baseUrl}/assets/public/images/favicon/favicon.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="${baseUrl}/assets/public/css/style.css">
    <link rel="stylesheet" href="${baseUrl}/assets/public/css/responsive.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/css/dripicon.css" type="text/css" />
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/jquery-upload/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/pixioverlay/css/leaflet.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.SlideMenu.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.Window.css">
    <link rel="stylesheet" href="${baseUrl}/assets/apps/js/plugins/leaflet/leaflet.fullscreen.css">
</head>
<body>
	<div class="preloader" style="z-index:99999999 !important;"></div>
    <div class="page-wrapper">
        <header class="site-header header-one" style="z-index:999999 !important;">
            <nav class="navbar navbar-expand-lg navbar-light header-navigation stricky">
                <div class="container clearfix">
                    <div class="logo-box clearfix">
                        <div class="navbar-brand" style="color:#fff;">
                        	<div class="row">
                        		<div class="col-5"><img id="header-logo" src="${baseUrl}/assets/public/images/logo/logo-kemenhub.png" class="main-logo header-logo"/></div>
                        		<div class="col-6 header-title">${application_name} ${application_version}</div>
                        	</div>
                        </div>
                        <button class="menu-toggler" data-target=".main-navigation" style="padding-top:20px; display:none;">
                            <span class="fa fa-bars"></span>
                        </button>
                    </div>
                    <div class="main-navigation div-only-desktop">
                        <ul class="navigation-box one-page-scroll-menu right">
                            <li class="current scrollToLink">
                                <a href="/">Beranda</a>
                            </li>
                            <li class="scrollToLink">
                                <a href="/register">Registrasi</a>
                            </li>
                            <li class="scrollToLink">
                                <a href="${urls}">Login</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <section class="inner-banner" style="padding-bottom:60px !important;">
            <div class="container">
                <h2 class="inner-banner__title">Verifikasi Dokumen</h2>
            </div>
        </section>
        <section class="blog-details">
            <div class="container">
            <c:choose>
				  <c:when test="${signature_name != ''}">
		             <div class="row">
		                <div class="col-lg-6">
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Integritas</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
							            <c:choose>
											<c:when test="${signature_integrity == 'true'}">
					                    		<div><strong><span style="color:green;"><i class="fa fa-check"></i> &nbsp;Tanda Tangan Digital Valid</span></strong></div>
											</c:when>
											<c:otherwise>
					                    		<div><strong><span style="color:red;"><i class="fa fa-times"></i> &nbsp;Tanda Tangan Digital Tidak Valid</span></strong></div>
											</c:otherwise>
										</c:choose>
				                    </div>
				                </div>
				    		</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Tujuan</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_reason}</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Ditandatangani Pada</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_date} WIB</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Ditandatangani Di</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_location}</div>
				                    </div>
				                </div>
		                	</div>
				    	</div>
		                <div class="col-lg-6">
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Validitas</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
							            <c:choose>
											<c:when test="${signature_validity_now == 'valid'}">
					                    		<div><strong><span style="color:green;"><i class="fa fa-check"></i> &nbsp;Valid</span></strong></div>
											</c:when>
											<c:otherwise>
					                    		<div><strong><span style="color:red;"><i class="fa fa-times"></i> &nbsp;Tidak Valid</span></strong></div>
											</c:otherwise>
										</c:choose>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Penandatangan</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_signer}</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Direktorat / Unit</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_signer_unit}</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Kementerian</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_signer_org}</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Penerbit Sertifikat</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                  		<div>${signature_issuer}</div>
				                    </div>
				                </div>
		                	</div>
		                	<div class="row">
		                		<div class="col-lg-4">
				                    <div class="blog-details__content">
				                    	<div><strong>Public Key</strong></div>
				                    </div>                		
		                		</div>
				                <div class="col-lg-8">
				                    <div class="blog-details__content">
				                    	<div>${signature_digest_algorithm}-${signature_encryption_algorithm}</div>
				                    </div>
				                </div>
		                	</div>
		                </div>
				    </div>
		            <hr>
		            <br>
	              	<div class="row">
		                <div class="col-lg-12">
		                	<div class="row">
		                		<div class="col-lg-12">
				                    <div class="blog-details__content">
				                    	<div><strong>Hasil Verifikasi</strong></div>
				                    </div>                		
		                		</div>
		                	</div>
		                	<div class="row">
				                <div class="col-lg-12">
				                    <div class="blog-details__content">
							            <c:choose>
											<c:when test="${signature_registered == 'true' && signature_signer_org=='Kementerian Perhubungan'}">
						                    	<div><strong><span style="color:green;"><i class="fa fa-check"></i> &nbsp;Dokumen Asli Diterbitkan Oleh Direktorat Jenderal Perhubungan Laut</span></strong></div>													
											</c:when>
											<c:when test="${signature_registered == 'true' && signature_signer_org!='Kementerian Perhubungan'}">
						                    		<div><strong><span style="color:orange;"><i class="fa fa-exclamation-triangle"></i> &nbsp;Dokumen Tidak Diterbitkan Oleh Direktorat Jenderal Perhubungan Laut</span></strong></div>
											</c:when>
											<c:otherwise>
					                    		<div><strong><span style="color:red;"><i class="fa fa-times"></i> &nbsp;Dokumen Valid Tidak Diterbitkan Oleh Direktorat Jenderal Perhubungan Laut</span></strong></div>											
											</c:otherwise>
										</c:choose>
				                    </div>
				                </div>
		                	</div>
		                </div>
		                <div class="col-lg-12">
		                	<br>
		                	<a target="_blank" href="files/preview/${signature_file}" class="reply-form__btn thm-btn"><i class="fa fa-download"></i> &nbsp;Unduh PDF</a>
		                </div>
		            </div>
				  </c:when>
				  <c:otherwise>
	              	<div class="row">
		                <div class="col-lg-12 text-center">
		                	<div><span style="color:red;"><i class="fa fa-times"></i> &nbsp;Dokumen Tidak Ditandatangani Secara Digital</span></div>
		                	<br>
		                	<a href="/" class="reply-form__btn thm-btn">Kembali</a>
		                </div>
		            </div>
				  </c:otherwise>
			</c:choose>
			</div>
		</section>
        <footer class="site-footer">
            <div class="site-footer__upper">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <div class="footer-widget">
                                <a href="index.html" class="footer-widget__logo" style="color:#fff;">${application_name} ${application_version}<br>${application_description}</a>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="footer-widget">
                                <ul class="footer-widget__links">
                                    <li class="footer-widget__links-item"><a href="#home">Beranda</a></li>
                                    <li class="footer-widget__links-item"><a href="#dashboard">Dashboard</a></li>
                                    <li class="footer-widget__links-item"><a href="#layanan">Layanan</a></li>
                                </ul>
                                <ul class="footer-widget__links">
                                    <li class="footer-widget__links-item"><a href="http://dephub.go.id" target="_blank">Portal Kemenhub</a></li>
                                    <li class="footer-widget__links-item"><a href="http://hubla.dephub.go.id" target="_blank">Portal Hubla</a></li>
                                    <li class="footer-widget__links-item"><a href="https://oss.go.id" target="_blank">Online Single Submission</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="site-footer__bottom">
                <div class="container">
                    <p class="site-footer__copy-text">
                    	Copyright <i class="fa fa-copyright"></i> 2020. <a href="http://dephub.go.id" target="blank">Kementerian Perhubungan Republik Indonesia</a>
                    </p>
                </div>
            </div>
        </footer>
    </div>
    <a href="#" data-target="html" class="scroll-to-target scroll-to-top"><i class="fa fa-angle-up"></i></a>
    <script src="${baseUrl}/assets/public/js/jquery.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/jquery/jquery_ui.min.js"></script>
    <script src="${baseUrl}/assets/public/js/bootstrap.bundle.min.js"></script>
    <script src="${baseUrl}/assets/public/js/owl.carousel.min.js"></script>
    <script src="${baseUrl}/assets/public/js/waypoints.min.js"></script>
    <script src="${baseUrl}/assets/public/js/jquery.counterup.min.js"></script>
    <script src="${baseUrl}/assets/public/js/waypoints.min.js"></script>
    <script src="${baseUrl}/assets/public/js/jquery.counterup.min.js"></script>
    <script src="${baseUrl}/assets/public/js/owl.carousel.min.js"></script>
    <script src="${baseUrl}/assets/public/js/swiper.min.js"></script>
    <script src="${baseUrl}/assets/public/js/jquery.magnific-popup.min.js"></script>
    <script src="${baseUrl}/assets/public/js/jquery.easing.min.js"></script>
    <script src="${baseUrl}/assets/public/js/theme.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.ui.widget.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/jquery-upload/js/jquery.fileupload.js"></script>
	<script src="${baseUrl}/assets/apps/js/plugins/pixioverlay/js/example.min.js"></script>
	<script src="${baseUrl}/assets/apps/js/plugins/pixioverlay/js/tools.min.js"></script>
	<script src="${baseUrl}/assets/apps/js/plugins/leaflet/esri-leaflet.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.SlideMenu.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/L.Control.Window.js"></script>
    <script src="${baseUrl}/assets/apps/js/plugins/leaflet/leaflet.fullscreen.js"></script>   
</body>
</html>