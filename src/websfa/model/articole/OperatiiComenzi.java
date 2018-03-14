package websfa.model.articole;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.ArticolCmdAprob;
import websfa.beans.ArticolComanda;
import websfa.beans.ArticolSimplu;
import websfa.beans.CautaCmdAprob;
import websfa.beans.Comanda;
import websfa.beans.ComandaAprobareAfis;
import websfa.beans.ComandaAprobareDetalii;
import websfa.beans.ComandaAprobareOperare;
import websfa.beans.ComandaModificareDetalii;
import websfa.beans.DateGeneraleAfis;
import websfa.beans.DateLivrare;
import websfa.beans.Status;
import websfa.beans.articole.Adresa;
import websfa.beans.articole.ArticolAfis;
import websfa.beans.articole.Cantitate;
import websfa.beans.articole.CautareComanda;
import websfa.beans.articole.ComandaDetalii;
import websfa.beans.articole.ComandaHeader;
import websfa.beans.articole.DateLivrareAfis;
import websfa.database.connection.DBManager;
import websfa.enums.EnumOpereazaComanda;
import websfa.helpers.HelperComanda;
import websfa.queries.user.ComenziSqlQueries;
import websfa.soap.model.SapServices;
import websfa.utils.DateUtils;
import websfa.utils.Utils;

public class OperatiiComenzi {

	private static final Logger logger = LogManager.getLogger(OperatiiComenzi.class);

	public List<ComandaHeader> getComenziAfisare(CautareComanda cautareComanda) {

		List<ComandaHeader> listHeaderComenzi = new ArrayList<>();

		String stareComanda = " and a.status_aprov in (0,1,2,4) ";
		if (cautareComanda.getTipComanda().equals("1"))
			stareComanda = " and a.status_aprov in (0,1,2,4) ";
		if (cautareComanda.getTipComanda().equals("2"))
			stareComanda = " and a.status = 6 ";

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(ComenziSqlQueries.getListComenziAfisare(stareComanda))) {

			String dataCautare = "";
			if (cautareComanda.getInterval().equals("1"))
				dataCautare = DateUtils.getCurrentDate();
			else if (cautareComanda.getInterval().equals("2"))
				dataCautare = DateUtils.addDaysToDate(-7);
			else if (cautareComanda.getInterval().equals("3"))
				dataCautare = DateUtils.addDaysToDate(-30);

			stmt.clearParameters();
			stmt.setString(1, cautareComanda.getCodAngajat());
			stmt.setString(2, dataCautare);

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
			logger.error(Utils.getStackTrace(e));
		}

		return listHeaderComenzi;

	}

	public ComandaDetalii getDetaliiComanda(String idComanda) {
		ComandaDetalii detaliiComanda = new ComandaDetalii();
		DateLivrareAfis dateLivrare = new DateLivrareAfis();
		DateGeneraleAfis dateGenerale = new DateGeneraleAfis();

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

					dateGenerale.setNrComandaSap(rs.getString(6));

				}

			}

			detaliiComanda.setListArticole(listArticole);
			detaliiComanda.setDateLivrare(dateLivrare);
			detaliiComanda.setDateGenerale(dateGenerale);

			// TODO Transaction

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return detaliiComanda;
	}

	public Status salveazaComanda(Comanda comanda) {

		return salveazaAntetComanda(comanda);

	}

	private Status salveazaAntetComanda(Comanda comanda) {

		long idComanda = 0;

		Status status = new Status();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.salveazaAntetComanda())) {

			conn.setAutoCommit(false);

			stmt.clearParameters();
			stmt.setString(1, comanda.getCodClient());
			stmt.setString(2, comanda.getUnitLog());
			stmt.setString(3, "0");
			stmt.setString(4, comanda.isAprobaSD() ? "1" : "0");
			stmt.setString(5, DateUtils.getCurrentDate());
			stmt.setString(6, comanda.getCodAgent());
			stmt.setString(7, comanda.getDateLivrare().getTipPlata());
			stmt.setString(8, comanda.getDateLivrare().getPersContact());
			stmt.setString(9, comanda.getDateLivrare().getTelPersContact());
			stmt.setString(10, comanda.getDateLivrare().getStrada());
			stmt.setDouble(11, comanda.getTotalComanda());
			stmt.setString(12, comanda.getDateLivrare().getTipTransp());
			stmt.setString(13, " ");
			stmt.setString(14, comanda.isAprobaSD() ? "X" : " ");
			stmt.setString(15, comanda.isAprobaDV() ? "X" : " ");
			stmt.setString(16, HelperComanda.getTipReducere(comanda.getDateLivrare().getTipReducere()));
			stmt.setString(17, comanda.getDateLivrare().getLocalitate());
			stmt.setString(18, comanda.getDateLivrare().getCodJudet());
			stmt.setString(19, comanda.getDateLivrare().getObsLivrare());
			stmt.setString(20, DateUtils.formatDateSap(comanda.getDateLivrare().getDataLivrare()));
			stmt.setString(21, comanda.getDateLivrare().getDocumentInsotitor());
			stmt.setString(22, comanda.getDateLivrare().getRespIncasare());
			stmt.setString(23, comanda.getCodDepart());
			stmt.setString(24, comanda.getCodAgent());
			stmt.setString(25, DateUtils.getCurrentTime());
			stmt.setString(26, comanda.getTipUser());
			stmt.setString(27, comanda.getIdCmdSap() == null ? "-1" : comanda.getIdCmdSap());
			stmt.setString(28, DateUtils.getCurrentTime());

			stmt.registerOutParameter(29, Types.NUMERIC);

			stmt.executeQuery();

			idComanda = stmt.getLong(29);

			status = salveazaArticoleComanda(conn, comanda.getListArticole(), idComanda, comanda.getUnitLog());

			conn.commit();

			if (status.isSuccess())
				status = SapServices.creeazaComanda(idComanda);

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			status.setSuccess(false);
			status.setMessage("Eroare salvare date comanda");
		}

		return status;
	}

	private Status salveazaArticoleComanda(Connection conn, List<ArticolComanda> listArticole, long idComanda, String unitLog) {

		Status status = new Status();
		status.setSuccess(true);
		status.setMessage("Comanda salvata");

		int poz = 10;

		String codArticol;

		for (ArticolComanda articol : listArticole) {

			try (CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.salveazaArticoleComanda())) {

				stmt.clearParameters();
				stmt.setLong(1, idComanda);
				stmt.setString(2, String.valueOf(poz));
				stmt.setString(3, "0");

				codArticol = articol.getCod();
				if (codArticol.length() == 8)
					codArticol = "0000000000" + codArticol;

				stmt.setString(4, codArticol);
				stmt.setDouble(5, articol.getCantitate());
				stmt.setDouble(6, articol.getPretUnitar());
				stmt.setString(7, articol.getDepozit());
				stmt.setDouble(8, articol.getProcentReducere());
				stmt.setString(9, articol.getUm());

				BigDecimal valoarePozitie = BigDecimal.valueOf(articol.getPretUnitar()).divide(BigDecimal.valueOf(articol.getMultiplu()))
						.multiply(BigDecimal.valueOf(articol.getCantitate()));

				stmt.setDouble(10, valoarePozitie.doubleValue());
				stmt.setDouble(11, articol.getProcentFact().doubleValue());
				stmt.setDouble(12, articol.getDiscountClient().doubleValue());
				stmt.setDouble(13, articol.getProcentAprob().doubleValue());
				stmt.setString(14, articol.getInfoArticol());
				stmt.setDouble(15, articol.getMultiplu());
				stmt.setDouble(16, articol.getCantUmBaza().doubleValue());
				stmt.setString(17, articol.getUmBaza());
				stmt.setString(18, unitLog);

				stmt.executeQuery();

				poz += 10;

			} catch (SQLException e) {
				logger.error(Utils.getStackTrace(e));
				status.setSuccess(false);
				status.setMessage("Eroare salvare articole comanda");
			}

		}

		return status;

	}

	public List<ComandaAprobareAfis> getComenziAprobare(CautaCmdAprob cautaCmd) {

		List<ComandaAprobareAfis> comenzi = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getComenziAprobare())) {

			stmt.clearParameters();
			stmt.setString(1, cautaCmd.getUnitLog());
			stmt.setString(2, cautaCmd.getCodDepart());
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
			logger.error(Utils.getStackTrace(e));
		}

		return comenzi;

	}

	public List<ComandaAprobareAfis> getComenziModificare(CautaCmdAprob cautaCmd) {
		List<ComandaAprobareAfis> comenzi = new ArrayList<>();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getComenziModificare())) {

			stmt.clearParameters();
			stmt.setString(1, cautaCmd.getCodAngajat());
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
			logger.error(Utils.getStackTrace(e));
		}

		return comenzi;
	}

	public ComandaModificareDetalii getDetaliiComandaModif(String idComanda) {
		ComandaModificareDetalii comandaModificare = new ComandaModificareDetalii();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getHeaderComandaAprob())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				comandaModificare.setIdComandaSAP(rs.getString(1));
				comandaModificare.setValoare(rs.getDouble(2));
				comandaModificare.setDataEmitere(rs.getString(3));
				comandaModificare.setNumeClient(rs.getString(4));
				comandaModificare.setCodClient(rs.getString(6));
				comandaModificare.setUnitLog(rs.getString(7));
				comandaModificare.setIdStareComanda(rs.getString(8));

			}

			comandaModificare.setListArticole(getArticoleComandaModificare(conn, idComanda));
			comandaModificare.setDateLivrare(getDateLivrareComandaModificare(conn, idComanda));
			comandaModificare.setConditii(new OperatiiConditii().getConditiiComanda(conn, idComanda));

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return comandaModificare;
	}

	public List<ArticolComanda> getArticoleComandaModificare(Connection conn, String idComanda) {
		List<ArticolComanda> listArticole = new ArrayList<>();

		try (CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.getArticoleComandaAprob())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				ArticolComanda articol = new ArticolComanda();
				articol.setCod(rs.getString(1));
				articol.setCantitate(rs.getDouble(2));
				articol.setPretUnitar(rs.getDouble(3));
				articol.setDepozit(rs.getString(4));
				articol.setUm(rs.getString(5));
				articol.setDiscountClient(BigDecimal.valueOf(rs.getDouble(6)));
				articol.setProcentReducere(rs.getDouble(7));
				articol.setNume(rs.getString(8));
				articol.setMultiplu(rs.getInt(9));

				BigDecimal valoarePozitie = BigDecimal.valueOf(articol.getPretUnitar()).divide(BigDecimal.valueOf(articol.getMultiplu()))
						.multiply(BigDecimal.valueOf(articol.getCantitate()));

				articol.setValoarePoz(valoarePozitie);

				articol.setInfoArticol(rs.getString(10));
				articol.setProcentFact(BigDecimal.valueOf(rs.getDouble(11)));
				articol.setProcentAprob(BigDecimal.valueOf(rs.getDouble(12)));
				articol.setCantUmBaza(BigDecimal.valueOf(rs.getDouble(13)));
				articol.setUmBaza(rs.getString(14));
				articol.setUlStoc(rs.getString(15));

				listArticole.add(articol);

			}

			getConditiiArticoleComanda(conn, idComanda, listArticole);

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return listArticole;
	}

	public DateLivrare getDateLivrareComandaModificare(Connection conn, String idComanda) {
		DateLivrare dateLivrare = new DateLivrare();

		try (PreparedStatement stmt = conn.prepareStatement(ComenziSqlQueries.getDateLivrareCmdModif())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				dateLivrare.setCodJudet(rs.getString(1));
				dateLivrare.setLocalitate(rs.getString(2));
				dateLivrare.setStrada(rs.getString(3));
				dateLivrare.setPersContact(rs.getString(4));
				dateLivrare.setTelPersContact(rs.getString(5));
				dateLivrare.setTipReducere(HelperComanda.getTipReducereFromDB(rs.getString(6)));
				dateLivrare.setDocumentInsotitor(rs.getString(7));
				dateLivrare.setTipPlata(rs.getString(8));
				dateLivrare.setRespIncasare(rs.getString(9));
				dateLivrare.setTipTransp(rs.getString(10));
				dateLivrare.setDataLivrare(DateUtils.formatDateFromSap(rs.getString(11)));
				dateLivrare.setObsLivrare(rs.getString(12));

			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return dateLivrare;
	}

	private void getConditiiArticoleComanda(Connection conn, String idComanda, List<ArticolComanda> listArticole) {

		try (PreparedStatement stmt = conn.prepareStatement(ComenziSqlQueries.getConditiiArticole())) {

			stmt.clearParameters();
			stmt.setString(1, idComanda);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				for (ArticolComanda articol : listArticole) {
					if (articol.getCod().equals(rs.getString(1))) {
						articol.setConditiiCant(rs.getDouble(2));
						articol.setConditiiVal(rs.getDouble(3));
						break;
					}
				}

			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

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

			comandaAprobare.setListArticole(getArticoleComandaAprobare(conn, idComanda));

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return comandaAprobare;
	}

	private List<ArticolCmdAprob> getArticoleComandaAprobare(Connection conn, String idComanda) {
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
			logger.error(Utils.getStackTrace(e));
		}

		return listArticole;
	}

	public Status opereazaComanda(ComandaAprobareOperare comanda) {

		Status status;
		EnumOpereazaComanda stareComanda;

		if (comanda.isSeAproba())
			stareComanda = EnumOpereazaComanda.APROBARE;
		else
			stareComanda = EnumOpereazaComanda.RESPINGERE;

		if (!comanda.getListConditii().isEmpty() && comanda.isSeAproba()) {
			status = salveazaConditiiComanda(comanda);
		} else
			status = modificaStareComanda(comanda, stareComanda);

		return status;
	}

	public Status salveazaConditiiComanda(ComandaAprobareOperare comanda) {

		Status status = new Status();

		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.salveazaAntetConditii())) {

			stmt.clearParameters();
			stmt.setString(1, comanda.getCodAngajat());
			stmt.setString(2, DateUtils.getCurrentDate());
			stmt.setString(3, DateUtils.getCurrentTime());
			stmt.setString(4, comanda.getId());
			stmt.setString(5, " ");// cmd modif
			stmt.setFloat(6, 0);// conditii calitative
			stmt.setInt(7, 0);// nr facturi
			stmt.setString(8, " ");// observatii
			stmt.setFloat(9, 0);// castig brut

			stmt.registerOutParameter(10, Types.INTEGER);

			stmt.executeQuery();

			int idComanda = stmt.getInt(10);

			int poz = 1;
			for (ArticolSimplu articol : comanda.getListConditii()) {

				try (PreparedStatement stmtArt = conn.prepareCall(ComenziSqlQueries.salveazaArticoleConditii())) {

					stmtArt.clearParameters();
					stmtArt.setInt(1, idComanda);
					stmtArt.setInt(2, poz);

					String codArticol = articol.getCod();
					codArticol = codArticol.length() == 8 ? "0000000000" + codArticol : codArticol;

					stmtArt.setString(3, codArticol);
					stmtArt.setDouble(4, articol.getCantitate().doubleValue());
					stmtArt.setString(5, articol.getUm());
					stmtArt.setDouble(6, articol.getValoare().doubleValue());

					stmtArt.executeQuery();

					poz++;

				}

			}

			setStatusComandaConditii(conn, comanda.getId());

			status.setSuccess(true);
			status.setMessage("Conditii salvate.");

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			status.setSuccess(false);
			status.setMessage("Eroare salvare conditii comanda.");

		}

		return status;

	}

	private void setStatusComandaConditii(Connection conn, String idComanda) {

		try (CallableStatement stmt = conn.prepareCall(ComenziSqlQueries.setStatusComandaConditii())) {

			stmt.clearParameters();
			stmt.setInt(1, Integer.valueOf(idComanda));
			stmt.executeQuery();

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}
	}

	public Status modificaStareComanda(ComandaAprobareOperare comanda, EnumOpereazaComanda stareComanda) {
		Status status = new Status();

		if (stareComanda == EnumOpereazaComanda.APROBARE || stareComanda == EnumOpereazaComanda.RESPINGERE) {
			status = SapServices.opereazaComanda(comanda.getId(), comanda.getCodAngajat(), stareComanda.getCodStare());

		}

		return status;
	}

	public Status modificaStareComanda_old(ComandaAprobareOperare comanda, EnumOpereazaComanda stareComanda) {
		Status status = new Status();

		String sqlString = ComenziSqlQueries.respingeComanda();

		if (stareComanda == EnumOpereazaComanda.APROBARE)
			sqlString = ComenziSqlQueries.aprobaComanda();
		else if (stareComanda == EnumOpereazaComanda.RESPINGERE)
			sqlString = ComenziSqlQueries.respingeComanda();
		else if (stareComanda == EnumOpereazaComanda.CONDITIONARE)
			sqlString = ComenziSqlQueries.conditioneazaComanda();

		try (Connection conn = new DBManager().getTestDataSource().getConnection(); PreparedStatement stmt = conn.prepareCall(sqlString)) {

			stmt.clearParameters();
			stmt.setString(1, comanda.getId());

			stmt.executeQuery();

			status.setSuccess(true);
			status.setMessage("Operatie reusita");
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			status.setSuccess(false);
			status.setMessage("Eroare modificare stare");
		}

		return status;
	}

}
