var userObj;
var globalListArticole = [];

$(document).on('pageshow', '#aprobacomanda', function() {

	userObj = JSON.parse($('#userbean').text());

	getComenziAprobare();

});

$(document).on('pagecreate', '#aprobacomanda', function() {

	setColapsibleArticoleListener();

});

function setColapsibleArticoleListener() {
	$('#articoleset').bind('collapsibleexpand', function(data) {

		var contentId = '#' + data.target.id;

		var position = $(contentId).offset().top;
		$.mobile.silentScroll(position);

		afisDetaliiArticol(contentId);

	});
}

function afisDetaliiArticol(codArticol) {
	// alert(codArticol);

	var idContCant = '#idCCant' + codArticol;

	var result_style = document.getElementById(idContCant).style;
	result_style.display = 'table-row';

}

function getComenziAprobare() {

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
			afisComenziAprob(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afisComenziAprob(listComenzi) {

	if (listComenzi.length > 0) {
		$('#cmd_aprob_select').append($('<option>', {
			value : 0,
			text : "Selectati o comanda"
		}));

		for (var i = 0; i < listComenzi.length; i++) {
			$('#cmd_aprob_select').append($('<option>', {
				value : listComenzi[i].idComanda,
				text : listComenzi[i].numeClient
			}));
		}

		$("#cmd_aprob_select").val("0").change();

	}

}

$('#cmd_aprob_select').on('change', function() {
	var idComanda = $("#cmd_aprob_select option:selected").val();

	if (idComanda > 0)
		getDetaliiCmdAprob(idComanda);

});

function getDetaliiCmdAprob(idComanda) {

	$.mobile.loading('show');

	$.ajax({
		type : 'GET',
		url : 'getDetCmdAprob',
		data : ({
			idComanda : idComanda
		}),
		success : function(data) {
			afiseazaComandaAprob(data);
		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {

		}

	});

	$.mobile.loading('hide');

}

function afiseazaComandaAprob(comanda) {

	var content = '<table data-role="table" id="antetTable" class="ui-responsive" data-mode="reflow" >';

	content += '<tr>';
	content += '<td  style="width:30%">Nr. comanda SAP</td>';
	content += '<td>' + comanda.idComandaSAP + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>Valoare</td>';
	content += '<td>' + comanda.valoare + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>Date emitere</td>';
	content += '<td>' + comanda.dataEmitere + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>Client</td>';
	content += '<td>' + comanda.numeClient + '</td>';
	content += '</tr>';

	content += '<tr>';
	content += '<td>Agent</td>';
	content += '<td>' + comanda.numeAgent + '</td>';
	content += '</tr>';

	content += '</table>';

	$(content).appendTo('#antetCmdAprob').enhanceWithin();

	globalListArticole = comanda.listArticole;

	afiseazaArticoleComanda(comanda.listArticole);

}

function afiseazaArticoleComanda(listArticole) {

	for (var i = 0; i < listArticole.length; i++) {

		var bgColor = '';

		if (i % 2 == 0)
			bgColor = '#f2f2f2';

		var mytable = $('<table></table>').attr({
			id : "rowTable",
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
		var tdAddCond = $('<td></td>').appendTo(row);

		var btnAddConditii = $('<button>', {
			text : 'Conditii',
		}).bind('click', {
			articol : listArticole[i]
		}, function(event) {
			clickFunc(event.data.articol);
		});

		$(btnAddConditii).appendTo(tdAddCond);

		row = $('<tr></tr>', {
			id : 'rowCondCant' + listArticole[i].codArticol,
			style : 'display:none'

		});

		$(row).appendTo(mytable);

		$('<td></td>').attr('style', 'width:3%').appendTo(row);
		$('<td></td>').attr('style', 'align:right').text('Cantitative')
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
		$('<td></td>').attr('style', 'align:right').text('Valorice').appendTo(
				row);

		var inputCondVal = $('<input>', {
			id : 'condVal' + listArticole[i].codArticol,
			maxlength : '6',
			size : '6',
			style : 'width:80%'
		});

		var tdCondValInput = $('<td></td>').appendTo(row);
		$(inputCondVal).appendTo(tdCondValInput);
		$('<td></td>').attr('style', 'width:3%').text("RON").appendTo(row);

		$(mytable).appendTo('#articoleTable');

	}

}

function clickFunc(articol) {

	var rowCondCant = '#rowCondCant' + articol.codArticol;
	var rowCondVal = '#rowCondVal' + articol.codArticol;

	if ($(rowCondCant).css('display') == 'none') {
		$(rowCondCant).show();
		$(rowCondVal).show();
	} else {
		$(rowCondCant).hide();
		$(rowCondVal).hide();
	}

	var idTextCondCant = '#condCant' + articol.codArticol;
	var idTextCondVal = '#condVal' + articol.codArticol;

	if (isFinite($(idTextCondCant).val()))
		articol.conditiiCant = $(idTextCondCant).val().trim();

	if (isFinite($(idTextCondVal).val()))
		articol.conditiiVal = $(idTextCondVal).val().trim();

}

function handlerForAfisConditii(articol) {
	alert(articol);
}

function getDateSuplimentare(articol) {

	var dateSupl = '<table><thead></thead><tbody><tr>';
	dateSupl += '<td >Cmp</td>';
	dateSupl += '<td style="width:10%">' + articol.pretCmp + '</td>';
	dateSupl += '<td >Discount client</td>';
	dateSupl += '<td style="width:10%">' + articol.discountClient + '</td>';
	dateSupl += '</tr></tbody></table>';

	return dateSupl;

}

function getConditiiLayout(articol) {

	var condLayout = '<table data-role="table" style="width:100%"  class="ui-responsive" data-mode="reflow"><thead></thead><tbody><tr>';

	var btnAddCond = '<input type="button" id="afisConditii" value="Adauga	  conditii" />';

	condLayout += '<td colspan=3 align="right">' + btnAddCond + '</td>';

	condLayout += '<td>111</td>';

	condLayout += '</tr></tbody></table>';

	return condLayout;

}

function afiseazaArticoleComanda_old(listArticole) {

	var content = '<table data-role="table" style="width:100%" id="articoleTable" class="ui-responsive" data-mode="reflow" border =1><thead></thead><tbody>';

	/*
	 * 
	 * for (var i = 0; i < listArticole.length; i++) {
	 * 
	 * if (i % 2 == 0) content += '<tr bgcolor="#EAEAEA"><td>'; else content += '<tr><td>';
	 * 
	 * var innerContent = '<table data-role="table" style="width:100%"
	 * class="ui-responsive" data-mode="reflow" ><thead></thead><tbody>';
	 * 
	 * innerContent += '<tr>'; innerContent += '<td style="width:3%">' + (i +
	 * 1) + '</td>'; innerContent += '<td style="width:55%">' +
	 * listArticole[i].numeArticol + '</td>'; innerContent += '<td style="width:5%">' +
	 * listArticole[i].cantitate + '</td>'; innerContent += '<td style="width:5%">' +
	 * listArticole[i].um + '</td>'; innerContent += '</tr>';
	 * 
	 * innerContent += '<tr>'; innerContent += '<td></td>'; innerContent += '<td>' +
	 * listArticole[i].codArticol + '</td>'; innerContent += '<td style="width:5%">' +
	 * listArticole[i].pretUnitar + '</td>'; innerContent += '<td style="width:5%">RON</td>';
	 * innerContent += '</tr>';
	 * 
	 * innerContent += '<tr>'; innerContent += '<td></td>'; innerContent += '<td></td>';
	 * innerContent += '<td style="width:5%">' +
	 * listArticole[i].procentReducere + '</td>'; innerContent += '<td style="width:5%">%</td>';
	 * innerContent += '</tr>';
	 * 
	 * innerContent += '<tr><td></td><td colspan=3><table
	 * style="width:50%"><thead></thead><tbody><tr>'; innerContent += '<td >Cmp</td>';
	 * innerContent += '<td style="width:10%">' + listArticole[i].pretCmp + '</td>';
	 * 
	 * innerContent += '<td >Discount client</td>'; innerContent += '<td style="width:10%">' +
	 * listArticole[i].discountClient + '</td>';
	 * 
	 * innerContent += '</tr></tbody></table></td></tr>';
	 * 
	 * innerContent += '<tr><td></td><td colspan=3  align="right"><table><thead></thead><tbody><tr>';
	 * innerContent += '<td></td>';
	 * 
	 * var idBtnConditii = 'conditii' + listArticole[i].codArticol; var
	 * listenerBtnConditii = 'afisConditiiLayout(' + listArticole[i].codArticol +
	 * ')';
	 * 
	 * var btnAddCond = '<input type="button" id="' + idBtnConditii + '"
	 * value="Adauga conditii" onClick="' + listenerBtnConditii + '" />';
	 * 
	 * innerContent += '<td colspan=3 align="right">' + btnAddCond + '</td>';
	 * innerContent += '</tr></td>';
	 * 
	 * var idTableConditii = 'tblCond' + listArticole[i].codArticol; var
	 * idTextCondCant = 'condCant' + listArticole[i].codArticol; var
	 * idTextCondVal = 'condVal' + listArticole[i].codArticol; var idBtnSaveCond =
	 * 'saveCond' + listArticole[i].codArticol;
	 * 
	 * var listenerSaveConditii = 'saveConditiiLayout(' + i + ')';
	 * 
	 * var btnSaveCond = '<input type="button" style="width:100%" id="' +
	 * idBtnSaveCond + '" value="Salveaza" onClick="' + listenerSaveConditii + '"
	 * />';
	 * 
	 * 
	 * 
	 * 
	 * innerContent += '<tr><td></td><td><table id=' + idTableConditii + '
	 * style="display:none" style="width:100%" ><thead></thead><tbody><tr><td>Conditii
	 * cantitative</td> <td><input type="text" id=' + idTextCondCant + '></td><td>' +
	 * listArticole[i].um + '</td></tr><tr><td>Conditii valorice</td><td><input
	 * type="text" id=' + idTextCondVal + '></td><td>RON</td></tr><tr><td colspan="3" >' +
	 * btnSaveCond + '</td></tr></td></tbody></table></tr>';
	 * 
	 * innerContent += '</tbody></table></td></tr>'; // Afisare conditii
	 * var idRowCondCant = 'condCant' + listArticole[i].codArticol; var
	 * idRowCondVal = 'condVal' + listArticole[i].codArticol;
	 * 
	 * innerContent += '<tr style="display:none" id=' + idRowCondCant + '><td></td><td align="right" >Conditii</td><td>3</td><td>BUC</td></tr>';
	 * innerContent += '<tr style="display:none" id=' + idRowCondVal + '><td></td><td align="right" ></td><td>4.55</td><td>RON</td></tr>'; //
	 * sf. afisare conditii
	 * 
	 * innerContent += '</tbody></table>';
	 * 
	 * content += innerContent;
	 * 
	 * content += '</td></tr>'; // var salveazaConditiiButton = //
	 * document.getElementById('salveazaConditii'); //
	 * salveazaConditiiButton.addEventListener('click', //
	 * handlerForSalveazaConditii(listArticole[i])); }
	 * 
	 * 
	 */
	content += '</tbody></table>';

	$(content).appendTo('#articoleCmdAprob').enhanceWithin();

	// var salveazaConditiiButton = document.getElementById('salveazaConditii');

	// $('#articoleCmdAprob').html(content);

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

	alert(idRowCondVal);

	$(idRowCondCant).show();
	$(idRowCondVal).show();

}

function isNumeric(val) {
	return Number(parseFloat(val)) === val;
}
