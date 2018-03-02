package websfa.beans;

import java.util.List;

public class ComandaModificareDetalii {

	private String idComanda;
	private String idComandaSAP;
	private double valoare;
	private String numeClient;
	private String dataEmitere;
	private List<ArticolComanda> listArticole;
	private DateLivrare dateLivrare;
	private String codClient;
	private String unitLog;
	private String idStareComanda;

	public String getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(String idComanda) {
		this.idComanda = idComanda;
	}

	public String getIdComandaSAP() {
		return idComandaSAP;
	}

	public void setIdComandaSAP(String idComandaSAP) {
		this.idComandaSAP = idComandaSAP;
	}

	public double getValoare() {
		return valoare;
	}

	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public String getDataEmitere() {
		return dataEmitere;
	}

	public void setDataEmitere(String dataEmitere) {
		this.dataEmitere = dataEmitere;
	}

	public List<ArticolComanda> getListArticole() {
		return listArticole;
	}

	public void setListArticole(List<ArticolComanda> listArticole) {
		this.listArticole = listArticole;
	}

	public DateLivrare getDateLivrare() {
		return dateLivrare;
	}

	public void setDateLivrare(DateLivrare dateLivrare) {
		this.dateLivrare = dateLivrare;
	}

	public String getCodClient() {
		return codClient;
	}

	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}

	public String getUnitLog() {
		return unitLog;
	}

	public void setUnitLog(String unitLog) {
		this.unitLog = unitLog;
	}

	public String getIdStareComanda() {
		return idStareComanda;
	}

	public void setIdStareComanda(String idStareComanda) {
		this.idStareComanda = idStareComanda;
	}
	
	

}
