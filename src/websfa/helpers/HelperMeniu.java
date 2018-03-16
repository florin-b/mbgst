package websfa.helpers;

import java.util.List;

import websfa.beans.nagivation.NavigationDetails;

public class HelperMeniu {

	public static void addMenuOption(List<NavigationDetails> navigationLinks, NavigationDetails nd, String tipUser) {

		switch (tipUser) {
		case "SD":
			switch (nd.getNume()) {
			case AFISARE_COMANDA:
			case APROBARE_COMANDA:
			case PRETURI:
			case STOCURI:
				navigationLinks.add(nd);
				break;
			default:
				break;
			}
			break;
		case "AV":
			switch (nd.getNume()) {
			case CREARE_COMANDA:
			case MODIFICARE_COMANDA:
			case AFISARE_COMANDA:
			case PRETURI:
			case STOCURI:
				navigationLinks.add(nd);
				break;
			default:
				break;
			}
			break;
		}

	}

}
