

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>

<%--    <title>SEHATI - Sistem Elektronik Hubla Terintegrasi</title>--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <meta name="description" content="SEHATI - Sistem Elektronik Hubla Terintegrasi">--%>
<%--    <meta name="keywords" content="SEHATI - Sistem Elektronik Hubla Terintegrasi">--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">--%>
<%--	<meta http-equiv="Pragma" content="no-cache">--%>
<%--	<meta http-equiv="Expires" content="-1">--%>
<%--    <link rel="shortcut icon" href="../assets/public/images/favicon/favicon.png">--%>
<%--    <meta name="msapplication-TileColor" content="#ffffff">--%>
<%--    <meta name="theme-color" content="#ffffff">--%>
<%--    <link rel="stylesheet" href="../assets/public/css/style.css">--%>
<%--    <link rel="stylesheet" href="../assets/public/css/responsive.css">--%>
<%--    <link rel="stylesheet" href="../assets/apps/js/plugins/sweetalert/sweetalert.css">--%>
<%--    <link rel="stylesheet" href="../assets/apps/css/dripicon.css" type="text/css">--%>
<%--</head>--%>
<%--<style>--%>
<%--    .body {--%>
<%--        font-family: Poppins, sans-serif;--%>
<%--    }--%>
<%--    .title-modal {--%>
<%--        font-style: normal;--%>
<%--        font-weight: normal;--%>
<%--        font-size: 18px;--%>
<%--        line-height: 34px;--%>
<%--        text-align: center;--%>
<%--        color: #6D6D6D;--%>
<%--        margin-bottom: 16px;--%>
<%--    }--%>
<%--    .button-back {--%>
<%--        background: #FF5C75;--%>
<%--        border: 1px solid #FF5C75;--%>
<%--        color: #ffffff;--%>
<%--        box-sizing: border-box;--%>
<%--        border-radius: 4px;--%>
<%--    }--%>
<%--    .button-register {--%>
<%--        background: #5D8BD0;--%>
<%--        border: 1px solid #5D8BD0;--%>
<%--        color: #ffffff;--%>
<%--        box-sizing: border-box;--%>
<%--        border-radius: 4px;--%>
<%--    }--%>
<%--    .input.largerCheckbox {--%>
<%--        width: 15px;--%>
<%--        height: 15px;--%>
<%--    }--%>
<%--    input[type=file]::file-selector-button {--%>
<%--        padding: .2em .4em;--%>
<%--        transition: 1s;--%>
<%--        background: #5D8BD0;--%>
<%--        border: 1px solid #5D8BD0;--%>
<%--        color: #ffffff;--%>
<%--        box-sizing: border-box;--%>
<%--        border-radius: 4px;--%>
<%--    }--%>
<%--    input[type=file]::file-selector-button:hover {--%>
<%--        background-color: #81ecec;--%>
<%--        border: 2px solid #00cec9;--%>
<%--    }--%>
<%--    .thm-btn:hover{--%>
<%--        color:#fff !important;--%>
<%--    }--%>
<%--    .btn-circle {--%>
<%--        width: 30px;--%>
<%--        height: 30px;--%>
<%--        padding: 6px 0px;--%>
<%--        border-radius: 15px;--%>
<%--        text-align: center;--%>
<%--        font-size: 12px;--%>
<%--        line-height: 1.42857;--%>
<%--    }--%>
<%--    .required > label::after {--%>
<%--        content: " * ";--%>
<%--        color: red;--%>
<%--    }--%>
<%--    legend {--%>
<%--        font-size: 1.3rem !important;--%>
<%--        font-weight: bold;--%>
<%--        margin-bottom: 25px--%>
<%--    }--%>
<%--</style>--%>

<%--<body>--%>
<%--    <div class="preloader" style="z-index:99999999 !important;"></div>--%>
<%--    <div class="page-wrapper">--%>
<%--        <header class="site-header header-one" style="z-index:999999 !important;">--%>
<%--            <nav class="navbar navbar-expand-lg navbar-light header-navigation stricky">--%>
<%--                <div class="container clearfix">--%>
<%--                    <div class="logo-box clearfix">--%>
<%--                        <div class="navbar-brand" style="color:#fff;">--%>
<%--                        	<div class="row">--%>
<%--                        		<div class="col-5"><img id="header-logo" src="../assets/public/images/logo/logo-kemenhub.png" class="main-logo header-logo"></div>--%>
<%--                        		<div class="col-6 header-title">SEHATI 2.0</div>--%>
<%--                        	</div>--%>
<%--                        </div>--%>
<%--                        <button class="menu-toggler" data-target=".main-navigation" style="padding-top:20px; display:none;">--%>
<%--                            <span class="fa fa-bars"></span>--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                    <div class="main-navigation div-only-desktop">--%>
<%--                        <ul class="navigation-box one-page-scroll-menu right">--%>
<%--                            <li class="scrollToLink">--%>
<%--                                <a href="../portal/">Beranda</a>--%>
<%--                            </li>--%>
<%--                            <li class="scrollToLink">--%>
<%--                                <a href="../../sehatitest.hubla.dephub.go.id/sso/authenticationendpoint/login_client_id%3Db4mC">Login</a>--%>
<%--                            </li>--%>
<%--                            <li class="scrollToLink">--%>
<%--                                <a href="../forgotpass/">Lupa Password</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </nav>--%>
<%--        </header>--%>
<%--        <section class="inner-banner" style="padding-bottom:60px !important;">--%>
<%--            <div class="container">--%>
<%--                <h2 class="inner-banner__title">Registrasi</h2>--%>
<%--                <ul class="thm-breadcrumb">--%>
<%--                    <li class="thm-breadcrumb__item current">--%>
<%--                        <a href="#" class="thm-breadcrumb__link">Registrasi Akun SEHATI</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </section>--%>
<%--        <section class="blog-details">--%>
<%--            <div class="container" style="margin-top:-50px !important;">--%>
<%--				<div class="row">--%>
<%--                    <div class="col-lg-2"></div>--%>
<%--                    <div class="col-lg-8" id="contentRegistrasi">--%>
<%--                    	<p class="blog-details__text text-center">Silakan masukkan NIB atau NIP Anda pada form dibawah ini untuk mendapatkan Akun SEHATI</p>--%>
<%--                    	<br>--%>
<%--                        <form id="frmRegistrasi" name="frmRegistrasi" class="reply-form">--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-lg-12">--%>
<%--                                    <input id="txtUsername" name="txtUsername" type="text" placeholder="NIB atau NIP" class="reply-form__field">--%>
<%--                                </div>--%>
<%--                                <div class="col-lg-12">--%>
<%--                                    <button id="btnSubmit" style="outline:none !important; cursor:pointer; width:100%;" class="reply-form__btn thm-btn" type="submit">Registrasi</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                    <div class="col-lg-2"></div>--%>
<%--                 </div>--%>
<%--			</div>--%>
<%--		</section>--%>
<%--        <footer class="site-footer">--%>
<%--            <div class="site-footer__upper">--%>
<%--                <div class="container">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-lg-5">--%>
<%--                            <div class="footer-widget">--%>
<%--                                <a href="../portal/" class="footer-widget__logo" style="color:#fff;">SEHATI 2.0<br>Sistem Elektronik Hubla Terintegrasi</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-lg-4">--%>
<%--                            <div class="footer-widget">--%>
<%--                                <ul class="footer-widget__links">--%>
<%--                                    <li class="footer-widget__links-item"><a href="#home">Beranda</a></li>--%>
<%--                                    <li class="footer-widget__links-item"><a href="#dashboard">Dashboard</a></li>--%>
<%--                                    <li class="footer-widget__links-item"><a href="#layanan">Layanan</a></li>--%>
<%--                                </ul>--%>
<%--                                <ul class="footer-widget__links">--%>
<%--                                    <li class="footer-widget__links-item"><a href="http://dephub.go.id" target="_blank">Portal Kemenhub</a></li>--%>
<%--                                    <li class="footer-widget__links-item"><a href="http://hubla.dephub.go.id" target="_blank">Portal Hubla</a></li>--%>
<%--                                    <li class="footer-widget__links-item"><a href="https://oss.go.id" target="_blank">Online Single Submission</a></li>--%>
<%--                                </ul>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="site-footer__bottom">--%>
<%--                <div class="container">--%>
<%--                    <p class="site-footer__copy-text">--%>
<%--                    	Copyright <i class="fa fa-copyright"></i> 2020. <a href="http://dephub.go.id" target="blank">Kementerian Perhubungan Republik Indonesia</a>--%>
<%--                    </p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </footer>--%>
<%--    </div>--%>

<%--    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel"--%>
<%--    aria-hidden="true" style="z-index: 999999999 !important;" data-backdrop="static">--%>
<%--        <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable" style="z-index: 999999999 !important;">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-body">--%>
<%--                    <h2 class="title-modal">--%>
<%--                        Silakan isi form registrasi dibawah ini untuk mendapatkan Akun SEHATI--%>
<%--                    </h2>--%>

<%--                    <form id="regis_manual" method="post" novalidate>--%>
<%--                        &lt;%&ndash;@declare id="inputpemegangsaham"&ndash;%&gt;&lt;%&ndash;@declare id="inputpenanggungjawab"&ndash;%&gt;<fieldset>--%>
<%--                            <legend>Data Perusahaan</legend>--%>

<%--                            <div class="form-row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group required">--%>
<%--                                        <label for="NamaPerseroan">Nama Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="NamaPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputNpwp">NPWP Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="inputNpwp" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputTglTerbit">Tgl. Terbit NIB</label>--%>
<%--                                        <input type="text" class="form-control" id="inputTglTerbit" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputTotalModal">Total Modal Dasar</label>--%>
<%--                                        <input type="text" class="form-control" id="inputTotalModal" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="AlamatPerseroan">Alamat Perseroan</label>--%>
<%--                                        <textarea class="form-control" id="AlamatPerseroan" rows="4" style="height:133px" required></textarea>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="KecamatanPerseroan">Kecamatan Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="KecamatanPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="ProvinsiPerseroan">Provinsi Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="ProvinsiPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="NoTelponPerseroan">No. Telepon Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="NoTelponPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputNib1">NIB</label>--%>
<%--                                        <input type="text" class="form-control" id="inputNib1" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputStatus">Status NIB</label>--%>
<%--                                        <input type="text" class="form-control" id="inputStatus" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputTglPerubahan">Tgl. Perubahan NIB</label>--%>
<%--                                        <input type="text" class="form-control" id="inputTglPerubahan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputModalTempat">Total modal Ditempatkan</label>--%>
<%--                                        <input type="text" class="form-control" id="inputModalTempat" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="RTRWPerseroan">RT\RW Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="RTRWPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="KelurahanPerseroan">Kelurahan Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="KelurahanPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="KotaKabupaten">Kota/Kabupaten Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="KotaKabupaten" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        &lt;%&ndash;@declare id="inputnib"&ndash;%&gt;<label for="inputNib">Kode Pos Perseroan</label>--%>
<%--                                        <input type="text" class="form-control" id="KodeposPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </fieldset>--%>


<%--                        <label for="inputPemegangSaham">Pemegang Saham</label>--%>
<%--                        <div class="table-responsive">--%>
<%--                            <div class="table-wrapper">--%>
<%--                                <table class="table table-striped table-hover mt-table-field">--%>
<%--                                    <thead class="text-center" style="background-color: aliceblue">--%>
<%--                                        <tr>--%>
<%--                                            <th scope="col" width="25%">Nama Pemegang Saham</th>--%>
<%--                                            <th scope="col" width="20%">Jabatan</th>--%>
<%--                                            <th scope="col" width="20%">No. NPWP</th>--%>
<%--                                            <th scope="col">Alamat</th>--%>
<%--                                            <th scope="col">&nbsp;</th>--%>
<%--                                        </tr>--%>
<%--                                    </thead>--%>
<%--                                    <tbody>--%>
<%--                                        <tr>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PemegangSaham[nama][]" class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PemegangSaham[jabatan][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PemegangSaham[npwp][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PemegangSaham[alamat][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td><button type="button" class="btn btn-success btn-circle btn-xs mt-btn-add">--%>
<%--                                                <i class="fa fa-plus"></i></button></td>--%>
<%--                                        </tr>--%>
<%--                                    </tbody>--%>
<%--                                </table>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <label for="inputPenanggungJawab">Penanggung Jawab</label>--%>
<%--                        <div class="table-responsive">--%>
<%--                            <div class="table-wrapper">--%>
<%--                                <table class="table table-striped table-hover mt-table-field">--%>
<%--                                    <thead class="text-center" style="background-color: aliceblue">--%>
<%--                                        <tr>--%>
<%--                                            <th scope="col" width="25%">Nama Penanggung Jawab</th>--%>
<%--                                            <th scope="col" width="20%">Jabatan</th>--%>
<%--                                            <th scope="col" width="20%">No. NPWP</th>--%>
<%--                                            <th scope="col">Alamat</th>--%>
<%--                                            <th scope="col">&nbsp;</th>--%>
<%--                                        </tr>--%>
<%--                                    </thead>--%>
<%--                                    <tbody>--%>
<%--                                        <tr>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PenanggungJawab[nama][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PenanggungJawab[jabatan][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PenanggungJawab[npwp][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="form-group required">--%>
<%--                                                    <input type="text" name="PenanggungJawab[alamat][]"--%>
<%--                                                    class="form-control" required>--%>
<%--                                                    <div class="invalid-feedback"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td><button type="button" class="btn btn-success btn-circle btn-xs mt-btn-add">--%>
<%--                                                <i class="fa fa-plus"></i></button></td>--%>
<%--                                        </tr>--%>
<%--                                    </tbody>--%>
<%--                                </table>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <div class="form-group required">--%>
<%--                            <label for="uploadNib">Upload Izin NIB</label><br/>--%>
<%--                            <input type="file" id="uploadNib" class="form-control-file"--%>
<%--                                accept="application/pdf,image/*" required>--%>
<%--                            <div class="invalid-feedback"></div>--%>
<%--                        </div>--%>

<%--                        <br/>--%>

<%--                        <fieldset>--%>
<%--                            <legend>Data User Proses</legend>--%>

<%--                            <div class="form-row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputPerseroan">Nama User Proses</label>--%>
<%--                                        <input type="text" class="form-control" id="NamaUserProses" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputPerseroan">Daerah ID User Proses</label>--%>
<%--                                        <input type="text" class="form-control" id="inputPerseroan" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputPerseroan">Alamat User Proses</label>--%>
<%--                                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" required></textarea>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputNib">Email User Proses</label>--%>
<%--                                        <input type="email" class="form-control" id="Email" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group required">--%>
<%--                                        <label for="inputNib2">No. HP User Proses</label>--%>
<%--                                        <input type="text" class="form-control" id="inputNib2" required>--%>
<%--                                        <div class="invalid-feedback"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </fieldset>--%>

<%--                        <!-- <p class="font-weight-bold" style="margin-top: 60px;">Data User Proses</p> -->--%>
<%--                        <div class="form-group form-check col-md-8 offset-md-2">--%>
<%--                            <input class="form-check-input largerCheckbox" name="term" type="checkbox" id="tanda" required>--%>
<%--                            <label class="form-check-label text-justify" for="tanda">--%>
<%--                                Saya menyatakan bahwa data yang saya masukkan adalah benar dan bersedia bertanggung jawab--%>
<%--                                serta menerima sanksi sesuai hukum yang berlaku di Indonesia apabila data yang saya masukkan tidak benar.--%>
<%--                            </label>--%>
<%--                        </div>--%>

<%--                        <!-- <p class="text-center text-danger">Silahkan Centang Box Disclaimer</p> -->--%>

<%--                        <div class="form-group text-center">--%>
<%--                            <button id="kembali" class="btn btn-danger" data-dismiss="modal">Kembali</button>--%>
<%--                            <!-- <button id="daftar" class="btn btn-primary" onclick="ingat()">Registrasi</button> -->--%>
<%--                            <button type="submit" id="btn-register" class="btn btn-primary">Registrasi</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <a href="#" data-target="html" class="scroll-to-target scroll-to-top"><i class="fa fa-angle-up"></i></a>--%>
<%--    <script src="https://www.google.com/recaptcha/api.js?render=6LdTQeMUAAAAAOBQHYlOX1kH7LsQLfxknChH5ZGD"></script>--%>
<%--    <script src="../assets/public/js/jquery.js"></script>--%>
<%--    <script src="../assets/apps/js/plugins/jquery/jquery_ui.min.js"></script>--%>
<%--    <script src="../assets/public/js/bootstrap.bundle.min.js"></script>--%>
<%--    <script src="../assets/public/js/owl.carousel.min.js"></script>--%>
<%--    <script src="../assets/public/js/waypoints.min.js"></script>--%>
<%--    <script src="../assets/public/js/jquery.counterup.min.js"></script>--%>
<%--    <script src="../assets/public/js/waypoints.min.js"></script>--%>
<%--    <script src="../assets/public/js/jquery.counterup.min.js"></script>--%>
<%--    <script src="../assets/public/js/owl.carousel.min.js"></script>--%>
<%--    <script src="../assets/public/js/swiper.min.js"></script>--%>
<%--    <script src="../assets/public/js/jquery.magnific-popup.min.js"></script>--%>
<%--    <script src="../assets/public/js/jquery.easing.min.js"></script>--%>
<%--    <script src="../assets/public/js/theme.js"></script>--%>
<%--    <script src="../assets/apps/js/plugins/sweetalert/sweetalert.min.js"></script>--%>
<%--    <script src="./mt-table-field.js"></script>--%>
<%--    <script src="./req-validation.js"></script>--%>
<%--    <script>--%>
<%--    	$('#frmRegistrasi').submit(function(){--%>
<%--    		$('#btnSubmit').html('Silahkan Tunggu...').prop('disabled',true).css('cursor','wait');--%>
<%--			 //grecaptcha.ready(function() {--%>
<%--		            //grecaptcha.execute('6LdTQeMUAAAAAJH-PPa-MlEWSQgv0NPM2DfmDz_o',{action:'login'}).then(function(token) {--%>
<%--			    		if($('#txtUsername').val().length>0){--%>
<%--			    			$.ajax({--%>
<%--			        	        url: 'register',--%>
<%--			        	        type: 'POST',--%>
<%--			        	        data: {username:$('#txtUsername').val()}--%>
<%--			        	    }).done(function(data){--%>
<%--			        	    	var result = JSON.parse(data);--%>
<%--			        	    	if(result.code.toString()=='01'){--%>
<%--			        	    		$('#contentRegistrasi').html('<h3 class="blog-blog-details__title text-center"><strong>Registrasi Berhasil</strong></h3><p class="blog-details__text text-center">'+result.message.toString()+'</p>');--%>
<%--			        	    	}else{--%>
<%--			        	    		$('#btnSubmit').html('Registrasi').prop('disabled',false).css('cursor','default');--%>
<%--			        	    		swal({--%>
<%--			    	        			title: "Perhatian",--%>
<%--			    	        			text: result.message.toString(),--%>
<%--			    	        			type: "warning",--%>
<%--			    	        			showCancelButton: false--%>
<%--			    	        		});--%>
<%--			        	    	}--%>
<%--			        	    }).fail(function( jqXHR, textStatus ) {--%>
<%--			    	    		$('#btnSubmit').html('Registrasi').prop('disabled', false).css('cursor','default');--%>
<%--                                swal({--%>
<%--                                    title: "Perhatian",--%>
<%--                                    text: "NIB belum teregistrasi otomatis. Silahkan Registrasi Manual.",--%>
<%--                                    type: "warning",--%>
<%--                                    confirmButtonColor: "#134563",--%>
<%--                                    confirmButtonText: "OK",--%>
<%--                                    closeOnConfirm: false,--%>
<%--                                    html: false--%>
<%--                                },  function(){--%>
<%--                                    $('#myModal').modal('show');--%>
<%--                                    swal.close();--%>
<%--                                });--%>
<%--			        	    });--%>
<%--			    		}else{--%>
<%--				    		$('#btnSubmit').html('Registrasi').prop('disabled',false).css('cursor','default');--%>
<%--			    			swal({--%>
<%--			        			title: "Perhatian",--%>
<%--			        			text: "Silakan masukkan NIB atau NIP terlebih dahulu",--%>
<%--			        			type: "warning",--%>
<%--			        			showCancelButton: false--%>
<%--			        		});--%>
<%--			    		}--%>
<%--		           // });--%>
<%--			//});--%>
<%--    		return false;--%>
<%--    	});--%>
<%--        //$('#frmRegistrasi1').submit(function(){--%>
<%--        //    $('#btnSubmit1').html('Silahkan Tunggu...').prop('disabled',false).css('cursor','wait');--%>
<%--        //});--%>
<%--        // function ingat(){--%>
<%--        //     // alert("String");--%>
<%--        //     var t_input = 0;--%>
<%--        //     var arr_input = [];--%>
<%--        //     $("#regis_manual").find('input').each(function(q,val) {--%>
<%--        //         if ($(this).val() == ""){--%>
<%--        //             t_input++;--%>
<%--        //         } else {--%>
<%--        //             arr_input = $(this).val();--%>
<%--        //         }--%>
<%--        //     });--%>
<%--        //     if(t_input > 0) {--%>
<%--        //         alert('Registrasi Tidak Boleh Kosong !');--%>
<%--        //     } else {--%>
<%--        //         $.ajax({--%>
<%--        //             url: 'regis_manual',--%>
<%--        //             type: 'POST',--%>
<%--        //             data: {username:$('#regis_manual').val()}--%>
<%--        //         })--%>
<%--        //         // ajax API post--%>
<%--        //     }--%>
<%--        //     var tanda = document.getElementById("tanda");--%>
<%--        //     var centang = document.getElementById("centangbox");--%>
<%--        //     if (centang.check == false){--%>
<%--        //         tanda.display = "block";--%>
<%--        //     }else {--%>
<%--        //         tanda.display = "none";--%>
<%--        //     }--%>
<%--        //     return false;--%>
<%--        // };--%>
<%--    </script>--%>
<%--</body>--%>
<%--</html>--%>
