package websfa.beans;

public class ComandaAprobareAfis {

	private String idComanda;
	private String numeClient;
	private double valoareComanda;

	public String getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(String idComanda) {
		this.idComanda = idComanda;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public double getValoareComanda() {
		return valoareComanda;
	}

	public void setValoareComanda(double valoareComanda) {
		this.valoareComanda = valoareComanda;
	}

	@Override
	public String toString() {
		return "ComandaAprobare [idComanda=" + idComanda + ", numeClient=" + numeClient + ", valoareComanda=" + valoareComanda + "]";
	}
	
	

}
