package websfa.model.articole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import websfa.beans.articole.Articol;
import websfa.beans.articole.ArticolPret;
import websfa.beans.articole.ArticolStoc;
import websfa.database.connection.DBManager;
import websfa.helper.articole.ArticoleHelper;
import websfa.queries.articole.SqlQueries;

public class OperatiiArticole {

	public List<Articol> cautaArticole(Articol articol) {
		List<Articol> listArticole = new ArrayList<>();

		String sqlString;

		if (articol.getCod() != null)
			sqlString = SqlQueries.cautaArticoleCod(articol.getCod());
		else
			sqlString = SqlQueries.cautaArticoleNume(articol.getNume());

		try (Connection conn = new DBManager().getTestDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlString)) {

			stmt.clearParameters();
			stmt.setString(1, articol.getDepart());
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Articol art = new Articol();
				art.setNume(rs.getString(1));
				art.setCod(rs.getString(2));
				art.setDepart(articol.getDepart());

				listArticole.add(art);

			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return listArticole;
	}

	public List<ArticolStoc> getStoc(String codArticol, String filiala) {
		List<ArticolStoc> listArticole = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(SqlQueries.getStoc())) {

			stmt.clearParameters();
			stmt.setString(1, ArticoleHelper.formatCodArt(codArticol));
			stmt.setString(2, filiala);
			stmt.setString(3, filiala);
			stmt.setString(4, ArticoleHelper.formatCodArt(codArticol));
			stmt.setString(5, filiala);
			stmt.setString(6, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				ArticolStoc articol = new ArticolStoc();

				articol.setDepozit(rs.getString(1));
				articol.setCantitate(rs.getDouble(2));
				articol.setUm(rs.getString(3));
				listArticole.add(articol);

			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return listArticole;
	}

	public ArticolStoc getStoc(String codArticol, String filiala, String depozit) {

		ArticolStoc articolStoc = new ArticolStoc();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getStocDepozit())) {

			stmt.clearParameters();
			stmt.setString(1, ArticoleHelper.formatCodArt(codArticol));
			stmt.setString(2, filiala);
			stmt.setString(3, depozit);
			stmt.setString(4, ArticoleHelper.formatCodArt(codArticol));
			stmt.setString(5, filiala);
			stmt.setString(6, depozit);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				articolStoc.setCantitate(rs.getDouble(1));
				articolStoc.setUm(rs.getString(2));
				articolStoc.setSintetic(rs.getString(3));

			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return articolStoc;
	}

	public ArticolPret getPret(String codArticol, String filiala, String departament) {
		ArticolPret artPret = new ArticolPret();

		artPret.setPret(generatePret());
		artPret.setUm("BUC");

		return artPret;

	}

	public static int generatePret() {
		Random rand = new Random();

		return rand.nextInt(100) + 10;
	}

}
