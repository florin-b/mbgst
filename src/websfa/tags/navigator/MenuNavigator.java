package websfa.tags.navigator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import websfa.beans.nagivation.NavigationDetails;
import websfa.enums.EnumMeniu;
import websfa.helpers.HelperMeniu;
import websfa.model.articole.OperatiiComenzi;

public class MenuNavigator {

	public List<NavigationDetails> createNavigationLinks(String tipUser, String codDepart, HttpServletRequest request) {

		ArrayList<NavigationDetails> navigationLinks = new ArrayList<>();

		String root = request.getServletContext().getContextPath();

		NavigationDetails nd;

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/comanda", root));
		nd.setText("Creare comanda");
		nd.setNume(EnumMeniu.CREARE_COMANDA);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/modifcmd", root));
		nd.setText("Modificare comanda");
		nd.setNume(EnumMeniu.MODIFICARE_COMANDA);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/afiscom", root));
		nd.setText("Afisare comanda");
		nd.setNume(EnumMeniu.AFISARE_COMANDA);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/aprobacmd", root));
		nd.setText("Aprobare comanda");
		nd.setNume(EnumMeniu.APROBARE_COMANDA);
		if (tipUser.equals("SD"))
			nd.setNavNumber(new OperatiiComenzi().getComenziAprobareSD(codDepart));
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/stocuri", root));
		nd.setText("Stocuri");
		nd.setNume(EnumMeniu.STOCURI);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/preturi", root));
		nd.setText("Preturi");
		nd.setNume(EnumMeniu.PRETURI);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/exit", root));
		nd.setText("Iesire");
		nd.setNume(EnumMeniu.EXIT);
		navigationLinks.add(nd);

		return navigationLinks;

	}

}
