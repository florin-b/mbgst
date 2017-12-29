$(document).on('pagecreate', '#crearecomanda', function() {

	initDateField();

});

function initDateField() {

	$("#dataLivrare").datepicker({

		dateFormat : "dd-mm-yy"

	});

}
