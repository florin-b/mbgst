package websfa.beans.articole;

import java.util.List;

import websfa.beans.DateGeneraleAfis;

public class ComandaDetalii {

	private List<ArticolAfis> listArticole;
	private DateLivrareAfis dateLivrare;
	private DateGeneraleAfis dateGenerale;

	public List<ArticolAfis> getListArticole() {
		return listArticole;
	}

	public void setListArticole(List<ArticolAfis> listArticole) {
		this.listArticole = listArticole;
	}

	public DateLivrareAfis getDateLivrare() {
		return dateLivrare;
	}

	public void setDateLivrare(DateLivrareAfis dateLivrare) {
		this.dateLivrare = dateLivrare;
	}

	public DateGeneraleAfis getDateGenerale() {
		return dateGenerale;
	}

	public void setDateGenerale(DateGeneraleAfis dateGenerale) {
		this.dateGenerale = dateGenerale;
	}

	@Override
	public String toString() {
		return "ComandaDetalii [listArticole=" + listArticole + ", dateLivrare=" + dateLivrare + ", dateGenerale=" + dateGenerale + "]";
	}

	
	
	

}
