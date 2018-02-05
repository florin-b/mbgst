package websfa.beans;

public class CautaCmdAprob {

	private String tipAngajat;
	private String unitLog;
	private String codDepart;
	private String codAngajat;

	public String getTipAngajat() {
		return tipAngajat;
	}

	public void setTipAngajat(String tipAngajat) {
		this.tipAngajat = tipAngajat;
	}

	public String getUnitLog() {
		return unitLog;
	}

	public void setUnitLog(String unitLog) {
		this.unitLog = unitLog;
	}

	public String getCodDepart() {
		return codDepart;
	}

	public void setCodDepart(String codDepart) {
		this.codDepart = codDepart;
	}

	public String getCodAngajat() {
		return codAngajat;
	}

	public void setCodAngajat(String codAngajat) {
		this.codAngajat = codAngajat;
	}

	@Override
	public String toString() {
		return "CautaCmdAprob [tipAngajat=" + tipAngajat + ", unitLog=" + unitLog + ", codDepart=" + codDepart + ", codAngajat=" + codAngajat + "]";
	}

	
	
}
