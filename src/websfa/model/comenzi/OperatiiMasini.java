package websfa.model.comenzi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import websfa.beans.MasinaNeincarcata;
import websfa.database.connection.DBManager;
import websfa.queries.articole.SqlQueries;
import websfa.utils.Utils;

public class OperatiiMasini {

	private static final Logger logger = LogManager.getLogger(OperatiiMasini.class);

	public List<MasinaNeincarcata> getMasiniNeincarcate(String filiala) {

		List<MasinaNeincarcata> listMasini = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getMasiniNeincarcate())) {

			stmt.clearParameters();
			stmt.setString(1, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				MasinaNeincarcata masina = new MasinaNeincarcata();

				masina.setNrBorderou(rs.getString("numarb"));
				masina.setNrMasina(rs.getString("masina"));
				listMasini.add(masina);
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return listMasini;

	}

	public boolean setSfarsitIncarcare(String borderou, String codSofer) {

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.setSfarsitIncarcare())) {

			stmt.clearParameters();
			stmt.setString(1, borderou);
			stmt.setString(2, codSofer);

			stmt.executeQuery();

			return true;

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			return false;
		}
	}

}
