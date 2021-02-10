package websfa.test;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.xml.namespace.QName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import websfa.beans.Login;
import websfa.model.comenzi.OperatiiMasini;

public class TestClass {

	private static final QName SERVICE_NAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZTBL_WEBSERVICE");

	private static final Logger logger = LogManager.getLogger(TestClass.class);

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

		
		
		//new TestClass().testMe();
		
		
		//System.out.println(new Utils().getConnectionData());
		
		/*
		
		try {
			new OperatiiMasini().saveSfarsitIncImg("123123", "3213");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		//System.out.println("Resp: "  + new OperatiiMasini().getPlateNumber());
		
		 
		
		try {
			
			
			
			//System.out.println(new Utils().getPlateNrFromJson(new OperatiiMasini().getPlateNumber_old()));
			
			
			//System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime()));
			
			Login l = new Login();
			l.setUsername("NSTANCU2");
			l.setPassword("F6LQVP");
			
			//new UserDAO().validateUser(l);
			
			
			
			System.out.println(new OperatiiMasini().getMasiniNeincarcate("NT10"));
			
			
			
			
			/*
			Object obj = new JSONParser().parse(new OperatiiMasini().getPlateNumber_old());
			
			org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj; 
			
			
			JSONArray ja = (JSONArray) jo.get("results"); 
			
			
			org.json.simple.JSONObject articolObject = (org.json.simple.JSONObject) ja.get(0);
			
			
			
			System.out.println("Plate: " + articolObject.get("plate"));
			*/
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

				
	
		

	}

	public void testMe() throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");

		String pathArr[] = fullPath.split("/WEB-INF/classes/");

		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("WEB-INF/resources/db_connect.txt");

		System.out.println(fullPath);

		System.out.println(pathArr[0]);

		fullPath = pathArr[0];
	}

	public void testRes() throws UnsupportedEncodingException, MalformedURLException {

		System.out.println(TestClass.class.getResource("sap_bg_test.wsdl"));

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/wsdl/sap_bg_test.wsdl");

		// InputStream inputStream =
		// this.getClass().getClassLoader().getResourceAsStream("resource/sap_bg_test.wsdl");

		// URL url =
		// ZTBLWEBSERVICE_Service.class.getClass().getClassLoader().getResource("resource/sap_bg_test.wsdl");

		URL url = new URL("file:sap_bg_test.wsdl");

		// String dd =
		// this.getClass().getResource("resource/sap_bg_test.wsdl").getPath();

		System.out.println("URL:" + url);
	}

}
