package websfa.beans;

public class MasinaNeincarcata {

	private String nrMasina;
	private String nrBorderou;
	private String dataOra;

	public MasinaNeincarcata() {

	}

	public MasinaNeincarcata(String nrMasina, String nrBorderou) {
		super();
		this.nrMasina = nrMasina;
		this.nrBorderou = nrBorderou;
	}

	public String getNrMasina() {
		return nrMasina;
	}

	public void setNrMasina(String nrMasina) {
		this.nrMasina = nrMasina;
	}

	public String getNrBorderou() {
		return nrBorderou;
	}

	public void setNrBorderou(String nrBorderou) {
		this.nrBorderou = nrBorderou;
	}

	public String getDataOra() {
		return dataOra;
	}

	public void setDataOra(String dataOra) {
		this.dataOra = dataOra;
	}

	@Override
	public String toString() {
		return "MasinaNeincarcata [nrMasina=" + nrMasina + ", nrBorderou=" + nrBorderou + "]";
	}

}
