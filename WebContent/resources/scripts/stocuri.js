var userObj;
var numeArtSel;

$(document).on('pagebeforeshow', '#stocuri', function() {
	$('#cautaArticol').parent().css('margin-left', '80px');
	$('#cautaArticol').parent().css('margin-right', '80px');

	$('#numeArtSel').parent().css('margin-left', '50px');
	$('#numeArtSel').parent().css('margin-right', '50px');

});

$(document).on('pageshow', '#stocuri', function() {

	clearScreen();

	$(":input[name= 'radio-articol']").on('change', function() {
		var clicked = $(this).val();
		clearScreen();

		$('#codArticol').focus();

	});

});

$(document).on('pagecreate', '#stocuri', function() {

	setColapsibleArticolListenerStoc();

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

function setColapsibleArticolListenerStoc() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var codArt = data.target.id;

		var contentId = '#' + codArt;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		getStoc(codArt);

	});

	$('#articoleset').bind('collapsiblecollapse', function(data) {

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
				+ listArticole[u].nume
				+ "</div></h2><div id='articol"
				+ listArticole[u].cod
				+ "'></div></div>";

		$("#articoleset").append(content).collapsibleset("refresh");
	}

}

function getStoc(codArticol) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'stoc',
		data : ({
			codArticol : codArticol,
			filiala : userObj.unitLog
		}),
		success : function(data) {
			afiseazaStoc(codArticol, data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afiseazaStoc(articolCod, articolStoc) {

	var contentId = '#articol' + articolCod;

	var content = '<table data-role="table" id="stocTable" class="ui-responsive table" data-mode="reflow"  style="width:100%;padding: 10px;">';

	content += '<tr>';
	content += '<td style="width:25%">Cantitate</td>';
	content += '<td style="width:25%">Um</td>';
	content += '<td style="width:25%">Depozit</td>';
	content += '</tr>';

	for (var i = 0; i < articolStoc.length; i++) {
		content += '<tr><td>' + articolStoc[i].cantitate + '</td>';
		content += '<td>' + articolStoc[i].um + '</td>';
		content += '<td>' + articolStoc[i].depozit + '</td></tr>';

	}

	content += '</table>';

	$("#stocTable").remove();
	$(content).appendTo(contentId).enhanceWithin();

}
