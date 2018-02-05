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
		var content = "<div data-role='collapsible' data-content-theme='a' id='"
				+ listArticole[u].cod
				+ "'><h2><div id='numeart"
				+ listArticole[u].cod
				+ "'>"
				+ listArticole[u].nume
				+ "</div></h2><div id='articol"
				+ listArticole[u].cod
				+ "'></div><div id='obj1' style='display:none'>"
				+ JSON.stringify(listArticole[u]) + "</div></div>";

		$("#articoleset").append(content).collapsibleset("refresh");
	}

}

function setColapsibleArticolListener() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var codArt = data.target.id;

		var contentId = '#' + codArt;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		getDetaliiArticol(codArt);

		var numeArtId = '#numeart' + codArt;

		numeArticolCurent = $("#articoleset h2 " + numeArtId).text();

		var obj = jQuery.parseJSON($('#obj1').text());

		departArticol = obj.depart;

		// $('#numeart'+data.target.id).addClass('greybackcolor');

	});

	$('#articoleset').bind('collapsiblecollapse', function(data) {

		// $('#numeart'+data.target.id).removeClass('greybackcolor');

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

	var getPretFunc = 'getPretArticol(' + codArticol + ')';

	var content = '<table data-role="table" id="stocTable" class="ui-responsive table" data-mode="reflow"  style="width:100%;padding: 10px;">';

	content += '<tr>';
	content += '<td style="width:25%">Stoc</td>';
	content += '<td style="width:25%"><div id=' + stocId + '>'
			+ dateStoc.cantitate + '</div></td>';
	content += '<td style="width:10%">' + dateStoc.um + '</td>';
	content += '</tr>';

	var idTextCantitate = 'cant' + codArticol;

	content += '<tr>';
	content += '<td style="width:25%" >Cantitate</td>';
	content += '<td ><input type="text" value=1 id=' + idTextCantitate
			+ '></td>';
	content += '<td >' + dateStoc.um + '</td>';
	content += '<td><input type="button" name="pretArticol" id="pretArticol" onClick="'
			+ getPretFunc + '" value="Pret" /></td>';
	content += '</tr>';

	content += '</table>';

	$("#stocTable").remove();

	$("#pretTable").remove();

	$(content).appendTo(contentId).enhanceWithin();

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

	var content = '<table data-role="table" id="pretTable" class="ui-responsive table" data-mode="reflow" style="width:100%;padding: 10px;">';
	content += '<tr>';
	content += '<td style="width:25%">Pret</td>';

	var keyUpPretFunc = 'setProcentReducere(' + codArticol + ')';
	var keyUpProcFunc = 'setPretReducere(' + codArticol + ')';

	var idTextPret = 'pret' + codArticol;

	content += '<td style="width:25%"><input type="text" value='
			+ datePret.pret + ' id=' + idTextPret + ' onKeyUp=' + keyUpPretFunc
			+ '></td>';
	content += '<td >' + datePret.um + '</td>';

	content += '<td >Unitate pret:</td>';
	content += '<td >1 BUC</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Reducere</td>';

	var idTextProcent = 'procent' + codArticol;

	content += '<td style="width:25%"><input type="text" value=0 id='
			+ idTextProcent + ' onKeyUp=' + keyUpProcFunc + ' ></td>';
	content += '<td >%</td>';
	content += '</tr>';

	content += '<tr><td style="width:25%">Pret cu tva</td>';
	content += '<td >22</td>';
	content += '</tr>';

	content += '<tr><td style="width:25%">Discount client (%)</td>';
	content += '<td >2</td>';
	content += '</tr>';

	var articolSelectat = new Object();
	articolSelectat.nume = numeArticolCurent;
	articolSelectat.cod = codArticol;
	articolSelectat.um = datePret.um;

	content += '<tr>';
	content += '<td colspan=5><input type="button" name="adaugaArticol" id="adaugaArticol"  value="Adauga" /></td>';
	content += '</tr>';

	content += '</table>';

	$("#pretTable").remove();

	$(content).appendTo(contentId).enhanceWithin();

	var adaugaArticolButton = document.getElementById('adaugaArticol');

	adaugaArticolButton.addEventListener('click', handlerForAdaugaArt);

	function handlerForAdaugaArt() {
		trateazaArticol(articolSelectat);
	}

}

function trateazaArticol(articolSelectat) {

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

	var idTextPret = '#pret' + articolSelectat.cod;
	var idTextProcent = '#procent' + articolSelectat.cod;

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

}

function setProcentReducere(codArticol) {

	var idTextPret = '#pret' + codArticol;

	var idTextProcent = '#procent' + codArticol;

	var pretSchimbat = $(idTextPret).val();

	var procentReducere = (1 - Number(pretSchimbat / pretInit)) * 100;

	$(idTextProcent).val(Number(procentReducere).toFixed(2));

}

function setPretReducere(codArticol) {

	var idTextPret = '#pret' + codArticol;

	var idTextProcent = '#procent' + codArticol;

	var procent = $(idTextProcent).val();

	var valoarePret = pretInit - (procent / 100) * pretInit;

	$(idTextPret).val(Number(valoarePret).toFixed(2));

}

$('#salveazaComanda').click(function() {

	var comanda = new Object();
	var articole = new Object();
	var dateLivrare = new Object();

	comanda.codClient = globalDetaliiClient.cod;
	comanda.codAgent = userObj.codPers;
	comanda.aprobaSD = aprobaSD;
	comanda.aprobaDV = aprobaDV;
	comanda.unitLog = userObj.unitLog;
	comanda.dateLivrare = getDateLivrare();
	comanda.listArticole = getArticoleComanda(listaArticole);

	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'salveazaComanda',
		data : JSON.stringify(comanda),
		success : function(data) {
			$.mobile.loading('hide');

		},
		dataType : 'json',
		contentType : 'application/json',
		
		error : function(exception) {
			$.mobile.loading('hide');

		}

	});

	resetCmdData();

});
