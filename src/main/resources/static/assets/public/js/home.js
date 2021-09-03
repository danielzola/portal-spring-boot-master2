$(document).ready(function(){
	$('#dashboard-map-load').load('map');
	$('#filePDF').fileupload({
		url: 'files/upload/verifypdf',
		dataType: 'json',
		start: function (e) {
			$('#choosePDF').val('Loading... 0%');
		},
		done: function (e, data) {
			if(data.result.status=='001'){
				$('#pathPDF').val(data.result.location);
				$('#choosePDF').val(data.result.name);
				$('#submitVerifyPDF').prop('disabled',false);
				$('#frmVerifyPDF').submit();
			}else{
				swal('Perhatian', data.result.message.toString(), 'warning');
				$('#pathPDF').val('');
				$('#choosePDF').val('');
				$('#submitVerifyPDF').prop('disabled',true);
			}
		},
		progressall: function (e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#choosePDF').val('Loading... '+progress+'%');
		}
	}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
});