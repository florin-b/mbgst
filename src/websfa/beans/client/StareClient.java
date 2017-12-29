package websfa.beans.client;

public class StareClient {

	private double limitaCredit;
	private double restCredit;
	private String stare;
	private String numeClient;

	public double getLimitaCredit() {
		return limitaCredit;
	}

	public void setLimitaCredit(double limitaCredit) {
		this.limitaCredit = limitaCredit;
	}

	public double getRestCredit() {
		return restCredit;
	}

	public void setRestCredit(double restCredit) {
		this.restCredit = restCredit;
	}

	public String getStare() {
		return stare;
	}

	public void setStare(String stare) {
		this.stare = stare;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	@Override
	public String toString() {
		return "StareClient [limitaCredit=" + limitaCredit + ", restCredit=" + restCredit + ", stare=" + stare + ", numeClient=" + numeClient + "]";
	}

}
