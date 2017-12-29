package websfa.helper.articole;

public class ArticoleHelper {

	public static String formatCodArt(String codArt) {

		if (codArt.length() == 8)
			return "0000000000" + codArt;

		return codArt;

	}

}
