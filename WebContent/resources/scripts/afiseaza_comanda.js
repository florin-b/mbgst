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

	getComenzi();

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

function getComenzi() {

	userObj = JSON.parse($('#userbean').text());

	$.mobile.loading('show');

	var cautareComanda = new Object();

	cautareComanda.interval = '1';
	cautareComanda.tipComanda = '1';
	cautareComanda.codAngajat = userObj.codPers;

	$.ajax({
		type : 'GET',
		url : 'getcom',
		data : cautareComanda,
		success : function(data) {
			afiseazaComenziHeader(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

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

	$.ajax({
		type : 'GET',
		url : 'getcom',
		data : cautareComanda,
		success : function(data) {
			afiseazaComenziHeader(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisDetaliiComanda(detaliiComanda) {

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

	var content = '<table data-role="table" id="datelivrareTable" class="ui-responsive table" data-mode="reflow" style="width:100%">';

	content += '<tr>';
	content += '<th colspan=2>Date livrare</th>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Judet</td>';
	content += '<td >' + dateLivrare.adresaLivrare.numeJudet + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Localitate</td>';
	content += '<td >' + dateLivrare.adresaLivrare.localitate + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Strada</td>';
	content += '<td >' + dateLivrare.adresaLivrare.strada + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Pers. contact</td>';
	content += '<td >' + dateLivrare.persoanaContact + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Telefon</td>';
	content += '<td >' + dateLivrare.telPersContact + '</td>';
	content += '</tr>';

	content += '</table>';

	return content;

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
