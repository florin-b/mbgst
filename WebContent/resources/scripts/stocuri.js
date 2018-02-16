var userObj;
var numeArtSel;

$(document).on('pagebeforeshow', '#stocuri', function() {
	$('#cautaArticol').parent().css('margin-left', '80px');
	$('#cautaArticol').parent().css('margin-right', '80px');

	$('#numeArtSel').parent().css('margin-left', '50px');
	$('#numeArtSel').parent().css('margin-right', '50px');

});

$(document).on('pageshow', '#stocuri', function() {

	$(":input[name= 'radio-articol']").on('change', function() {
		var clicked = $(this).val();

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
				+ listArticole[u].cod + ' - ' + listArticole[u].nume
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

	$(contentId).html('');

	var stocTable = $('<table></table>').attr({
		id : 'stocTable',
		width : "100%",
		border : "0"
	}).addClass('stocTable');

	$('#stocTable tbody').remove();

	var row = $('<tr></tr>').appendTo(stocTable);

	$('<td></td>').attr('style', 'width:25%').text('Cantitate').appendTo(row);

	$('<td></td>').attr('style', 'width:25%').text('Um').appendTo(row);

	$('<td></td>').attr('style', 'width:25%').text('Depozit').appendTo(row);

	if (articolStoc.length == 0) {
		row = $('<tr></tr>').appendTo(stocTable);

		$('<td></td>').text('0').appendTo(row);
		$('<td></td>').appendTo(row);
		$('<td></td>').appendTo(row);
	} else
		for (var i = 0; i < articolStoc.length; i++) {

			row = $('<tr></tr>').appendTo(stocTable);

			$('<td></td>').text(articolStoc[i].cantitate).appendTo(row);
			$('<td></td>').text(articolStoc[i].um).appendTo(row);
			$('<td></td>').text(articolStoc[i].depozit).appendTo(row);

		}

	$('<br>').appendTo($(contentId));
	$(stocTable).appendTo($(contentId));
	$('<br>').appendTo($(contentId));

}
