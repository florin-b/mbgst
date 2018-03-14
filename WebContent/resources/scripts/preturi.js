var userObj;
var numeArtSel;

$(document).on('pagebeforeshow', '#preturi', function() {
	$('#cautaArticol').parent().css('margin-left', '80px');
	$('#cautaArticol').parent().css('margin-right', '80px');

	$('#numeArtSel').parent().css('margin-left', '50px');
	$('#numeArtSel').parent().css('margin-right', '50px');

});

$(document).on('pageshow', '#preturi', function() {

	$(":input[name= 'radio-articol']").on('change', function() {
		var clicked = $(this).val();

		$('#codArticol').focus();

	});

});

$(document).on('pagecreate', '#preturi', function() {

	setColapsibleArticolListenerPret();

});

function cautaArticol() {

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

}

function afiseazaListArticole(listArticole) {

	$("#articoleset").empty();

	for (var u = 0; u < listArticole.length; u++) {
		var content = "<div data-role='collapsible' data-content-theme='a' id='"
				+ listArticole[u].cod
				+ "'><h2><div id='numeart"
				+ listArticole[u].cod
				+ "'>"
				+ listArticole[u].cod
				+ ' - '
				+ listArticole[u].nume
				+ "</div></h2><div id='articol"
				+ listArticole[u].cod + "'></div></div>";

		$("#articoleset").append(content).collapsibleset("refresh");
	}

}

function setColapsibleArticolListenerPret() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var codArt = data.target.id;
		var contentId = '#' + codArt;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		getPret(codArt);

	});

	$('#articoleset').bind('collapsiblecollapse', function(data) {

	});

}

function getPret(codArticol) {

	var cautaPret = new Object();
	cautaPret.codArticol = codArticol;
	cautaPret.filiala = userObj.unitLog;
	cautaPret.departament = userObj.codDepart;
	cautaPret.codClient = "4110006325"// trebuie schimbat cu unul generic;
	cautaPret.depozit = " ";
	cautaPret.cantitate = "1";
	cautaPret.um = " ";

	$.mobile.loading('show');

	$.ajax({
		type : 'POST',
		url : 'pret',
		data : JSON.stringify(cautaPret),
		success : function(data) {
			afiseazaPret(codArticol, data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		},
		dataType : 'json',
		contentType : 'application/json',

	});

}

function afiseazaPret(articolCod, articolPret) {

	var contentId = '#articol' + articolCod;

	$(contentId).html('');

	var pretTable = $('<table></table>').attr({
		id : 'pretTable',
		width : "100%",
		border : "0"
	}).addClass('pretTable');

	$('#pretTable tbody').remove();

	var row = $('<tr></tr>').appendTo(pretTable);
	$('<td></td>').attr('style', 'width:25%').text('Pret').appendTo(row);
	$('<td></td>').text('Um').appendTo(row);

	row = $('<tr></tr>').appendTo(pretTable);
	$('<td></td>').attr('style', 'width:25%').text(articolPret.pret).appendTo(
			row);
	$('<td></td>').text(articolPret.um).appendTo(row);

	$('<br>').appendTo($(contentId));
	$(pretTable).appendTo($(contentId));
	$('<br>').appendTo($(contentId));

}

function afiseazaPret_old(articolCod, articolPret) {

	var contentId = '#articol' + articolCod;

	var content = '<table data-role="table" id="pretTable" class="ui-responsive table" data-mode="reflow"  style="width:100%;padding: 10px;">';

	content += '<tr>';
	content += '<td style="width:25%">Pret</td>';
	content += '<td style="width:25%">Um</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td style="width:25%">' + articolPret.pret + '</td>';
	content += '<td style="width:25%">' + articolPret.um + '</td>';
	content += '</tr>';

	content += '</table>';

	$("#pretTable").remove();
	$(content).appendTo(contentId).enhanceWithin();

}
