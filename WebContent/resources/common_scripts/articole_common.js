
var colorGroup1 = '#7A8FF0';
var colorGroup2 = '#5EB87B';

function tableStocArticol(codArticol, dateStoc) {

	if (dateStoc.um == null)
		dateStoc.um = '';

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

	return stocTable;

}

function tablePretArticol(codArticol, datePret) {

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
	$('<td></td>').text('Отстъпка клиент').attr('style', 'width:30%').css(
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

	return pretTable;

}
