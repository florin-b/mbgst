var userObj;
var numeArtSel;
var comandaId;

$(document).on('pagebeforeshow', '#afiscomanda', function() {

	$('#optiuni_div').parent().css('margin-left', '10px');
	$('#optiuni_div').parent().css('margin-right', '10px');

	$('#comenziset').parent().css('margin-left', '10px');
	$('#comenziset').parent().css('margin-right', '10px');

});

$(document).on('pageshow', '#afiscomanda', function() {

	getComenziAfisare();

});

$(document).on('pagecreate', '#afiscomanda', function() {

	setColapsibleListener();

})

function setColapsibleListener() {
	$('#comenziset').bind('collapsibleexpand', function(data) {

		var contentId = '#' + data.target.id;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		setContentComanda(data.target.id);

	});
}

function setContentComanda(idComanda) {

	comandaId = idComanda;

	getDetaliiComanda(idComanda);

}

function getComenziAfisare() {

	userObj = JSON.parse($('#userbean').text());

	$.mobile.loading('show');

	var cautareComanda = new Object();

	cautareComanda.interval = '1';
	cautareComanda.tipComanda = '1';
	cautareComanda.codAngajat = userObj.codPers;

	cautaComandaService(cautareComanda);

}

function afiseazaComenziHeader(listComenzi) {

	afiseazaComenziHeaderColapse(listComenzi);

}

function afiseazaComenziHeaderColapse(listComenzi) {

	for (var u = 0; u < listComenzi.length; u++) {
		var content = "<div data-role='collapsible' data-content-theme='a' id='"
				+ listComenzi[u].id
				+ "'><h2>"
				+ afisComandaHeader(listComenzi[u])
				+ "</h2><div id='content"
				+ listComenzi[u].id + "'></div></div>";

		$("#comenziset").append(content).collapsibleset("refresh");
	}

}

function getDetaliiComanda(idComanda) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getdetcom',
		data : ({
			idCmd : idComanda
		}),
		success : function(data) {
			$.mobile.loading('hide');
			afisDetaliiComanda(data);

		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisComandaHeader(comanda) {

	var content = '<table data-role="table" id="comenziTable" class="ui-responsive" data-mode="reflow" >';

	content += '<tr>';
	content += '<td style="width:80%"><b>' + comanda.id + '</b></td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>' + comanda.numeClient + '</td>';
	content += '<td>' + comanda.valoare + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>' + comanda.dataCreare + '</td>';
	content += '<td></td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>' + getStareComanda(comanda.stare) + '</td>';
	content += '<td></td>';
	content += '</tr>';

	content += '</table>';

	return content;
}

function cautaComenzi() {
	$('#inner_optiuni_div').collapsible("collapse");

	var cautareComanda = new Object();

	cautareComanda.interval = $("#intervalCautare").val();
	cautareComanda.tipComanda = $("#tipComanda").val();
	cautareComanda.codAngajat = userObj.codPers;

	cautaComandaService(cautareComanda);

}

function cautaComandaService(cautareComanda) {
	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getComAfis',
		data : cautareComanda,
		success : function(data) {
			afiseazaComenziHeader(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			$.mobile.loading('hide');
		}

	});

}

function afisDetaliiComanda(detaliiComanda) {

	var strArticole = '';

	var swatch = [ '#f2f2f2', '#FFF0F5' ];

	var backStyle = '';

	var listArticole = $('<ul></ul>', {

	}).attr('data-role', 'listview').attr('data-inset', 'true');

	for (var u = 0; u < detaliiComanda.listArticole.length; u++) {

		var articol = $('<li />', {
			html : afisArticolComanda(detaliiComanda.listArticole[u])
		});

		$(listArticole).append(articol);

	}

	var dateLivrare = $('<div></div>').append(
			afisdateLivrareComanda(detaliiComanda.dateLivrare));

	$(dateLivrare).append(listArticole);

	var contentId = '#content' + comandaId;

	$(contentId).html(dateLivrare).enhanceWithin();

}

function afisDetaliiComanda_old(detaliiComanda) {

	var strArticole = '';

	var swatch = [ '#f2f2f2', '#FFF0F5' ];

	var backStyle = '';

	for (var u = 0; u < detaliiComanda.listArticole.length; u++) {

		backStyle = 'background:' + swatch[u % 2];

		strArticole += '<li data-rowid = ' + detaliiComanda.listArticole[u].cod
				+ ' style=' + backStyle + '>' + '<div  id ='
				+ detaliiComanda.listArticole[u].cod + '>'
				+ afisArticolComanda(detaliiComanda.listArticole[u]) + '</div>'
				+ '</li>';

	}

	var dateLivrare = '<div>'
			+ afisdateLivrareComanda(detaliiComanda.dateLivrare) + '</div><br>';

	var articole = '<div><ul data-role="listview"  data-inset="true" class="ui-alt-icon"'
			+ 'style="overflow: auto;  list-style: none;">'
			+ strArticole
			+ '</ul></div><br><br>';

	var contentId = '#content' + comandaId;

	$(contentId).html(dateLivrare + articole).enhanceWithin();

}

function afisArticolComanda(articol) {

	var articoleTable = $('<table></table>').attr({
		id : "articoleTable",
		width : "100%",
		border : "0"

	}).addClass('dateLivrareTable');

	var row = $('<tr></tr>').appendTo(articoleTable);
	$('<td></td>').attr('style', 'width:80%').attr('style', 'font-weight:bold')
			.text(articol.nume).appendTo(row);
	$('<td></td>').attr('style', 'width:10%').text(articol.cantitate.valoare)
			.appendTo(row);
	$('<td></td>').text(articol.cantitate.um).appendTo(row);

	row = $('<tr></tr>').appendTo(articoleTable);
	$('<td></td>').attr('style', 'width:80%').text(articol.cod).appendTo(row);
	$('<td></td>').attr('style', 'width:10%').text(articol.pretUnitar)
			.appendTo(row);
	$('<td></td>').text('RON').appendTo(row);

	row = $('<tr></tr>').appendTo(articoleTable);
	$('<td></td>').text(articol.depozit).appendTo(row);
	$('<td></td>').attr('style', 'width:10%').text(articol.procReducere)
			.appendTo(row);
	$('<td></td>').text('RON').appendTo(row);

	return articoleTable;
}

function afisArticolComanda_old(articol) {

	var content = '<table data-role="table" id="articoleTable" class="ui-responsive" data-mode="reflow" >';

	content += '<tr>';
	content += '<td style="width:80%"><b>' + articol.nume + '</b></td>';
	content += '<td style="width:10%">' + articol.cantitate.valoare + '</td>';
	content += '<td>' + articol.cantitate.um + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>' + articol.cod + '</td>';
	content += '<td style="width:10%">' + articol.pretUnitar + '</td>';
	content += '<td>RON</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>' + articol.depozit + '</td>';
	content += '<td style="width:10%">' + articol.procReducere + '</td>';
	content += '<td>%</td>';
	content += '</tr>';

	content += '</table>';

	return content;
}

function afisdateLivrareComanda(dateLivrare) {

	var dateLivrTable = $('<table></table>').attr({
		id : "dateLivrareTable",
		width : "100%",
		border : "0"

	}).addClass('dateLivrareTable');

	var row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('colspan', '2').attr('style', 'font-weight:bold').text(
			'Date livrare').appendTo(row);

	row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('style', 'width:5%').text('Judet').appendTo(row);
	$('<td></td>').text(dateLivrare.adresaLivrare.numeJudet).appendTo(row);

	row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('style', 'width:5%').text('Localitate').appendTo(row);
	$('<td></td>').text(dateLivrare.adresaLivrare.localitate).appendTo(row);

	row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('style', 'width:25%').text('Strada').appendTo(row);
	$('<td></td>').text(dateLivrare.adresaLivrare.strada).appendTo(row);

	row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('style', 'width:25%').text('Pers. contact').appendTo(
			row);
	$('<td></td>').text(dateLivrare.persoanaContact).appendTo(row);

	row = $('<tr></tr>').appendTo(dateLivrTable);
	$('<td></td>').attr('style', 'width:25%').text('Telefon').appendTo(row);
	$('<td></td>').text(dateLivrare.telPersContact).appendTo(row);

	return dateLivrTable;

}

function getStareComanda(intStare) {

	var retVal = "";

	switch (Number(intStare)) {
	case -1:
		retVal = "Comanda NU a fost salvata.";
		break;

	case 0:
		retVal = "Comanda emisa.";
		break;

	case 1:
		retVal = "Comanda in curs de aprobare.";
		break;

	case 2:
		retVal = "Comanda aprobata.";
		break;

	case 3:
		retVal = "Comanda respinsa.";
		break;

	case 4:
		retVal = "Comanda supusa unor conditii.";
		break;

	case 5:
		retVal = "Comanda stearsa.";
		break;

	case 6:
		retVal = "Cmd. angaj. in curs de aprobare.";
		break;

	case 7:
		retVal = "Cmd. angaj. aprobata.";
		break;

	case 8:
		retVal = "Cmd. angaj. respinsa.";
		break;

	case 9:
		retVal = "Comanda invalida. Stoc insuficient.";
		break;

	case 10:
		retVal = "Comanda invalida datorita limitei de credit.";
		break;

	case 15:
		retVal = "Comanda facturata.";
		break;

	case 20:
		retVal = "Comanda simulata cu rezervare stoc.";
		break;

	case 21:
		retVal = "Comanda simulata fara rezervare stoc.";
		break;

	case 30:
		retVal = "Comanda nealocata pe borderou";
		break;

	case 31:
		retVal = "Comanda alocata pe borderou";
		break;

	}
	return retVal;
}
