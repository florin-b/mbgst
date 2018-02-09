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

	public static String salveazaAntetComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" begin insert into sapprd.zcomhead_tableta(mandt,id,cod_client,ul,status,status_aprov ,datac, cod_agent,tip_plata, ");
		sqlString.append(" pers_contact,telefon,adr_livrare,valoare,mt,nrcmdsap,accept1,accept2,  fact_red, city, region,  obstra) ");
		sqlString.append(" values ('900', pk_key.nextval, :codCl,:ul,:status,:status_aprov, ");
		sqlString.append(" :datac, :agent,:plata,:perscont,:tel,:adr,:valoare,:transp,:comsap,:accept1,:accept2, ");
		sqlString.append(" :factred,:city,:region,:obslivr) ");
		sqlString.append(" returning id into ?; end; ");

		return sqlString.toString();
	}

	public static String salveazaArticoleComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into sapprd.zcomdet_tableta(mandt,id,poz,status,cod,cantitate,valoare,depoz,procent,um,val_poz) ");
		sqlString.append(" values");
		sqlString.append("('900',?,?,?,?,?,?,?,?,?,?)");

		return sqlString.toString();
	}

	public static String getComenziAprobare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.valoare, b.nume from sapprd.zcomhead_tableta a, clienti b where datac=? and cod_agent=? and ");
		sqlString.append(" a.status_aprov = 1 and b.cod = a.cod_client ");
		return sqlString.toString();
	}

	public static String getHeaderComandaAprob() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(
				" select a.nrcmdsap, a.valoare, to_char(to_date(a.datac,'yyyymmdd'),'dd-mm-yyyy'), b.nume, c.nume from sapprd.zcomhead_tableta a, clienti b, agenti c where ");
		sqlString.append(" a.mandt='900' and a.id=? and b.cod=a.cod_client and c.cod = a.cod_agent ");

		return sqlString.toString();
	}

	public static String getArticoleComandaAprob() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume ");
		sqlString.append(" from sapprd.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

}
