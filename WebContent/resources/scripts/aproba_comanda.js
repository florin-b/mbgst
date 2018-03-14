var userObj;
var globalListArticole = [];
var comandaCurenta;

$(document).on('pageshow', '#aprobacomanda', function() {

	userObj = JSON.parse($('#userbean').text());

	getComenziAprobare();

});

$(document).on('pagecreate', '#aprobacomanda', function() {

	setColapsibleArticoleListener();

});

function setColapsibleArticoleListener() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var contentId = '#' + data.target.id;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		afisDetaliiArticol(contentId);

	});
}

function afisDetaliiArticol(codArticol) {

	var idContCant = '#idCCant' + codArticol;

	var result_style = document.getElementById(idContCant).style;
	result_style.display = 'table-row';

}

function hideSections() {
	$('#divDateGenAprob').hide();
	$('#divArticoleAprob').hide();
	$('#dateGenTable tbody').remove();
	$('#articoleTable tbody').remove();
	$('#aprobaCmd').hide();
	$('#respingeCmd').hide();

}

function getComenziAprobare() {

	hideSections();

	var cautaCmdAprob = new Object();

	cautaCmdAprob.tipAngajat = userObj.tipAngajat;
	cautaCmdAprob.unitLog = userObj.unitLog;
	cautaCmdAprob.codDepart = userObj.codDepart;
	cautaCmdAprob.codAngajat = userObj.codPers;

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getCmdAprob',
		data : cautaCmdAprob,
		success : function(data) {
			$.mobile.loading('hide');
			afisComenziAprob(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {
			alert(exception);
		}

	});

	$.mobile.loading('hide');

}

function afisComenziAprob(listComenzi) {

	$('#cmd_aprob_select').empty();

	if (listComenzi.length > 0) {
		$('#cmd_aprob_select').append($('<option>', {
			value : 0,
			text : "Selectati o comanda"
		}));

		for (var i = 0; i < listComenzi.length; i++) {
			$('#cmd_aprob_select').append($('<option>', {
				value : listComenzi[i].idComanda,
				text : listComenzi[i].numeClient
			}));
		}

		$("#cmd_aprob_select").val("0").change();

	}

}

$('#cmd_aprob_select').on('change', function() {
	var idComanda = $("#cmd_aprob_select option:selected").val();

	if (idComanda > 0)
		getDetaliiCmdAprob(idComanda);

});

function getDetaliiCmdAprob(idComanda) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getDetCmdAprob',
		data : ({
			idComanda : idComanda
		}),
		success : function(data) {
			afiseazaComandaAprob(data);
		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afiseazaComandaAprob(comanda) {

	var dateGenTable = '#dateGenTable';

	$('#divDateGenAprob').show();
	$('#aprobaCmd').show();
	$('#respingeCmd').show();

	comandaCurenta = new Object();

	comandaCurenta.id = $("#cmd_aprob_select option:selected").val();
	comandaCurenta.nrCmdSap = comanda.idComandaSAP;
	comandaCurenta.codAngaj = userObj.codPers;

	$('#dateGenTable tbody').remove();

	var row = $('<tr></tr>').appendTo(dateGenTable);

	$('<td></td>').attr('style', 'width:15%').text('Nr. comanda SAP').appendTo(
			row);

	$('<td></td>').text(comanda.idComandaSAP).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Valoare').appendTo(row);
	$('<td></td>').text(comanda.valoare).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Data emirere')
			.appendTo(row);
	$('<td></td>').text(comanda.dataEmitere).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Nume client').appendTo(row);
	$('<td></td>').text(comanda.numeClient).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Nume agent').appendTo(row);
	$('<td></td>').text(comanda.numeAgent).appendTo(row);

	globalListArticole = comanda.listArticole;
	afisArtComAprob(comanda.listArticole);

}

function afisArtComAprob(listArticole) {

	$('#articoleTable tbody').remove();

	if (listArticole.length > 0) {
		$('#divArticoleAprob').show();
		$('#opereazaCmdDiv').show();
	}

	for (var i = 0; i < listArticole.length; i++) {

		var bgColor = '';

		if (i % 2 == 0)
			bgColor = '#f2f2f2';

		var mytable = $('<table></table>').attr({
			id : "rowTable" + listArticole[i].codArticol,
			width : "100%",
			border : "0",
			bgcolor : bgColor

		});

		var row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').text((i + 1) + '').appendTo(
				row);
		$('<td></td>').attr('style', 'width:50%').text(
				listArticole[i].numeArticol).appendTo(row);
		$('<td></td>').attr({
			style : 'width:10%;text-align:right;'
		}).text(listArticole[i].cantitate).appendTo(
				row);
		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').text(
				listArticole[i].codArticol).appendTo(row);
		$('<td></td>').attr({
			style : 'width:10%;text-align:right;'
		}).text(
				listArticole[i].pretUnitar).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("RON").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').appendTo(row);
		$('<td></td>').attr({
			style : 'width:10%;text-align:right;'
		}).text(
				listArticole[i].procentReducere).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("%").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:5%').appendTo(row);
		
		$('<td></td>').appendTo(row);

		var tdAddCond = $('<td></td>').attr('style', 'text-align:center').attr('colspan', '2');

		var btnAddConditii = $('<button>', {
			text : 'Adauga conditii',
			position: 'relative',
			margin : '0 auto',
			style : 'width : 70%'
		}).bind('click', {
			articol : listArticole[i]
		}, function(event) {
			setConditiiInputVisibility(event.data.articol);
		}).addClass('ui-btn ui-mini');

		$(btnAddConditii).appendTo(tdAddCond);

		tdAddCond.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondCant' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text('Cantitative')
				.appendTo(row);

		var inputCondCant = $('<input>', {
			id : 'condCant' + listArticole[i].codArticol,
			maxlength : '6',
			size : '6',
			style : 'width:80%'
		});

		var tdCondCantInput = $('<td></td>').appendTo(row);

		$(inputCondCant).appendTo(tdCondCantInput);

		$('<td></td>').attr('style', 'width:3%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondVal' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text('Valorice')
				.appendTo(row);

		var inputCondVal = $('<input>', {
			id : 'condVal' + listArticole[i].codArticol,
			maxlength : '6',
			size : '6',
			style : 'width:80%'
		});

		var tdCondValInput = $('<td></td>').appendTo(row);
		$(inputCondVal).appendTo(tdCondValInput);
		$('<td></td>').attr('style', 'width:3%').text("RON").appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowSaveCond' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').attr('style',
				'text-align:right').appendTo(row);

		$('<td></td>').appendTo(row);
		
		var tdSaveCond = $('<td></td>').attr('style', 'text-align:right').attr(
				'colspan', '2').appendTo(row);

		var btnSaveConditii = $('<button>', {
			text : 'Salveaza conditii',
			class : 'ui-btn ui-mini',
			style : 'width : 70%'
		}).bind('click', {
			articol : listArticole[i]
		}, function(event) {
			salveazaConditii(event.data.articol);
		});

		$(btnSaveConditii).appendTo(tdSaveCond);

		row = $('<tr></tr>', {
			id : 'rowCondCantImpuse' + listArticole[i].codArticol,
			style : 'display:none;color:red'

		});
		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>', {
			style : 'text-align:right'
		}).text('Conditii cantitative').appendTo(row);

		$('<td></td>', {
			id : 'tdCondCant' + listArticole[i].codArticol,
			style : 'width:10%',
			text : listArticole[i].conditiiCant

		}).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondValImpuse' + listArticole[i].codArticol,
			style : 'display:none;color:red'

		});
		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii valorice').appendTo(row);

		$('<td></td>', {
			id : 'tdCondVal' + listArticole[i].codArticol,
			style : 'width:10%',
			text : listArticole[i].conditiiVal

		}).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text('RON').appendTo(row);

		$(mytable).appendTo('#articoleTable');

	}

}

function salveazaConditii(articol) {

	var idInputCondCant = '#condCant' + articol.codArticol;
	var idInputCondVal = '#condVal' + articol.codArticol;

	if (isFinite($(idInputCondCant).val()) && $(idInputCondCant).val() > 0) {
		articol.conditiiCant = $(idInputCondCant).val().trim();

	} else {
		articol.conditiiCant = 0;
	}

	if (isFinite($(idInputCondVal).val()) && $(idInputCondVal).val() > 0) {
		articol.conditiiVal = $(idInputCondVal).val().trim();

	} else {
		articol.conditiiVal = 0;
	}

	setConditiiInputVisibility(articol);

}

function setConditiiInputVisibility(articol) {

	var rowCondCant = '#rowCondCant' + articol.codArticol;
	var rowCondVal = '#rowCondVal' + articol.codArticol;
	var rowSaveCond = '#rowSaveCond' + articol.codArticol;

	if ($(rowCondCant).css('display') == 'none') {
		$(rowCondCant).show();
		$(rowCondVal).show();
		$(rowSaveCond).show();
		$('#rowCondCantImpuse' + articol.codArticol).hide();
		$('#rowCondValImpuse' + articol.codArticol).hide();
	} else {
		$(rowCondCant).hide();
		$(rowCondVal).hide();
		$(rowSaveCond).hide();

		setConditiiAfisVisibility(articol);

	}

}

function setConditiiAfisVisibility(articol) {

	if (isFinite(articol.conditiiCant) && articol.conditiiCant > 0) {

		$('#tdCondCant' + articol.codArticol).text(articol.conditiiCant);
		$('#rowCondCantImpuse' + articol.codArticol).show();

	} else {
		articol.conditiiCant = 0;
		$('#tdCondCant' + articol.codArticol).text('');
		$('#rowCondCantImpuse' + articol.codArticol).hide();
	}

	if (isFinite(articol.conditiiVal) && articol.conditiiVal > 0) {

		$('#tdCondVal' + articol.codArticol).text(articol.conditiiVal);
		$('#rowCondValImpuse' + articol.codArticol).show();

	} else {
		articol.conditiiVal = 0;
		$('#tdCondVal' + articol.codArticol).text('');
		$('#rowCondValImpuse' + articol.codArticol).hide();
	}

}

function afisConditiiLayout(codArticol) {

	var idRowConditii = '#tblCond' + codArticol;

	if ($(idRowConditii).css('display') == 'none')
		$(idRowConditii).show();
	else
		$(idRowConditii).hide();

}

function saveConditiiLayout(index) {

	var idTextCondCant = '#condCant' + globalListArticole[index].codArticol;
	var idTextCondVal = '#condVal' + globalListArticole[index].codArticol;

	if (isFinite($(idTextCondCant).val()))
		globalListArticole[index].conditiiCant = $(idTextCondCant).val().trim();

	if (isFinite($(idTextCondVal).val()))
		globalListArticole[index].conditiiVal = $(idTextCondVal).val().trim();

	var idRowCondCant = '#condCant' + globalListArticole[index].codArticol;
	var idRowCondVal = '#condVal' + globalListArticole[index].codArticol;

	$(idRowCondCant).show();
	$(idRowCondVal).show();

}

function isNumeric(val) {
	return Number(parseFloat(val)) === val;
}

$("#aprobaCmd").click(
		function() {

			var listConditii = [];

			for (var i = 0; i < globalListArticole.length; i++) {

				if (globalListArticole[i].conditiiCant > 0
						|| globalListArticole[i].conditiiVal > 0) {
					var conditie = new Object();
					conditie.cod = globalListArticole[i].codArticol;
					conditie.cantitate = globalListArticole[i].conditiiCant;
					conditie.valoare = globalListArticole[i].conditiiVal;
					conditie.um = globalListArticole[i].um;
					listConditii.push(conditie);

				}

			}

			comandaCurenta.listConditii = listConditii;
			comandaCurenta.seAproba = true;
			comandaCurenta.codAngajat = userObj.codPers;

			callService(comandaCurenta);

		});

$("#respingeCmd").click(function() {
	comandaCurenta.listConditii = [];
	comandaCurenta.seAproba = false;
	comandaCurenta.codAngajat = userObj.codPers;
	callService(comandaCurenta);
});

function callService(comanda) {
	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'aprobaComanda',
		data : JSON.stringify(comanda),
		success : function(data) {
			showAlertStatus(data);
		},
		dataType : 'json',
		contentType : 'application/json',

	});

	$.mobile.loading('hide');

}

function showAlertStatus(statusAprobare) {

	showAlertDialog("Status", statusAprobare.message);

	if (statusAprobare.success) {
		$('body,html').animate({
			scrollTop : 0
		}, 500);
		getComenziAprobare();
	}

}

function showAlertDialog(tipAlert, mesajAlert) {

	$('#tipAlertM').text(tipAlert);
	$('#textAlertM').text(mesajAlert);
	$.mobile.changePage('#dialogAprobare', {
		transition : "none"
	});
}
