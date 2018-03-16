var userObj;
var numeArtSel;
var comandaId;
var globalDetaliiClient;

$(document).on('pagebeforeshow', '#crearecomanda', function() {

});

$(document).on('pageshow', '#crearecomanda', function() {

});

$(document).on('pagecreate', '#crearecomanda', function() {

	setColapsibleClientiListener();

	$('#select_client_div').bind('collapsibleexpand', function(data) {

		$("#alegeClntDiv").hide();

	});

	$("#selectArticoleDiv").on("onShowArticoleDiv", clearScreenArticole);

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

	var cautareClient = new Object();

	cautareClient.nume = $('#numeClient').val();

	$.ajax({
		type : 'POST',
		url : 'cautaclient',
		data : JSON.stringify(cautareClient),
		dataType : 'json',
		contentType : 'application/json',
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			afisClienti(data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));

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

	var cautareClient = new Object();

	cautareClient.nume = $('#numeClient').val();

	$.ajax({
		type : 'GET',
		url : 'getdetaliiclient',
		data : ({
			codClient : codClient1
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			afisDetaliiClient(data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
		}

	});

}

function afisDetaliiClient(detaliiClient) {

	var clientTable = $('<table></table>').attr({
		id : "clientTable",
		width : "100%",
		border : "0",

	}).addClass("clientDetTable");

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
			detaliiClient.stareClient.limitaCredit.toFixed(2)).appendTo(row);

	row = $('<tr></tr>').appendTo(clientTable);

	$('<td></td>').attr('style', 'width:20%').text('Rest credit').appendTo(row);
	$('<td></td>').attr('style', 'width:70%').text(
			detaliiClient.stareClient.restCredit.toFixed(2)).appendTo(row);

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

	$(clientTable).appendTo($('#client' + detaliiClient.cod));

}

function selecteazaClient(client) {

	globalDetaliiClient = client;

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

		if (globalDetaliiClient == null) {
			showAlertDialog("Atentie!", "Selectati mai intai clientul");
			return;
		}

		$("#selectArticoleDiv").trigger("onShowArticoleDiv");
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

	$('#codJudet').val("00").change();
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

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}
