package websfa.beans;

public class CautaPret {

	private String codArticol;
	private String filiala;
	private String departament;
	private String codClient;
	private String depozit;
	private double cantitate;
	private String um;

	public String getCodArticol() {
		return codArticol;
	}

	public void setCodArticol(String codArticol) {

		this.codArticol = codArticol;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public String getCodClient() {
		return codClient;
	}

	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
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

	@Override
	public String toString() {
		return "CautaPret [codArticol=" + codArticol + ", filiala=" + filiala + ", departament=" + departament + ", codClient=" + codClient + ", depozit="
				+ depozit + ", cantitate=" + cantitate + ", um=" + um + "]";
	}

}
