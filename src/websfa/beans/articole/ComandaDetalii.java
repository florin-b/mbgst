package websfa.beans.articole;

import java.util.List;

public class ComandaDetalii {

	private List<ArticolAfis> listArticole;
	private DateLivrareAfis dateLivrare;

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

	@Override
	public String toString() {
		return "ComandaDetalii [listArticole=" + listArticole + ", dateLivrare=" + dateLivrare + "]";
	}
	
	

}
