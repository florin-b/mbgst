package websfa.beans;

import java.util.List;

public class ComandaAprobareOperare {

	private String id;
	private String nrCmdSap;
	private String codAngaj;
	private List<ArticolConditii> listConditii;
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

	public String getCodAngaj() {
		return codAngaj;
	}

	public void setCodAngaj(String codAngaj) {
		this.codAngaj = codAngaj;
	}

	public List<ArticolConditii> getListConditii() {
		return listConditii;
	}

	public void setListConditii(List<ArticolConditii> listConditii) {
		this.listConditii = listConditii;
	}

	public boolean isSeAproba() {
		return seAproba;
	}

	public void setSeAproba(boolean seAproba) {
		this.seAproba = seAproba;
	}

	@Override
	public String toString() {
		return "ComandaAprobareOperare [id=" + id + ", nrCmdSap=" + nrCmdSap + ", codAngaj=" + codAngaj + ", listConditii=" + listConditii + ", seAproba="
				+ seAproba + "]";
	}

	

}
