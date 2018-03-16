var numeArticolCurent;
var departArticol;
var aprobaSD = false;
var aprobaDV = false;
var depozitSelectat;
var listaArticole = [];

var pretInit;

$(document).on('pagecreate', '#modifcmd', function() {

	setColapsibleArticolListener();

});

$('#cautaArticol').click(function() {

	if ($('#codArticol').val().trim() == '')
		return;

	var tipCautare = $('input[name=radio-articol]:checked').val();

	var articol = new Object();
	articol.depart = userObj.codDepart;

	if (tipCautare == 'cod')
		articol.cod = $('#codArticol').val();
	else
		articol.nume = $('#codArticol').val();

	$.ajax({
		type : 'POST',
		url : 'cautaArticol',
		data : JSON.stringify(articol),
		dataType : 'json',
		contentType : 'application/json',
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			afiseazaListArticole(data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
		}

	});

});

function afiseazaListArticole(listArticole) {

	$("#articoleset").empty();

	for (var u = 0; u < listArticole.length; u++) {

		var articol = $(
				"<div data-role='collapsible' data-content-theme='a' id='"
						+ listArticole[u].cod + "'><h2><div id='numeart"
						+ listArticole[u].cod + "'>" + listArticole[u].cod
						+ ' - ' + listArticole[u].nume
						+ "</div></h2><div id='articol" + listArticole[u].cod
						+ "'></div></div>").data('objArt', listArticole[u]);

		$("#articoleset").append(articol).collapsibleset("refresh");

	}

}

function setColapsibleArticolListener() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var codArt = data.target.id;

		var contentId = '#' + codArt;

		var articolCurent = $(contentId).data('objArt');

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		numeArticolCurent = articolCurent.nume;
		departArticol = articolCurent.depart;

		getDetaliiArticol(codArt);

	});

	$('#articoleset').bind('collapsiblecollapse', function(data) {

	});

}

function getDetaliiArticol(codArticol) {
	var depozit = departArticol + $('#select-depoz').val();
	depozitSelectat = depozit;
	getStocArticol(codArticol, depozit);

}

function getStocArticol(codArticol, depozit) {

	$.ajax({
		type : 'GET',
		url : 'stocdepozit',
		data : ({
			codArticol : codArticol,
			filiala : userObj.unitLog,
			depozit : depozit
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			afisStocArticol(codArticol, data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
		}

	});

}

function afisStocArticol(codArticol, dateStoc) {

	var contentId = '#articol' + codArticol;

	var stocTable = tableStocArticol(codArticol, dateStoc);

	$(contentId).empty();

	$(stocTable).appendTo(contentId);
}

function getPretArticol(codArticol) {

	var cantArticol = $('#cant' + codArticol).val().trim();
	var umVanz = $('#umVanz' + codArticol).html();

	if (!$.isNumeric(cantArticol)) {
		showAlertDialogModif("Atentie!", "Cantitate invalida.");
		return;
	}

	var cautaPret = new Object();
	cautaPret.codArticol = codArticol;
	cautaPret.filiala = userObj.unitLog;
	cautaPret.departament = userObj.codDepart;
	cautaPret.codClient = comandaCurenta.codClient;
	cautaPret.depozit = depozitSelectat;
	cautaPret.cantitate = cantArticol;
	cautaPret.um = umVanz;

	$.ajax({
		type : 'POST',
		url : 'pret',
		data : JSON.stringify(cautaPret),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			afisPretArticol(codArticol, data);
		},
		error : function(exception) {
			showAlertDialogModif("Atentie!", JSON.stringify(exception));
		},
		dataType : 'json',
		contentType : 'application/json',

	});

}

function afisPretArticol(codArticol, datePret) {

	pretInit = datePret.pret;
	var contentId = '#articol' + codArticol;

	var pretTable = tablePretArticol(codArticol, datePret);

	$(pretTable).appendTo(contentId);
}

function trateazaArticol(articolSelectat) {

	if ($('#divArticole').css('display') == 'none')
		$('#divArticole').show();

	var idTextStoc = '#stoc' + articolSelectat.cod;
	var idTextCant = '#cant' + articolSelectat.cod;

	if (Number($(idTextStoc).text()) < Number($(idTextCant).val())) {
		showAlertDialogModif('Info', 'Stoc insuficient.');
		return;
	}

	var idTextPret = '#pret_art' + articolSelectat.cod;
	var idTextProcent = '#procent_art' + articolSelectat.cod;

	if (!$.isNumeric($(idTextPret).val())) {
		showAlertDialogModif("Atentie!", "Valoare pret invalida.");
		return;
	}

	if (!$.isNumeric($(idTextProcent).val())) {
		showAlertDialogModif("Atentie!", "Valoare procent invalida.");
		return;
	}

	for (var i = 0; i < listaArticole.length; i++) {

		if (listaArticole[i].cod == articolSelectat.cod) {
			listaArticole.splice(i, 1);
			break;
		}
	}

	articolSelectat.cantitate = $(idTextCant).val();
	articolSelectat.pretUnitar = $(idTextPret).val();

	articolSelectat.procentReducere = $(idTextProcent).val().trim() == '' ? '0'
			: $(idTextProcent).val();
	articolSelectat.depozit = departArticol + $('#select-depoz').val();

	articolSelectat.procentFact = getProcentFact(articolSelectat);
	articolSelectat.procentAprob = getProcentAprob(articolSelectat);
	articolSelectat.valoareArticol = getValoareArticol(articolSelectat);

	if (articolSelectat.procent > 0) {
		aprobaSD = true;
		aprobaDV = true;
	}

	$('#' + articolSelectat.cod).collapsible("collapse");

	eliminaArticolCreat(articolSelectat);

	globalListArticole.push(articolSelectat);
	afiseazaArticoleComanda(globalListArticole);

	calculeazaTotalCmdModificare();

}

function eliminaArticolCreat(articol) {

	for (var i = 0; i < globalListArticole.length; i++) {

		if (globalListArticole[i].cod == articol.cod) {
			globalListArticole.splice(i, 1);

			break;
		}
	}

}

function adaugaArticol(articolSelectat, poz) {

	var swatch = [ '#f2f2f2', '#FFF0F5' ];

	var ulArticole = $('#listArticoleModif');

	var articol = $('<li></li>', {
		html : getTabelaArticolNou(articolSelectat, poz)
	}).css("background-color", swatch[poz / 2]);

	$(ulArticole).append(articol);

	calculeazaTotalCmdModificare();

}

function afiseazaListaArticole() {

	$('#listArticoleModif').empty();

	for (var i = 0; i < globalListArticole.length; i++) {
		adaugaArticol(globalListArticole[i], i);
	}

}

function getTabelaArticolNou(articol, poz) {

	var articoleTable = $('<table></table>').attr({
		width : "100%",
		border : "0"

	}).addClass('articoleCreare');

	var row = $('<tr></tr>').appendTo(articoleTable);

	$('<td></td>').attr('style', 'width:3%').attr({
		style : 'align:left'
	}).text((poz + 1) + '').appendTo(row);

	$('<td></td>').attr('style', 'width:80%').attr('style', 'font-weight:bold')
			.text(articol.nume).appendTo(row);
	$('<td></td>').attr({
		style : 'width:10%;text-align:right;'
	}).text(articol.cantitate).appendTo(row);
	$('<td></td>').text(articol.um).appendTo(row);

	row = $('<tr></tr>').appendTo(articoleTable);
	$('<td></td>').attr('style', 'width:3%').appendTo(row);
	$('<td></td>').attr('style', 'width:80%').text(articol.cod).appendTo(row);
	$('<td></td>').attr({
		style : 'width:10%;text-align:right;'
	}).text(parseFloat(articol.pretUnitar).toFixed(2)).appendTo(row);
	$('<td></td>').text('RON').appendTo(row);

	var btnEliminaArt = $('<img></img>', {
		src : 'resources/images/minus.png'
	}).attr('data-role', 'button').bind('click', {
		articol : articol
	}, function(event) {
		eliminaArticolCreat(event.data.articol);
	});

	row = $('<tr></tr>').appendTo(articoleTable);

	var tdElimBtn = $('<td></td>').attr('style', 'width:3%');

	$(btnEliminaArt).appendTo(tdElimBtn);

	$(tdElimBtn).appendTo(row);
	$('<td></td>').text(articol.depozit).appendTo(row);
	$('<td></td>').attr({
		style : 'width:10%;text-align:right;'
	}).text(articol.procent).appendTo(row);
	$('<td></td>').text('%').appendTo(row);

	return articoleTable;
}

function trateazaArticol_old(articolSelectat) {

	if ($('#divArticole').css('display') == 'none')
		$('#divArticole').show();

	var idTextStoc = '#stoc' + articolSelectat.codArticol;
	var idTextCant = '#cant' + articolSelectat.codArticol;

	if (Number($(idTextStoc).text()) < Number($(idTextCant).val())) {
		alert('Stoc insuficient.');
		return;
	}

	for (var i = 0; i < globalListArticole.length; i++) {

		if (globalListArticole[i].codArticol == articolSelectat.codArticol) {
			globalListArticole.splice(i, 1);
			break;
		}
	}

	var idTextPret = '#pret_art' + articolSelectat.codArticol;
	var idTextProcent = '#procent_art' + articolSelectat.codArticol;

	articolSelectat.cantitate = $(idTextCant).val();
	articolSelectat.pretUnitar = $(idTextPret).val();
	articolSelectat.procentReducere = $(idTextProcent).val();
	articolSelectat.depozit = departArticol + $('#select-depoz').val();
	articolSelectat.conditiiCant = 0;
	articolSelectat.conditiiVal = 0;

	globalListArticole.push(articolSelectat);

	if (articolSelectat.procentReducere > 0) {
		aprobaSD = true;
		aprobaDV = true;
	}

	$('#' + articolSelectat.codArticol).collapsible("collapse");

	afiseazaArticoleComanda(globalListArticole);

	calculeazaTotalCmdModificare();

}

function setProcentReducere(codArticol) {

	var idTextPret = '#pret_art' + codArticol;

	var idTextProcent = '#procent_art' + codArticol;

	var pretSchimbat = $(idTextPret).val();

	var procentReducere = (1 - Number(pretSchimbat / pretInit)) * 100;

	$(idTextProcent).val(Number(procentReducere).toFixed(2));

}

function setPretReducere(codArticol) {

	var idTextPret = '#pret_art' + codArticol;

	var idTextProcent = '#procent_art' + codArticol;

	var procent = $(idTextProcent).val();

	var valoarePret = pretInit - (procent / 100) * pretInit;

	$(idTextPret).val(Number(valoarePret).toFixed(2));

}

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}
