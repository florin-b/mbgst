package websfa.beans;

import java.util.List;

public class Comanda {

	private String codClient;
	private String codAgent;
	private String unitLog;
	private boolean aprobaSD;
	private boolean aprobaDV;
	private double totalComanda;
	private String idCmd;
	private String idCmdSap;
	private String codDepart;
	private String tipUser;
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

	public double getTotalComanda() {
		return totalComanda;
	}

	public void setTotalComanda(double totalComanda) {
		this.totalComanda = totalComanda;
	}

	public String getIdCmdSap() {
		return idCmdSap;
	}

	public void setIdCmdSap(String idCmdSap) {
		this.idCmdSap = idCmdSap;
	}

	public String getIdCmd() {
		return idCmd;
	}

	public void setIdCmd(String idCmd) {
		this.idCmd = idCmd;
	}

	public String getCodDepart() {
		return codDepart;
	}

	public void setCodDepart(String codDepart) {
		this.codDepart = codDepart;
	}

	public String getTipUser() {
		return tipUser;
	}

	public void setTipUser(String tipUser) {
		this.tipUser = tipUser;
	}

	@Override
	public String toString() {
		return "Comanda [codClient=" + codClient + ", codAgent=" + codAgent + ", unitLog=" + unitLog + ", aprobaSD=" + aprobaSD + ", aprobaDV=" + aprobaDV
				+ ", totalComanda=" + totalComanda + ", idCmd=" + idCmd + ", idCmdSap=" + idCmdSap + ", codDepart=" + codDepart + ", tipUser=" + tipUser
				+ ", dateLivrare=" + dateLivrare + ", listArticole=" + listArticole + "]";
	}

}
