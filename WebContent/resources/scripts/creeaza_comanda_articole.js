var userObj;
var numeArtSel;
var comandaId;
var globalDetaliiClient;
var pretInit;
var numeArticolCurent;
var listaArticole = [];
var departArticol;
var aprobaSD = false;
var aprobaDV = false;

$(document).on('pagebeforeshow', '#crearecomanda', function() {

	// $('#select_articole_div').parent().css('margin-left', '10px');
	// $('#select_articole_div').parent().css('margin-right', '20px');

	// $('#select_articole_div').css('max-height', '800px');

	// $('#select_articole_div').css('overflow', 'auto');

});

$(document).on('pageshow', '#crearecomanda', function() {

});

$(document).on('pagecreate', '#crearecomanda', function() {

	setColapsibleArticolListener();
	setColapsibleSelectArticolListener();

});

$('#cautaArticol').click(function() {

	if ($('#codArticol').val().trim() == '')
		return;

	userObj = JSON.parse($('#userbean').text());

	var tipCautare = $('input[name=radio-articol]:checked').val();

	var articol = new Object();
	articol.depart = userObj.codDepart;

	if (tipCautare == 'cod')
		articol.cod = $('#codArticol').val();
	else
		articol.nume = $('#codArticol').val();

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'cautaArticol',
		data : articol,
		success : function(data) {
			afiseazaListArticole(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
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

function setColapsibleSelectArticolListener() {
	$('#inner_optiuni_articole_div').bind('collapsibleexpand', function(data) {

		if (data.target !== data.currentTarget)
			return;

		$('#codArticol').val('');
		$("#articoleset").empty();

	});

}

function getDetaliiArticol(codArticol) {
	var depozit = departArticol + $('#select-depoz').val();

	getStocArticol(codArticol, depozit);

}

function getStocArticol(codArticol, depozit) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'stocdepozit',
		data : ({
			codArticol : codArticol,
			filiala : userObj.unitLog,
			depozit : depozit
		}),
		success : function(data) {
			$.mobile.loading('hide');
			afisStocArticol(codArticol, data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisStocArticol(codArticol, dateStoc) {

	if (dateStoc.um == null)
		dateStoc.um = '';

	var contentId = '#articol' + codArticol;

	var stocId = 'stoc' + codArticol;

	var stocTable = $(
			'<table data-role="table" class="ui-responsive table" data-mode="reflow"></table>',
			{
				id : "stocTable" + codArticol
			}).addClass("detaliiTable");

	var row = $('<tr></tr>');
	$(row).appendTo(stocTable);

	$('<td></td>').text('Stoc').attr('style', 'width:25%').appendTo(row);
	$('<td></td>').text(dateStoc.cantitate).attr('style', 'width:30%').attr(
			'id', stocId).appendTo(row);
	$('<td></td>').text(dateStoc.um).attr('style', 'width:70%').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(stocTable);

	$('<td></td>').text('Cantitate').attr('style', 'width:25%').appendTo(row);

	var textCant = $('<input/>', {
		type : 'text',
		id : 'cant' + codArticol,
		value : 1,
		width : '40%'

	});

	var tdCant = $('<td></td>', {
		width : '30%'
	}).appendTo(row);
	$(textCant).appendTo(tdCant).textinput();
	$('<td></td>').text(dateStoc.um).attr('style', 'width:10%').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(stocTable);

	var tdPret = $('<td></td>', {
		colspan : '3'
	}).appendTo(row);

	var btnPretArt = $('<input>', {
		type : 'button',
		name : 'pret',
		value : 'Pret',
		style : 'width:100%'
	}).bind('click', {
		articol : codArticol
	}, function(event) {
		getPretArticol(event.data.articol);
	});

	$(btnPretArt).appendTo(tdPret).buttonMarkup();

	$(contentId).empty();

	$(stocTable).appendTo(contentId);

}

function getPretArticol(codArticol) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'pret',
		data : ({
			codArticol : codArticol,
			filiala : userObj.unitLog,
			departament : userObj.codDepart
		}),
		success : function(data) {
			$.mobile.loading('hide');
			afisPretArticol(codArticol, data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisPretArticol(codArticol, datePret) {

	pretInit = datePret.pret;

	var contentId = '#articol' + codArticol;

	$('#pretTable' + codArticol).remove();

	var pretTable = $(
			'<table data-role="table" class="ui-responsive table" data-mode="reflow"></table>')
			.attr('id', "pretTable" + codArticol).addClass("detaliiTable");

	var row = $('<tr></tr>');
	$(row).appendTo(pretTable);

	$('<td></td>').text('Pret').attr('style', 'width:25%').appendTo(row);

	var textPret = $('<input/>', {
		type : 'text',
		id : 'pret_art' + codArticol,
		value : datePret.pret,
		width : '40%'

	}).keyup(function() {
		setProcentReducere(codArticol);
	});

	var tdPret = $('<td></td>', {
		width : '25%'
	}).appendTo(row);

	$(textPret).appendTo(tdPret).textinput();

	$('<td></td>').text(datePret.um).appendTo(row);

	$('<td></td>').text('Unitate pret').appendTo(row);
	$('<td></td>').text('1 BUC').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);
	$('<td></td>').text('Reducere').attr('style', 'width:25%').appendTo(row);

	var textProcRed = $('<input/>', {
		type : 'text',
		id : 'procent_art' + codArticol,
		value : 0,
		width : '40%'

	}).keyup(function() {
		setPretReducere(codArticol);
	});

	var tdProcRed = $('<td></td>', {
		width : '30%'
	}).appendTo(row);
	$(textProcRed).appendTo(tdProcRed).textinput();

	$('<td></td>').text('%').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);
	$('<td></td>').text('Pret cu tva').attr('style', 'width:25%').appendTo(row);
	$('<td></td>').text('24.55').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);
	$('<td></td>').text('Discount client').attr('style', 'width:25%').appendTo(
			row);
	$('<td></td>').text('5').appendTo(row);
	$('<td></td>').text('%').appendTo(row);

	var articolSelectat = new Object();
	articolSelectat.nume = numeArticolCurent;
	articolSelectat.cod = codArticol;
	articolSelectat.um = datePret.um;

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);

	var tdAdaugaArt = $('<td></td>', {
		colspan : '5'
	}).appendTo(row);

	var btnAdaugaArt = $('<input>', {
		type : 'button',
		name : 'adauga',
		value : 'Adauga',
		style : 'width:100%'
	}).bind('click', {
		articol : articolSelectat
	}, function(event) {
		trateazaArticol(event.data.articol);
	});

	$(btnAdaugaArt).appendTo(tdAdaugaArt).buttonMarkup();

	$(pretTable).appendTo(contentId);

}

function trateazaArticol(articolSelectat) {

	if ($('#divArticole').css('display') == 'none')
		$('#divArticole').show();

	var idTextStoc = '#stoc' + articolSelectat.cod;
	var idTextCant = '#cant' + articolSelectat.cod;

	if (Number($(idTextStoc).text()) < Number($(idTextCant).val())) {
		alert('Stoc insuficient.');
		return;
	}

	for (var i = 0; i < listaArticole.length; i++) {

		if (listaArticole[i].cod == articolSelectat.cod) {
			listaArticole.splice(i, 1);
			break;
		}
	}

	var idTextPret = '#pret_art' + articolSelectat.cod;
	var idTextProcent = '#procent_art' + articolSelectat.cod;

	articolSelectat.cant = $(idTextCant).val();
	articolSelectat.pret = $(idTextPret).val();
	articolSelectat.procent = $(idTextProcent).val();
	articolSelectat.depozit = departArticol + $('#select-depoz').val();

	listaArticole.push(articolSelectat);

	if (articolSelectat.procent > 0) {
		aprobaSD = true;
		aprobaDV = true;
	}

	$('#' + articolSelectat.cod).collapsible("collapse");

	afiseazaListaArticole();

	calculeazaTotalCmd();

}

function afiseazaListaArticole() {

	$("#art_com_table > tbody").html("");

	for (var i = 0; i < listaArticole.length; i++) {

		var extraRowId = 'extra' + listaArticole[i].cod;

		$("#art_com_table")
				.append(
						"<tr><td>"
								+ listaArticole[i].nume
								+ "</td><td style='text-align:right;'>"
								+ listaArticole[i].cant
								+ "</td><td>"
								+ listaArticole[i].um
								+ "</td><td style='text-align:right;'>"
								+ listaArticole[i].pret
								+ "</td><td style='text-align:right;'>"
								+ listaArticole[i].procent
								+ " </td><td style='text-align:right;'><a href='#' onClick='eliminaArticol("
								+ listaArticole[i].cod
								+ ");'>Sterge</a></td></tr>").enhanceWithin();

		/*
		 * <td><a href='#' onClick='showDetArt(" + listaArticole[i].cod +
		 * ");'>Extra</a></td>
		 * 
		 */

		/*
		 * 
		 * $("#art_com_table").append( "<tr><td colspan='6'><div id=" +
		 * extraRowId + " style='display: none;'>Hidden</div></td></tr>")
		 * .enhanceWithin();
		 */

	}

}

function showDetArt(codArticol) {

	$("#extra" + codArticol).slideToggle("fast");

}

function eliminaArticol(codArticol) {

	for (var i = 0; i < listaArticole.length; i++) {

		if (listaArticole[i].cod == codArticol) {

			listaArticole.splice(i, 1);
			break;
		}
	}

	afiseazaListaArticole();
	calculeazaTotalCmd();

}

function calculeazaTotalCmd() {

	var totalComanda = 0;
	for (var i = 0; i < listaArticole.length; i++) {
		totalComanda += listaArticole[i].pret * listaArticole[i].cant;

	}

	$('#divTotalCmd').html(totalComanda).css({
		'font-weight' : 'bold'
	}).data("totalCmd", totalComanda);

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

function afisSalveazaCmdButton() {

	if ($('#divClient').is(':visible') && $('#divArticole').is(':visible')
			&& $('#divDateLivrare').is(':visible'))
		$('#divSalveazaComanda').show();

}

function hidePageSections() {
	$('#divClient').hide();
	$('#divArticole').hide();
	$('#divDateLivrare').hide()
	$('#divSalveazaComanda').hide();
	$("#creare_comanda_select").val(0).change();
}

$('#salveazaComanda').click(function() {

	var comanda = new Object();

	comanda.codClient = globalDetaliiClient.cod;
	comanda.codAgent = userObj.codPers;
	comanda.aprobaSD = aprobaSD;
	comanda.aprobaDV = aprobaDV;
	comanda.unitLog = userObj.unitLog;
	comanda.dateLivrare = getDateLivrare();
	comanda.listArticole = getArticoleComanda(listaArticole);
	comanda.totalComanda = $('#divTotalCmd').data('totalCmd');

	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'salveazaComanda',
		data : JSON.stringify(comanda),
		success : function(data) {
			showAlertStatus(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function() {

		}

	});

	$.mobile.loading('hide');

});

function showAlertStatus(statusCreare) {

	if (statusCreare.success) {
		resetCmdData();
		hidePageSections();
	}

	showAlertDialog("Status", statusCreare.message);

}

function showAlertDialog(tipAlert, mesajAlert) {
	$('#tipAlertC').text(tipAlert);
	$('#textAlertC').text(mesajAlert);
	$.mobile.changePage('#dialogCreare', {
		transition : "none"
	});
}
