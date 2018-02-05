package websfa.database.connection;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DBManager {

	public DataSource getTestDataSource() {

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.89:1527:tes");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oracleDS;
	}

	

	
}
