package websfa.beans;

import java.math.BigDecimal;

public class ArticolComanda {

	private String cod;
	private double cantitate;
	private String um;
	private double pretUnitar;
	private double procentReducere;
	private String depozit;
	private BigDecimal procentFact;
	private BigDecimal procentAprob;
	private String infoArticol;
	private String umBaza;
	private BigDecimal cantUmBaza;
	private BigDecimal discountClient;
	private int multiplu;
	private String nume;
	private BigDecimal valoarePoz;
	private String ulStoc;
	private double conditiiCant;
	private double conditiiVal;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public double getCantitate() {
		return cantitate;
	}

	public void setCantitate(double cantitate) {
		this.cantitate = cantitate;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public double getPretUnitar() {
		return pretUnitar;
	}

	public void setPretUnitar(double pretUnitar) {
		this.pretUnitar = pretUnitar;
	}

	public double getProcentReducere() {
		return procentReducere;
	}

	public void setProcentReducere(double procentReducere) {
		this.procentReducere = procentReducere;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}

	public BigDecimal getProcentFact() {
		return procentFact;
	}

	public void setProcentFact(BigDecimal procentFact) {
		this.procentFact = procentFact;
	}

	public BigDecimal getProcentAprob() {
		return procentAprob;
	}

	public void setProcentAprob(BigDecimal procentAprob) {
		this.procentAprob = procentAprob;
	}

	public String getInfoArticol() {
		return infoArticol;
	}

	public void setInfoArticol(String infoArticol) {
		this.infoArticol = infoArticol;
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

	public BigDecimal getDiscountClient() {
		return discountClient;
	}

	public void setDiscountClient(BigDecimal discountClient) {
		this.discountClient = discountClient;
	}

	public int getMultiplu() {
		return multiplu;
	}

	public void setMultiplu(int multiplu) {
		this.multiplu = multiplu;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public BigDecimal getValoarePoz() {
		return valoarePoz;
	}

	public void setValoarePoz(BigDecimal valoarePoz) {
		this.valoarePoz = valoarePoz;
	}

	public String getUlStoc() {
		return ulStoc;
	}

	public void setUlStoc(String ulStoc) {
		this.ulStoc = ulStoc;
	}
	
	

	public double getConditiiCant() {
		return conditiiCant;
	}

	public void setConditiiCant(double conditiiCant) {
		this.conditiiCant = conditiiCant;
	}

	public double getConditiiVal() {
		return conditiiVal;
	}

	public void setConditiiVal(double conditiiVal) {
		this.conditiiVal = conditiiVal;
	}

	@Override
	public String toString() {
		return "ArticolComanda [cod=" + cod + ", cantitate=" + cantitate + ", um=" + um + ", pretUnitar=" + pretUnitar + ", procentReducere=" + procentReducere
				+ ", depozit=" + depozit + ", procentFact=" + procentFact + ", procentAprob=" + procentAprob + ", infoArticol=" + infoArticol + ", umBaza="
				+ umBaza + ", cantUmBaza=" + cantUmBaza + ", discountClient=" + discountClient + ", multiplu=" + multiplu + ", nume=" + nume + "]";
	}

}
