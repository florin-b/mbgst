package websfa.beans;

public class DateLivrare {

	private String codJudet;
	private String localitate;
	private String strada;
	private String persContact;
	private String telPersContact;
	private String tipReducere;
	private String documentInsotitor;
	private String tipPlata;
	private String respIncasare;
	private String tipTransp;
	private String dataLivrare;
	private String obsLivrare;

	public String getCodJudet() {
		return codJudet;
	}

	public void setCodJudet(String codJudet) {
		this.codJudet = codJudet;
	}

	public String getLocalitate() {
		return localitate;
	}

	public void setLocalitate(String localitate) {
		this.localitate = localitate;
	}

	public String getStrada() {
		return strada;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	public String getPersContact() {
		return persContact;
	}

	public void setPersContact(String persContact) {
		this.persContact = persContact;
	}

	public String getTelPersContact() {
		return telPersContact;
	}

	public void setTelPersContact(String telPersContact) {
		this.telPersContact = telPersContact;
	}

	public String getTipReducere() {
		return tipReducere;
	}

	public void setTipReducere(String tipReducere) {
		this.tipReducere = tipReducere;
	}

	public String getDocumentInsotitor() {
		return documentInsotitor;
	}

	public void setDocumentInsotitor(String documentInsotitor) {
		this.documentInsotitor = documentInsotitor;
	}

	public String getTipPlata() {
		return tipPlata;
	}

	public void setTipPlata(String tipPlata) {
		this.tipPlata = tipPlata;
	}

	public String getRespIncasare() {
		return respIncasare;
	}

	public void setRespIncasare(String respIncasare) {
		this.respIncasare = respIncasare;
	}

	public String getTipTransp() {
		return tipTransp;
	}

	public void setTipTransp(String tipTransp) {
		this.tipTransp = tipTransp;
	}

	public String getDataLivrare() {
		return dataLivrare;
	}

	public void setDataLivrare(String dataLivrare) {
		this.dataLivrare = dataLivrare;
	}

	public String getObsLivrare() {
		return obsLivrare;
	}

	public void setObsLivrare(String obsLivrare) {
		this.obsLivrare = obsLivrare;
	}

	@Override
	public String toString() {
		return "DateLivrare [codJudet=" + codJudet + ", localitate=" + localitate + ", strada=" + strada + ", persContact=" + persContact + ", telPersContact="
				+ telPersContact + ", tipReducere=" + tipReducere + ", documentInsotitor=" + documentInsotitor + ", tipPlata=" + tipPlata + ", respIncasare="
				+ respIncasare + ", tipTransp=" + tipTransp + ", dataLivrare=" + dataLivrare + ", obsLivrare=" + obsLivrare + "]";
	}

}
