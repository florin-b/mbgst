package websfa.beans.articole;

public class Articol {

	private String nume;
	private String cod;
	private String depart;

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@Override
	public String toString() {
		return "Articol [nume=" + nume + ", cod=" + cod + ", depart=" + depart + "]";
	}

	

}
