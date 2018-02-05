package websfa.beans;

import websfa.beans.articole.Adresa;

public class DetaliiClient {

	private String cod;
	private String nume;
	private Adresa adresa;
	private StareClient stareClient;

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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public StareClient getStareClient() {
		return stareClient;
	}

	public void setStareClient(StareClient stareClient) {
		this.stareClient = stareClient;
	}

	@Override
	public String toString() {
		return "DetaliiClient [cod=" + cod + ", nume=" + nume + ", adresa=" + adresa + ", stareClient=" + stareClient + "]";
	}
	
	

}
