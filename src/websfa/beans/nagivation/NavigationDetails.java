package websfa.beans.nagivation;

import java.io.Serializable;

import websfa.enums.EnumMeniu;

public class NavigationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String link;
	private String text;
	private EnumMeniu nume;

	public NavigationDetails() {

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EnumMeniu getNume() {
		return nume;
	}

	public void setNume(EnumMeniu nume) {
		this.nume = nume;
	}

}
