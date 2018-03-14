var userObj;
var globalListArticole = [];
var comandaCurenta;
var aprobaSD = false;
var aprobaDV = false;

$(document).on('pagebeforeshow', '#modifcmd', function() {

});

$(document).on('pageshow', '#modifcmd', function() {

	userObj = JSON.parse($('#userbean').text());

	initHeaderDateGen();
	initHeaderDateLivrare();

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

	var swatch = [ '#f2f2f2', '#FFF0F5' ];

	$('#articoleTable tbody').remove();

	$('#listArticoleModif').empty();

	var ulArticole = $('#listArticoleModif');

	if (listArticole != null && listArticole.length > 0)
		$('#opereazaCmdDiv').show();
	else
		return;

	for (var i = 0; i < listArticole.length; i++) {

		var bgColor = '';

		if (i % 2 == 0)
			bgColor = '#f2f2f2';

		var mytable = $('<table></table>').attr({
			id : "rowTable" + listArticole[i].cod,
			width : "100%",
			border : "0",
			bgcolor : bgColor

		});

		var row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').text((i + 1) + '').appendTo(
				row);
		$('<td></td>').attr('style', 'width:50%').text(listArticole[i].nume)
				.appendTo(row);
		$('<td></td>').attr({
			style : 'align:right;text-align:right;',
			id : 'cantArt' + listArticole[i].cod
		}).text(listArticole[i].cantitate).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').text(listArticole[i].cod)
				.appendTo(row);

		$('<td></td>').attr({
			style : 'width:10%;text-align:right;',
			id : 'pretArt' + listArticole[i].cod

		}).text(listArticole[i].pretUnitar).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("RON").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);

		var btnElimina = $('<img></img>', {
			src : 'resources/images/minus.png'
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
			style : 'width:10%;text-align:right;',
			id : 'procArt' + listArticole[i].cod
		}).text(listArticole[i].procentReducere).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text("%").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:5%').appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondCant' + listArticole[i].cod,
			style : 'color:red'

		});

		if (isConditiiCant(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii cantitative').appendTo(row);

		$('<td></td>').attr('style', 'text-align:right').text(
				listArticole[i].conditiiCant).appendTo(row);

		$('<td></td>').attr('style', 'width:3%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondVal' + listArticole[i].cod,
			style : 'color:red'

		});

		if (isConditiiVal(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii valorice').appendTo(row);

		$('<td></td>').attr('style', 'text-align:right').text(
				listArticole[i].conditiiVal).appendTo(row);

		$('<td></td>').attr('style', 'width:3%').text("RON").appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowSaveCond' + listArticole[i].cod

		});

		if (isConditiiCant(listArticole[i]) || isConditiiVal(listArticole[i]))
			$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').appendTo(row);

		var tdSaveCond = $('<td></td>').attr('style', 'text-align:center')
				.attr('colspan', 2);

		var btnSaveConditii = $('<img></img>', {
			src : 'resources/images/accept.png'
		}).attr('data-role', 'button').bind('click', {
			articol : listArticole[i]
		}, function(event) {
			acceptaConditii(event.data.articol);
		});

		$(btnSaveConditii).appendTo(tdSaveCond);
		tdSaveCond.appendTo(row);

		var articol = $('<li></li>', {
			html : mytable
		}).css("background-color", swatch[i / 2]);

		$(ulArticole).append(articol);

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
		$('#rowCondCant' + articol.cod).show();
		$('#rowCondVal' + articol.cod).show();
		$('#rowSaveCond' + articol.cod).show();
	} else {
		$('#rowCondCant' + articol.cod).hide();
		$('#rowCondVal' + articol.cod).hide();
		$('#rowSaveCond' + articol.cod).hide();
	}

}

function acceptaConditiiCantitative(articol) {
	articol.cantitate = articol.conditiiCant;
	var idCant = '#cantArt' + articol.cod;
	$(idCant).text(articol.cantitate);
	articol.conditiiCant = 0;

}

function acceptaConditiiValorice(articol) {

	var pretInit = articol.pretUnitar / (1 - articol.procentReducere / 100);

	var newProcRed = (1 - articol.conditiiVal / pretInit) * 100;
	articol.procentReducere = newProcRed.toFixed(2);

	var idProc = '#procArt' + articol.cod;

	$(idProc).text(articol.procentReducere);

	articol.pretUnitar = articol.conditiiVal;
	var idPret = '#pretArt' + articol.cod;
	$(idPret).text(articol.pretUnitar);
	articol.conditiiVal = 0;

}

function eliminaArticol(articol) {

	for (var i = 0; i < globalListArticole.length; i++) {

		if (globalListArticole[i].cod == articol.cod) {
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

	var rowCondCant = '#rowCondCant' + articol.cod;
	var rowCondVal = '#rowCondVal' + articol.cod;
	var rowSaveCond = '#rowSaveCond' + articol.cod;

	if ($(rowCondCant).css('display') == 'none') {
		$(rowCondCant).show();
		$(rowCondVal).show();
		$(rowSaveCond).show();
		$('#rowCondCantImpuse' + articol.cod).hide();
		$('#rowCondValImpuse' + articol.cod).hide();
	} else {
		$(rowCondCant).hide();
		$(rowCondVal).hide();
		$(rowSaveCond).hide();

		setConditiiAfisVisibility(articol);

	}

}

function setConditiiAfisVisibility(articol) {

	if (isFinite(articol.conditiiCant) && articol.conditiiCant > 0) {

		$('#tdCondCant' + articol.cod).text(articol.conditiiCant);
		$('#rowCondCantImpuse' + articol.cod).show();

	} else {
		articol.conditiiCant = 0;
		$('#tdCondCant' + articol.cod).text('');
		$('#rowCondCantImpuse' + articol.cod).hide();
	}

	if (isFinite(articol.conditiiVal) && articol.conditiiVal > 0) {

		$('#tdCondVal' + articol.cod).text(articol.conditiiVal);
		$('#rowCondValImpuse' + articol.cod).show();

	} else {
		articol.conditiiVal = 0;
		$('#tdCondVal' + articol.cod).text('');
		$('#rowCondValImpuse' + articol.cod).hide();
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
	comanda.codDepart = userObj.codDepart;
	comanda.tipUser = userObj.tipAngajat;
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

	var comanda = new Object();
	comanda.id = $("#cmd_modif_select option:selected").val();
	comanda.listConditii = [];
	comanda.codAngajat = userObj.codPers;
	comanda.seAproba = false;

	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'stergeComanda',
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

function initHeaderDateGen() {

	$('#dateGenTable').hide();

	$('#headerDateGen').empty();

	var dateGenTable = $('<table></table>').attr({
		width : "100%",
		border : "0"

	})

	var row = $('<tr></tr>').appendTo(dateGenTable);

	$('<td></td>').attr('style', 'width:3%').attr({
		style : 'align:left;'

	}).text('Date generale').css('color', '#008B00').css("font-weight", "bold")
			.appendTo(row);

	var tdBtn = $('<td></td>').attr('style', 'width:3%').attr({
		style : 'text-align:right'
	}).appendTo(row);

	var btnToogleDateGen = $('<img></img>', {
		id : 'toggleDateGenBtn',
		src : 'resources/images/plus_black.png'
	}).attr('data-role', 'button').bind('click', {

	}, function() {
		toggleDateGen();
	});

	$(btnToogleDateGen).appendTo(tdBtn);

	$(dateGenTable).appendTo($('#headerDateGen'));

}

function toggleDateGen() {
	$('#dateGenTable').toggle();

	if ($("#dateGenTable").is(":hidden"))
		$('#toggleDateGenBtn').attr("src", "resources/images/plus_black.png");
	else
		$('#toggleDateGenBtn').attr("src", "resources/images/minus_black.png");

}

function initHeaderDateLivrare() {

	$('#dateLivrareModifTable').hide();

	$('#headerDateLivrare').empty();

	var dateLivrTable = $('<table></table>').attr({
		width : "100%",
		border : "0"

	})

	var row = $('<tr></tr>').appendTo(dateLivrTable);

	$('<td></td>').attr('style', 'width:3%').attr({
		style : 'align:left;'

	}).text('Date livrare').css('color', '#008B00').css("font-weight", "bold")
			.appendTo(row);

	var tdBtn = $('<td></td>').attr('style', 'width:3%').attr({
		style : 'text-align:right'
	}).appendTo(row);

	var btnToogleDateLivr = $('<img></img>', {
		id : 'toggleDateLivrBtn',
		src : 'resources/images/plus_black.png'
	}).attr('data-role', 'button').bind('click', {

	}, function() {
		toggleDateLivrare();
	});

	$(btnToogleDateLivr).appendTo(tdBtn);

	$(dateLivrTable).appendTo($('#headerDateLivrare'));

}

function toggleDateLivrare() {
	$('#dateLivrareModifTable').toggle();

	if ($("#dateLivrareModifTable").is(":hidden"))
		$('#toggleDateLivrBtn').attr("src", "resources/images/plus_black.png");
	else
		$('#toggleDateLivrBtn').attr("src", "resources/images/minus_black.png");

}

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
