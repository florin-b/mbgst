package websfa.test;

import websfa.model.articole.HelperArticole;

public class TestClass {

	 
	
	public static void main(String[] args) {

		//System.out.println(new HelperArticole().getProcenteDiscount("GL10", "02", "000000000010200066"));
		
		
		//System.out.println(new HelperArticole().getCmpArticol("GL10", "000000000010200066"));
		
		//System.out.println(new HelperArticole().getPretMediu("000000000010200066", "GL10"));
		
		System.out.println(new HelperArticole().getProcentRedCmp("000000000010200066"));
				

	}

}
