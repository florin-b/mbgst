package websfa.model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import websfa.beans.articole.Adresa;
import websfa.beans.client.CautareClient;
import websfa.beans.client.ClientLite;
import websfa.beans.client.DetaliiClient;
import websfa.beans.client.StareClient;
import websfa.database.connection.DBManager;
import websfa.enums.EnumJudete;
import websfa.queries.user.ClientSqlQueries;

public class OperatiiClient {

	public List<ClientLite> getClient(CautareClient cautareClient) {

		List<ClientLite> listClienti = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
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

			e.printStackTrace();
		}

		return listClienti;
	}

	public DetaliiClient getDetaliiClient(String codClient) {

		DetaliiClient detaliiClient = new DetaliiClient();
		try (Connection conn = new DBManager().getProdDataSource().getConnection();) {

			detaliiClient.setCod(codClient);
			detaliiClient.setAdresa(getAdresaSediulSocialClient(conn, codClient));
			detaliiClient.setStareClient(getStareClient(conn, codClient));

		} catch (SQLException e) {

			e.printStackTrace();
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
				adresa.setNumeJudet(EnumJudete.getNumeJudet(Integer.valueOf(rs.getString("region"))));
				adresa.setCodJudet(rs.getString("region"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				stareClient.setStare(rs.getString(7).equals("X") ? "Client blocat" : " ");
				stareClient.setNumeClient(rs.getString(8));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stareClient;
	}

}
