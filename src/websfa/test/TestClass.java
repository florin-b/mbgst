package websfa.test;

import websfa.beans.client.CautareClient;
import websfa.model.articole.OperatiiArticole;

public class TestClass {

	public static void main(String[] args) {

		
		CautareClient cautareClient = new CautareClient();
		cautareClient.setNume("ava");
		
		
		
		
		
		System.out.println(new OperatiiArticole().getStoc("10900027", "GL10", "09V1"));
		
		
		
		
		

	}

}
