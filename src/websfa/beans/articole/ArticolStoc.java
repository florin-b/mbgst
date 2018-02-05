package websfa.beans.articole;

public class ArticolStoc {

	private double cantitate;
	private String um;
	private String depozit;
	private String sintetic;
	private String codDepart;

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

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}

	public String getSintetic() {
		return sintetic;
	}

	public void setSintetic(String sintetic) {
		this.sintetic = sintetic;
	}

	public String getCodDepart() {
		return codDepart;
	}

	public void setCodDepart(String codDepart) {
		this.codDepart = codDepart;
	}

	@Override
	public String toString() {
		return "ArticolStoc [cantitate=" + cantitate + ", um=" + um + ", depozit=" + depozit + ", sintetic=" + sintetic + ", codDepart=" + codDepart + "]";
	}

	

}
