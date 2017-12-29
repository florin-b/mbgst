package websfa.model.articole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import websfa.beans.articole.Adresa;
import websfa.beans.articole.ArticolAfis;
import websfa.beans.articole.Cantitate;
import websfa.beans.articole.CautareComanda;
import websfa.beans.articole.ComandaDetalii;
import websfa.beans.articole.ComandaHeader;
import websfa.beans.articole.DateLivrareAfis;
import websfa.database.connection.DBManager;
import websfa.queries.user.ComenziSqlQueries;

public class OperatiiComenzi {

	public List<ComandaHeader> getComenzi(CautareComanda cautareComanda) {

		List<ComandaHeader> listHeaderComenzi = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(ComenziSqlQueries.getListComenzi())) {

			stmt.clearParameters();
			stmt.setString(1, cautareComanda.getCodAngajat());

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				ComandaHeader header = new ComandaHeader();
				header.setId(rs.getString(1));

				header.setNumeClient(rs.getString(2) == "-1" ? rs.getString(2) : rs.getString(6));
				header.setDataCreare(rs.getString(3));
				header.setStare(String.valueOf(rs.getInt(4)));
				header.setValoare(rs.getDouble(5));
				listHeaderComenzi.add(header);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listHeaderComenzi;

	}

	public ComandaDetalii getDetaliiComanda(String idComanda) {
		ComandaDetalii detaliiComanda = new ComandaDetalii();
		DateLivrareAfis dateLivrare = new DateLivrareAfis();

		List<ArticolAfis> listArticole = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(ComenziSqlQueries.getArticoleComandaAfis())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				ArticolAfis articol = new ArticolAfis();
				articol.setCod(rs.getString(1));
				articol.setNume(rs.getString(2));
				articol.setDepozit(rs.getString(3));

				Cantitate cant = new Cantitate();
				cant.setValoare(rs.getDouble(4));
				cant.setUm(rs.getString(5));
				articol.setCantitate(cant);
				articol.setPretUnitar(rs.getDouble(6));
				articol.setProcReducere(rs.getDouble(7));
				listArticole.add(articol);

			}

			try (PreparedStatement stmt1 = conn.prepareStatement(ComenziSqlQueries.getDateLivrareAfis())) {

				stmt1.clearParameters();
				stmt1.setString(1, idComanda);

				stmt1.executeQuery();

				rs = stmt1.getResultSet();

				while (rs.next()) {

					dateLivrare.setPersoanaContact(rs.getString(1));
					dateLivrare.setTelPersContact(rs.getString(2));

					Adresa adresa = new Adresa();
					adresa.setStrada(rs.getString(3));
					adresa.setLocalitate(rs.getString(4));
					adresa.setNumeJudet(rs.getString(5));
					dateLivrare.setAdresaLivrare(adresa);

				}

			}

			detaliiComanda.setListArticole(listArticole);
			detaliiComanda.setDateLivrare(dateLivrare);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return detaliiComanda;
	}

}
