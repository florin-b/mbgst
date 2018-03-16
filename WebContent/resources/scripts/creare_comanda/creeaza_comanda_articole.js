var userObj;
var numeArtSel;
var comandaId;
var pretInit;
var numeArticolCurent;
var listaArticole = [];
var departArticol;
var aprobaSD = false;
var aprobaDV = false;
var depozitSelectat;

var colorGroup1 = '#7A8FF0';
var colorGroup2 = '#5EB87B';

$(document).on('pagebeforeshow', '#crearecomanda', function() {

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
			showAlertDialog("Atentie!", JSON.stringify(exception));

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
			showAlertDialog("Atentie!", JSON.stringify(exception));
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
			}).addClass("stocArtTable");

	var row = $('<tr></tr>');
	$(row).appendTo(stocTable);

	$('<td></td>').text('Stoc').attr('style', 'width:30%').appendTo(row);

	$('<td></td>', {
		text : dateStoc.cantitate,
		id : stocId,
		style : 'width:25%;text-align:right;'
	}).appendTo(row);

	$('<td></td>').text(dateStoc.um).attr('style', 'width:40%').appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(stocTable);

	$('<td></td>').text('Cantitate').attr('style', 'width:30%').appendTo(row);

	var textCant = $('<input/>', {
		type : 'text',
		id : 'cant' + codArticol,
		value : 1,
		width : '40%',
		style : 'text-align:right;'

	});

	var tdCant = $('<td></td>', {
		width : '25%'
	}).appendTo(row);
	$(textCant).appendTo(tdCant).textinput();

	$('<td></td>', {
		text : dateStoc.um,
		style : 'width:10%',
		id : 'umVanz' + codArticol,
	}).appendTo(row);

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

	var cantArticol = $('#cant' + codArticol).val().trim();
	var umVanz = $('#umVanz' + codArticol).html();

	if (!$.isNumeric(cantArticol)) {
		showAlertDialog("Atentie!", "Cantitate invalida.");
		return;
	}

	var cautaPret = new Object();
	cautaPret.codArticol = codArticol;
	cautaPret.filiala = userObj.unitLog;
	cautaPret.departament = userObj.codDepart;
	cautaPret.codClient = globalDetaliiClient.cod;
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
			showAlertDialog("Atentie!", JSON.stringify(exception));
		},
		dataType : 'json',
		contentType : 'application/json',

	});

}

function afisPretArticol(codArticol, datePret) {

	pretInit = datePret.pret;

	var contentId = '#articol' + codArticol;

	$('#pretTable' + codArticol).remove();

	var pretTable = $(
			'<table data-role="table" class="ui-responsive table" data-mode="reflow"></table>')
			.attr('id', "pretTable" + codArticol).addClass("pretArtTable");

	var row = $('<tr></tr>');
	$(row).appendTo(pretTable);

	$('<td></td>').text('Pret').attr('style', 'width:30%').appendTo(row);

	var textPret = $('<input/>', {
		type : 'text',
		id : 'pret_art' + codArticol,
		value : datePret.pret,
		width : '40%',
		maxlength : 10,
		style : 'text-align:right;'

	}).keyup(function() {
		setProcentReducere(codArticol, datePret);
	});

	var tdPret = $('<td></td>', {

	}).appendTo(row);

	$(textPret).appendTo(tdPret).textinput();

	var infoPret = datePret.moneda + ' / ' + datePret.multiplu + ' '
			+ datePret.um;

	$('<td></td>', {
		style : 'width:40%'
	}).text(infoPret).appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);
	$('<td></td>').text('Reducere').attr('style', 'width:30%').appendTo(row);

	var textProcRed = $('<input/>', {
		type : 'text',
		id : 'procent_art' + codArticol,
		value : 0,
		width : '40%',
		maxlength : 3,
		style : 'text-align:right;'

	}).keyup(function() {
		setPretReducere(codArticol, datePret);
	});

	var tdProcRed = $('<td></td>', {
		width : '25%'
	}).appendTo(row);
	$(textProcRed).appendTo(tdProcRed).textinput();

	$('<td></td>').text('%').appendTo(row);

	row = $('<tr></tr>');
	$('<td></td>').html('<br>').appendTo(row);
	$(row).appendTo(pretTable);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);

	var valTVA = getTVAFormula(datePret);

	var pretCuTva = parseFloat(datePret.pret) + parseFloat(valTVA);

	$('<td></td>').text('Pret cu tva').attr('style', 'width:30%').css('color',
			colorGroup1).appendTo(row);
	$('<td></td>', {
		id : 'prettva' + codArticol,
		style : 'text-align:right;'
	}).text(pretCuTva.toFixed(2)).css('color', colorGroup1).appendTo(row);

	row = $('<tr></tr>');
	$(row).appendTo(pretTable);
	$('<td></td>').text('Discount client').attr('style', 'width:30%').css(
			'color', colorGroup1).appendTo(row);

	var procentReducereClient = (100 - (datePret.pret / datePret.pretLista) * 100)
			.toFixed(2);

	$('<td></td>', {
		text : procentReducereClient,
		style : 'text-align:right;'
	}).css('color', colorGroup1).appendTo(row);
	$('<td></td>').text('%').css('color', colorGroup1).appendTo(row);

	row = $('<tr></tr>');
	$('<td></td>').html('<br>').appendTo(row);
	$(row).appendTo(pretTable);

	for (var u = 0; u < datePret.infoArticolDesc.length; u++) {

		row = $('<tr></tr>');
		$(row).appendTo(pretTable);

		var tokDesc = datePret.infoArticolDesc[u].split('#');

		$('<td></td>').text(tokDesc[0]).css('color', colorGroup2).appendTo(row);
		$('<td></td>', {
			text : tokDesc[1],
			style : 'text-align:right;'
		}).css('color', colorGroup2).appendTo(row);
		$('<td></td>').text(tokDesc[2]).css('color', colorGroup2).appendTo(row);

	}

	var articolSelectat = new Object();
	articolSelectat.nume = numeArticolCurent;
	articolSelectat.cod = codArticol;
	articolSelectat.um = datePret.um;
	articolSelectat.discountClient = procentReducereClient;
	articolSelectat.multiplu = datePret.multiplu;
	articolSelectat.pretLista = datePret.pretLista;
	articolSelectat.moneda = datePret.moneda;
	articolSelectat.cantUmBaza = datePret.cantUmBaza;
	articolSelectat.umBaza = datePret.umBaza;
	articolSelectat.infoArticol = datePret.infoArticol;
	articolSelectat.discountClient = procentReducereClient;

	row = $('<tr></tr>');
	$('<td></td>').html('<br>').appendTo(row);
	$(row).appendTo(pretTable);

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
		showAlertDialog('Info', 'Stoc insuficient.');
		return;
	}

	var idTextPret = '#pret_art' + articolSelectat.cod;
	var idTextProcent = '#procent_art' + articolSelectat.cod;

	if (!$.isNumeric($(idTextPret).val())) {
		showAlertDialog("Atentie!", "Valoare pret invalida.");
		return;
	}

	if (!$.isNumeric($(idTextProcent).val())) {
		showAlertDialog("Atentie!", "Valoare procent invalida.");
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

	if (articolSelectat.procentReducere > 0) {
		aprobaSD = true;
		aprobaDV = true;
	}

	$('#' + articolSelectat.cod).collapsible("collapse");

	eliminaArticolCreat(articolSelectat);
	listaArticole.push(articolSelectat);
	adaugaArticol(articolSelectat, listaArticole.length - 1);

}

function adaugaArticol(articolSelectat, poz) {

	var swatch = [ '#f2f2f2', '#FFF0F5' ];

	var ulArticole = $('#listArticoleCreare');

	var articol = $('<li></li>', {
		html : getTabelaArticolNou(articolSelectat, poz)
	}).css("background-color", swatch[poz / 2]);

	$(ulArticole).append(articol);

	calculeazaTotalCmdCreare();
	afisSalveazaCmdButton();

}

function eliminaArticolCreat(articol) {

	for (var i = 0; i < listaArticole.length; i++) {

		if (listaArticole[i].cod == articol.cod) {
			listaArticole.splice(i, 1);

			break;
		}
	}

	afiseazaListaArticole();
	calculeazaTotalCmdCreare();

}

function afiseazaListaArticole() {

	$('#listArticoleCreare').empty();

	for (var i = 0; i < listaArticole.length; i++) {
		adaugaArticol(listaArticole[i], i);
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
	}).text(articol.procentReducere).appendTo(row);
	$('<td></td>').text('%').appendTo(row);

	return articoleTable;
}

function calculeazaTotalCmdCreare() {

	var totalComanda = 0;
	for (var i = 0; i < listaArticole.length; i++) {
		totalComanda += listaArticole[i].pretUnitar
				* listaArticole[i].cantitate;

	}

	$('#divTotalCmd').html(totalComanda.toFixed(2)).css({
		'font-weight' : 'bold'
	}).data("totalCmd", totalComanda.toFixed(2));

}

function setProcentReducere(codArticol, datePret) {

	var idTextPret = '#pret_art' + codArticol;

	var idTextProcent = '#procent_art' + codArticol;

	var idTextPretCuTva = '#prettva' + codArticol;

	var pretClient = $(idTextPret).val();

	var procentReducere = (1 - Number(pretClient / pretInit)) * 100;

	$(idTextProcent).val(Number(procentReducere).toFixed(2));

	var valTVA = getTVAFormula(datePret);

	var pretCuTva = parseFloat($(idTextPret).val()) + parseFloat(valTVA);

	$(idTextPretCuTva).text(pretCuTva.toFixed(2));

}

function setPretReducere(codArticol, datePret) {

	var idTextPret = '#pret_art' + codArticol;

	var idTextProcent = '#procent_art' + codArticol;

	var procent = $(idTextProcent).val();

	var valoarePret = pretInit - (procent / 100) * pretInit;
	$(idTextPret).val(Number(valoarePret).toFixed(2));

	var idTextPretCuTva = '#prettva' + codArticol;

	var valTVA = getTVAFormula(datePret);

	var pretCuTva = parseFloat($(idTextPret).val()) + parseFloat(valTVA);

	$(idTextPretCuTva).text(pretCuTva.toFixed(2));

}

function afisSalveazaCmdButton() {

	if ($('#divClient').is(':visible') && $('#divArticole').is(':visible')
			&& $('#divDateLivrare').is(':visible'))
		$('#divSalveazaComanda').show();

}

function hidePageSections() {
	$('#divClient').hide();
	$('#divArticole').hide();
	$('#divDateLivrare').hide();
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
	comanda.codDepart = userObj.codDepart;
	comanda.tipUser = userObj.tipAngajat;
	comanda.dateLivrare = getDateLivrare();
	comanda.listArticole = getArticoleComanda(listaArticole);
	comanda.totalComanda = $('#divTotalCmd').data('totalCmd');

	if (!valideazaComandaNoua(comanda)) {
		return;
	}

	$.ajax({
		type : 'POST',
		url : 'salveazaComanda',
		data : JSON.stringify(comanda),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			showAlertStatus(data);

		},
		dataType : 'json',
		contentType : 'application/json',

	});

});

function showAlertStatus(statusCreare) {

	if (statusCreare.success) {
		resetCmdData();
		hidePageSections();
	}

	showAlertDialog("Status", statusCreare.message);

}

function clearScreenArticole(e) {

	e.preventDefault();

	$('#codArticol').val('');
	listaArticole = [];
	$("#articoleset").empty();

}

function showAlertDialog(tipAlert, mesajAlert) {
	$('#tipAlertC').text(tipAlert);
	$('#textAlertC').text(mesajAlert);
	$.mobile.changePage('#dialogCreare', {
		transition : "none"
	});
}

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}
