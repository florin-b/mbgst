package websfa.queries.articole;

public class SqlQueries {

	public static String cautaArticoleCod(String searchString) {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nume, decode(length(cod),18,substr(cod,-8),cod) from articole where ");
		sqlString.append(" lower(decode(length(cod),18,substr(cod,-8),cod)) like lower('" + searchString + "%') and spart =? order by nume");

		return sqlString.toString();
	}

	public static String cautaArticoleNume(String searchString) {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nume, decode(length(cod),18,substr(cod,-8),cod) from articole where ");
		sqlString.append(" lower(nume) like lower('" + searchString + "%') and spart =? order by nume ");

		return sqlString.toString();
	}

	public static String getStoc() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select lgort depoz , nvl(sum(labst),0) stoc, meins,lgort, sintetic from ");
		sqlString.append(" (select m.lgort,m.labst , mn.meins, mn.matnr  from sapprd.mard m, sapprd.mara mn ");
		sqlString.append(" where m.mandt = '900'  and m.mandt = mn.mandt and m.matnr = mn.matnr and m.lgort != 'CUSF' ");
		sqlString.append(" and m.matnr =? and m.werks in (?,?) ");
		sqlString.append(" union all ");
		sqlString.append(" select e.lgort depoz,-1 * sum(e.omeng), e.meins, e.matnr  from sapprd.vbbe e ");
		sqlString.append(" where e.mandt = '900' ");
		sqlString.append(" and e.matnr =:art and e.lgort != 'CUSF' and e.werks in (?,?) ");
		sqlString.append(" group by e.meins,e.lgort, e.matnr), articole ar where ar.cod = matnr ");
		sqlString.append(" group by meins,lgort, sintetic having sum(labst) > 0 order by depoz ");

		return sqlString.toString();

	}

	public static String getStocDepozit() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nvl(sum(labst),0) stoc, meins, ar.sintetic from ");
		sqlString.append(" (select m.labst , mn.meins, mn.matnr  from sapprd.mard m, sapprd.mara mn ");
		sqlString.append(" where m.mandt = '900' and m.mandt = mn.mandt ");
		sqlString.append(" and m.matnr = mn.matnr and m.matnr =?  and m.werks =? and m.lgort=?  ");
		sqlString.append(" union all ");
		sqlString.append(" select -1 * nvl(sum(e.omeng),0), e.meins, e.matnr  from sapprd.vbbe e ");
		sqlString.append(" where e.mandt = '900' and e.matnr =? and e.werks =? and e.lgort=? ");
		sqlString.append(" group by e.meins, e.matnr), articole ar where ar.cod = matnr group by meins, ar.sintetic having sum(labst) > 0 ");

		return sqlString.toString();
	}

}
