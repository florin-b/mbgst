package websfa.beans;

import java.util.List;

import websfa.enums.EnumTipAngajat;
import websfa.enums.EnumTipAprobare;

public class ComandaAprobareOperare {

	private String id;
	private String nrCmdSap;
	private String codAngajat;
	private List<ArticolSimplu> listConditii;
	private boolean seAproba;
	private EnumTipAprobare aprobariNecesare;
	private EnumTipAngajat tipAngajatAprob;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNrCmdSap() {
		return nrCmdSap;
	}

	public void setNrCmdSap(String nrCmdSap) {
		this.nrCmdSap = nrCmdSap;
	}

	public List<ArticolSimplu> getListConditii() {
		return listConditii;
	}

	public void setListConditii(List<ArticolSimplu> listConditii) {
		this.listConditii = listConditii;
	}

	public boolean isSeAproba() {
		return seAproba;
	}

	public void setSeAproba(boolean seAproba) {
		this.seAproba = seAproba;
	}

	public String getCodAngajat() {
		return codAngajat;
	}

	public void setCodAngajat(String codAngajat) {
		this.codAngajat = codAngajat;
	}

	public EnumTipAprobare getAprobariNecesare() {
		return aprobariNecesare;
	}

	public void setAprobariNecesare(EnumTipAprobare aprobariNecesare) {
		this.aprobariNecesare = aprobariNecesare;
	}

	public EnumTipAngajat getTipAngajatAprob() {
		return tipAngajatAprob;
	}

	public void setTipAngajatAprob(EnumTipAngajat tipAngajatAprob) {
		this.tipAngajatAprob = tipAngajatAprob;
	}

	@Override
	public String toString() {
		return "ComandaAprobareOperare [id=" + id + ", nrCmdSap=" + nrCmdSap + ", codAngajat=" + codAngajat + ", listConditii=" + listConditii + ", seAproba="
				+ seAproba + ", aprobariNecesare=" + aprobariNecesare + ", tipAngajatAprob=" + tipAngajatAprob + "]";
	}

}
