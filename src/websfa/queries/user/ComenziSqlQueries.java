package websfa.queries.user;

public class ComenziSqlQueries {

	public static String getListComenzi() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.nume_client, to_char(to_date(a.datac,'yyyymmdd')), a.status_aprov, a.valoare, ");
		sqlString.append(" b.nume from sapprd.zcomhead_tableta a, clienti b where a.cod_agent =? ");
		sqlString.append(" and a.cod_client = b.cod ");
		sqlString.append(" and rownum<30 ");

		return sqlString.toString();

	}

	public static String getArticoleComandaAfis() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod) cod, b.nume, a.depoz, a.cantitate, a.um, a.valoare, ");
		sqlString.append(" a.procent from sapprd.zcomdet_tableta a, articole b where a.id=? and a.cod=b.cod order by a.poz");

		return sqlString.toString();
	}

	public static String getDateLivrareAfis() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select  pers_contact, telefon, adr_livrare, city, region from sapprd.zcomhead_tableta where id=? ");

		return sqlString.toString();
	}

}
