package websfa.queries.user;

public class ComenziSqlQueries {

	public static String getListComenziAfisare(String status) {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.nume_client, to_char(to_date(a.datac,'yyyymmdd')), a.status_aprov, a.valoare, ");
		sqlString.append(" b.nume, a.status from sapdev.zcomhead_tableta a, clienti b where a.cod_agent =? ");
		sqlString.append(" and a.cod_client = b.cod and a.datac >= ? ");
		sqlString.append(status);
		sqlString.append(" and rownum<30 ");

		return sqlString.toString();

	}

	public static String getArticoleComandaAfis() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod) cod, b.nume, a.depoz, a.cantitate, a.um, a.valoare, ");
		sqlString.append(" a.procent from sapdev.zcomdet_tableta a, articole b where a.id=? and a.cod=b.cod order by a.poz");

		return sqlString.toString();
	}

	public static String getDateLivrareAfis() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select  pers_contact, telefon, adr_livrare, city, region, nrcmdsap from sapdev.zcomhead_tableta where id=? ");

		return sqlString.toString();
	}

	public static String salveazaAntetComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" begin insert into sapdev.zcomhead_tableta(mandt,id,cod_client,ul,status,status_aprov ,datac, cod_agent,tip_plata, ");
		sqlString.append(" pers_contact,telefon,adr_livrare,valoare,mt,nrcmdsap,accept1,accept2,  fact_red, city, region,  obstra, ketdat, docin, obsplata,");
		sqlString.append(" depart, cantar, cod_init, timpc, tip_pers, com_referinta ) ");
		sqlString.append(" values ('900', pk_key.nextval, :codCl,:ul,:status,:status_aprov, ");
		sqlString.append(" :datac, :agent,:plata,:perscont,:tel,:adr,:valoare,:transp,:comsap,:accept1,:accept2, ");
		sqlString.append(" :factred,:city,:region,:obslivr, :dataLivrare, :docInsot, :obsPlata, :depart, '0', :cod_init, :timpc, :tip_pers, :comref ) ");
		sqlString.append(" returning id into ?; end; ");

		return sqlString.toString();
	}

	public static String salveazaArticoleComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into sapdev.zcomdet_tableta(mandt,id,poz,status,cod,cantitate,valoare,depoz, ");
		sqlString.append(" procent,um,val_poz, procent_fc, disclient, procent_aprob, inf_pret, multiplu, ");
		sqlString.append(" cant_umb, umb, ul_stoc) ");
		sqlString.append(" values");
		sqlString.append("('900',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		return sqlString.toString();
	}

	public static String getComenziModificare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.valoare, b.nume from sapdev.zcomhead_tableta a, clienti b where ");
		sqlString.append(" a.cod_agent =? ");
		sqlString.append(" and a.status in ('2','9','10') and a.status_aprov in ('1','3','4','9') and b.cod = a.cod_client order by a.id ");

		return sqlString.toString();
	}

	public static String getComenziAprobare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.id, a.valoare, b.nume from sapdev.zcomhead_tableta a, clienti b where ");
		sqlString.append(" a.cod_agent in (select p.cod from agenti p where p.filiala in ('BU10','BU11') and p.divizie =? ) ");
		sqlString.append(" and a.status in ('2','11') and a.status_aprov in ('1') and a.accept1 ='X' ");
		sqlString.append(" and a.ora_accept1 ='000000' and b.cod = a.cod_client order by a.id ");

		return sqlString.toString();
	}

	public static String getHeaderComandaAprob() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.nrcmdsap, a.valoare, to_char(to_date(a.datac,'yyyymmdd'),'dd-mm-yyyy'), b.nume, c.nume, a.cod_client, a.ul,  ");
		sqlString.append(" a.status_aprov, accept1, accept2 from sapdev.zcomhead_tableta a, clienti b, agenti c where ");
		sqlString.append(" a.mandt='900' and a.id=? and b.cod=a.cod_client and c.cod = a.cod_agent ");

		return sqlString.toString();
	}

	public static String getArticoleComandaAprob_old() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume ");
		sqlString.append(" from sapdev.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

	public static String getArticoleComandaAprob() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume, ");
		sqlString.append(" multiplu, inf_pret, procent_fc, procent_aprob, cant_umb, umb, ul_stoc ");
		sqlString.append(" from sapdev.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

	public static String getArticoleComandaModificare() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select decode(length(a.cod),18,substr(a.cod,-8),a.cod), a.cantitate, a.valoare, a.depoz, a.um, a.disclient, a.procent, b.nume ");
		sqlString.append(" from sapdev.zcomdet_tableta a, articole b where a.id=? and b.cod = a.cod order by a.poz ");

		return sqlString.toString();
	}

	public static String getDateLivrareCmdModif() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select region, city, adr_livrare, pers_contact, telefon, fact_red, docin, tip_plata, obsplata resp_plata, mt transp, ");
		sqlString.append(" ketdat data_livr, obstra obs_livrare ");
		sqlString.append(" from sapdev.zcomhead_tableta where id=? ");

		return sqlString.toString();
	}

	public static String getConditiiArticole() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select  decode(length(a.codart),18,substr(a.codart,-8),a.codart) codart ,a.cant, a.valoare");
		sqlString.append(" from sapdev.zconddettableta a, sapdev.zcondheadtableta c  ");
		sqlString.append(" where c.id = a.id and c.cmdref =? order by poz ");

		return sqlString.toString();
	}

	public static String aprobaComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapdev.zcomhead_tableta set status_aprov=2 where id=? ");

		return sqlString.toString();

	}

	public static String aprobaComandaSD_DV() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapdev.zcomhead_tableta set ora_accept1 = (select to_char(sysdate,'hh24miss') from dual) where id=? ");

		return sqlString.toString();

	}

	public static String conditioneazaComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapdev.zcomhead_tableta set status_aprov=4 where id=? ");

		return sqlString.toString();

	}

	public static String respingeComanda() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" update sapdev.zcomhead_tableta set status = 6, status_aprov=6 where id=? ");

		return sqlString.toString();

	}

	public static String salveazaAntetConditii() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" begin insert into sapdev.zcondheadtableta(mandt,id,codpers,datac,orac,cmdref, cmdmodif,condcalit,nrfact,observatii, castigbrut) ");
		sqlString.append(" values ('900', pk_key.nextval, :codAg, :datac, :orac, :cmdref, ");
		sqlString.append(" :cmdmodif, :condcalit, :nrfact, :observatii, :castigbrut) returning id into ?; end;");

		return sqlString.toString();
	}

	public static String salveazaArticoleConditii() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into sapdev.zconddettableta(mandt,id,poz,codart,cant,um,valoare) ");
		sqlString.append(" values ('900',:idCmd,:pozArt, :codArt, :cant, :um, :valoare) ");

		return sqlString.toString();
	}

	public static String setStatusComandaConditii() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("update sapdev.zcomhead_tableta set status_aprov = '4' where id=?");

		return sqlString.toString();

	}

	public static String getComenziAprobareSD() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select count(id) from sapdev.zcomhead_tableta where mandt='900' and status = 2 and status_aprov = 1 ");
		sqlString.append(" and accept1 = 'X' and ora_accept1 = '000000' and depart = ? ");

		return sqlString.toString();
	}

}
