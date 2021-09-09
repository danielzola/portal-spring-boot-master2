

<!DOCTYPE html>
<html>
<head>

    <title>SEHATI - Sistem Elektronik Hubla Terintegrasi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="SEHATI - Sistem Elektronik Hubla Terintegrasi">
    <meta name="keywords" content="SEHATI - Sistem Elektronik Hubla Terintegrasi">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <link rel="shortcut icon" href="../assets/public/images/favicon/favicon.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="../assets/public/css/style.css">
    <link rel="stylesheet" href="../assets/public/css/responsive.css">
    <link rel="stylesheet" href="../assets/apps/js/plugins/sweetalert/sweetalert.css">
    <link rel="stylesheet" href="../assets/apps/css/dripicon.css" type="text/css">
</head>
<style>
    .body {
        font-family: Poppins, sans-serif;
    }
    .title-modal {
        font-style: normal;
        font-weight: normal;
        font-size: 18px;
        line-height: 34px;
        text-align: center;
        color: #6D6D6D;
        margin-bottom: 16px;
    }
    .button-back {
        background: #FF5C75;
        border: 1px solid #FF5C75;
        color: #ffffff;
        box-sizing: border-box;
        border-radius: 4px;
    }
    .button-register {
        background: #5D8BD0;
        border: 1px solid #5D8BD0;
        color: #ffffff;
        box-sizing: border-box;
        border-radius: 4px;
    }
    .input.largerCheckbox {
        width: 15px;
        height: 15px;
    }
    input[type=file]::file-selector-button {
        padding: .2em .4em;
        transition: 1s;
        background: #5D8BD0;
        border: 1px solid #5D8BD0;
        color: #ffffff;
        box-sizing: border-box;
        border-radius: 4px;
    }
    input[type=file]::file-selector-button:hover {
        background-color: #81ecec;
        border: 2px solid #00cec9;
    }
    .thm-btn:hover{
        color:#fff !important;
    }
    .btn-circle {
        width: 30px;
        height: 30px;
        padding: 6px 0px;
        border-radius: 15px;
        text-align: center;
        font-size: 12px;
        line-height: 1.42857;
    }
    .required > label::after {
        content: " * ";
        color: red;
    }
    label.required::after {
        content: " * ";
        color: red;
    }
    legend {
        font-size: 1.3rem !important;
        font-weight: bold;
        margin-bottom: 25px
    }
    .select2-container .select2-selection--single {
        height: 38px !important;
    }
    .select2-container--default .select2-selection--single .select2-selection__rendered {
        line-height: 37px !important;
    }
</style>

<body>
<div class="preloader" style="z-index:99999999 !important;"></div>
<div class="page-wrapper">
    <header class="site-header header-one">
        <nav class="navbar navbar-expand-lg navbar-light header-navigation stricky">
            <div class="container clearfix">
                <div class="logo-box clearfix">
                    <div class="navbar-brand" style="color:#fff;">
                        <div class="row">
                            <div class="col-5"><img id="header-logo" src="../assets/public/images/logo/logo-kemenhub.png" class="main-logo header-logo"></div>
                            <div class="col-6 header-title">SEHATI 2.0</div>
                        </div>
                    </div>
                    <button class="menu-toggler" data-target=".main-navigation" style="padding-top:20px; display:none;">
                        <span class="fa fa-bars"></span>
                    </button>
                </div>
                <div class="main-navigation div-only-desktop">
                    <ul class="navigation-box one-page-scroll-menu right">
                        <li class="scrollToLink">
                            <a href="../portal/">Beranda</a>
                        </li>
                        <li class="scrollToLink">
                            <a href="https://sehatidev.hubla.dephub.go.id/sso/authenticationendpoint/login_client_id%3Db4mC">Login</a>
                        </li>
                        <li class="scrollToLink">
                            <a href="../forgotpass/">Lupa Password</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <section class="inner-banner" style="padding-bottom:60px !important;">
        <div class="container">
            <h2 class="inner-banner__title">Registrasi</h2>
            <ul class="thm-breadcrumb">
                <li class="thm-breadcrumb__item current">
                    <a href="#" class="thm-breadcrumb__link">Registrasi Akun SEHATI</a>
                </li>
            </ul>
        </div>
    </section>
    <section class="blog-details">
        <div class="container" style="margin-top:-50px !important;">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8" id="contentRegistrasi">
                    <p class="blog-details__text text-center">Silakan masukkan NIB atau NIP Anda pada form dibawah ini untuk mendapatkan Akun SEHATI</p>
                    <br>
                    <form id="frmRegistrasi" name="frmRegistrasi" class="reply-form">
                        <div class="row">
                            <div class="col-lg-12">
                                <input id="txtUsername" name="txtUsername" type="text" placeholder="NIB atau NIP" class="reply-form__field">
                            </div>
                            <div class="col-lg-12">
                                <button id="btnSubmit" style="outline:none !important; cursor:pointer; width:100%;" class="reply-form__btn thm-btn" type="submit">Registrasi</button>
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
                            <a href="../portal/" class="footer-widget__logo" style="color:#fff;">SEHATI 2.0<br>Sistem Elektronik Hubla Terintegrasi</a>
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

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-body">
                <h2 class="title-modal">
                    Silakan isi form registrasi dibawah ini untuk mendapatkan Akun SEHATI
                </h2>

                <form id="regis_manual" method="post" novalidate>
                    <input type="hidden" name="file_ijin" id="file_ijin">
                    <fieldset>
                        <legend>Data Perusahaan</legend>

                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="NamaPerseroan">Nama Perseroan</label>
                                    <input type="text" name="nama_perseroan" class="form-control"
                                           id="NamaPerseroan" tabindex="1" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputNpwp">NPWP Perseroan</label>
                                    <input type="text" name="npwp_perseroan" class="form-control" id="inputNpwp" tabindex="3" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputTglTerbit">Tgl. Terbit NIB</label>
                                    <input type="text" name="tgl_terbit_nib" class="form-control datepicker"
                                           id="inputTglTerbit" tabindex="5" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputTotalModal">Total Modal Dasar</label>
                                    <input type="text" name="total_modal_dasar" class="form-control numeric"
                                           id="inputTotalModal" tabindex="7" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="AlamatPerseroan">Alamat Perseroan</label>
                                    <textarea class="form-control" id="AlamatPerseroan" name="alamat_perseroan" rows="4"
                                              style="height:133px" tabindex="9" required></textarea>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="KodeposPerseroan">Kode Pos Perseroan</label>
                                    <input type="text" class="form-control" name="kode_pos_perseroan"
                                           id="KodeposPerseroan" tabindex="16" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="NoTelponPerseroan">No. Telepon Perseroan</label>
                                    <input type="text" class="form-control" name="nomor_telpon_perseroan"
                                           id="NoTelponPerseroan" tabindex="17" required>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="inputNib">NIB</label>
                                    <input type="text" class="form-control" name="nib"
                                           id="inputNib" tabindex="2" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputStatus">Status NIB</label>
                                    <input type="text" class="form-control" name="status_nib"
                                           id="inputStatus" tabindex="4" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputTglPerubahan">Tgl. Perubahan NIB</label>
                                    <input type="text" class="form-control datepicker" name="tgl_perubahan_nib"
                                           id="inputTglPerubahan" tabindex="6" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="inputModalTempat">Total modal Ditempatkan</label>
                                    <input type="text" class="form-control" name="total_modal_ditempatkan"
                                           id="inputModalTempat" tabindex="8" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="form-group required">
                                            <label for="RTPerseroan">RT</label>
                                            <input type="text" name="rt" class="form-control"
                                                   id="RTPerseroan" tabindex="10" required>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group required">
                                            <label for="RWPerseroan">RW</label>
                                            <input type="text" name="rw" class="form-control"
                                                   id="RWPerseroan" tabindex="11" required>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group required">
                                    <label for="ProvinsiPerseroan">Provinsi Perseroan</label>
                                    <select name="provinsi_perseroan" id="ProvinsiPerseroan" class="form-control" tabindex="12" required>
                                        <option value="">- Pilih Provinsi -</option>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="KotaKabupaten">Kota/Kabupaten Perseroan</label>
                                    <select name="kabkota_perseroan" id="KotaKabupaten" class="form-control" tabindex="13" required>
                                        <option value="">- Pilih Kota/Kabupaten -</option>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="KecamatanPerseroan">Kecamatan Perseroan</label>
                                    <select name="kecamatan_perseroan" id="KecamatanPerseroan" class="form-control" tabindex="14" required>
                                        <option value="">- Pilih Kecamatan -</option>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="KelurahanPerseroan">Kelurahan Perseroan</label>
                                    <select name="kelurahan_perseroan" id="KelurahanPerseroan" class="form-control" tabindex="15" required>
                                        <option value="">- Pilih Kelurahan -</option>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <div class="float-left">
                        <%--@declare id="inputpemegangsaham"--%><label for="inputPemegangSaham" class="required">Pemegang Saham</label>
                    </div>
                    <div class="float-right">
                        <button type="button" class="btn btn-primary btn-popup-mt" data-id="pemegangSaham"
                                data-popup-title="Pemegang Saham"><i class="fa fa-plus"></i> Tambah</button>
                    </div>
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <table class="table table-striped table-hover mt-table-field">
                                <thead class="text-center" style="background-color: aliceblue">
                                <tr>
                                    <th scope="col" width="25%">Nama Pemegang Saham</th>
                                    <th scope="col" width="20%">Jabatan</th>
                                    <th scope="col" width="20%">No. NPWP</th>
                                    <th scope="col">Alamat</th>
                                    <th scope="col">&nbsp;</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>

                    <div class="float-left">
                        <%--@declare id="inputpenanggungjawab"--%><label for="inputPenanggungJawab">Penanggung Jawab</label>
                    </div>
                    <div class="float-right">
                        <button type="button" class="btn btn-primary btn-popup-mt" data-id="penanggungJawab"
                                data-popup-title="Penanggung Jawab"><i class="fa fa-plus"></i> Tambah</button>
                    </div>
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <table class="table table-striped table-hover mt-table-field">
                                <thead class="text-center" style="background-color: aliceblue">
                                <tr>
                                    <th scope="col" width="25%">Nama Penanggung Jawab</th>
                                    <th scope="col" width="20%">Jabatan</th>
                                    <th scope="col" width="20%">No. NPWP</th>
                                    <th scope="col">Alamat</th>
                                    <th scope="col">&nbsp;</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>

                    <div class="form-group required">
                        <label for="uploadNib">Upload Izin NIB</label><br/>
                        <input type="file" id="uploadNib" class="form-control-file"
                               name="file_izin" accept="application/pdf,image/*" required>
                        <div class="invalid-feedback"></div>
                    </div>

                    <br/>

                    <fieldset>
                        <legend>Data User Proses</legend>

                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="NamaUser">Nama User Proses</label>
                                    <input id="NamaUser" type="text" class="form-control" name="nama_user_proses" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="DaerahUser">Daerah ID User Proses</label>
                                    <input id="DaerahUser" type="text" class="form-control" name="daerah_id_user_proses" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="AlamatUser">Alamat User Proses</label>
                                    <textarea id="AlamatUser" class="form-control" name="alamat_user_proses" rows="4" required></textarea>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="EmailUser">Email User Proses</label>
                                    <input id="EmailUser" type="email" class="form-control" name="email_user_proses" required>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-group required">
                                    <label for="HpUser">No. HP User Proses</label>
                                    <input id="HpUser" type="text" class="form-control" name="hp_user_proses" required>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <!-- <p class="font-weight-bold" style="margin-top: 60px;">Data User Proses</p> -->
                    <div class="form-group form-check col-md-8 offset-md-2">
                        <input class="form-check-input largerCheckbox" name="term" type="checkbox" id="tanda" required>
                        <label class="form-check-label text-justify" for="tanda">
                            Saya menyatakan bahwa data yang saya masukkan adalah benar dan bersedia bertanggung jawab
                            serta menerima sanksi sesuai hukum yang berlaku di Indonesia apabila data yang saya masukkan tidak benar.
                        </label>
                    </div>

                    <!-- <p class="text-center text-danger">Silahkan Centang Box Disclaimer</p> -->

                    <div class="form-group text-center">
                        <button id="kembali" class="btn btn-danger" data-dismiss="modal">Kembali</button>
                        <!-- <button id="daftar" class="btn btn-primary" onclick="ingat()">Registrasi</button> -->
                        <button type="submit" id="btn-register" class="btn btn-primary">Registrasi</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-popup-mt" tabindex="-1" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-body">
                <form id="formPemegang">
                    <fieldset>
                        <legend id="title-legend">Tambah Pemegang Saham</legend>

                        <div class="form-group required">
                            <label for="PS-Nama">Nama</label>
                            <input type="text" name="mt_nama" class="form-control" id="PS-Nama" required>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Jabatan">Jabatan</label>
                            <input type="text" name="mt_jabatan" class="form-control" id="PS-Jabatan" required>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Npwp">NPWP</label>
                            <input type="text" name="mt_npwp" class="form-control" id="PS-Npwp" required>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Email">Email</label>
                            <input type="email" name="mt_email" class="form-control" id="PS-Email" required>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Alamat">Alamat</label>
                            <textarea name="mt_alamat" class="form-control" id="PS-Alamat" required></textarea>
                            <div class="invalid-feedback"></div>
                        </div>

                        <!-- <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="PS-Rt">RT</label>
                                    <input type="text" name="mt_rt" class="form-control" id="PS-Rt" required>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group required">
                                    <label for="PS-Rw">RW</label>
                                    <input type="text" name="mt_rw" class="form-control" id="PS-Rw" required>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Provinsi">Provinsi</label>
                            <select name="mt_provinsi" id="PS-Provinsi" class="form-control" required>
                                <option value="">- Pilih Provinsi -</option>
                            </select>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-KotaKabupaten">Kota/Kabupaten</label>
                            <select name="mt_kabkota" id="PS-KotaKabupaten" class="form-control" required>
                                <option value="">- Pilih Kota/Kabupaten -</option>
                            </select>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Kecamatan">Kecamatan</label>
                            <select name="mt_kecamatan" id="PS-Kecamatan" class="form-control" required>
                                <option value="">- Pilih Kecamatan -</option>
                            </select>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group required">
                            <label for="PS-Kelurahan">Kelurahan</label>
                            <select name="mt_kelurahan" id="PS-Kelurahan" class="form-control" required>
                                <option value="">- Pilih Kelurahan -</option>
                            </select>
                            <div class="invalid-feedback"></div>
                        </div> -->

                        <div class="form-group text-center">
                            <button id="mt-kembali" class="btn btn-danger" data-dismiss="modal">Batal</button>
                            <button type="submit" id="mt-tambah" class="btn btn-primary">Simpan</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<a href="#" data-target="html" class="scroll-to-target scroll-to-top"><i class="fa fa-angle-up"></i></a>
<script src="https://www.google.com/recaptcha/api.js?render=6LdTQeMUAAAAAOBQHYlOX1kH7LsQLfxknChH5ZGD"></script>
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
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<!-- <script src="./mt-table-field.js"></script>
<script src="./mt-popup-field.js"></script>
<script src="./req-validation.js"></script>
<script src="./wilayah.js"></script> -->
<script>
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function() {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });

    $(document).on('hidden.bs.modal', '.modal', function () {
        $('.modal:visible').length && $(document.body).addClass('modal-open');
    });

    $('#frmRegistrasi').submit(function(){
        $('#btnSubmit').html('Silahkan Tunggu...').prop('disabled',true).css('cursor','wait');
        //grecaptcha.ready(function() {
        //grecaptcha.execute('6LdTQeMUAAAAAJH-PPa-MlEWSQgv0NPM2DfmDz_o',{action:'login'}).then(function(token) {
        if($('#txtUsername').val().length > 0){
            $.ajax({
                url: 'register',
                type: 'POST',
                data: {username:$('#txtUsername').val()}
            }).done(function(data){
                var result = JSON.parse(data);

                if (result.code.toString()=='01') {
                    $('#contentRegistrasi').html('<h3 class="blog-blog-details__title text-center"><strong>Registrasi Berhasil</strong></h3><p class="blog-details__text text-center">'+result.message.toString()+'</p>');
                } else {
                    $('#btnSubmit').html('Registrasi').prop('disabled',false).css('cursor','default');
                    swal({
                        title: "Perhatian",
                        text: result.message.toString(),
                        type: "warning",
                        showCancelButton: false
                    });
                }
            }).fail(function( jqXHR, textStatus ) {
                $('#btnSubmit').html('Registrasi').prop('disabled', false).css('cursor','default');
                swal({
                    title: "Perhatian",
                    text: "NIB belum teregistrasi otomatis. Silahkan Registrasi Manual.",
                    type: "warning",
                    confirmButtonColor: "#134563",
                    confirmButtonText: "OK",
                    closeOnConfirm: false,
                    html: false
                },  function(){
                    setToken();

                    $.each(ids.provinsiId, function(index, val) {
                        getWilayah('/apis/data/provinsi', val, {
                            id: "KODE_PROVINSI",
                            value: "NAMA_PROVINSI"
                        }, {}, '- Pilih Provinsi -');
                    })
                    $('#myModal').modal('show');
                    swal.close();
                });
            });
        }else{
            $('#btnSubmit').html('Registrasi').prop('disabled',false).css('cursor','default');
            swal({
                title: "Perhatian",
                text: "Silakan masukkan NIB atau NIP terlebih dahulu",
                type: "warning",
                showCancelButton: false
            });
        }
        // });
        //});
        return false;
    });
    //$('#frmRegistrasi1').submit(function(){
    //    $('#btnSubmit1').html('Silahkan Tunggu...').prop('disabled',false).css('cursor','wait');
    //});
    // function ingat(){
    //     // alert("String");
    //     var t_input = 0;
    //     var arr_input = [];
    //     $("#regis_manual").find('input').each(function(q,val) {
    //         if ($(this).val() == ""){
    //             t_input++;
    //         } else {
    //             arr_input = $(this).val();
    //         }
    //     });
    //     if(t_input > 0) {
    //         alert('Registrasi Tidak Boleh Kosong !');
    //     } else {
    //         $.ajax({
    //             url: 'regis_manual',
    //             type: 'POST',
    //             data: {username:$('#regis_manual').val()}
    //         })
    //         // ajax API post
    //     }
    //     var tanda = document.getElementById("tanda");
    //     var centang = document.getElementById("centangbox");
    //     if (centang.check == false){
    //         tanda.display = "block";
    //     }else {
    //         tanda.display = "none";
    //     }
    //     return false;
    // };

    $(document).on("submit", "#regis_manual", function(event) {
        event.preventDefault();

        let regisFormData = new FormData($(this)[0]);

        let dataPerseroan = {
            nama_perseroan: regisFormData.get('nama_perseroan'),
            npwp_perseroan: regisFormData.get('npwp_perseroan'),
            tgl_terbit_nib: regisFormData.get('tgl_terbit_nib'),
            total_modal_dasar: regisFormData.get('total_modal_dasar'),
            alamat_perseroan: regisFormData.get('alamat_perseroan'),
            kode_pos_perseroan: regisFormData.get('kode_pos_perseroan'),
            nomor_telpon_perseroan: regisFormData.get('nomor_telpon_perseroan'),
            nib: regisFormData.get('nib'),
            status_nib: regisFormData.get('status_nib'),
            tgl_perubahan_nib: regisFormData.get('tgl_perubahan_nib'),
            total_modal_ditempatkan: regisFormData.get('total_modal_ditempatkan'),
            propinsi_perseroan: regisFormData.get('provinsi_perseroan'),
            kabkota_perseroan: regisFormData.get('kabkota_perseroan'),
            kecamatan_perseroan: regisFormData.get('kecamatan_perseroan'),
            kelurahan_perseroan: regisFormData.get('kelurahan_perseroan'),
            rt_rw_perseroan: regisFormData.get('rt') +'/'+ regisFormData.get('rw'),
            nama_user_proses: regisFormData.get('nama_user_proses'),
            daerah_id_user_proses: regisFormData.get('daerah_id_user_proses'),
            alamat_user_proses: regisFormData.get('alamat_user_proses'),
            email_user_proses: regisFormData.get('email_user_proses'),
            hp_user_proses: regisFormData.get('hp_user_proses'),
            pemegang_saham: dataPemegang,
            penanggung_jwb: dataPenanggung,
            file_ijin: regisFormData.get('file_ijin'),
        };

        console.log(dataPerseroan);

        function createFormData(formData, key, data) {
            if (data === Object(data) || Array.isArray(data)) {
                for (var i in data) {
                    createFormData(formData, key + '[' + i + ']', data[i]);
                }
            } else {
                formData.append(key, data);
            }
        }

        // createFormData(regisFormData, 'pemegang_saham', dataPemegang);
        // createFormData(regisFormData, 'penanggung_jwb', dataPenanggung);

        // regisFormData.append('pemegang_saham', JSON.stringify(dataPemegang));
        // regisFormData.append('penanggung_jwb', JSON.stringify(dataPenanggung));
        // regisFormData.append('rt_rw_perseroan', regisFormData.get('rt') +'/'+ regisFormData.get('rw'));

        // regisFormData.delete('rt');
        // regisFormData.delete('rw');
        // regisFormData.delete('term');
        // regisFormData.delete('file_izin');

        // regisFormDataObj = objectifyForm(regisFormData);
        // regisFormDataObj["pemegang_saham"] = dataPemegang;
        // regisFormDataObj["penanggung_jwb"] = dataPenanggung;

        // console.log(objectifyForm(regisFormData));
        // console.log(regisFormDataObj);

        let theToken = localStorage.getItem('hublaToken');

        // call registerNib
        $.ajax({
            url: 'https://sehatidev.hubla.dephub.go.id/apis/sso/v1/registerNib',
            type: 'POST',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + theToken);
                //xhr.setRequestHeader('Authorization', `Bearer ${token}`);
            },
            cache: false,
            // processData: false,
            contentType: "application/json; charset=utf-8",
            async: false,
            data: JSON.stringify(dataPerseroan),
        })
            .done(function(res) {
                if (res.status == 1) {
                    swal({
                        title: "Registrasi Berhasil",
                        text: "Silahkan login ke aplikasi menggunakan nomor NIB anda untuk melanjutkan",
                        type: "success",
                        confirmButtonColor: "#134563",
                        confirmButtonText: "OK",
                        closeOnConfirm: false,
                        html: false
                    },  function(){
                        location.reload();

                        swal.close();
                    });
                }
            })
            .fail(function() {
                swal({
                    title: "Perhatian",
                    text: "Proses registrasi belum berhasil, silahkan coba beberapa saat lagi",
                    type: "error",
                    confirmButtonColor: "#134563",
                    confirmButtonText: "OK",
                    closeOnConfirm: false,
                    html: false
                });
            });

    })

    function objectifyForm(formData) {
        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });

        return JSON.stringify(object);
    }

</script>
<script>
    var baseUrlHubla = "https://sehatidev.hubla.dephub.go.id";
    var version = "";
    var ids = {
        provinsiId: ['ProvinsiPerseroan', 'PS-Provinsi'],
        kotaKabId: ['KotaKabupaten', 'PS-KotaKabupaten'],
        kecamatanId: ['KecamatanPerseroan', 'PS-Kecamatan'],
        kelurahanId: ['KelurahanPerseroan', 'PS-Kelurahan'],
    };

    let mtTableClassName = "mt-table-field";
    let mtButton;
    let countTbody;

    let dataPemegang = [];
    let dataPenanggung = [];

    let popup = $('#modal-popup-mt');

    // get a reference to the file input
    const fileInput = document.getElementById("uploadNib");

    // listen for the change event so we can capture the file
    fileInput.addEventListener("change", (e) => {
        // get a reference to the file
        const file = e.target.files[0];

        // encode the file using the FileReader API
        const reader = new FileReader();
        reader.onloadend = () => {
            // use a regex to remove data url part
            const base64String = reader.result
                .replace("data:", "")
                .replace(/^.+,/, "");

            // log to console
            // logs wL2dvYWwgbW9yZ...
            console.log(base64String);
            document.getElementById("file_ijin").value = base64String;
        };
        reader.readAsDataURL(file);
    });

    $(document).on('click', '.btn-popup-mt', function(e) {

        mtButton = $(this);

        // Set title
        popup.find("#title-legend").text($(this).data('popup-title'));
        popup.modal('show');
    });

    $(document).on('submit', '#formPemegang', function(e) {
        e.preventDefault();

        let dataForm = new FormData($(this)[0]);
        let idButton = mtButton.data('id');
        let varName;
        let aliasPrefix;

        if (idButton == 'pemegangSaham') {
            varName = 'pemegang_saham';
        } else if (idButton == 'penanggungJawab') {
            varName = 'penanggung_jwb';
        };

        let data = {
            ['nama_'+ varName]: dataForm.get("mt_nama"),
            ['jabatan_'+ varName]: dataForm.get("mt_jabatan"),
            ['npwp_'+ varName]: dataForm.get("mt_npwp"),
            ['email_'+ varName]: dataForm.get("mt_email"),
            ['alamat_'+ varName]: dataForm.get("mt_alamat"),
        };

        if (idButton == 'pemegangSaham') {
            dataPemegang.push(data);
            aliasPrefix = 'ps';
        } else if (idButton == 'penanggungJawab') {
            dataPenanggung.push(data);
            aliasPrefix = 'pj';
        };

        let mt_tbody = '<tr>';
        let ps_nama_val = $(this).find('#PS-Nama').val();
        let ps_jabatan_val = $(this).find('#PS-Jabatan').val();
        let ps_npwp_val = $(this).find('#PS-Npwp').val();
        let ps_alamat_val = $(this).find('#PS-Alamat').val();
        mt_tbody += '<td class="'+ aliasPrefix +'-nama">'+ ps_nama_val +'</td>';
        mt_tbody += '<td>'+ ps_jabatan_val +'</td>';
        mt_tbody += '<td>'+ ps_npwp_val +'</td>';
        mt_tbody += '<td>'+ ps_alamat_val +'</td>';
        mt_tbody += '<td>';
        mt_tbody += '<button type="button" class="btn btn-danger btn-delete-mt btn-xs" data-alias="'+ aliasPrefix +'">';
        mt_tbody += '<i class="fa fa-minus"></i></button>';
        mt_tbody += '</td>';
        mt_tbody += '</tr>';

        mtButton.parent().next().find('.'+ mtTableClassName +' > tbody:last').append(mt_tbody);
        popup.modal('hide');

        $(this)[0].reset();
    })

    $(document).on('click', '.btn-delete-mt', function(e) {
        e.preventDefault();

        let alias = $(this).data('alias');
        let row = $(this).parent().parent();
        let nama = row.find('.'+ alias +'-nama');

        if (alias == 'ps') {
            let index = findIndexByKeyValue(dataPemegang, 'nama_pemegang_saham', nama.text());
            dataPemegang.splice(index, 1);

        } else if (alias == 'pj') {
            let index = findIndexByKeyValue(dataPenanggung, 'nama_penanggung_jwb', nama.text());
            dataPenanggung.splice(index, 1);

        }

        row.remove();
    })

    function findIndexByKeyValue(arraytosearch, key, valuetosearch) {
        for (var i = 0; i < arraytosearch.length; i++) {
            if (arraytosearch[i][key] == valuetosearch) {
                return i;
            }
        }

        return null;
    }
    $(document).ready(function() {
        // Set message error by label
        $(".invalid-feedback").each(function(val, key) {
            let label = $(this).parent().find("label").text();
            $(this).text(label +' mohon diisi.');
        })
    });

    $(document).on("click", "#btn-register", function(e) {
        var form = $("#regis_manual")[0];
        var isValid = form.checkValidity();
        if (!isValid) {
            e.preventDefault();
            e.stopPropagation();
        }
        form.classList.add('was-validated');
    });

    function setToken() {
        let token = localStorage.getItem('hublaToken');
        let expiredToken = new Date(localStorage.getItem('hublaExpiresIn'));
        let now = new Date;

        $.ajax({
            url: '/token/generate',
            type: 'post',
            dataType: 'json',
            success: function(res) {
                let expiredDate = new Date();
                expiredDate.setSeconds(expiredDate.getSeconds() + (res.data.expires_in - 10));

                localStorage.setItem('hublaToken', res.data.access_token);
                localStorage.setItem('hublaExpiresIn', expiredDate);
            }
        })

        // Aktifkan nanti
        // if (
        //     !token
        //     || (expiredToken.getTime() < now.getTime())
        // ) {
        //
        // }

        // // Nonaktifkan nanti
        // let expiredDate = new Date();
        // expiredDate.setSeconds(expiredDate.getSeconds() + 3600);
        //
        // localStorage.setItem('hublaToken', '8ff58092-a885-355d-8e4c-50009e119f48');
        // localStorage.setItem('hublaExpiresIn', expiredDate);
        //
        // $.each(ids.provinsiId, function(index, val) {
        //     getWilayah('/apis/data/provinsi', val, {
        //         id: "KODE_PROVINSI",
        //         value: "NAMA_PROVINSI"
        //     }, {}, '- Pilih Provinsi -');
        // })
    }

    $(document).ready(function() {
        // setToken();
        //
        // // INIT PROVINSI
        // $.each(ids.provinsiId, function(index, val) {
        //     getWilayah('/apis/data/provinsi', val, {
        //         id: "KODE_PROVINSI",
        //         value: "NAMA_PROVINSI"
        //     }, {}, '- Pilih Provinsi -');
        // })
    })

    // INIT KOTA KABUPATEN
    $.each(ids.kotaKabId, function(index, val) {
        $(document).on('change', '#'+ ids.provinsiId[index], function(e) {
            let idProvinsi = $(this).val();

            if (idProvinsi)
                getWilayah('/apis/data'+ version +'/kabkota', val, {
                    id: "KODE_KABKOTA",
                    value: "NAMA_KABKOTA"
                }, {p: idProvinsi}, '- Pilih Kota/Kabupaten -');
            else
                setDropdown({}, val, '- Pilih Kota/Kabupaten -');
        })
    })

    // INIT KECAMATAN
    $.each(ids.kecamatanId, function(index, val) {
        $(document).on('change', '#'+ ids.kotaKabId[index], function(e) {
            let idKabupaten = $(this).val();

            if (idKabupaten)
                getWilayah('/apis/data'+ version +'/kecamatan', val, {
                    id: "KODE_KECAMATAN",
                    value: "NAMA_KECAMATAN"
                }, {p: idKabupaten}, '- Pilih Kecamatan -');
            else
                setDropdown({}, val, '- Pilih Kecamatan -');
        })
    })

    // INIT KELURAHAN
    $.each(ids.kelurahanId, function(index, val) {
        $(document).on('change', '#'+ ids.kecamatanId[index], function(e) {
            let idKecamatan = $(this).val();

            //     if (idKecamatan)
            //         getWilayah('/apis/data'+ version +'/kelurahan', val, {
            //             id: "KODE_KELURAHAN",
            //             value: "NAMA_KELURAHAN"
            //         }, {p: idKecamatan}, '- Pilih Kelurahan -');
            //     else
            //         setDropdown({}, val, '- Pilih Kelurahan -');

            if (idKecamatan) {
                if ($('#'+ val).data('select2'))
                    $('#'+ val).select2('destroy');

                let theToken = localStorage.getItem('hublaToken');

                console.log(theToken);

                $('#'+ val).select2({
                    minimumInputLength: 3,
                    allowClear: true,
                    placeholder: 'Cari Nama Kelurahan',
                    dropdownParent: $('#'+ val).parent(),
                    height: '37px',
                    ajax: {
                        dataType: 'json',
                        url: baseUrlHubla + '/apis/data'+ version +'/kelurahan',
                        delay: 800,
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader('Authorization', 'Bearer ' + theToken);
                            //xhr.setRequestHeader('Authorization', `Bearer ${token}`);
                        },
                        data: function(params) {
                            return {
                                q: params.term,
                                p: idKecamatan
                            }
                        },
                        processResults: function (res, page) {
                            let kelurahans = [];

                            $.each(res.data, function(key, val) {
                                kelurahans[key] = {id: val.KODE_KELURAHAN, text: val.NAMA_KELURAHAN};
                            });

                            console.log(kelurahans)

                            return {
                                results: kelurahans
                            };
                        },
                    }
                });
            }
        })
    })


    function getWilayah(path, idDropdown, propSet, params = {}, label = '- Pilih -') {
        // let baseUrlHubla = "https://sehatitest.hubla.dephub.go.id";

        let fullUrl = baseUrlHubla + path;
        let theToken = localStorage.getItem('hublaToken');

        $.ajax({
            url: fullUrl,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + theToken);
                //xhr.setRequestHeader('Authorization', `Bearer ${token}`);
            },
            data: params
        })
            .done(function(res) {
                let data = [];

                $.each(res.data, function(key, val) {
                    let id = val[propSet.id];
                    let value = val[propSet.value];

                    data[id] = value;
                });

                setDropdown(data, idDropdown, label);
            })
            .fail(function() {
                console.log("error");
            });
    }

    // START SET DROPDOWN
    function setDropdown(data, id, label = '- Pilih -', $default = null) {
        let dropdown = $('#'+ id);
        dropdown.empty();

        dropdown.append('<option value="">'+ label +'</option>');
        $.each(data, function(index, val) {

            if (typeof val === 'object') {
                if (val.length == 0) return;

                let opts = '<optgroup label="'+ index +'">';

                $.each(val, function(index1, val1) {
                    let selected2 = '';
                    if ($default == index) {
                        selected2 = 'selected';
                    }

                    opts += '<option value="'+ index1 +'" '+ selected2 +'>'+ val1 +'</option>';
                })

                opts += '</optgroup>';

                dropdown.append(opts);

            } else {
                let selected = '';
                if ($default == index) {
                    selected = 'selected';
                }

                if (val)
                    dropdown.append('<option value="'+ index +'" '+ selected +'>'+ val +'</option>');
            }
        });
        dropdown.trigger('change');
    }
    // END SET DROPDOWN
</script>
</body>
</html>
