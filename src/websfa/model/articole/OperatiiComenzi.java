package websfa.model.articole;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import websfa.beans.ArticolCmdAprob;
import websfa.beans.ArticolComanda;
import websfa.beans.CautaCmdAprob;
import websfa.beans.Comanda;
import websfa.beans.ComandaAprobareAfis;
import websfa.beans.ComandaAprobareDetalii;
import websfa.beans.Status;
import websfa.beans.articole.Adresa;
import websfa.beans.articole.ArticolAfis;
import websfa.beans.articole.Cantitate;
import websfa.beans.articole.CautareComanda;
import websfa.beans.articole.ComandaDetalii;
import websfa.beans.articole.ComandaHeader;
import websfa.beans.articole.DateLivrareAfis;
import websfa.database.connection.DBManager;
import websfa.queries.user.ComenziSqlQueries;
import websfa.utils.DateUtils;

public class OperatiiComenzi {

	public List<ComandaHeader> getComenzi(CautareComanda cautareComanda) {

		List<ComandaHeader> listHeaderComenzi = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
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

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
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

	public Status salveazaComanda(Comanda comanda) {

		return salveazaAntetComanda(comanda);

	}

	private Status salveazaAntetComanda(Comanda comanda) {

		int idComanda = 0;

		Status status = new Status();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.salveazaAntetComanda())) {

			stmt.clearParameters();
			stmt.setString(1, comanda.getCodClient());
			stmt.setString(2, comanda.getUnitLog());
			stmt.setString(3, "2");
			stmt.setString(4, comanda.isAprobaSD() ? "1" : "0");
			stmt.setString(5, DateUtils.getCurrentDate());
			stmt.setString(6, comanda.getCodAgent());
			stmt.setString(7, comanda.getDateLivrare().getTipPlata());
			stmt.setString(8, comanda.getDateLivrare().getPersContact());
			stmt.setString(9, comanda.getDateLivrare().getTelPersContact());
			stmt.setString(10, comanda.getDateLivrare().getCodJudet());
			stmt.setDouble(11, comanda.getTotalComanda());
			stmt.setString(12, comanda.getDateLivrare().getTipTransp());
			stmt.setString(13, generateCmdSap());
			stmt.setString(14, comanda.isAprobaSD() ? "X" : " ");
			stmt.setString(15, comanda.isAprobaDV() ? "X" : " ");
			stmt.setString(16, comanda.getDateLivrare().getTipReducere());
			stmt.setString(17, comanda.getDateLivrare().getLocalitate());
			stmt.setString(18, comanda.getDateLivrare().getCodJudet());
			stmt.setString(19, comanda.getDateLivrare().getObsLivrare());

			stmt.registerOutParameter(20, Types.INTEGER);

			stmt.executeQuery();

			idComanda = stmt.getInt(20);

			status = salveazaArticoleComanda(conn, comanda.getListArticole(), idComanda);

		} catch (SQLException e) {
			status.setSuccess(false);
			status.setMessage("Eroare salvare date comanda");
		}

		return status;
	}

	public static String generateCmdSap() {
		Random rand = new Random();

		return String.valueOf(rand.nextInt(1000000) + 100000);
	}

	private Status salveazaArticoleComanda(Connection conn, List<ArticolComanda> listArticole, int idComanda) {

		Status status = new Status();
		status.setSuccess(true);
		status.setMessage("Comanda salvata");

		int poz = 10;

		String codArticol;

		for (ArticolComanda articol : listArticole) {

			try (CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.salveazaArticoleComanda())) {

				stmt.clearParameters();
				stmt.setInt(1, idComanda);
				stmt.setString(2, String.valueOf(poz));
				stmt.setString(3, "0");

				codArticol = articol.getCodArticol();
				if (codArticol.length() == 8)
					codArticol = "0000000000" + codArticol;

				stmt.setString(4, codArticol);
				stmt.setDouble(5, articol.getCantitate());
				stmt.setDouble(6, articol.getPretUnitar());
				stmt.setString(7, articol.getDepozit());
				stmt.setDouble(8, articol.getProcentReducere());
				stmt.setString(9, articol.getUm());
				stmt.setDouble(10, articol.getPretUnitar() * articol.getCantitate());

				stmt.executeQuery();

				poz += 10;

			} catch (SQLException e) {
				e.printStackTrace();
				status.setSuccess(false);
				status.setMessage("Eroare salvare articole comanda");
			}

		}

		return status;

	}

	public List<ComandaAprobareAfis> getComenziAprobare(CautaCmdAprob cautaCmd) {

		List<ComandaAprobareAfis> comenzi = new ArrayList<ComandaAprobareAfis>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getComenziAprobare())) {

			stmt.clearParameters();
			stmt.setString(1, DateUtils.getCurrentDate());
			stmt.setString(2, cautaCmd.getCodAngajat());
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				ComandaAprobareAfis cmd = new ComandaAprobareAfis();
				cmd.setIdComanda(String.valueOf(rs.getInt(1)));
				cmd.setValoareComanda(rs.getDouble(2));
				cmd.setNumeClient(rs.getString(3));

				comenzi.add(cmd);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comenzi;

	}

	public ComandaAprobareDetalii getDetaliiComandaAprob(String idComanda) {
		ComandaAprobareDetalii comandaAprobare = new ComandaAprobareDetalii();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getHeaderComandaAprob())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				comandaAprobare.setIdComandaSAP(rs.getString(1));
				comandaAprobare.setValoare(rs.getDouble(2));
				comandaAprobare.setDataEmitere(rs.getString(3));
				comandaAprobare.setNumeClient(rs.getString(4));
				comandaAprobare.setNumeAgent(rs.getString(5));

			}

			comandaAprobare.setListArticole(getArticoleComanda(conn, idComanda));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comandaAprobare;
	}

	private List<ArticolCmdAprob> getArticoleComanda(Connection conn, String idComanda) {
		List<ArticolCmdAprob> listArticole = new ArrayList<>();

		try (CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getArticoleComandaAprob())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				ArticolCmdAprob articol = new ArticolCmdAprob();
				articol.setCodArticol(rs.getString(1));
				articol.setCantitate(rs.getDouble(2));
				articol.setPretUnitar(rs.getDouble(3));
				articol.setDepozit(rs.getString(4));
				articol.setUm(rs.getString(5));
				articol.setDiscountClient(rs.getDouble(6));
				articol.setProcentReducere(rs.getDouble(7));
				articol.setPretCmp(2);
				articol.setNumeArticol(rs.getString(8));

				listArticole.add(articol);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listArticole;
	}

}
