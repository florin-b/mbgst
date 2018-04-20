var userObj;

$(document).on('pageshow', '#user', function() {

	userObj = JSON.parse($('#userbean').text());

	populateUserData();

});

$(document).on('pagecreate', '#user', function() {

});

function populateUserData() {

	var dateGenTable = '#userdef-table';
	$('#userdef-table tbody').remove();

	var row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text(
			'Име').appendTo(row);
	$('<td></td>').text(userObj.nume).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Филиал').appendTo(row);
	$('<td></td>').text(userObj.filiala).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Код служител').appendTo(row);
	$('<td></td>').text(userObj.codPers).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Отдел').appendTo(row);
	$('<td></td>').text(userObj.codDepart).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Вид служител').appendTo(row);
	$('<td></td>').text(getTipAngajat(userObj.tipAngajat)).appendTo(row);

}