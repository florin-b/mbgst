package websfa.beans;

import java.util.List;

public class ComandaAprobareOperare {

	private String id;
	private String nrCmdSap;
	private String codAngajat;
	private List<ArticolSimplu> listConditii;
	private boolean seAproba;

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

	@Override
	public String toString() {
		return "ComandaAprobareOperare [id=" + id + ", nrCmdSap=" + nrCmdSap + ", codAngajat=" + codAngajat + ", listConditii=" + listConditii + ", seAproba="
				+ seAproba + "]";
	}

}
