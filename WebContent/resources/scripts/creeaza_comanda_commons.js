function valideazaComandaNoua(comanda) {
	var comandaValida = true;

	if (comanda.listArticole.length == 0) {
		showAlertDialog('Atentie!', 'Comanda nu contine articole.');
		comandaValida = false;
	}

	return comandaValida;

}

function valideazaDateLivare(dateLivrare) {
	var dateLivrareValide = true;

	if (dateLivrare.codJudet == null || dateLivrare.codJudet.length == 0) {
		showAlertDialog('Atentie!', 'Completati judetul.');
		dateLivrareValide = false;
	}

	if (dateLivrare.localitate == null || dateLivrare.localitate.length == 0) {
		showAlertDialog('Atentie!', 'Completati localitatea.');
		dateLivrareValide = false;
	}

	if (dateLivrare.dataLivrare == null || dateLivrare.dataLivrare.length == 0) {
		showAlertDialog('Atentie!', 'Completati data de livrare.');
		dateLivrareValide = false;
	}

	return dateLivrareValide;
}
