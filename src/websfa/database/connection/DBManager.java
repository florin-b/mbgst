package websfa.database.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.pool.OracleDataSource;
import websfa.utils.Utils;

public class DBManager {

	private static final Logger logger = LogManager.getLogger(DBManager.class);

	public DataSource getProdDataSource() {

		String[] accesData = new Utils().getConnectionData().split("#");

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL(accesData[0]);
			oracleDS.setUser(accesData[1]);
			oracleDS.setPassword(accesData[2]);

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
		}
		return oracleDS;
	}

	public DataSource getProdDataSource_env() {

		InitialContext initContext;
		DataSource ds = null;
		try {

			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/prod_db");

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
		}
		return ds;
	}

}
