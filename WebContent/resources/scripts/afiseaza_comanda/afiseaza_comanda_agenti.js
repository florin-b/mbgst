function getAgentiListComanda() {
	$.ajax({
		type : 'GET',
		url : 'getAgentiDepart',
		data : ({
			filiala : userObj.unitLog,
			departament : userObj.codDepart
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			populateAgentiList(data);

		},
		error : function(exception) {
			alert('Exeption:' + JSON.stringify(exception));
		}

	});

}

function populateAgentiList(listAgenti) {

	var $dropdown = $("#selectAgenti");

	$dropdown.append($("<option />").val('-1').text('Selectati'));
	$dropdown.append($("<option />").val('0').text('Toti agentii'));

	$.each(listAgenti, function() {
		$dropdown.append($("<option />").val(this.cod).text(this.nume));
	});

	$("#selectAgenti").val('-1').change();

}