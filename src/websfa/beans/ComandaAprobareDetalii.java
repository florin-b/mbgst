package websfa.beans;

import java.util.List;

import websfa.enums.EnumTipAprobare;

public class ComandaAprobareDetalii {

	private String idComanda;
	private String idComandaSAP;
	private double valoare;
	private String numeClient;
	private String dataEmitere;
	private String numeAgent;
	private List<ArticolCmdAprob> listArticole;
	private EnumTipAprobare aprobariNecesare;

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

	public String getNumeAgent() {
		return numeAgent;
	}

	public void setNumeAgent(String numeAgent) {
		this.numeAgent = numeAgent;
	}

	public List<ArticolCmdAprob> getListArticole() {
		return listArticole;
	}

	public void setListArticole(List<ArticolCmdAprob> listArticole) {
		this.listArticole = listArticole;
	}

	public EnumTipAprobare getAprobariNecesare() {
		return aprobariNecesare;
	}

	public void setAprobariNecesare(EnumTipAprobare aprobariNecesare) {
		this.aprobariNecesare = aprobariNecesare;
	}

	@Override
	public String toString() {
		return "ComandaAprobareDetalii [idComanda=" + idComanda + ", idComandaSAP=" + idComandaSAP + ", valoare=" + valoare + ", numeClient=" + numeClient
				+ ", dataEmitere=" + dataEmitere + ", numeAgent=" + numeAgent + ", listArticole=" + listArticole + ", aprobariNecesare=" + aprobariNecesare
				+ "]";
	}

}
