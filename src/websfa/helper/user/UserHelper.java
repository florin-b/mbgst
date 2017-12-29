package websfa.helper.user;

public class UserHelper {

	public static String getLogonStatus(int msgId) {

		String logonStatus;

		switch (msgId) {
		case 0:
			logonStatus = "Cont inexistent";
			break;
		case 1:
			logonStatus = "Cont blocat 60 minute";
			break;
		case 2:
			logonStatus = "Parola incorecta";
			break;
		case 4:
			logonStatus = "Cont inactiv";
			break;
		default:
			logonStatus = "Eroare necunoscuta";
			break;

		}

		return logonStatus;

	}

	public static String getCodDepart(String numeDepart) {
		String codDepart;

		switch (numeDepart) {
		case "CHIM":
			codDepart = "07";
			break;

		case "DIVE":
			codDepart = "10";
			break;

		case "ELEC":
			codDepart = "05";
			break;

		case "FERO":
			codDepart = "02";
			break;

		case "GIPS":
			codDepart = "06";
			break;

		case "INST":
			codDepart = "08";
			break;

		case "LEMN":
			codDepart = "01";
			break;

		case "MATE":
			codDepart = "04";
			break;

		case "PARC":
			codDepart = "03";
			break;

		case "HIDR":
			codDepart = "09";
			break;

		default:
			codDepart = "00";
			break;
		}

		return codDepart;

	}

	public static String getUnitLog(String numeFiliala) {
		String fl = "NN10";

		if (numeFiliala.equals("BACAU"))
			fl = "BC10";
		else if (numeFiliala.equals("BUZAU"))
			fl = "BZ10";
		else if (numeFiliala.equals("GALATI"))
			fl = "GL10";
		else if (numeFiliala.equals("PITESTI"))
			fl = "AG10";
		else if (numeFiliala.equals("TIMISOARA"))
			fl = "TM10";
		else if (numeFiliala.equals("ORADEA"))
			fl = "BH10";
		else if (numeFiliala.equals("FOCSANI"))
			fl = "VN10";
		else if (numeFiliala.equals("GLINA"))
			fl = "BU10";
		else if (numeFiliala.equals("ANDRONACHE"))
			fl = "BU13";
		else if (numeFiliala.equals("OTOPENI"))
			fl = "BU12";
		else if (numeFiliala.equals("CLUJ"))
			fl = "CJ10";
		else if (numeFiliala.equals("BAIA"))
			fl = "MM10";
		else if (numeFiliala.equals("MILITARI"))
			fl = "BU11";
		else if (numeFiliala.equals("CONSTANTA"))
			fl = "CT10";
		else if (numeFiliala.equals("BRASOV"))
			fl = "BV10";
		else if (numeFiliala.equals("PLOIESTI"))
			fl = "PH10";
		else if (numeFiliala.equals("PIATRA"))
			fl = "NT10";
		else if (numeFiliala.equals("MURES"))
			fl = "MS10";
		else if (numeFiliala.equals("IASI"))
			fl = "IS10";
		else if (numeFiliala.equals("CRAIOVA"))
			fl = "DJ10";
		else if (numeFiliala.equals("SIBIU"))
			fl = "SB10";
		else if (numeFiliala.equals("DEVA"))
			fl = "HD10";
		else if (numeFiliala.equals("GL_CENTRAL"))
			fl = "GL90";
		else if (numeFiliala.equals("TOATE"))
			fl = "BU90";

		return fl;

	}

}
