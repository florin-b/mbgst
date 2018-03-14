package websfa.helpers;

public class HelperComanda {

	public static String getTipReducere(String codReducere) {
		String tipReducere;

		switch (codReducere) {
		case "1":
			tipReducere = " ";
			break;
		case "2":
			tipReducere = "X";
			break;
		case "3":
			tipReducere = "R";
			break;
		default:
			tipReducere = "-1";
			break;
		}

		return tipReducere;
	}
	
	
	
	public static String getTipReducereFromDB(String codReducere) {
		String tipReducere;

		switch (codReducere) {
		case " ":
			tipReducere = "1";
			break;
		case "X":
			tipReducere = "2";
			break;
		case "R":
			tipReducere = "3";
			break;
		default:
			tipReducere = "-1";
			break;
		}

		return tipReducere;
	}
	
	

}
