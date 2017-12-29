package websfa.tags.navigator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import websfa.beans.nagivation.NavigationDetails;
import websfa.enums.EnumMeniu;

public class Navigator extends SimpleTagSupport {

	private List<NavigationDetails> navigationLinks;

	private String tipUser;

	public String getTipUser() {
		return tipUser;
	}

	public void setTipUser(String tipUser) {
		this.tipUser = tipUser;
	}

	private void CreateNavigationLinks() {

		navigationLinks = new ArrayList<>();

		PageContext pageContext = (PageContext) getJspContext();
		String root = pageContext.getServletContext().getContextPath();

		NavigationDetails nd;

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/comanda", root));
		nd.setText("Creare comanda");
		nd.setNume(EnumMeniu.CREARE_COMANDA);
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/auth/modificaDelegatie.jsp", root));
		nd.setText("Modificare comanda");
		nd.setNume(EnumMeniu.MODIFICARE_COMANDA);
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/afiscom", root));
		nd.setText("Afisare comanda");
		nd.setNume(EnumMeniu.AFISARE_COMANDA);
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/stocuri", root));
		nd.setText("Stocuri");
		nd.setNume(EnumMeniu.STOCURI);
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/preturi", root));
		nd.setText("Preturi");
		nd.setNume(EnumMeniu.PRETURI);
		navigationLinks.add(nd);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/exit.jsp", root));
		nd.setText("Iesire");
		nd.setNume(EnumMeniu.EXIT);
		navigationLinks.add(nd);

	}

	@Override
	public void doTag() throws JspException, IOException {

		CreateNavigationLinks();

		for (NavigationDetails nd : navigationLinks) {
			getJspContext().setAttribute("navdetails", nd);
			getJspBody().invoke(null);
		}
	}

}
