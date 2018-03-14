function afiseazaDateLivrare(dateLivrare) {

	$('#dateLivrareModifTable tbody').remove();
	var livrareTable = $('#dateLivrareModifTable');

	var row;

	if (dateLivrare.codJudet != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Judet').attr('style', 'width:30%').appendTo(row);
		$('<td></td>').text(getNumeJudet(dateLivrare.codJudet)).appendTo(row);

	}

	if (dateLivrare.localitate != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Localitate').attr('style', 'width:30%').appendTo(
				row);
		$('<td></td>').text(dateLivrare.localitate).appendTo(row);

	}

	if (dateLivrare.strada != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Strada').attr('style', 'width:25%').appendTo(row);
		$('<td></td>').text(dateLivrare.strada).appendTo(row);

	}

	if (dateLivrare.persContact != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Persoana contact').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(dateLivrare.persContact).appendTo(row);

	}

	if (dateLivrare.telPersContact != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Telefon').attr('style', 'width:25%').appendTo(row);
		$('<td></td>').text(dateLivrare.telPersContact).appendTo(row);

	}

	if (dateLivrare.tipReducere != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Tip reducere').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(getTipReducereText(dateLivrare.tipReducere))
				.appendTo(row);

	}

	if (dateLivrare.documentInsotitor != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Doc. insotitor').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(getDocInsotitorText(dateLivrare.documentInsotitor))
				.appendTo(row);

	}

	if (dateLivrare.tipPlata != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Tip plata').attr('style', 'width:25%').appendTo(
				row);
		$('<td></td>').text(getTipPlataText(dateLivrare.tipPlata))
				.appendTo(row);

	}

	if (dateLivrare.respIncasare != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Resp. incasare').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(getRespIncasareText(dateLivrare.respIncasare))
				.appendTo(row);

	}

	if (dateLivrare.tipTransp != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Transport').attr('style', 'width:25%').appendTo(
				row);
		$('<td></td>').text(getTipTranspText(dateLivrare.tipTransp)).appendTo(
				row);

	}

	if (dateLivrare.dataLivrare != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Data livrare').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(dateLivrare.dataLivrare).appendTo(row);

		
		

	}

	if (dateLivrare.obsLivrare != null) {

		row = $('<tr></tr>');
		$(row).appendTo(livrareTable);

		$('<th></th>').text('Obs. livrare').attr('style', 'width:25%')
				.appendTo(row);
		$('<td></td>').text(dateLivrare.obsLivrare).appendTo(row);

	}

	$(livrareTable).appendTo($('#divTable'));

}

function modificaDateLivrare(dateLivrare) {

	$('#numeJudet').val(dateLivrare.codJudet);
	$('#localitate').val(dateLivrare.localitate);
	$('#strada').val(dateLivrare.strada);
	$('#persContact').val(dateLivrare.persContact);
	$('#telPersContact').val(dateLivrare.telPersContact);

	$("#selectTipReducere").val(dateLivrare.tipReducere).change();
	$("#selectDocInsotitor").val(dateLivrare.documentInsotitor).change();
	$("#selectPlata").val(dateLivrare.tipPlata).change();
	$("#selectResponsabil").val(dateLivrare.respIncasare).change();
	$("#selectTransport").val(dateLivrare.tipTransp).change();

	$('#dataLivrare').val(dateLivrare.dataLivrare);

	$('#obsLivrare').val(dateLivrare.obsLivrare);

}

$("#saveDateLivr").click(function() {

	var dateLivrare = getDateLivrareModificate();
	comandaCurenta.dateLivrare = dateLivrare;

	afiseazaDateLivrare(dateLivrare);
	$('#selectDateLivrareModifDiv').hide();
	$("#modif_act_select").val(0).change();
});

function getDateLivrareModificate() {

	var dateLivrare = new Object();

	dateLivrare.codJudet = $('#numeJudet').val();
	dateLivrare.localitate = $('#localitate').val();
	dateLivrare.strada = $('#strada').val();
	dateLivrare.persContact = $('#persContact').val();
	dateLivrare.telPersContact = $('#telPersContact').val();
	dateLivrare.tipReducere = $('#selectTipReducere').val();
	dateLivrare.documentInsotitor = $('#selectDocInsotitor').val();
	dateLivrare.tipPlata = $('#selectPlata').val();
	dateLivrare.respIncasare = $('#selectResponsabil').val();
	dateLivrare.tipTransp = $('#selectTransport').val();
	dateLivrare.dataLivrare = $('#dataLivrare').val();
	dateLivrare.obsLivrare = $('#obsLivrare').val();

	return dateLivrare;

}

function getDateLivrareModifCmd(dateLivrareNew) {

	var dateLivrare = new Object();

	dateLivrare.codJudet = dateLivrareNew.codJudet;
	dateLivrare.localitate = dateLivrareNew.localitate;
	dateLivrare.strada = dateLivrareNew.strada;
	dateLivrare.persContact = dateLivrareNew.persContact;
	dateLivrare.telPersContact = dateLivrareNew.telPersContact;
	dateLivrare.tipReducere = dateLivrareNew.tipReducere;
	dateLivrare.documentInsotitor = dateLivrareNew.documentInsotitor;
	dateLivrare.tipPlata = dateLivrareNew.tipPlata;
	dateLivrare.respIncasare = dateLivrareNew.respIncasare;
	dateLivrare.tipTransp = dateLivrareNew.tipTransp;
	dateLivrare.dataLivrare = dateLivrareNew.dataLivrare;
	dateLivrare.obsLivrare = dateLivrareNew.obsLivrare;

	return dateLivrare;

}
