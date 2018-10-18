var userObj;
var globalListArticole = [];
var comandaCurenta;

$(document).on('pageshow', '#incarc_masini', function() {

	userObj = JSON.parse($('#userbean').text());

	getMasiniNeincarcate();

	onChangeMasini();

	onClickIncarcare();
	onClickRefreshList();

});

function getMasiniNeincarcate() {

	$.ajax({
		type : 'GET',
		url : 'getMasiniNeincarcate',
		data : ({
			filiala : userObj.unitLog
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			$.mobile.loading('hide');
			afisMasiniNeincarcate(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {
			alert(exception);
		}

	});

}

function afisMasiniNeincarcate(listMasini) {

	$('#masini_select').empty();

	if (listMasini.length == 0) {
		$('#masini_select').append($('<option>', {
			value : 0,
			text : "Nu exista masini de incarcat"
		}));

		$('#labelMasini').text("");
		$('#sfIncarcare').hide();
	}

	if (listMasini.length > 0) {

		if (listMasini.length == 1)
			$('#labelMasini')
					.text("Exista 1 masina ce urmeaza a fi incarcata.");

		else
			$('#labelMasini').text(
					"Exista " + listMasini.length
							+ " masini ce urmeaza a fi incarcate.");

		$('#masini_select').append($('<option>', {
			value : 0,
			text : "Selectati o masina"
		}));

		for (var i = 0; i < listMasini.length; i++) {
			$('#masini_select').append($('<option>', {
				value : listMasini[i].nrBorderou,
				text : listMasini[i].nrMasina
			}));
		}

		$("#masini_select").val("0").change();

	}

	$('#masini_select').selectmenu("refresh", true);

}

function onChangeMasini() {

	$('#masini_select').on('change', function() {
		var documentId = $("#masini_select option:selected").val();

		if (documentId == '0') {
			$('#sfIncarcare').hide();
		} else {
			$('#sfIncarcare').show();
		}

	});

}

function onClickIncarcare() {

	$("#sfIncarcare").click(function() {
		setSfarsitIncarcare();
	});

}

function onClickRefreshList() {

	$("#refreshList").click(function() {
		getMasiniNeincarcate();
	});

}

function setSfarsitIncarcare() {

	$.ajax({
		type : 'GET',
		url : 'setSfarsitIncarcare',
		data : ({
			document : $("#masini_select option:selected").val(),
			codSofer : userObj.codPers
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
			getMasiniNeincarcate();
		},
		success : function(data) {
			$.mobile.loading('hide');
		},
		dataType : 'json',
		contentType : 'application/json',

	});

}

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}

function showAlertDialog(tipAlert, mesajAlert) {

	$('#tipAlertM').text(tipAlert);
	$('#textAlertM').text(mesajAlert);
	$.mobile.changePage('#dialogIncarcare', {
		transition : "none"
	});
}
