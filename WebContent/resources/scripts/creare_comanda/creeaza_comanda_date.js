function getDateLivrare() {

	var dateLivrare = new Object();

	dateLivrare.codJudet = $('#codJudet').val();
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

function getArticoleComanda(listaArticole) {

	var articoleComanda = [];

	for (var i = 0; i < listaArticole.length; i++) {
		var articol = new Object();
		articol.cod = listaArticole[i].cod;
		articol.cantitate = listaArticole[i].cantitate;
		articol.um = listaArticole[i].um;
		articol.pretUnitar = listaArticole[i].pretUnitar;
		articol.procentReducere = listaArticole[i].procentReducere;
		articol.depozit = listaArticole[i].depozit;
		articol.procentFact = listaArticole[i].procentFact;
		articol.procentAprob = listaArticole[i].procentAprob;
		articol.infoArticol = listaArticole[i].infoArticol;
		articol.umBaza = listaArticole[i].umBaza;
		articol.cantUmBaza = listaArticole[i].cantUmBaza;
		articol.discountClient = listaArticole[i].discountClient;
		articol.multiplu = listaArticole[i].multiplu;

		articoleComanda.push(articol);
	}

	return articoleComanda;

}
