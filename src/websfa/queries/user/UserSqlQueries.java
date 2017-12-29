package websfa.queries.user;

public class UserSqlQueries {

	public static String getFullName() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select nume from personal where cod =? ");

		return sqlString.toString();
	}

}
