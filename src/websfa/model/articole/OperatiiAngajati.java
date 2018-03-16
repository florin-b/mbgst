package websfa.model.articole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.Angajat;
import websfa.database.connection.DBManager;
import websfa.queries.user.UserSqlQueries;
import websfa.utils.Utils;

public class OperatiiAngajati {

	private static final Logger logger = LogManager.getLogger(OperatiiAngajati.class);

	public List<Angajat> getAgenti(String filiala, String departament) {
		List<Angajat> listAgenti = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(UserSqlQueries.getAgenti())) {

			stmt.clearParameters();
			// stmt.setString(1, filiala);
			stmt.setString(1, departament);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Angajat angajat = new Angajat();
				angajat.setCod(rs.getString("cod").trim());
				angajat.setNume(rs.getString("nume").trim());

				listAgenti.add(angajat);
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return listAgenti;
	}

}
