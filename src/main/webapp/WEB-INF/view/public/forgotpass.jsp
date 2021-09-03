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
                            <li class="scrollToLink">
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
                <h2 class="inner-banner__title">Reset Password</h2>
                <ul class="thm-breadcrumb">
                    <li class="thm-breadcrumb__item current">
                        <a href="#" class="thm-breadcrumb__link">Reset Password Akun SEHATI</a>
                    </li>
                </ul>
            </div>
        </section>
        <section class="blog-details">
            <div class="container" style="margin-top:-50px !important;">
				<div class="row">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-8" id="contentRegistrasi">
                    	<p class="blog-details__text text-center">Silakan masukkan NIB atau NIP Anda pada form dibawah ini untuk mereset password</p>
                    	<br>
                        <form id="frmRegistrasi" name="frmRegistrasi" class="reply-form">
                            <div class="row">
                                <div class="col-lg-12">
                                    <input id="txtUsername" name="txtUsername" type="text" placeholder="NIB atau NIP" class="reply-form__field">
                                </div>
                                <div class="col-lg-12">
                                    <button id="btnSubmit" style="outline:none !important; cursor:pointer; width:100%;" class="reply-form__btn thm-btn" type="submit">Reset Password</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-lg-2"></div>
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
    <script src="https://www.google.com/recaptcha/api.js?render=6LffqdEUAAAAAAzlMS7uTs9DwK1kdgACFzeRx1ts"></script>
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
    <script>
    	$('#frmRegistrasi').submit(function(){
    		$('#btnSubmit').html('Silahkan Tunggu...').prop('disabled',true).css('cursor','wait');
			 grecaptcha.ready(function() {
	            grecaptcha.execute('6LffqdEUAAAAAAzlMS7uTs9DwK1kdgACFzeRx1ts',{action:'login'}).then(function(token) {
	        		if($('#txtUsername').val().length>0){
	        			$.ajax({
	            	        url: 'forgotpass',
	            	        type: 'POST',
	            	        data: {username:$('#txtUsername').val()}
	            	    }).done(function(data){
	            	    	var result = JSON.parse(data);
	            	    	if(result.code.toString()=='01'){
	            	    		$('#contentRegistrasi').html('<h3 class="blog-blog-details__title text-center"><strong>Email Terkirim</strong></h3><p class="blog-details__text text-center">'+result.message.toString()+'</p>');
	            	    	}else{
	            	    		$('#btnSubmit').html('Reset Password').prop('disabled',false).css('cursor','default');
	            	    		swal({
	        	        			title: "Perhatian",
	        	        			text: result.message.toString(),
	        	        			type: "warning",
	        	        			showCancelButton: false
	        	        		});
	            	    	}
	            	    }).fail(function( jqXHR, textStatus ) {
	        	    		$('#btnSubmit').html('Reset Password').prop('disabled',false).css('cursor','default');
	            	    	swal({
	    	        			title: "Perhatian",
	    	        			text: "Terjadi kesalahan sistem. Silakan hubungi Administrator.",
	    	        			type: "warning",
	    	        			showCancelButton: false
	    	        		});
	            	    });
	        		}else{
	    	    		$('#btnSubmit').html('Reset Password').prop('disabled',false).css('cursor','default');
	        			swal({
	            			title: "Perhatian",
	            			text: "Silakan masukkan NIB atau NIP terlebih dahulu",
	            			type: "warning",
	            			showCancelButton: false
	            		});
	        		}	            	
	            });
	        });
    		return false;
    	});
    </script>
</body>
</html>