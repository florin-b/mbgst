package websfa.test;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import websfa.utils.Utils;

public class TestClass {

	private static final QName SERVICE_NAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZTBL_WEBSERVICE");

	public static void main(String[] args) throws MalformedURLException {

		
		/*
		Login l = new Login();
		l.setUsername("MTOMESCU");
		l.setPassword("7vT3mM");

		 new UserDAO().validateUser(l);
		 */
		
		
		System.out.println(new Utils().getConnectionData());
		
		
		//new DBManager().getProdDataSource();
		
	
		

		//new OperatiiMasini().setSfarsitIncarcare("112", "221");

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

}
