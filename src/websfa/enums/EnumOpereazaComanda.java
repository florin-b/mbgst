package websfa.enums;

public enum EnumOpereazaComanda {
	APROBARE(2), RESPINGERE(3), CONDITIONARE(0);

	private int codStare;

	private EnumOpereazaComanda(int codStare) {
		this.codStare = codStare;
	}

	public int getCodStare() {
		return codStare;
	}

}
