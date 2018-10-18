package websfa.helpers;

import java.util.List;

import websfa.beans.nagivation.NavigationDetails;

public class HelperMeniu {

	public static void addMenuOption(List<NavigationDetails> navigationLinks, NavigationDetails nd, String tipUser) {

		
		
		switch (tipUser) {
		case "GS":
		case "GD":
		case "DZ": 
		case "DADMIN":
			switch (nd.getNume()) {
			case UTILIZATOR:
			case INCARCARE_MASINI:
				navigationLinks.add(nd);
				break;
			default:
				break;
			}
			break;
		}

	}

}
