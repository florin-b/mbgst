package websfa.beans.articole;

public class ComandaHeader {

	private String id;
	private String numeClient;
	private String stare;
	private double valoare;
	private String dataCreare;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public String getStare() {
		return stare;
	}

	public void setStare(String stare) {
		this.stare = stare;
	}

	public double getValoare() {
		return valoare;
	}

	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	public String getDataCreare() {
		return dataCreare;
	}

	public void setDataCreare(String dataCreare) {
		this.dataCreare = dataCreare;
	}

}
