package websfa.beans.articole;

import websfa.beans.ArticolSimplu;
import websfa.beans.Discount;

public class ArticolPret {

	private double pret;
	private String um;
	private double cantitate;
	private boolean permiteDiscount;
	private double multiplu;
	private String umBaza;
	private double cantUmBaza;
	private double cmp;
	private double pretCuDiscount;
	private double pretLista;
	private String impachetare;
	private double procReducereCmp;
	private Discount procenteDiscount;
	private String infoArticol;
	private String conditiiPret;
	private ArticolSimplu articolPromo;
	private double pretMediu;

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public double getMultiplu() {
		return multiplu;
	}

	public void setMultiplu(double multiplu) {
		this.multiplu = multiplu;
	}

	public String getUmBaza() {
		return umBaza;
	}

	public void setUmBaza(String umBaza) {
		this.umBaza = umBaza;
	}

	public double getCantUmBaza() {
		return cantUmBaza;
	}

	public void setCantUmBaza(double cantUmBaza) {
		this.cantUmBaza = cantUmBaza;
	}

	public double getCmp() {
		return cmp;
	}

	public void setCmp(double cmp) {
		this.cmp = cmp;
	}

	public double getPretCuDiscount() {
		return pretCuDiscount;
	}

	public void setPretCuDiscount(double pretCuDiscount) {
		this.pretCuDiscount = pretCuDiscount;
	}

	public double getPretLista() {
		return pretLista;
	}

	public void setPretLista(double pretLista) {
		this.pretLista = pretLista;
	}

	public String getImpachetare() {
		return impachetare;
	}

	public void setImpachetare(String impachetare) {
		this.impachetare = impachetare;
	}

	public double getProcReducereCmp() {
		return procReducereCmp;
	}

	public void setProcReducereCmp(double procReducereCmp) {
		this.procReducereCmp = procReducereCmp;
	}

	public String getInfoArticol() {
		return infoArticol;
	}

	public void setInfoArticol(String infoArticol) {
		this.infoArticol = infoArticol;
	}

	public ArticolSimplu getArticolPromo() {
		return articolPromo;
	}

	public void setArticolPromo(ArticolSimplu articolPromo) {
		this.articolPromo = articolPromo;
	}

	public String getConditiiPret() {
		return conditiiPret;
	}

	public void setConditiiPret(String conditiiPret) {
		this.conditiiPret = conditiiPret;
	}

	public double getCantitate() {
		return cantitate;
	}

	public void setCantitate(double cantitate) {
		this.cantitate = cantitate;
	}

	public boolean isPermiteDiscount() {
		return permiteDiscount;
	}

	public void setPermiteDiscount(boolean permiteDiscount) {
		this.permiteDiscount = permiteDiscount;
	}

	public Discount getProcenteDiscount() {
		return procenteDiscount;
	}

	public void setProcenteDiscount(Discount procenteDiscount) {
		this.procenteDiscount = procenteDiscount;
	}

	public double getPretMediu() {
		return pretMediu;
	}

	public void setPretMediu(double pretMediu) {
		this.pretMediu = pretMediu;
	}
	
	

}
