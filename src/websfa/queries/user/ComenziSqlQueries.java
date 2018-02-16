package websfa.queries.user;

public class ComenziSqlQueries {

	public static String getListComenzi() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.nume_client, to_char(to_date(a.datac,'yyyymmdd')), a.status_aprov, a.valoare, ");
		sqlString.append(" b.nume from sapprd.zcomhead_tableta a, clienti b where a.cod_agent =? ");
		sqlString.append(" and a.cod_client = b.cod and a.datac >= ? ");
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
		sqlString.append(" pers_contact,telefon,adr_livrare,valoare,mt,nrcmdsap,accept1,accept2,  fact_red, city, region,  obstra, ketdat, docin, obsplata) ");
		sqlString.append(" values ('900', pk_key.nextval, :codCl,:ul,:status,:status_aprov, ");
		sqlString.append(" :datac, :agent,:plata,:perscont,:tel,:adr,:valoare,:transp,:comsap,:accept1,:accept2, ");
		sqlString.append(" :factred,:city,:region,:obslivr, :dataLivrare, :docInsot, :obsPlata) ");
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

	public static String getComenziModificare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.valoare, b.nume from sapprd.zcomhead_tableta a, clienti b where ");
		sqlString.append(" a.cod_agent =? ");
		sqlString.append(" and a.status in ('2','9','10') and a.status_aprov in ('1','3','4','9') and b.cod = a.cod_client order by a.id ");

		return sqlString.toString();
	}

	public static String getComenziAprobare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.valoare, b.nume from sapprd.zcomhead_tableta a, clienti b where ");
		sqlString.append(" a.cod_agent in (select p.cod from personal p where p.filiala=? and p.departament =? ) ");
		sqlString.append(" and a.status in ('2','11') and a.status_aprov in ('1') and b.cod = a.cod_client order by a.id ");

		return sqlString.toString();
	}

	public static String getHeaderComandaAprob() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.nrcmdsap, a.valoare, to_char(to_date(a.datac,'yyyymmdd'),'dd-mm-yyyy'), b.nume, c.nume, a.cod_client, a.ul,  ");
		sqlString.append(" a.status_aprov from sapprd.zcomhead_tableta a, clienti b, agenti c where ");
		sqlString.append(" a.mandt='900' and a.id=? and b.cod=a.cod_client and c.cod = a.cod_agent ");

		return sqlString.toString();
	}

	public static String getArticoleComandaAprob() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume ");
		sqlString.append(" from sapprd.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

	public static String getArticoleComandaModificare() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume ");
		sqlString.append(" from sapprd.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

	public static String getDateLivrareCmdModif() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select region, city, adr_livrare, pers_contact, telefon, fact_red, docin, tip_plata, obsplata resp_plata, mt transp, ");
		sqlString.append(" to_char(to_date(ketdat,'yyyymmdd'))  data_livr, obstra obs_livrare ");
		sqlString.append(" from sapprd.zcomhead_tableta where id=? ");

		return sqlString.toString();
	}

	public static String getConditiiArticole() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select  decode(length(a.codart),18,substr(a.codart,-8),a.codart) codart ,a.cant, a.valoare");
		sqlString.append(" from sapprd.zconddettableta a, sapprd.zcondheadtableta c  ");
		sqlString.append(" where c.id = a.id and c.cmdref =? order by poz ");

		return sqlString.toString();
	}

	public static String aprobaComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapprd.zcomhead_tableta set status_aprov=2 where id=? ");

		return sqlString.toString();

	}

	public static String conditioneazaComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapprd.zcomhead_tableta set status_aprov=4 where id=? ");

		return sqlString.toString();

	}

	public static String respingeComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapprd.zcomhead_tableta set status_aprov=6 where id=? ");

		return sqlString.toString();

	}

	public static String salveazaAntetConditii() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" begin insert into sapprd.zcondheadtableta(mandt,id,codpers,datac,orac,cmdref, cmdmodif,condcalit,nrfact,observatii, castigbrut) ");
		sqlString.append(" values ('900', pk_key.nextval, :codAg, :datac, :orac, :cmdref, ");
		sqlString.append(" :cmdmodif, :condcalit, :nrfact, :observatii, :castigbrut) returning id into ?; end;");

		return sqlString.toString();
	}

	public static String salveazaArticoleConditii() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into sapprd.zconddettableta(mandt,id,poz,codart,cant,um,valoare) ");
		sqlString.append(" values ('900',:idCmd,:pozArt, :codArt, :cant, :um, :valoare) ");

		return sqlString.toString();
	}

}
