package websfa.beans;

public class ArticolSimplu {
	private String cod;
	private double cantitate;
	private String um;
	private double valoare;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
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

	public double getValoare() {
		return valoare;
	}

	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	@Override
	public String toString() {
		return "ArticolConditii [cod=" + cod + ", cantitate=" + cantitate + ", um=" + um + ", valoare=" + valoare + "]";
	}

}
