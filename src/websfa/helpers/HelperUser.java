package websfa.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.queries.user.UserSqlQueries;
import websfa.utils.Utils;

public class HelperUser {

	private static final Logger logger = LogManager.getLogger(HelperUser.class);

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

	public static String getDepartAngajat(Connection conn, String angajatId) {

		String codDepart = null;

		try (PreparedStatement stmt = conn.prepareStatement(UserSqlQueries.getCodDepart())) {

			stmt.setString(1, angajatId);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				codDepart = rs.getString("divizie");
			}

		} catch (Exception ex) {
			logger.error(Utils.getStackTrace(ex));
		}

		return codDepart;
	}

}
