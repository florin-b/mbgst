package websfa.queries.user;

public class ClientSqlQueries {

	public static String getClient() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select nume, cod from clienti where lower(nume) like ? and rownum<30 order by nume ");

		return sqlString.toString();
	}

	public static String getAdresaSediulSocial() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.city1, a.street, a.house_num1, a.region ");
		sqlString.append(" from sapprd.adrc a ");
		sqlString.append(" where a.client = '900' and a.addrnumber = ");
		sqlString.append(" (select k.adrnr from sapprd.kna1 k where k.mandt = '900' and k.kunnr =?) ");

		return sqlString.toString();
	}

	public static String getLimitaCreditClient() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select distinct k.kunnr,k.klimk lcredit, ");
		sqlString.append(" k.skfor,k.ssobl, nvl((select s2.olikw+s2.ofakw from sapprd.s067 s2 where s2.mandt='900' and ");
		sqlString.append(" s2.kkber='1000' and s2.knkli=k.kunnr),0) olikw, nvl((select sum(s1.oeikw) from sapprd.s066 s1 ");
		sqlString.append(" where s1.mandt='900' and s1.kkber='1000' and spmon=to_char(sysdate,'yyyymm')  and s1.knkli=k.kunnr),0) oeikw,  k.crblb, c.nume ");
		sqlString.append(" from sapprd.knkk k, clienti c ");
		sqlString.append(" where k.mandt='900' and k.kkber='1000' and c.cod = k.kunnr and k.kunnr=? ");

		return sqlString.toString();
	}

}
