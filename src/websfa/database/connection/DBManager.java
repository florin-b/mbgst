package websfa.database.connection;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.pool.OracleDataSource;
import websfa.utils.MailOperations;
import websfa.utils.Utils;

public class DBManager {

	private static final Logger logger = LogManager.getLogger(DBManager.class);

	
	/*
	
	public DataSource getTestDataSource() {

		OracleDataSource oracleDS = null;
		try {
			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.89:1527:tes");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
			MailOperations.sendMail(Utils.getStackTrace(e));
		}
		return oracleDS;
	}
	*/
	
	
	public DataSource getProdDataSource_old() {

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.94:1521:prd002");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oracleDS;
	}
	
	
	public DataSource getProdDataSource() {

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.76:1521/PRD");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oracleDS;
	}		
	
	

}
