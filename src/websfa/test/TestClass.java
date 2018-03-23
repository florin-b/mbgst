package websfa.test;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import websfa.model.articole.OperatiiComenzi;
import websfa.soap.client.ZTBLWEBSERVICE;
import websfa.soap.client.ZTBLWEBSERVICE_Service;
import websfa.utils.Constants;

public class TestClass {

	private static final QName SERVICE_NAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZTBL_WEBSERVICE");

	

	public static void main(String[] args) throws MalformedURLException {

		System.out.println(new OperatiiComenzi().aprobaComandaSDDV("739"));

	}

	public void testRes() throws UnsupportedEncodingException, MalformedURLException {

		System.out.println(TestClass.class.getResource("sap_bg_test.wsdl"));

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/sap_bg_test.wsdl");

		// URL url =
		// ZTBLWEBSERVICE_Service.class.getClass().getClassLoader().getResource("resource/sap_bg_test.wsdl");

		URL url = new URL("file:sap_bg_test.wsdl");

		// String dd =
		// this.getClass().getResource("resource/sap_bg_test.wsdl").getPath();

		System.out.println("URL:" + url);
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

	}

}
