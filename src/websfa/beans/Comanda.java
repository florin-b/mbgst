package websfa.beans;

import java.util.List;

public class Comanda {

	private String codClient;
	private String codAgent;
	private String unitLog;
	private boolean aprobaSD;
	private boolean aprobaDV;
	private DateLivrare dateLivrare;
	List<ArticolComanda> listArticole;

	public String getCodClient() {
		return codClient;
	}

	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}

	public String getCodAgent() {
		return codAgent;
	}

	public void setCodAgent(String codAgent) {
		this.codAgent = codAgent;
	}

	public boolean isAprobaSD() {
		return aprobaSD;
	}

	public void setAprobaSD(boolean aprobaSD) {
		this.aprobaSD = aprobaSD;
	}

	public boolean isAprobaDV() {
		return aprobaDV;
	}

	public void setAprobaDV(boolean aprobaDV) {
		this.aprobaDV = aprobaDV;
	}

	public DateLivrare getDateLivrare() {
		return dateLivrare;
	}

	public void setDateLivrare(DateLivrare dateLivrare) {
		this.dateLivrare = dateLivrare;
	}

	public List<ArticolComanda> getListArticole() {
		return listArticole;
	}

	public void setListArticole(List<ArticolComanda> listArticole) {
		this.listArticole = listArticole;
	}

	public String getUnitLog() {
		return unitLog;
	}

	public void setUnitLog(String unitLog) {
		this.unitLog = unitLog;
	}

	@Override
	public String toString() {
		return "Comanda [codClient=" + codClient + ", codAgent=" + codAgent + ", dateLivrare=" + dateLivrare + ", listArticole=" + listArticole + "]";
	}

}
