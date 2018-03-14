package websfa.model.articole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.CautareClient;
import websfa.beans.ClientLite;
import websfa.beans.DetaliiClient;
import websfa.beans.StareClient;
import websfa.beans.articole.Adresa;
import websfa.database.connection.DBManager;
import websfa.enums.EnumRegiuniBG;
import websfa.queries.user.ClientSqlQueries;
import websfa.utils.Utils;

public class OperatiiClient {

	private static final Logger logger = LogManager.getLogger(OperatiiClient.class);
	
	public List<ClientLite> getClient(CautareClient cautareClient) {

		List<ClientLite> listClienti = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(ClientSqlQueries.getClient())) {

			stmt.clearParameters();
			stmt.setString(1, cautareClient.getNume().toLowerCase() + '%');

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				ClientLite client = new ClientLite();
				client.setNume(rs.getString("nume"));
				client.setCod(rs.getString("cod"));

				listClienti.add(client);
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return listClienti;
	}

	public DetaliiClient getDetaliiClient(String codClient) {

		DetaliiClient detaliiClient = new DetaliiClient();
		try (Connection conn = new DBManager().getTestDataSource().getConnection();) {

			detaliiClient.setCod(codClient);
			detaliiClient.setAdresa(getAdresaSediulSocialClient(conn, codClient));
			detaliiClient.setStareClient(getStareClient(conn, codClient));

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return detaliiClient;
	}

	public Adresa getAdresaSediulSocialClient(Connection conn, String codClient) {

		Adresa adresa = new Adresa();

		try (PreparedStatement stmt = conn.prepareStatement(ClientSqlQueries.getAdresaSediulSocial())) {

			stmt.clearParameters();
			stmt.setString(1, codClient);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				adresa.setLocalitate(rs.getString("city1"));
				adresa.setStrada(rs.getString("street") + " " + rs.getString("house_num1"));
				adresa.setNumeJudet(EnumRegiuniBG.getNumeJudet(Integer.valueOf(rs.getString("region"))));
				adresa.setCodJudet(rs.getString("region"));

			}
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return adresa;
	}

	public StareClient getStareClient(Connection conn, String codClient) {

		StareClient stareClient = new StareClient();

		try (PreparedStatement stmt = conn.prepareStatement(ClientSqlQueries.getLimitaCreditClient())) {

			stmt.clearParameters();
			stmt.setString(1, codClient);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				stareClient.setLimitaCredit(rs.getDouble(2));
				stareClient.setRestCredit(rs.getDouble(2) - (rs.getDouble(3) + rs.getDouble(4)) - (rs.getDouble(5) + rs.getDouble(6)));
				stareClient.setBlocat(rs.getString(7).equals("X") ? true : false);
				stareClient.setNumeClient(rs.getString(8));

			}
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return stareClient;
	}

}
