package websfa.beans.articole;

public class Adresa {

	private String numeJudet;
	private String codJudet;
	private String localitate;
	private String strada;

	public String getNumeJudet() {
		return numeJudet;
	}

	public void setNumeJudet(String numeJudet) {
		this.numeJudet = numeJudet;
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

	public String getCodJudet() {
		return codJudet;
	}

	public void setCodJudet(String codJudet) {
		this.codJudet = codJudet;
	}

	@Override
	public String toString() {
		return "Adresa [numeJudet=" + numeJudet + ", localitate=" + localitate + ", strada=" + strada + "]";
	}

}
