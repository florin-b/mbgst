package websfa.beans;

public class ConditiiComanda {

	private String id = "-1";
	private double conditiiCalitative;
	private int nrFacturi;
	private String observatii = " ";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getConditiiCalitative() {
		return conditiiCalitative;
	}

	public void setConditiiCalitative(double conditiiCalitative) {
		this.conditiiCalitative = conditiiCalitative;
	}

	public int getNrFacturi() {
		return nrFacturi;
	}

	public void setNrFacturi(int nrFacturi) {
		this.nrFacturi = nrFacturi;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}

	@Override
	public String toString() {
		return "ConditiiComanda [id=" + id + ", conditiiCalitative=" + conditiiCalitative + ", nrFacturi=" + nrFacturi + ", observatii=" + observatii + "]";
	}

}
