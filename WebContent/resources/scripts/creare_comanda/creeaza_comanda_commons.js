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

	if (dateLivrare.persContact == null || dateLivrare.persContact.length == 0) {
		showAlertDialog('Atentie!', 'Completati persoana de contact.');
		dateLivrareValide = false;
	}

	if (dateLivrare.telPersContact == null
			|| dateLivrare.telPersContact.length == 0) {
		showAlertDialog('Atentie!',
				'Completati telefonul persoanei de contact.');
		dateLivrareValide = false;
	}

	if (dateLivrare.dataLivrare == null || dateLivrare.dataLivrare.length == 0) {
		showAlertDialog('Atentie!', 'Completati data de livrare.');
		dateLivrareValide = false;
	}

	return dateLivrareValide;
}

function getNumeRegiune(codRegiune) {

	var numeRegiune = 'nedefinit';

	if (codRegiune == '01')
		numeRegiune = 'БЛАГОЕВГРАДСКА';
	else if (codRegiune == '02')
		numeRegiune = 'БУРГАСКА';
	else if (codRegiune == '03')
		numeRegiune = 'ДОБРИЧСКА';
	else if (codRegiune == '04')
		numeRegiune = 'ГАБРОВСКА';
	else if (codRegiune == '05')
		numeRegiune = 'ХАСКОВСКА';
	else if (codRegiune == '06')
		numeRegiune = 'КЪРДЖАЛИЙСКА';
	else if (codRegiune == '07')
		numeRegiune = 'КЮСТЕНДИЛСКА';
	else if (codRegiune == '08')
		numeRegiune = 'ЛОВЕШКА';
	else if (codRegiune == '09')
		numeRegiune = 'МОНТАНАНСКА';
	else if (codRegiune == '10')
		numeRegiune = 'ПАЗАРДЖИШКА';
	else if (codRegiune == '11')
		numeRegiune = 'ПЕРНИШКА';
	else if (codRegiune == '12')
		numeRegiune = 'ПЛЕВЕНСКА';
	else if (codRegiune == '13')
		numeRegiune = 'ПЛОВДИВСКА';
	else if (codRegiune == '14')
		numeRegiune = 'РАЗГРАДСКА';
	else if (codRegiune == '15')
		numeRegiune = 'РУСЕНСКА';
	else if (codRegiune == '16')
		numeRegiune = 'ШУМЕНСКА';
	else if (codRegiune == '17')
		numeRegiune = 'СИЛИСТРЕНСКА';
	else if (codRegiune == '18')
		numeRegiune = 'СЛИВЕНСКА';
	else if (codRegiune == '19')
		numeRegiune = 'СМОЛЯНСКА';
	else if (codRegiune == '20')
		numeRegiune = 'СОФИЯ';
	else if (codRegiune == '21')
		numeRegiune = 'СОФИЙСКА';
	else if (codRegiune == '22')
		numeRegiune = 'СТАРОЗАГОРСКА';
	else if (codRegiune == '23')
		numeRegiune = 'ТЪРГОВИШКА';
	else if (codRegiune == '24')
		numeRegiune = 'ВАРНЕНСКА';
	else if (codRegiune == '25')
		numeRegiune = 'ВЕЛИКОТЪРНОВСКА';
	else if (codRegiune == '26')
		numeRegiune = 'ВИДИНСКА';
	else if (codRegiune == '27')
		numeRegiune = 'ВРАЧАНСКА';
	else if (codRegiune == '28')
		numeRegiune = 'ЯМБОЛСКА';

	return numeRegiune;
}
