package websfa.model.articole;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import websfa.beans.Discount;
import websfa.database.connection.DBManager;
import websfa.queries.articole.SqlQueries;

public class HelperArticole {

	public Discount getProcenteDiscount(String filiala, String codDepart, String codArticol) {
		Discount discount = new Discount();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getProcenteDiscount())) {

			stmt.clearParameters();
			stmt.setString(1, codDepart);
			stmt.setString(2, filiala);
			stmt.setString(3, codDepart);
			stmt.setString(4, filiala);
			stmt.setString(5, codArticol);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				discount.setDiscountAV(rs.getDouble(1));
				discount.setDiscountSD(rs.getDouble(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return discount;
	}

	public double getCmpArticol(String codArticol, String filiala) {
		double cmp = 0;

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCmpArticol())) {

			stmt.clearParameters();
			stmt.setString(1, codArticol);
			stmt.setString(2, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				cmp = rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cmp;
	}

	public double getPretMediu(String codArticol, String filiala) {
		BigDecimal pretMediu = new BigDecimal(0);

		try (Connection conn = new DBManager().getTestDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(SqlQueries.getPretMediu())) {

			stmt.clearParameters();
			stmt.setString(1, codArticol);
			stmt.setString(2, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				BigDecimal val1 = BigDecimal.valueOf(rs.getDouble(1));
				BigDecimal val2 = BigDecimal.valueOf(rs.getDouble(2));
				BigDecimal val3 = BigDecimal.valueOf(rs.getDouble(3));

				BigDecimal interm1 = val1.divide(val3);

				BigDecimal interm2 = val2.divide(val3).multiply(BigDecimal.valueOf(0.15));

				pretMediu = interm1.add(interm2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pretMediu.doubleValue();
	}

	public double getProcentRedCmp(String codArticol) {
		double procentReducere = 0;

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getProcentReducereCmp())) {

			stmt.clearParameters();
			stmt.setString(1, codArticol);
			stmt.setString(2, codArticol);
			stmt.setString(3, codArticol);
			stmt.setString(4, codArticol);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				procentReducere = rs.getDouble(1) != 0 ? rs.getDouble(0) : rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return procentReducere;
	}

}
