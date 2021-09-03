$(window).on("load", function(){
	  $('header').load('/header');
	  $('.more-opt-panel').load('/options');
	  $('[data-rel="tooltip"]').tooltip({ container: 'body' });
	  $("body").on("click", ".sidemenu-opener", function(){
            $(".console-sidemenu").toggleClass('active');
      });
      
      // Sidemenu Dropdown
      $(".console-sidemenu ul ul").parent().addClass("menu-item-has-children");
      $(".console-sidemenu ul li.menu-item-has-children > a").on("click", function() {
            $(this).parent().toggleClass("active").siblings().removeClass("active");
            $(this).next("ul").slideToggle();
            $(this).parent().siblings().find("ul").slideUp();
            return false;
      });

      let selectCurrent = $("#toparea").attr('class');
      $("#select_toparea").val(selectCurrent).change();   
     
      $(document).on("click", ".content-area > div", function(){
            $(".more-opt-panel").removeClass('active');
      });
      // ========== Sidemenu Functions Ends ============ //

      $.LoadingOverlay("hide");
      
      // ========== More Option Panel Functions ============ //
      $("body").on("click", ".more-opt-open", function(){
            $(".more-opt-panel").toggleClass('active');
      });
      
            // ========== Panel Switch Full Screen ============ //
      $("body").on("click",".switch-full", function(){
            let parent = $(this).parents('.console-panel');
            parent.toggleClass('fullscreen');
            parent.hasClass('fullscreen') ? $(this).find("i").attr('class','icon dripicons-contract-2') : $(this).find("i").attr('class','icon dripicons-expand-2');
            parent.hasClass('fullscreen') ? $(".content-area").addClass('fullscreen-active') : $(".content-area").removeClass('fullscreen-active');
            return false;
      });


      // ========== Collapse Panel ============ //
      $("body").on("click",".collapse-panel", function(){
            $(this).attr('class','expand-panel').find('i').attr('class','icon dripicons-chevron-down');
            let parent = $(this).parents('.grid-stack-item');
            let currentHeight = $(parent).attr('data-gs-height');
            parent.attr('data-heighthistory',currentHeight);

            let minHeight = $(parent).attr('data-gs-min-height');
            if(parent.attr('data-gs-min-height')){
                  parent.attr('data-minheighthistory',minHeight);
            }

            var grid = $('.grid-stack').data('gridstack');
            grid.minHeight(parent , '', 3);
            grid.resize(parent , '', 3);
            grid.resizable(parent,false);
            parent.find('.console-panel-body').slideUp();
            parent.find('.console-footer').slideUp();
            return false;
      });

      // ========== Uncollapse Panel ============ //
      $("body").on("click",".expand-panel", function(){
            $(this).attr('class','collapse-panel').find('i').attr('class','icon dripicons-chevron-up');
            let parent = $(this).parents('.grid-stack-item');
            var grid = $('.grid-stack').data('gridstack');
            if (parent.attr('data-heighthistory')){
                  let heightHistory = parseInt(parent.attr('data-heighthistory'));
                  grid.resize(parent , '', heightHistory);
            }else{
                  grid.resize(parent , '', 20);
            }

            if(parent.attr('data-minheighthistory')){
                  let minHeight = parseInt(parent.attr('data-minheighthistory'));
                  grid.minHeight(parent, minHeight)
            }

            grid.resizable(parent,true);
            parent.find('.console-panel-body').slideDown();
            parent.find('.console-footer').slideDown();
            return false;
      });
      
      var options = {
            float: false,
            cellHeight: 10,
            animate:true,
            width:12,
            verticalMargin: 20,
            handle: '.console-panel-header, .console-no-header'

      };
      $('.grid-stack').gridstack(options)
      
      $("body").on("click",".removeWidget", function(){
            let _this = this;
            let parent = $(this).parents('.grid-stack-item');
            $.confirm({
                  theme: 'bootstrap',
                  title: 'Apakah Anda Yakin?',
                  content: 'Anda akan menutup widget ini',
                  buttons: {
                        confirm:{
                              text: 'Tutup',
                              btnClass: 'btn-blue',
                              keys: ['enter', 'shift'],
                              action: function(){
                                    var grid = $('.grid-stack').data('gridstack');
                                    $(_this).parents('.console-panel').slideUp("complete", (function(){
                                          grid.removeWidget(parent, true)
                                    }));
                                    $.alert('Widget Removed!');
                              }
                        },
                        cancel:{
                        	text: 'Batal',
                        	action: function () {
                        	}
                        } 
                  }
            });
            return false;
      });

})

/******************** Liveclock ********************/
function set_datetimeclock(id) {
    var sekarang = new Date();
    var tanggal = sekarang.getDate();
    var hari = sekarang.getDay();
    if (hari === 0){
        hari = 'Minggu';
    }if (hari === 1){
        hari = 'Senin';
    }if (hari === 2){
        hari = 'Selasa';
    }if (hari === 3){
        hari = 'Rabu';
    }if (hari === 4){
        hari = 'Kamis';
    }if (hari === 5){
        hari = 'Jumat';
    }if (hari === 6){
        hari = 'Sabtu';
    }
    var bulan = sekarang.getMonth();
    if (bulan === 0){
        bulan = 'Januari';
    }if (bulan === 1){
        bulan = 'Februari';
    }if (bulan === 2){
        bulan = 'Maret';
    }if (bulan === 3){
        bulan = 'April';
    }if (bulan === 4){
        bulan = 'Mei';
    }if (bulan === 5){
        bulan = 'Juni';
    }if (bulan === 6){
        bulan = 'Juli';
    }if (bulan === 7){
        bulan = 'Agustus';
    }if (bulan === 8){
        bulan = 'September';
    }if (bulan === 9){
        bulan = 'Oktober';
    }if (bulan === 10){
        bulan = 'November';
    }if (bulan === 11){
        bulan = 'Desember';
    }
    var tahun = sekarang.getFullYear();
    var detik = sekarang.getSeconds();
    if (detik < 10){
        detik = '0' + detik;
    }
    var menit = sekarang.getMinutes();
    if (menit < 10){
        menit = '0' + menit;
    }
    var jam = sekarang.getHours();
    if (jam < 10){
        jam = '0' + jam;
    }
	var lv = 'th';
	if(tanggal==1){
		var lv = 'st';
	}
	if(tanggal==1){
		var lv = 'nd';
	}
	if(tanggal==1){
		var lv = 'rd';
	}
	var ampm = 'WIB';
	
	var showdate = '' + hari + ', ' + tanggal  + ' ' + bulan + ' ' + tahun + ' - ' + jam + ':' + menit + ':' + detik + ' '+ampm;

    document.getElementById(id).innerHTML = showdate;
    setTimeout('set_datetimeclock(\'' + id + '\')', 1000);
}

document.addEventListener("DOMContentLoaded", function() {
	var elements = document.getElementsByTagName("input");
	for (var i = 0; i < elements.length; i++) {
		elements[i].oninvalid = function(e) {
			e.target.setCustomValidity("");
			if (!e.target.validity.valid) {
				e.target.setCustomValidity("Field ini harus diisi");
			}
		};
		elements[i].oninput = function(e) {
			e.target.setCustomValidity("");
		};
	}
	var elements = document.getElementsByTagName("select");
	for (var i = 0; i < elements.length; i++) {
		elements[i].oninvalid = function(e) {
			e.target.setCustomValidity("");
			if (!e.target.validity.valid) {
				e.target.setCustomValidity("Field ini harus diisi");
			}
		};
		elements[i].oninput = function(e) {
			e.target.setCustomValidity("");
		};
	}
})
