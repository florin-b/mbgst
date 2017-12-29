var userObj;
var numeArtSel;

$(document).on('pagebeforeshow', '#stocuri', function() {
	$('#cautaArticol').parent().css('margin-left', '80px');
	$('#cautaArticol').parent().css('margin-right', '80px');

	$('#numeArtSel').parent().css('margin-left', '50px');
	$('#numeArtSel').parent().css('margin-right', '50px');

	$('#list-articole').on('click', 'li', function() {
		numeArtSel = $(this).text();
		getStoc($(this).attr("codart"));

	});

});

$(document).on('pageshow', '#stocuri', function() {

	clearScreen();

	$(":input[name= 'radio-articol']").on('change', function() {
		var clicked = $(this).val();
		clearScreen();
		
		$('#codArticol').focus();

	});

});

function cautaArticol() {

	$("#stocResult").hide();
	$("#listDiv").hide();

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
		url : 'cauta',
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

	var html = "";

	if (listArticole.length == 0)
		return;

	for (var i = 0; i < listArticole.length; i++) {

		var backColor = "style='background-color:white;border:0px;'";
		if (i % 2 != 0)
			backColor = "style='background-color:#E6E6FA;border:0px;'";

		html += "<li codart=" + listArticole[i].cod + "  " + backColor + ">"
				+ listArticole[i].nume + "</li>";
	}

	$('#list-articole').html(html);
	$('#list-articole').children().removeClass('ui-body-c');

	$('#list-articole').listview("refresh");
	$('#list-articole').trigger("updatelayout");
	$("#listDiv").show();

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
			afiseazaStoc(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afiseazaStoc(articolStoc) {

	$("#stocResult").show();
	$('#numeArtSel').html(numeArtSel);

	var stocuriArt = '';

	for (var i = 0; i < articolStoc.length; i++) {
		stocuriArt += '<tr><td>' + articolStoc[i].cantitate + '</td>';
		stocuriArt += '<td>' + articolStoc[i].um + '</td>';
		stocuriArt += '<td>' + articolStoc[i].depozit + '</td></tr>';

	}

	$('#stocuriArticol').html(stocuriArt);

}

function clearScreen() {
	$("#stocResult").hide();
	$("#listDiv").hide();
	$("#codArticol").val('');
}
