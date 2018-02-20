package websfa.database.connection;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;
import websfa.utils.MailOperations;
import websfa.utils.Utils;

public class DBManager {

	public DataSource getTestDataSource() {

		OracleDataSource oracleDS = null;
		try {
			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.88:1527:qas");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");

		} catch (Exception e) {
			MailOperations.sendMail(Utils.getStackTrace(e));
		}
		return oracleDS;
	}

}
