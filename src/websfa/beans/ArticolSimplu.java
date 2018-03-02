package websfa.beans;

import java.math.BigDecimal;

public class ArticolSimplu {
	private String cod;
	private BigDecimal cantitate;
	private String um;
	private BigDecimal valoare;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public BigDecimal getCantitate() {
		return cantitate;
	}

	public void setCantitate(BigDecimal cantitate) {
		this.cantitate = cantitate;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public BigDecimal getValoare() {
		return valoare;
	}

	public void setValoare(BigDecimal valoare) {
		this.valoare = valoare;
	}

	@Override
	public String toString() {
		return "ArticolSimplu [cod=" + cod + ", cantitate=" + cantitate + ", um=" + um + ", valoare=" + valoare + "]";
	}

}
