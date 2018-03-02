package websfa.beans.articole;

import java.math.BigDecimal;
import java.util.List;

import websfa.beans.ArticolSimplu;
import websfa.beans.Discount;

public class ArticolPret {

	private BigDecimal pret;
	private String um;
	private BigDecimal cantitate;
	private boolean faraDiscount;
	private BigDecimal multiplu;
	private String umBaza;
	private BigDecimal cantUmBaza;
	private BigDecimal cmp;
	private BigDecimal pretCuDiscount;
	private BigDecimal pretLista;
	private String impachetare;
	private BigDecimal procReducereCmp;
	private Discount procenteDiscount;
	private String infoArticol;
	private List<String> infoArticolDesc;
	private String conditiiPret;
	private ArticolSimplu articolPromo;
	private BigDecimal pretMediu;
	private String moneda;

	public BigDecimal getPret() {
		return pret;
	}

	public void setPret(BigDecimal pret) {
		this.pret = pret;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public BigDecimal getCantitate() {
		return cantitate;
	}

	public void setCantitate(BigDecimal cantitate) {
		this.cantitate = cantitate;
	}

	public boolean isFaraDiscount() {
		return faraDiscount;
	}

	public void setFaraDiscount(boolean faraDiscount) {
		this.faraDiscount = faraDiscount;
	}

	public BigDecimal getMultiplu() {
		return multiplu;
	}

	public void setMultiplu(BigDecimal multiplu) {
		this.multiplu = multiplu;
	}

	public String getUmBaza() {
		return umBaza;
	}

	public void setUmBaza(String umBaza) {
		this.umBaza = umBaza;
	}

	public BigDecimal getCantUmBaza() {
		return cantUmBaza;
	}

	public void setCantUmBaza(BigDecimal cantUmBaza) {
		this.cantUmBaza = cantUmBaza;
	}

	public BigDecimal getCmp() {
		return cmp;
	}

	public void setCmp(BigDecimal cmp) {
		this.cmp = cmp;
	}

	public BigDecimal getPretCuDiscount() {
		return pretCuDiscount;
	}

	public void setPretCuDiscount(BigDecimal pretCuDiscount) {
		this.pretCuDiscount = pretCuDiscount;
	}

	public BigDecimal getPretLista() {
		return pretLista;
	}

	public void setPretLista(BigDecimal pretLista) {
		this.pretLista = pretLista;
	}

	public String getImpachetare() {
		return impachetare;
	}

	public void setImpachetare(String impachetare) {
		this.impachetare = impachetare;
	}

	public BigDecimal getProcReducereCmp() {
		return procReducereCmp;
	}

	public void setProcReducereCmp(BigDecimal procReducereCmp) {
		this.procReducereCmp = procReducereCmp;
	}

	public Discount getProcenteDiscount() {
		return procenteDiscount;
	}

	public void setProcenteDiscount(Discount procenteDiscount) {
		this.procenteDiscount = procenteDiscount;
	}

	public String getInfoArticol() {
		return infoArticol;
	}

	public void setInfoArticol(String infoArticol) {
		this.infoArticol = infoArticol;
	}

	public String getConditiiPret() {
		return conditiiPret;
	}

	public void setConditiiPret(String conditiiPret) {
		this.conditiiPret = conditiiPret;
	}

	public ArticolSimplu getArticolPromo() {
		return articolPromo;
	}

	public void setArticolPromo(ArticolSimplu articolPromo) {
		this.articolPromo = articolPromo;
	}

	public BigDecimal getPretMediu() {
		return pretMediu;
	}

	public void setPretMediu(BigDecimal pretMediu) {
		this.pretMediu = pretMediu;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List<String> getInfoArticolDesc() {
		return infoArticolDesc;
	}

	public void setInfoArticolDesc(List<String> infoArticolDesc) {
		this.infoArticolDesc = infoArticolDesc;
	}

	@Override
	public String toString() {
		return "ArticolPret [pret=" + pret + ", um=" + um + ", cantitate=" + cantitate + ", faraDiscount=" + faraDiscount + ", multiplu=" + multiplu
				+ ", umBaza=" + umBaza + ", cantUmBaza=" + cantUmBaza + ", cmp=" + cmp + ", pretCuDiscount=" + pretCuDiscount + ", pretLista=" + pretLista
				+ ", impachetare=" + impachetare + ", procReducereCmp=" + procReducereCmp + ", procenteDiscount=" + procenteDiscount + ", infoArticol="
				+ infoArticol + ", infoArticolDesc=" + infoArticolDesc + ", conditiiPret=" + conditiiPret + ", articolPromo=" + articolPromo + ", pretMediu="
				+ pretMediu + ", moneda=" + moneda + "]";
	}

}
