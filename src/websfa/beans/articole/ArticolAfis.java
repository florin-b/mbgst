package websfa.beans.articole;

public class ArticolAfis {

	private String cod;
	private String nume;
	private String depozit;
	private Cantitate cantitate;
	private double pretUnitar;
	private double procReducere;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}

	public Cantitate getCantitate() {
		return cantitate;
	}

	public void setCantitate(Cantitate cantitate) {
		this.cantitate = cantitate;
	}

	public double getPretUnitar() {
		return pretUnitar;
	}

	public void setPretUnitar(double pretUnitar) {
		this.pretUnitar = pretUnitar;
	}

	public double getProcReducere() {
		return procReducere;
	}

	public void setProcReducere(double procReducere) {
		this.procReducere = procReducere;
	}

	@Override
	public String toString() {
		return "ArticolAfis [cod=" + cod + ", nume=" + nume + ", depozit=" + depozit + ", cantitate=" + cantitate + ", pretUnitar=" + pretUnitar
				+ ", procReducere=" + procReducere + "]";
	}

}
