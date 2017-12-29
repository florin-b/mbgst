package websfa.beans.articole;

public class Cantitate {

	private double valoare;
	private String um;

	public double getValoare() {
		return valoare;
	}

	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	@Override
	public String toString() {
		return "Cantitate [valoare=" + valoare + ", um=" + um + "]";
	}

}
