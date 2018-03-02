package websfa.utils;

public class ArticoleUtils {

	public static String formatCodArticol(String codArticol) {
		if (codArticol.length() == 8)
			return "0000000000" + codArticol;

		return codArticol;
	}

}
