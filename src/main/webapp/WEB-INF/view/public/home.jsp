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
    <link rel="shortcut icon" href="../assets/public/images/favicon/favicon.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="../assets/public/css/style.css">
    <link rel="stylesheet" href="../assets/public/css/responsive.css">
    <link rel="stylesheet" href="../assets/apps/js/plugins/sweetalert/sweetalert.css">
    <link rel="stylesheet" href="../assets/apps/css/dripicon.css" type="text/css" />
    <link rel="stylesheet" href="../assets/apps/js/plugins/jquery-upload/css/jquery.fileupload.css">
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
                        		<div class="col-5"><img id="header-logo" src="../assets/public/images/logo/logo-kemenhub.png" class="main-logo header-logo"/></div>
                        		<div class="col-6 header-title">${application_name} ${application_version}</div>
                        	</div>
                        </div>
                        <button class="menu-toggler" data-target=".main-navigation" style="padding-top:20px; display:none;">
                            <span class="fa fa-bars"></span>
                        </button>
                    </div>
                    <div class="main-navigation div-only-desktop">
                        <ul class="navigation-box one-page-scroll-menu right">
                            <li class="scrollToLink">
                                <a href="/register" style="color:#F9B425;">Registrasi</a>
                            </li>
                            <li class="scrollToLink">
                                <a href="#dashboard">Dashboard</a>
                            </li>
                            <li class="current scrollToLink">
                                <a href="#layanan">Layanan</a>
                            </li>
                            <li class="scrollToLink">
                                <a href="#verifikasi">Verifikasi</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <section class="banner-one" id="home">
            <div class="container">
                <div class="banner-one__moc-wrap">
                    <img src="../assets/public/images/logo/logo-sehati.webp" class="banner-one__moc" width="500" style="left:5px; margin-top:-50px;"/>
                </div>
                <div class="row justify-content-end">
                    <div class="col-lg-6 col-md-6">
                        <div class="banner-one__content">
                            <h3 class="banner-one__title title-only-mobile" style="font-size:25px !important;">SISTEM ELEKTRONIK HUBLA TERINTEGRASI</h3>
                            <p class="banner-one__text title-only-mobile" style="line-height:10px;">
                            	Kementerian Perhubungan Republik Indonesia
                            </p>
                            <p class="banner-one__text title-only-mobile" style="line-height:15px; margin-top:-10px;">
                            	<span style="font-size:23px;">Direktorat Jenderal Perhubungan Laut</span>
                            </p>
                            <div class="title-only-mobile"><a href="https://sehatitest.hubla.dephub.go.id/signin/oauth2/authorization/wso2" class="banner-one__btn thm-btn">LOGIN APLIKASI</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="service-one" id="dashboard">
            <div class="container">
                <div class="block-title text-center">
                    <h2 class="block-title__title" style="text-transform:none !important;">Dashboard</h2>
                </div>
            </div>
        </section>
        <div class="row" id="dashboard-map-load" style="height:500px; margin:-40px 1px 0px 1px;">
        </div>
        <section class="service-one" id="layanan">
            <div class="container">
                <div class="block-title text-center">
                    <h2 class="block-title__title" style="text-transform:none !important;">Layanan Terintegrasi</h2><!-- /.block-title__title -->
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="service-one__single">
                            <div class="service-one__icon">
                                <i class="appyn-icon-target-1"></i>
                            </div>
                            <h3 class="service-one__title"><a>Satu Pintu</a></h3>
                            <p class="service-one__text">Pengajuan dan monitoring terpadu <br>satu pintu untuk semua layanan di Direktorat Jenderal Perhubungan Laut</p><!-- /.service-one__text -->
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="service-one__single">
                            <div class="service-one__icon">
                                <i class="appyn-icon-businessman"></i>
                            </div>
                            <h3 class="service-one__title"><a>Single Sign On</a></h3>
                            <p class="service-one__text">Satu otentikasi untuk masuk ke semua <br>layanan dan fitur aplikasi</p><!-- /.service-one__text -->
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="service-one__single">
                            <div class="service-one__icon">
                                <i class="appyn-icon-paper-plane"></i>
                            </div>
                            <h3 class="service-one__title"><a>Digital Signature</a></h3>
                            <p class="service-one__text">Semua layanan di SeHATI memiliki sertifikat dari <br>Balai Sertifikasi Elektronik</p><!-- /.service-one__text -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="subscribe-one" id="verifikasi">
            <div class="container">
                <div class="block-title text-center">
                    <h5 class="block-title__title" style="text-transform:none !important;">Verifikasi Dokumen</h5>
                    <p class="service-one__text">Dokumen Elektronik merupakan dokumen yang diterbitkan oleh Direktorat Jenderal Perhubungan Laut yang ditandatangani secara elektronik.<br>
Silahkan cek keabsahan dan keaslian dokumen Anda.</p><!-- /.service-one__text -->
                </div>
                <form id="frmVerifyPDF" class="subscribe-one__form" method="post" action="verifysign">
                	<input type="hidden" name="pathPDF" id="pathPDF">
                    <input type="file" id="filePDF" name="file" style="display:none;" accept=".pdf">
                    <input type="text" name="choosePDF" id="choosePDF" placeholder="File Dokumen" readonly onclick="$('#filePDF').click()" style="cursor:pointer; width:100%;">
                    <button type="submit" disabled id="submitVerifyPDF" class="subscribe-one__btn"><i class="fa fa-location-arrow"></i></button>
                </form>
            </div>
        </section>
        <section class="brand-one">
            <div class="container">
            	<div style="text-align:center;"><h3>Terintegrasi Dengan</h3></div>
            	<br>
                <div class="brand-one__carousel owl-theme owl-carousel">
                    <div class="item">
                        <img src="../assets/public/images/logo/logo-kemenkeu.webp" alt="Kementerian Keuangan" />
                    </div>
                    <div class="item">
                        <img src="../assets/public/images/logo/logo-bssn.webp" alt="Badan Siber dan Sandi Negara"/>
                    </div>
                    <div class="item">
                        <img src="../assets/public/images/logo/logo-bsre.webp" alt="Balai Sertifikasi Elektronik" />
                    </div>
                    <div class="item">
                        <img src="../assets/public/images/logo/logo-bkpm.webp" alt="Badan Koordinasi Penanaman Modal" />
                    </div>
                    <div class="item">
                        <img src="../assets/public/images/logo/logo-oss.webp" alt="Online Single Submission" />
                    </div>
                </div>
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
    <script src="../assets/public/js/jquery.js"></script>
    <script src="../assets/apps/js/plugins/jquery/jquery_ui.min.js"></script>
    <script src="../assets/public/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/public/js/owl.carousel.min.js"></script>
    <script src="../assets/public/js/waypoints.min.js"></script>
    <script src="../assets/public/js/jquery.counterup.min.js"></script>
    <script src="../assets/public/js/waypoints.min.js"></script>
    <script src="../assets/public/js/jquery.counterup.min.js"></script>
    <script src="../assets/public/js/owl.carousel.min.js"></script>
    <script src="../assets/public/js/swiper.min.js"></script>
    <script src="../assets/public/js/jquery.magnific-popup.min.js"></script>
    <script src="../assets/public/js/jquery.easing.min.js"></script>
    <script src="../assets/public/js/theme.js"></script>
    <script src="../assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="../assets/apps/js/plugins/jquery-upload/js/jquery.ui.widget.js"></script>
    <script src="../assets/apps/js/plugins/jquery-upload/js/jquery.fileupload.js"></script>
    <script src="../assets/public/js/home.js"></script>
</body>
</html>