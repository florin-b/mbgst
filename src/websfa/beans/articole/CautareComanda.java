package websfa.beans.articole;

public class CautareComanda {

	private String codAngajat;
	private String interval;
	private String tipComanda;

	public String getCodAngajat() {
		return codAngajat;
	}

	public void setCodAngajat(String codAngajat) {
		this.codAngajat = codAngajat;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getTipComanda() {
		return tipComanda;
	}

	public void setTipComanda(String tipComanda) {
		this.tipComanda = tipComanda;
	}

	@Override
	public String toString() {
		return "CautareComanda [codAngajat=" + codAngajat + ", interval=" + interval + ", tipComanda=" + tipComanda + "]";
	}

}
