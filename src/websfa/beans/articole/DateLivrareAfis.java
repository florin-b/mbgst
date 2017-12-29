package websfa.beans.articole;

public class DateLivrareAfis {

	private Adresa adresaLivrare;
	private String persoanaContact;
	private String telPersContact;

	public Adresa getAdresaLivrare() {
		return adresaLivrare;
	}

	public void setAdresaLivrare(Adresa adresaLivrare) {
		this.adresaLivrare = adresaLivrare;
	}

	public String getPersoanaContact() {
		return persoanaContact;
	}

	public void setPersoanaContact(String persoanaContact) {
		this.persoanaContact = persoanaContact;
	}

	public String getTelPersContact() {
		return telPersContact;
	}

	public void setTelPersContact(String telPersContact) {
		this.telPersContact = telPersContact;
	}

	@Override
	public String toString() {
		return "DateLivrareAfis [adresaLivrare=" + adresaLivrare + ", persoanaContact=" + persoanaContact + ", telPersContact=" + telPersContact + "]";
	}
	
	

}
