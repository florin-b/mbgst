package websfa.queries.user;

public class UserSqlQueries {

	public static String getFullName() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select nume from personal where cod =? ");

		return sqlString.toString();
	}

	public static String getTipAngajat() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select f.cod from personal u, functii_non_vanzari f where u.cod =? and f.cod = u.functie");

		return sqlString.toString();
	}

}
