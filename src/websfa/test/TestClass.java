package websfa.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import websfa.soap.client.ZTBLWEBSERVICE;
import websfa.soap.client.ZTBLWEBSERVICE_Service;
import websfa.soap.model.SapServices;
import websfa.utils.Constants;

public class TestClass {

	private static final QName SERVICE_NAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style",
			"ZTBL_WEBSERVICE");

	private TestClass() {
	}
	
	
	
	public static void main(String[] args)
	{
		
		//System.out.println(new HelperArticole().getInfoArticolText("222"));
		
		
		new SapServices().creeazaComanda("501");
		
		
	}
	
	
	
	
	
	public static void testWS(String[] args) {

		URL wsdlURL = ZTBLWEBSERVICE_Service.WSDL_LOCATION;
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		ZTBLWEBSERVICE_Service ss = new ZTBLWEBSERVICE_Service(wsdlURL, SERVICE_NAME);
		ZTBLWEBSERVICE port = ss.getZTBLWEBSERVICESoap12();

		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, Constants.userName);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, Constants.password);
		
		
		  System.out.println("Invoking zgetPrice...");
	        java.math.BigDecimal _zgetPrice_gvCantVal = new java.math.BigDecimal("1");
	        javax.xml.ws.Holder<java.math.BigDecimal> _zgetPrice_gvCant = new javax.xml.ws.Holder<java.math.BigDecimal>(_zgetPrice_gvCantVal);
	        java.lang.String _zgetPrice_gvKunnr = "4110006325";
	        java.lang.String _zgetPrice_gvLgort = "01V1";
	        java.lang.String _zgetPrice_gvMatnr = "000000000010100875";
	        java.lang.String _zgetPrice_gvSpart = "01";
	        java.lang.String _zgetPrice_gvVrkmeVal = "BUC";
	        javax.xml.ws.Holder<java.lang.String> _zgetPrice_gvVrkme = new javax.xml.ws.Holder<java.lang.String>(_zgetPrice_gvVrkmeVal);
	        java.lang.String _zgetPrice_gvWerks = "BU11";
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
	        port.zgetPrice(_zgetPrice_gvCant, _zgetPrice_gvKunnr, _zgetPrice_gvLgort, _zgetPrice_gvMatnr, _zgetPrice_gvSpart, _zgetPrice_gvVrkme, _zgetPrice_gvWerks, _zgetPrice_gvCantFree, _zgetPrice_gvCond, _zgetPrice_gvCurrency, _zgetPrice_gvMatnrFree, _zgetPrice_gvNetwr, _zgetPrice_gvNetwrFree, _zgetPrice_gvNetwrList, _zgetPrice_gvNoDisc, _zgetPrice_gvVrkmeFree, _zgetPrice_multiplu, _zgetPrice_outCantUmb, _zgetPrice_outUmb);

	        System.out.println("zgetPrice._zgetPrice_gvCant=" + _zgetPrice_gvCant.value);
	        System.out.println("zgetPrice._zgetPrice_gvVrkme=" + _zgetPrice_gvVrkme.value);
	        System.out.println("zgetPrice._zgetPrice_gvCantFree=" + _zgetPrice_gvCantFree.value);
	        System.out.println("zgetPrice._zgetPrice_gvCond=" + _zgetPrice_gvCond.value);
	        System.out.println("zgetPrice._zgetPrice_gvCurrency=" + _zgetPrice_gvCurrency.value);
	        System.out.println("zgetPrice._zgetPrice_gvMatnrFree=" + _zgetPrice_gvMatnrFree.value);
	        System.out.println("zgetPrice._zgetPrice_gvNetwr=" + _zgetPrice_gvNetwr.value);
	        System.out.println("zgetPrice._zgetPrice_gvNetwrFree=" + _zgetPrice_gvNetwrFree.value);
	        System.out.println("zgetPrice._zgetPrice_gvNetwrList=" + _zgetPrice_gvNetwrList.value);
	        System.out.println("zgetPrice._zgetPrice_gvNoDisc=" + _zgetPrice_gvNoDisc.value);
	        System.out.println("zgetPrice._zgetPrice_gvVrkmeFree=" + _zgetPrice_gvVrkmeFree.value);
	        System.out.println("zgetPrice._zgetPrice_multiplu=" + _zgetPrice_multiplu.value);
	        System.out.println("zgetPrice._zgetPrice_outCantUmb=" + _zgetPrice_outCantUmb.value);
	        System.out.println("zgetPrice._zgetPrice_outUmb=" + _zgetPrice_outUmb.value);
				
	       
	}

}
