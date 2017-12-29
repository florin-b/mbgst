var userObj;
var numeArtSel;

$(document).on('pagebeforeshow', '#preturi', function() {
	$('#cautaArticol').parent().css('margin-left', '80px');
	$('#cautaArticol').parent().css('margin-right', '80px');

	$('#numeArtSel').parent().css('margin-left', '50px');
	$('#numeArtSel').parent().css('margin-right', '50px');

	$('#list-articole').on('click', 'li', function() {
		numeArtSel = $(this).text();
		getPret($(this).attr("codart"));

	});

});

$(document).on('pageshow', '#preturi', function() {

	clearScreen();

	$(":input[name= 'radio-articol']").on('change', function() {
		var clicked = $(this).val();
		clearScreen();

		$('#codArticol').focus();

	});

});

function cautaArticol() {

	$("#pretResult").hide();
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

function getPret(codArticol) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'pret',
		data : ({
			codArticol : codArticol,
			filiala : userObj.unitLog,
			departament : userObj.codDepart
		}),
		success : function(data) {
			afiseazaPret(data);
			$.mobile.loading('hide');
		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
			$.mobile.loading('hide');
		}

	});

}

function afiseazaPret(articolPret) {

	$("#pretResult").show();
	$('#numeArtSel').html(numeArtSel);

	var pretArt = '';

	pretArt += '<tr><td>' + articolPret.pret + '</td>';
	pretArt += '<td>' + articolPret.um + '</td></tr>';

	$('#pretArticol').html(pretArt);

}

function clearScreen() {
	$("#pretResult").hide();
	$("#listDiv").hide();
	$("#codArticol").val('');
}
