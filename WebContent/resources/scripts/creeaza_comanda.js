var userObj;
var numeArtSel;
var comandaId;
var globalDetaliiClient;

$(document).on('pagebeforeshow', '#crearecomanda', function() {

	//$('#select_client_div').css('margin-left', '10px');
	//$('#select_client_div').css('margin-right', '20px');

});

$(document).on('pageshow', '#crearecomanda', function() {

});

$(document).on('pagecreate', '#crearecomanda', function() {

	setColapsibleClientiListener();

	$('#select_client_div').bind('collapsibleexpand', function(data) {

		$("#alegeClntDiv").hide();
		// highlist header

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
		var content = "<div data-role='collapsible'  data-content-theme='a' id='"
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
			afisdetaliiClient(data);
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afisdetaliiClient(detaliiClient) {

	var content = '<table data-role="table" id="clientTable" class="ui-responsive table" data-mode="reflow" >';

	var adresa = detaliiClient.adresa.numeJudet + ' '
			+ detaliiClient.adresa.localitate + ' '
			+ detaliiClient.adresa.strada;

	content += '<tr>';
	content += '<td style="width:25%">Adresa</td>';
	content += '<td >' + adresa + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Limita client</td>';
	content += '<td >' + detaliiClient.stareClient.limitaCredit + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Rest credit</td>';
	content += '<td >' + detaliiClient.stareClient.restCredit + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">Stare client</td>';
	content += '<td >' + detaliiClient.stareClient.stare + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td colspan=2><input type="button" name="getComenzi" id="selecteazaClient" onClick="selecteazaClient();" value="Selecteaza" /></td>';
	content += '</tr>';

	content += '</table>';

	var contentId = '#client' + detaliiClient.cod;

	globalDetaliiClient = detaliiClient;

	$("#datelivrareTable").remove();
	$(content).appendTo(contentId).enhanceWithin();

}

function selecteazaClient() {

	$("#clientiset").empty();
	$('#numeClient').val('');

	// $("#divCautaClient").hide();
	// $("#alegeClntDiv").show();

	$('#clientComanda').text(globalDetaliiClient.stareClient.numeClient);

	// $('#' + globalDetaliiClient.cod).collapsible("collapse");
	$('#inner_optiuni_div').collapsible("collapse");

}
