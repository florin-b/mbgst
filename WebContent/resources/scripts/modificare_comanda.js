var userObj;
var globalListArticole = [];
var comandaCurenta;
var aprobaSD = false;
var aprobaDV = false;

$(document).on('pagebeforeshow', '#modifcmd', function() {

});

$(document).on('pageshow', '#modifcmd', function() {

	userObj = JSON.parse($('#userbean').text());

	getComenziModificare();

});

$(document).on('pagecreate', '#modifcmd', function() {

	initDateField();

});

function initDateField() {

	$("#dataLivrare").datepicker({

		dateFormat : "dd-mm-yy"

	});

}

$(document).on('pagecreate', '#modifcmd', function() {

	hidePageSections();

});

function hidePageSections() {

	$('#modif_act_select').parent().hide();
	$('#antetCmdModif').hide();
	$('#articoleCmdModif').hide();
	$('#selectArtModifDiv').hide();
	$('#selectDateLivrareModifDiv').hide();
	$('#divDateLivrareModif').hide();
	$('#opereazaCmdDiv').hide();

}

$('#modif_act_select').on('change', function() {

	var selectId = this.value;

	$('#selectArtModifDiv').hide();
	$('#selectDateLivrareDiv').hide();
	$('#selectDateLivrareModifDiv').hide();

	if (selectId == 0) {
		$('#selectArtModifDiv').hide();
		$('#selectDateLivrareDiv').hide();
		$('#selectDateLivrareModifDiv').hide();
	} else if (selectId == 1) {
		$('#selectArtModifDiv').show();
	} else if (selectId == 2) {
		modificaDateLivrare(comandaCurenta.dateLivrare);
		$('#selectDateLivrareModifDiv').show();

	}

});

function getComenziModificare() {

	var cautaCmdModif = new Object();

	cautaCmdModif.tipAngajat = userObj.tipAngajat;
	cautaCmdModif.unitLog = userObj.unitLog;
	cautaCmdModif.codDepart = userObj.codDepart;
	cautaCmdModif.codAngajat = userObj.codPers;

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getCmdModif',
		data : cautaCmdModif,
		success : function(data) {
			$.mobile.loading('hide');
			afisComenziModificare(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afisComenziModificare(listComenzi) {

	$('#cmd_modif_select').empty();

	if (listComenzi.length > 0) {

		$('#cmd_modif_select').append($('<option>', {
			value : 0,
			text : "Selectati o comanda"
		}));

		for (var i = 0; i < listComenzi.length; i++) {
			$('#cmd_modif_select').append($('<option>', {
				value : listComenzi[i].idComanda,
				text : listComenzi[i].numeClient
			}));
		}

		$("#cmd_modif_select").val("0").change();

	}

}

$('#cmd_modif_select').on('change', function() {
	var idComanda = $("#cmd_modif_select option:selected").val();

	if (idComanda > 0)
		getDetaliiComandaModif(idComanda);

});

function getDetaliiComandaModif(idComanda) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getDetCmdModif',
		data : ({
			idComanda : idComanda
		}),
		success : function(data) {
			afiseazaComandaModif(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afiseazaComandaModif(comanda) {

	comandaCurenta = comanda;

	$('#antetCmdModif').show();
	$('#articoleCmdModif').show();
	$('#divDateLivrareModif').show();

	$('#modif_act_select').parent().show();

	var dateGenTable = '#dateGenTable';

	$('#dateGenTable tbody').remove();

	var row = $('<tr></tr>').appendTo(dateGenTable);

	$('<td></td>').attr('style', 'width:30%').text('Nr. comanda SAP').appendTo(
			row);

	$('<td></td>').text(comanda.idComandaSAP).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:30%').text('Valoare').appendTo(row);
	$('<td></td>').attr('id', 'totalCmdModif').text(comanda.valoare).appendTo(
			row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:25%').text('Data emirere')
			.appendTo(row);
	$('<td></td>').text(comanda.dataEmitere).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:25%').text('Stare').appendTo(row);
	$('<td></td>').text(getStareComandaText(comanda.idStareComanda)).appendTo(
			row);

	globalListArticole = comanda.listArticole;
	afiseazaArticoleComanda(comanda.listArticole);
	afiseazaDateLivrare(comanda.dateLivrare);

}

function afiseazaArticoleComanda(listArticole) {

	$('#articoleTable tbody').remove();

	if (listArticole != null && listArticole.length > 0)
		$('#opereazaCmdDiv').show();
	else
		return;

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
			style : 'align:right',
			id : 'cantArt' + listArticole[i].codArticol
		}).text(listArticole[i].cantitate).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').text(
				listArticole[i].codArticol).appendTo(row);

		$('<td></td>').attr({
			style : 'width:10%',
			id : 'pretArt' + listArticole[i].codArticol

		}).text(listArticole[i].pretUnitar).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("RON").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);

		var btnElimina = $('<img></img>', {
			src : 'resources/images/bin_blue.png'
		}).attr('data-role', 'button').bind('click', {
			articol : listArticole[i]
		}, function(event) {
			eliminaArticol(event.data.articol);
		});

		var tdBtn = $('<td></td>').attr('style', 'text-align:center').attr(
				'style', 'width:3%');

		$(btnElimina).appendTo(tdBtn);

		$(tdBtn).appendTo(row);

		$('<td></td>').attr('style', 'width:50%').appendTo(row);
		$('<td></td>').attr({
			style : 'width:10%',
			id : 'procArt' + listArticole[i].codArticol
		}).text(listArticole[i].procentReducere).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text("%").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:5%').appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondCant' + listArticole[i].codArticol,
			style : 'color:red'

		});

		if (isConditiiCant(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii cantitative').appendTo(row);

		$('<td></td>').text(listArticole[i].conditiiCant).appendTo(row);

		$('<td></td>').attr('style', 'width:3%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondVal' + listArticole[i].codArticol,
			style : 'color:red'

		});

		if (isConditiiVal(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii valorice').appendTo(row);

		$('<td></td>').text(listArticole[i].conditiiVal).appendTo(row);

		$('<td></td>').attr('style', 'width:3%').text("RON").appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowSaveCond' + listArticole[i].codArticol

		});

		if (isConditiiCant(listArticole[i]) || isConditiiVal(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').appendTo(row);

		var tdSaveCond = $('<td></td>').attr('style', 'text-align:center')
				.attr('colspan', 2);

		/*
		 * var btnSaveConditii = $('<button>', { text : 'Accepta', class :
		 * 'ui-btn ui-mini', style : 'width:90%', }).bind('click', { articol :
		 * listArticole[i] }, function(event) {
		 * acceptaConditii(event.data.articol); });
		 */

		var btnSaveConditii = $('<img></img>', {
			src : 'resources/images/accept_icon.png'
		}).attr('data-role', 'button').bind('click', {
			articol : listArticole[i]
		}, function(event) {
			acceptaConditii(event.data.articol);
		});

		$(btnSaveConditii).appendTo(tdSaveCond);
		tdSaveCond.appendTo(row);

		$(mytable).appendTo('#articoleTable');

		calculeazaTotalCmdModificare();

	}

}

function isConditiiCant(articol) {
	if (articol.conditiiCant != null && articol.conditiiCant > 0)
		return true;
	return false;
}

function isConditiiVal(articol) {
	if (articol.conditiiVal != null && articol.conditiiVal > 0)
		return true;
	return false;
}

function acceptaConditii(articol) {

	if (isConditiiCant(articol)) {
		acceptaConditiiCantitative(articol);
	}

	if (isConditiiVal(articol)) {
		acceptaConditiiValorice(articol);
	}

	setConditiiVisibility(false, articol);

}

function setConditiiVisibility(isVisible, articol) {

	if (isVisible) {
		$('#rowCondCant' + articol.codArticol).show();
		$('#rowCondVal' + articol.codArticol).show();
		$('#rowSaveCond' + articol.codArticol).show();
	} else {
		$('#rowCondCant' + articol.codArticol).hide();
		$('#rowCondVal' + articol.codArticol).hide();
		$('#rowSaveCond' + articol.codArticol).hide();
	}

}

function acceptaConditiiCantitative(articol) {
	articol.cantitate = articol.conditiiCant;
	var idCant = '#cantArt' + articol.codArticol;
	$(idCant).text(articol.cantitate);
	articol.conditiiCant = 0;

}

function acceptaConditiiValorice(articol) {

	var pretInit = articol.pretUnitar / (1 - articol.procentReducere / 100);

	var newProcRed = (1 - articol.conditiiVal / pretInit) * 100;
	articol.procentReducere = newProcRed.toFixed(2);

	var idProc = '#procArt' + articol.codArticol;

	$(idProc).text(articol.procentReducere);

	articol.pretUnitar = articol.conditiiVal;
	var idPret = '#pretArt' + articol.codArticol;
	$(idPret).text(articol.pretUnitar);
	articol.conditiiVal = 0;

}

function eliminaArticol(articol) {

	for (var i = 0; i < globalListArticole.length; i++) {

		if (globalListArticole[i].codArticol == articol.codArticol) {
			globalListArticole.splice(i, 1);
			break;
		}
	}

	afiseazaArticoleComanda(globalListArticole);

	calculeazaTotalCmdModificare();

}

function calculeazaTotalCmdModificare() {

	var totalComanda = 0;
	for (var i = 0; i < globalListArticole.length; i++) {
		totalComanda += globalListArticole[i].pretUnitar
				* globalListArticole[i].cantitate;

	}

	$('#totalCmdModif').html(totalComanda.toFixed(2));

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

function isNumeric(val) {
	return Number(parseFloat(val)) === val;
}

function setAprobariNecesare() {
	for (var i = 0; i < globalListArticole.length; i++) {
		if (globalListArticole[i].procentReducere > 0) {
			aprobaSD = true;
			aprobaDV = true;
		}
	}

}

function scrollToPageTop() {

	$('html, body').animate({
		scrollTop : ($('#modifcmd').offset().top)
	}, 500);
}

function isConditiiPreluate() {

	var preluareTotala = true;

	for (var i = 0; i < globalListArticole.length; i++) {
		if (isConditiiCant(globalListArticole[i])
				|| isConditiiVal(globalListArticole[i])) {
			preluareTotala = false;
		}
	}

	return preluareTotala;

}

$("#salveazaCmdModif").click(function() {

	if (!isConditiiPreluate()) {
		showAlertDialogModif('Atentie!', 'Preluati toate conditiile.');
		return;
	}

	setAprobariNecesare();

	var comanda = new Object();

	comanda.codClient = comandaCurenta.codClient;
	comanda.codAgent = userObj.codPers;
	comanda.aprobaSD = aprobaSD;
	comanda.aprobaDV = aprobaDV;
	comanda.unitLog = comandaCurenta.unitLog;
	comanda.idCmdSap = comandaCurenta.idComandaSAP;
	comanda.idCmd = $("#cmd_modif_select option:selected").val();
	comanda.dateLivrare = getDateLivrareModifCmd(comandaCurenta.dateLivrare);
	comanda.listArticole = comandaCurenta.listArticole;
	comanda.totalComanda = $('#totalCmdModif').text();

	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'salveazaComanda',
		data : JSON.stringify(comanda),
		success : function(data) {
			showAlertStatusModificare(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function() {

		}

	});

	$.mobile.loading('hide');

});

$("#stergeCmdModif").click(function() {
	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'stergeComanda',
		data : {
			idComanda : $("#cmd_modif_select option:selected").val()
		},
		success : function(data) {
			showAlertStatusModificare(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function() {

		}

	});

	$.mobile.loading('hide');

});

function showAlertStatusModificare(statusModificare) {

	if (statusModificare.success) {
		hidePageSections();
		getComenziModificare();
		scrollToPageTop();
	}
	showAlertDialogModif("Status", statusModificare.message);

}

function showAlertDialogModif(tipAlert, mesajAlert) {
	$('#tipAlertM').text(tipAlert);
	$('#textAlertM').text(mesajAlert);

	$.mobile.changePage('#dialogModificare', {
		transition : "none"
	});
}
