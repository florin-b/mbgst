package websfa.beans;

import java.nio.charset.Charset;

public class CautareClient {

	private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	private String nume;
	private String filiala;

	public String getNume() {
		
		//byte[] ptext = nume.getBytes(ISO_8859_1);
		//return new String(ptext, UTF_8);
		
		return nume;

	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	@Override
	public String toString() {
		return "CautareClient [nume=" + nume + ", filiala=" + filiala + "]";
	}

}
