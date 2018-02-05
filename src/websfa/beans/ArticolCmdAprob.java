package websfa.beans;

public class ArticolCmdAprob {

	private String codArticol;
	private String numeArticol;
	private String depozit;
	private double cantitate;
	private String um;
	private double pretUnitar;
	private double pretCmp;
	private double discountClient;
	private double procentReducere;
	private double conditiiCant;
	private double conditiiVal;

	public String getCodArticol() {
		return codArticol;
	}

	public void setCodArticol(String codArticol) {
		this.codArticol = codArticol;
	}

	public String getNumeArticol() {
		return numeArticol;
	}

	public void setNumeArticol(String numeArticol) {
		this.numeArticol = numeArticol;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
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

	public double getPretCmp() {
		return pretCmp;
	}

	public void setPretCmp(double pretCmp) {
		this.pretCmp = pretCmp;
	}

	public double getDiscountClient() {
		return discountClient;
	}

	public void setDiscountClient(double discountClient) {
		this.discountClient = discountClient;
	}

	public double getProcentReducere() {
		return procentReducere;
	}

	public void setProcentReducere(double procentReducere) {
		this.procentReducere = procentReducere;
	}

	public double getConditiiCant() {
		return conditiiCant;
	}

	public void setConditiiCant(double conditiiCant) {
		this.conditiiCant = conditiiCant;
	}

	public double getConditiiVal() {
		return conditiiVal;
	}

	public void setConditiiVal(double conditiiVal) {
		this.conditiiVal = conditiiVal;
	}

	@Override
	public String toString() {
		return "ArticolCmdAprob [codArticol=" + codArticol + ", numeArticol=" + numeArticol + ", depozit=" + depozit + ", cantitate=" + cantitate + ", um=" + um
				+ ", pretUnitar=" + pretUnitar + ", pretCmp=" + pretCmp + ", discountClient=" + discountClient + ", procentReducere=" + procentReducere
				+ ", conditiiCant=" + conditiiCant + ", conditiiVal=" + conditiiVal + "]";
	}

}
