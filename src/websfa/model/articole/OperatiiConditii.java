package websfa.model.articole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.ConditiiComanda;
import websfa.queries.articole.SqlQueries;
import websfa.utils.Utils;

public class OperatiiConditii {

	private static final Logger logger = LogManager.getLogger(OperatiiConditii.class);

	public ConditiiComanda getConditiiComanda(Connection conn, String idComanda) {
		ConditiiComanda conditii = new ConditiiComanda();

		try (PreparedStatement stmt = conn.prepareStatement(SqlQueries.getConditiiHeader())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				conditii.setId(String.valueOf(rs.getInt(1)));
				conditii.setConditiiCalitative(rs.getDouble(2));
				conditii.setNrFacturi(rs.getInt(3));
				conditii.setObservatii(rs.getString(4));

			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return conditii;

	}

}
