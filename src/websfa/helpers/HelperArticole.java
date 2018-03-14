package websfa.helpers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.Discount;
import websfa.database.connection.DBManager;
import websfa.queries.articole.SqlQueries;
import websfa.utils.ArticoleUtils;
import websfa.utils.Utils;

public class HelperArticole {
	
	private static final Logger logger = LogManager.getLogger(HelperArticole.class);

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
			logger.error(Utils.getStackTrace(e));
		}

		return discount;
	}

	public BigDecimal getCmpArticol(String codArticol, String filiala) {
		double cmp = 0;

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCmpArticol())) {

			stmt.clearParameters();
			stmt.setString(1, ArticoleUtils.formatCodArticol(codArticol));
			stmt.setString(2, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				cmp = rs.getDouble(1);
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return BigDecimal.valueOf(cmp);
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
			logger.error(Utils.getStackTrace(e));
		}

		return pretMediu.doubleValue();
	}

	public BigDecimal getProcentRedCmp(String codArticol) {
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
			logger.error(Utils.getStackTrace(e));
		}

		return BigDecimal.valueOf(procentReducere);
	}

	public List<String> getInfoArticolText(String infoArticolCod) {

		List<String> infoArticolList = new ArrayList<>();

		String[] artZone = infoArticolCod.split(";");

		for (int i = 0; i < artZone.length; i++) {
			String[] artToken = artZone[i].split(":");

			try (Connection conn = new DBManager().getTestDataSource().getConnection();
					PreparedStatement stmt = conn.prepareStatement(SqlQueries.getInfoArticolDesc())) {

				stmt.clearParameters();
				stmt.setString(1, artToken[0]);
				stmt.executeQuery();

				ResultSet rs = stmt.getResultSet();

				while (rs.next()) {

					String infoArt = rs.getString(1) + "#" + artToken[1] + "#" + artToken[2];
					infoArticolList.add(infoArt);

				}

			} catch (SQLException e) {
				logger.error(Utils.getStackTrace(e));
			}

		}

		return infoArticolList;
	}

	public static String formatCodArt(String codArt) {

		if (codArt.length() == 8)
			return "0000000000" + codArt;

		return codArt;

	}

}
