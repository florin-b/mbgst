package websfa.soap.model;

import java.math.BigDecimal;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import websfa.beans.ArticolSimplu;
import websfa.beans.CautaPret;
import websfa.beans.Status;
import websfa.beans.articole.ArticolPret;
import websfa.soap.client.ZTBLWEBSERVICE;
import websfa.soap.client.ZTBLWEBSERVICE_Service;
import websfa.utils.ArticoleUtils;
import websfa.utils.Constants;

public class SapServices {

	private static final QName SERVICE_NAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZTBL_WEBSERVICE");

	private ZTBLWEBSERVICE initWS() {
		URL wsdlURL = ZTBLWEBSERVICE_Service.WSDL_LOCATION;

		ZTBLWEBSERVICE_Service ss = new ZTBLWEBSERVICE_Service(wsdlURL, SERVICE_NAME);
		ZTBLWEBSERVICE port = ss.getZTBLWEBSERVICESoap12();

		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, Constants.userName);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, Constants.password);

		return port;
	}

	public ArticolPret getPretArticol(CautaPret cautaArticol) {
		ArticolPret articolPret = new ArticolPret();

		ZTBLWEBSERVICE port = initWS();

		java.math.BigDecimal _zgetPrice_gvCantVal = new java.math.BigDecimal(cautaArticol.getCantitate());
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvCant = new javax.xml.ws.Holder<java.math.BigDecimal>(_zgetPrice_gvCantVal);
		java.lang.String _zgetPrice_gvKunnr = cautaArticol.getCodClient();
		java.lang.String _zgetPrice_gvLgort = cautaArticol.getDepozit();
		java.lang.String _zgetPrice_gvMatnr = ArticoleUtils.formatCodArticol(cautaArticol.getCodArticol());
		java.lang.String _zgetPrice_gvSpart = cautaArticol.getDepartament();
		java.lang.String _zgetPrice_gvVrkmeVal = cautaArticol.getUm();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvVrkme = new javax.xml.ws.Holder<java.lang.String>(_zgetPrice_gvVrkmeVal);
		java.lang.String _zgetPrice_gvWerks = cautaArticol.getFiliala();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvCantFree = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvCond = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvCurrency = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvMatnrFree = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvNetwr = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvNetwrFree = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvNetwrList = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvNoDisc = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvVrkmeFree = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_multiplu = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_outCantUmb = new javax.xml.ws.Holder<java.math.BigDecimal>();
		javax.xml.ws.Holder<java.lang.String> _zgetPrice_outUmb = new javax.xml.ws.Holder<java.lang.String>();

		port.zgetPrice(_zgetPrice_gvCant, _zgetPrice_gvKunnr, _zgetPrice_gvLgort, _zgetPrice_gvMatnr, _zgetPrice_gvSpart, _zgetPrice_gvVrkme,
				_zgetPrice_gvWerks, _zgetPrice_gvCantFree, _zgetPrice_gvCond, _zgetPrice_gvCurrency, _zgetPrice_gvMatnrFree, _zgetPrice_gvNetwr,
				_zgetPrice_gvNetwrFree, _zgetPrice_gvNetwrList, _zgetPrice_gvNoDisc, _zgetPrice_gvVrkmeFree, _zgetPrice_multiplu, _zgetPrice_outCantUmb,
				_zgetPrice_outUmb);

		articolPret.setCantitate(_zgetPrice_gvCant.value);
		articolPret.setUm(_zgetPrice_gvVrkme.value);
		articolPret.setInfoArticol(_zgetPrice_gvCond.value.replace(",", "."));
		articolPret.setMoneda(_zgetPrice_gvCurrency.value);

		BigDecimal pretUnitar = _zgetPrice_gvNetwr.value.divide(_zgetPrice_gvCant.value).multiply(_zgetPrice_multiplu.value);
		articolPret.setPret(pretUnitar);

		BigDecimal pretLista = _zgetPrice_gvNetwrList.value.divide(_zgetPrice_gvCant.value).multiply(_zgetPrice_multiplu.value);
		articolPret.setPretLista(pretLista);

		articolPret.setFaraDiscount(Boolean.valueOf(_zgetPrice_gvNoDisc.value));
		articolPret.setMultiplu(_zgetPrice_multiplu.value);
		articolPret.setCantUmBaza(_zgetPrice_outCantUmb.value);
		articolPret.setUmBaza(_zgetPrice_outUmb.value);

		ArticolSimplu articolPromo = new ArticolSimplu();
		articolPromo.setCod(_zgetPrice_gvMatnrFree.value);
		articolPromo.setCantitate(_zgetPrice_gvCantFree.value);
		articolPromo.setUm(_zgetPrice_gvVrkmeFree.value);
		articolPromo.setValoare(_zgetPrice_gvNetwrFree.value);

		articolPret.setArticolPromo(articolPromo);

		return articolPret;
	}

	public Status creeazaComanda(String idComanda) {
		Status status = new Status();

		ZTBLWEBSERVICE port = initWS();

		java.lang.String _zcreazaComanda_gvEvent = "C";
		java.lang.String _zcreazaComanda_id = idComanda;
		javax.xml.ws.Holder<java.lang.String> _zcreazaComanda_vOk = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.math.BigDecimal> _zcreazaComanda_vTrans = new javax.xml.ws.Holder<java.math.BigDecimal>();
		port.zcreazaComanda(_zcreazaComanda_gvEvent, _zcreazaComanda_id, _zcreazaComanda_vOk, _zcreazaComanda_vTrans);

		System.out.println("zcreazaComanda._zcreazaComanda_vOk=" + _zcreazaComanda_vOk.value);
		System.out.println("zcreazaComanda._zcreazaComanda_vTrans=" + _zcreazaComanda_vTrans.value);

		return status;
	}

}
