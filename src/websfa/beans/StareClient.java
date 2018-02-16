package websfa.beans;

public class StareClient {

	private double limitaCredit;
	private double restCredit;
	private boolean isBlocat;
	private String motivBlocat;
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

	public boolean isBlocat() {
		return isBlocat;
	}

	public void setBlocat(boolean isBlocat) {
		this.isBlocat = isBlocat;
	}

	public String getMotivBlocat() {
		return motivBlocat;
	}

	public void setMotivBlocat(String motivBlocat) {
		this.motivBlocat = motivBlocat;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	@Override
	public String toString() {
		return "StareClient [limitaCredit=" + limitaCredit + ", restCredit=" + restCredit + ", isBlocat=" + isBlocat + ", motivBlocat=" + motivBlocat
				+ ", numeClient=" + numeClient + "]";
	}

}
