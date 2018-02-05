function getDateLivrare() {

	var dateLivrare = new Object();

	dateLivrare.codJudet = $('#numeJudet').val();
	dateLivrare.localitate = $('#localitate').val();
	dateLivrare.strada = $('#strada').val();
	dateLivrare.persContact = $('#persContact').val();
	dateLivrare.telPersContact=$('#telPersContact').val();
	dateLivrare.tipReducere = $('#selectTipReducere').val();
	dateLivrare.documentInsotitor = $('#selectDocInsotitor').val();
	dateLivrare.tipPlata = $('#selectPlata').val();
	dateLivrare.respIncasare = $('#selectResponsabil').val();
	dateLivrare.tipTransp = $('#selectTransport').val();
	dateLivrare.dataLivrare = $('#dataLivrare').val();
	dateLivrare.obsLivrare = $('#obsLivrare').val();

	return dateLivrare;

}

function getArticoleComanda(listaArticole) {

	var articoleComanda = [];

	for (var i = 0; i < listaArticole.length; i++) {
		var articol = new Object();
		articol.codArticol = listaArticole[i].cod;
		articol.cantitate = listaArticole[i].cant;
		articol.um = listaArticole[i].um;
		articol.pretUnitar = listaArticole[i].pret;
		articol.procentReducere = listaArticole[i].procent;
		articol.depozit = listaArticole[i].depozit;
		articoleComanda.push(articol);
	}

	return articoleComanda;

}
