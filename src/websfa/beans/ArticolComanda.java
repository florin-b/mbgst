package websfa.beans;

public class ArticolComanda {

	private String codArticol;
	private double cantitate;
	private String um;
	private double pretUnitar;
	private double procentReducere;
	private String depozit;

	public String getCodArticol() {
		return codArticol;
	}

	public void setCodArticol(String codArticol) {
		this.codArticol = codArticol;
	}

	public double getCantitate() {
		return cantitate;
	}

	public void setCantitate(double cantitate) {
		this.cantitate = cantitate;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public double getPretUnitar() {
		return pretUnitar;
	}

	public void setPretUnitar(double pretUnitar) {
		this.pretUnitar = pretUnitar;
	}

	public double getProcentReducere() {
		return procentReducere;
	}

	public void setProcentReducere(double procentReducere) {
		this.procentReducere = procentReducere;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}

	@Override
	public String toString() {
		return "ArticolComanda [codArticol=" + codArticol + ", cantitate=" + cantitate + ", um=" + um + ", pretUnitar=" + pretUnitar + ", procentReducere="
				+ procentReducere + ", depozit=" + depozit + "]";
	}

}
