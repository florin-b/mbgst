var userObj;
var globalListArticole = [];

$(document).on('pageshow', '#modifcmd', function() {

	userObj = JSON.parse($('#userbean').text());

	getComenziModificare();

});

$(document).on('pagecreate', '#modifcmd', function() {

	setColapsibleArticoleListener();
	$('#modif_act_select').parent().hide();
	$('#antetCmdModif').hide();
	$('#articoleCmdModif').hide();
	$('#selectArtModifDiv').hide();
	$('#selectDateLivrareModifDiv').hide();

});

function setColapsibleArticoleListener() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var contentId = '#' + data.target.id;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		afisDetaliiArticol(contentId);

	});
}

$('#modif_act_select').on('change', function() {

	var selectId = this.value;

	$('#selectArtModifDiv').hide();
	$('#selectDateLivrareDiv').hide();
	$('#selectDateLivrareModifDiv').hide();

	if (selectId == 0) {
		$('#selectArtModifDiv').hide();
		$('#selectDateLivrareDiv').hide();
		$('#selectDateLivrareModifDiv').hide();
	} else if (selectId == 1) {
		$('#selectArtModifDiv').show();
	} else if (selectId == 2) {
		$('#selectDateLivrareModifDiv').show();

	}

});

function afisDetaliiArticol(codArticol) {

	var idContCant = '#idCCant' + codArticol;

	var result_style = document.getElementById(idContCant).style;
	result_style.display = 'table-row';

}

function getComenziModificare() {

	var cautaCmdAprob = new Object();

	// cautaCmdAprob.tipAngajat = userObj.tipAngajat;
	cautaCmdAprob.unitLog = userObj.unitLog;
	cautaCmdAprob.codDepart = userObj.codDepart;
	cautaCmdAprob.codAngajat = userObj.codPers;

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getCmdAprob',
		data : cautaCmdAprob,
		success : function(data) {
			$.mobile.loading('hide');
			afisComenziModificare(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afisComenziModificare(listComenzi) {

	if (listComenzi.length > 0) {

		$('#cmd_modif_select').append($('<option>', {
			value : 0,
			text : "Selectati o comanda"
		}));

		for (var i = 0; i < listComenzi.length; i++) {
			$('#cmd_modif_select').append($('<option>', {
				value : listComenzi[i].idComanda,
				text : listComenzi[i].numeClient
			}));
		}

		$("#cmd_modif_select").val("0").change();

	}

}

$('#cmd_modif_select').on('change', function() {
	var idComanda = $("#cmd_modif_select option:selected").val();

	if (idComanda > 0)
		getDetaliiComandaModif(idComanda);

});

function getDetaliiComandaModif(idComanda) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getDetCmdAprob',
		data : ({
			idComanda : idComanda
		}),
		success : function(data) {
			afiseazaComandaModif(data);
		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afiseazaComandaModif(comanda) {

	$('#antetCmdModif').show();
	$('#articoleCmdModif').show();
	$('#divDateLivrareModif').show();

	$('#modif_act_select').parent().show();

	var dateGenTable = '#dateGenTable';

	$('#dateGenTable tbody').remove();

	var row = $('<tr></tr>').appendTo(dateGenTable);

	$('<td></td>').attr('style', 'width:15%').text('Nr. comanda SAP').appendTo(
			row);

	$('<td></td>').text(comanda.idComandaSAP).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Valoare').appendTo(row);
	$('<td></td>').text(comanda.valoare).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Data emirere')
			.appendTo(row);
	$('<td></td>').text(comanda.dataEmitere).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Nume client').appendTo(row);
	$('<td></td>').text(comanda.numeClient).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').text('Nume agent').appendTo(row);
	$('<td></td>').text(comanda.numeAgent).appendTo(row);

	afiseazaArticoleComanda(comanda.listArticole);

}

function afiseazaArticoleComanda(listArticole) {

	$('#articoleTable tbody').remove();

	if (listArticole.length > 0)
		$('#opereazaCmdDiv').show();

	for (var i = 0; i < listArticole.length; i++) {

		var bgColor = '';

		if (i % 2 == 0)
			bgColor = '#f2f2f2';

		var mytable = $('<table></table>').attr({
			id : "rowTable" + listArticole[i].codArticol,
			width : "100%",
			border : "0",
			bgcolor : bgColor

		});

		var row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').text((i + 1) + '').appendTo(
				row);
		$('<td></td>').attr('style', 'width:50%').text(
				listArticole[i].numeArticol).appendTo(row);
		$('<td></td>').attr({
			style : 'align:right'
		}).text(listArticole[i].cantitate).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').text(
				listArticole[i].codArticol).appendTo(row);
		$('<td></td>').attr('style', 'width:10%').text(
				listArticole[i].pretUnitar).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("RON").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'width:50%').appendTo(row);
		$('<td></td>').attr('style', 'width:10%').text(
				listArticole[i].procentReducere).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text("%").appendTo(row);

		row = $('<tr></tr>').appendTo(mytable);
		$('<td></td>').attr('style', 'width:5%').appendTo(row);

		var tdAddCond = $('<td></td>', {
			style : 'text-align : right'
		}).appendTo(row);

		var btnAddConditii = $('<button>', {
			text : 'Conditii'
		}).bind('click', {
			articol : listArticole[i]
		}, function(event) {
			setConditiiInputVisibility(event.data.articol);
		});

		$(btnAddConditii).appendTo(tdAddCond);

		row = $('<tr></tr>', {
			id : 'rowCondCant' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text('Cantitative')
				.appendTo(row);

		var inputCondCant = $('<input>', {
			id : 'condCant' + listArticole[i].codArticol,
			maxlength : '6',
			size : '6',
			style : 'width:80%'
		});

		var tdCondCantInput = $('<td></td>').appendTo(row);

		$(inputCondCant).appendTo(tdCondCantInput);

		$('<td></td>').attr('style', 'width:3%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondVal' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text('Valorice')
				.appendTo(row);

		var inputCondVal = $('<input>', {
			id : 'condVal' + listArticole[i].codArticol,
			maxlength : '6',
			size : '6',
			style : 'width:80%'
		});

		var tdCondValInput = $('<td></td>').appendTo(row);
		$(inputCondVal).appendTo(tdCondValInput);
		$('<td></td>').attr('style', 'width:3%').text("RON").appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowSaveCond' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);

		var tdSaveCond = $('<td></td>').attr('style', 'text-align:right')
				.appendTo(row);

		var btnSaveConditii = $('<button>', {
			text : 'Salveaza conditii',
		}).bind('click', {
			articol : listArticole[i]
		}, function(event) {
			salveazaConditii(event.data.articol);
		});

		$(btnSaveConditii).appendTo(tdSaveCond);

		row = $('<tr></tr>', {
			id : 'rowCondCantImpuse' + listArticole[i].codArticol,
			style : 'display:none;color:red'

		});
		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>', {
			style : 'text-align:right'
		}).text('Conditii cantitative').appendTo(row);

		$('<td></td>', {
			id : 'tdCondCant' + listArticole[i].codArticol,
			style : 'width:10%',
			text : listArticole[i].conditiiCant

		}).appendTo(row);

		$('<td></td>').attr('style', 'width:5%').text(listArticole[i].um)
				.appendTo(row);

		row = $('<tr></tr>', {
			id : 'rowCondValImpuse' + listArticole[i].codArticol,
			style : 'display:none;color:red'

		});
		$(row).appendTo(mytable);
		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'text-align:right').text(
				'Conditii valorice').appendTo(row);

		$('<td></td>', {
			id : 'tdCondVal' + listArticole[i].codArticol,
			style : 'width:10%',
			text : listArticole[i].conditiiVal

		}).appendTo(row);
		$('<td></td>').attr('style', 'width:5%').text('RON').appendTo(row);

		$(mytable).appendTo('#articoleTable');

	}

}

function salveazaConditii(articol) {

	var idInputCondCant = '#condCant' + articol.codArticol;
	var idInputCondVal = '#condVal' + articol.codArticol;

	if (isFinite($(idInputCondCant).val()) && $(idInputCondCant).val() > 0) {
		articol.conditiiCant = $(idInputCondCant).val().trim();

	} else {
		articol.conditiiCant = 0;
	}

	if (isFinite($(idInputCondVal).val()) && $(idInputCondVal).val() > 0) {
		articol.conditiiVal = $(idInputCondVal).val().trim();

	} else {
		articol.conditiiVal = 0;
	}

	setConditiiInputVisibility(articol);

}

function setConditiiInputVisibility(articol) {

	var rowCondCant = '#rowCondCant' + articol.codArticol;
	var rowCondVal = '#rowCondVal' + articol.codArticol;
	var rowSaveCond = '#rowSaveCond' + articol.codArticol;

	if ($(rowCondCant).css('display') == 'none') {
		$(rowCondCant).show();
		$(rowCondVal).show();
		$(rowSaveCond).show();
		$('#rowCondCantImpuse' + articol.codArticol).hide();
		$('#rowCondValImpuse' + articol.codArticol).hide();
	} else {
		$(rowCondCant).hide();
		$(rowCondVal).hide();
		$(rowSaveCond).hide();

		setConditiiAfisVisibility(articol);

	}

}

function setConditiiAfisVisibility(articol) {

	if (isFinite(articol.conditiiCant) && articol.conditiiCant > 0) {

		$('#tdCondCant' + articol.codArticol).text(articol.conditiiCant);
		$('#rowCondCantImpuse' + articol.codArticol).show();

	} else {
		articol.conditiiCant = 0;
		$('#tdCondCant' + articol.codArticol).text('');
		$('#rowCondCantImpuse' + articol.codArticol).hide();
	}

	if (isFinite(articol.conditiiVal) && articol.conditiiVal > 0) {

		$('#tdCondVal' + articol.codArticol).text(articol.conditiiVal);
		$('#rowCondValImpuse' + articol.codArticol).show();

	} else {
		articol.conditiiVal = 0;
		$('#tdCondVal' + articol.codArticol).text('');
		$('#rowCondValImpuse' + articol.codArticol).hide();
	}

}

function handlerForSalveazaConditii(articol) {
	trateazaArticol(articol);
}

function trateazaArticol(articol) {
	alert(articol.codArticol);
}

function afisConditiiLayout(codArticol) {

	var idRowConditii = '#tblCond' + codArticol;

	if ($(idRowConditii).css('display') == 'none')
		$(idRowConditii).show();
	else
		$(idRowConditii).hide();

}

function saveConditiiLayout(index) {

	var idTextCondCant = '#condCant' + globalListArticole[index].codArticol;
	var idTextCondVal = '#condVal' + globalListArticole[index].codArticol;

	if (isFinite($(idTextCondCant).val()))
		globalListArticole[index].conditiiCant = $(idTextCondCant).val().trim();

	if (isFinite($(idTextCondVal).val()))
		globalListArticole[index].conditiiVal = $(idTextCondVal).val().trim();

	var idRowCondCant = '#condCant' + globalListArticole[index].codArticol;
	var idRowCondVal = '#condVal' + globalListArticole[index].codArticol;

	$(idRowCondCant).show();
	$(idRowCondVal).show();

}

function isNumeric(val) {
	return Number(parseFloat(val)) === val;
}

$("#salveazaCmdModif").click(function() {
	alert("Salvare comanda modificata");
});
