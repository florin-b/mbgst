package websfa.beans;

public class ClientLite {

	private String nume;
	private String cod;

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

	@Override
	public String toString() {
		return "ClientLite [nume=" + nume + ", cod=" + cod + "]";
	}

}
