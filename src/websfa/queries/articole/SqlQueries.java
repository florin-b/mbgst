package websfa.queries.articole;

public class SqlQueries {

	public static String cautaArticoleCod(String searchString) {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nume, decode(length(cod),18,substr(cod,-8),cod) from articole where ");
		sqlString.append(" lower(decode(length(cod),18,substr(cod,-8),cod)) like lower('" + searchString + "%') and spart =? and rownum<50 order by nume");

		return sqlString.toString();
	}

	public static String cautaArticoleNume(String searchString) {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nume, decode(length(cod),18,substr(cod,-8),cod) from articole where ");
		sqlString.append(" lower(nume) like lower('" + searchString + "%') and spart =? and rownum<50 order by nume ");

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

	public static String getProcenteDiscount() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select distinct nvl(a.discount,0) av, ");
		sqlString.append(" nvl((select distinct discount from sapprd.zdisc_pers_sint where  functie='SD' ");
		sqlString.append(" and spart =? and werks =? and inactiv <> 'X' and matkl = c.cod),0) sd ");
		sqlString.append(" from sapprd.zdisc_pers_sint a, articole b, sintetice c where  a.functie='AV' and a.spart =? and a.werks =? ");
		sqlString.append(" and b.sintetic = c.cod and inactiv <> 'X' and matkl = c.cod and b.cod =? ");

		return sqlString.toString();
	}

	public static String getCmpArticol() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nvl(to_char(decode(y.lbkum,0,y.verpr,y.salk3/y.lbkum),'99999.9999'),0) cmp from sapprd.mbew y where ");
		sqlString.append(" y.mandt='900' and y.matnr=?  and y.bwkey =? ");

		return sqlString.toString();
	}

	public static String getProcentReducereCmp() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select nvl(sum(decode(art,'articol',procent)),0) proc_articol, nvl(sum(decode(art,'sintetic',procent)),0) proc_sintetic from ( ");
		sqlString.append(" select sum(procent)procent, 'articol' art from sapprd.ZSUBCMP p where p.mandt = '900' ");
		sqlString.append(" and p.spart = (select spart from articole where cod =:articol) ");
		sqlString.append(" and p.datab <= to_char(sysdate, 'yyyymmdd') and p.datbi >= to_char(sysdate, 'yyyymmdd') ");
		sqlString.append(" and matnr =:articol group by 'articol' ");
		sqlString.append(" union ");
		sqlString.append(" select sum(procent) procent, 'sintetic' art from sapprd.ZSUBCMP p ");
		sqlString.append(" where p.mandt = '900' and p.spart =(select sintetic from articole where cod =:articol)  and");
		sqlString.append(" p.datab <= to_char(sysdate, 'yyyymmdd') and p.datbi >= to_char(sysdate, 'yyyymmdd') ");
		sqlString.append(" and matkl = (select sintetic from articole where cod =:articol) group by 'sintetic') x group by 'articol' ");

		return sqlString.toString();
	}

	public static String getConditiiPret() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" SELECT vtext FROM SAPPRD.T685t r where mandt = '900' and spras = '4' ");
		sqlString.append(" and r.kvewe = 'A' and r.kappl = 'V' and KSCHL=? ");

		return sqlString.toString();
	}

	public static String getPretMediu() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select pret_med, adaos_med, cant from sapprd.zpret_mediu_oras where matnr =? and pdl=? ");

		return sqlString.toString();
	}

}
