var userObj;
var numeArtSel;
var comandaId;
var globalDetaliiClient;

$(document).on('pagebeforeshow', '#crearecomanda', function() {

	// $('#select_client_div').css('margin-left', '10px');
	// $('#select_client_div').css('margin-right', '20px');

});

$(document).on('pageshow', '#crearecomanda', function() {

});

$(document).on('pagecreate', '#crearecomanda', function() {

	setColapsibleClientiListener();

	$('#select_client_div').bind('collapsibleexpand', function(data) {

		$("#alegeClntDiv").hide();
		
	});

});

$('#alegeClnt').click(function() {

	$("#clientiset").empty();

	$("#divCautaClient").show();
	$("#alegeClntDiv").hide();
	$('#numeClient').val('');

});

$('#cautaClient').click(function() {

	if ($('#numeClient').val().trim() == '')
		return;

	$.mobile.loading('show');

	var cautareClient = new Object();

	cautareClient.nume = $('#numeClient').val();

	$.ajax({
		type : 'GET',
		url : 'cautaclient',
		data : cautareClient,
		success : function(data) {
			afisClienti(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

});

function afisClienti(listClienti) {

	$("#clientiset").empty();

	for (var u = 0; u < listClienti.length; u++) {
		var content = "<div data-role='collapsible'  data-content-theme='a'  id='"
				+ listClienti[u].cod
				+ "'><h2>"
				+ listClienti[u].nume
				+ "</h2><div id='client"
				+ listClienti[u].cod
				+ "'></div></div>";

		$("#clientiset").append(content).collapsibleset("refresh");
	}

	$('#clientiset').parent().css('max-height', '500px');

	$('#clientiset').parent().css('overflow', 'auto');

}

function setColapsibleClientiListener() {
	$('#clientiset').bind('collapsibleexpand', function(data) {

		var codClnt = data.target.id;

		var contentId = '#' + codClnt;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		getDetaliiClient(codClnt);

	});
}

function getDetaliiClient(codClient1) {

	$.mobile.loading('show');

	var cautareClient = new Object();

	cautareClient.nume = $('#numeClient').val();

	$.ajax({
		type : 'GET',
		url : 'getdetaliiclient',
		data : ({
			codClient : codClient1
		}),
		success : function(data) {
			$.mobile.loading('hide');
			afisDetaliiClient(data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisDetaliiClient(detaliiClient) {

	var clientTable = $('<table></table>').attr({
		id : "clientTable",
		width : "100%",
		border : "0",

	}).addClass("detaliiTable");

	$('#clientTable tbody').remove();

	var adresa = detaliiClient.adresa.numeJudet + ' '
			+ detaliiClient.adresa.localitate + ' '
			+ detaliiClient.adresa.strada;

	var row = $('<tr></tr>').appendTo(clientTable);

	$('<td></td>').attr('style', 'width:20%').text('Adresa').appendTo(row);
	$('<td></td>').attr('style', 'width:70%').text(adresa).appendTo(row);

	row = $('<tr></tr>').appendTo(clientTable);

	$('<td></td>').attr('style', 'width:20%').text('Limita credit').appendTo(
			row);
	$('<td></td>').attr('style', 'width:70%').text(
			detaliiClient.stareClient.limitaCredit).appendTo(row);

	row = $('<tr></tr>').appendTo(clientTable);

	$('<td></td>').attr('style', 'width:20%').text('Rest credit').appendTo(row);
	$('<td></td>').attr('style', 'width:70%').text(
			detaliiClient.stareClient.restCredit).appendTo(row);

	if (detaliiClient.stareClient.isBlocat) {

		row = $('<tr></tr>').appendTo(clientTable);

		$('<td></td>').attr('style', 'width:20%').text('Stare client')
				.appendTo(row);
		$('<td></td>').attr('style', 'width:70%').text('Client blocat')
				.appendTo(row);

	}

	if (!detaliiClient.stareClient.isBlocat) {
		row = $('<tr></tr>').appendTo(clientTable);

		var tdSelectClient = $('<td></td>', {
			colspan : '2',
			style : 'text-align : center'
		}).appendTo(row);

		var btnSelectClient = $('<button>', {
			text : 'Selecteaza',
			style : 'width:100%'

		}).bind('click', {
			client : detaliiClient
		}, function(event) {
			selecteazaClient(event.data.client);
		});

		btnSelectClient.appendTo(tdSelectClient).buttonMarkup();
	}

	globalDetaliiClient = detaliiClient;

	$('#client' + detaliiClient.cod).css('background-color', '#FFFFF2');

	$(clientTable).appendTo($('#client' + detaliiClient.cod));

}

function selecteazaClient(client) {

	$("#clientiset").empty();
	$('#numeClient').val('');

	$('#clientComanda').text(client.stareClient.numeClient);
	$('#inner_optiuni_div').collapsible("collapse");

	if ($('#divClient').css('display') == 'none')
		$('#divClient').show();
	
	afisSalveazaCmdButton();

	resetSelectOptions();

}

$('#creare_comanda_select').on('change', function() {
	var selectId = this.value;

	$('#selectClientDiv').hide();
	$('#selectArticoleDiv').hide();
	$('#selectDateLivrareDiv').hide();

	if (selectId == 0) {
		$('#selectClientDiv').hide();
		$('#selectArticoleDiv').hide();
		$('#selectDateLivrareDiv').hide();
	} else if (selectId == 1) {
		$('#selectClientDiv').show();

	} else if (selectId == 2) {
		$('#selectArticoleDiv').show();

	} else if (selectId == 3) {
		$('#selectDateLivrareDiv').show();

	}

});

function resetSelectOptions() {
	$('#selectClientDiv').hide();
	$('#selectArticoleDiv').hide();
	$('#selectDateLivrareDiv').hide();

	$("#creare_comanda_select").val("0").change();

}

function resetCmdData() {

	$('#clientComanda').html("");

	listaArticole = "";
	$("#art_table_body").empty();

	$('#numeJudet').val("");
	$('#localitate').val("");
	$('#strada').val("");
	$('#persContact').val("");
	$('#telPersContact').val("");
	$('#selectTipReducere').val("1").change();
	$('#selectDocInsotitor').val("1").change();
	$('#selectPlata').val("B").change();
	$('#selectResponsabil').val("AV").change();
	$('#selectTransport').val("TRAP").change();
	$('#dataLivrare').val("");
	$('#obsLivrare').val("");

	$('#datelivrare_table_afis tbody').remove();

	resetSelectOptions();

}
