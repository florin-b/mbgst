package websfa.enums;

public enum EnumRegiuniBG {

	БЛАГОЕВГРАДСКА("01"), БУРГАСКА("02"), ДОБРИЧСКА("03"), ГАБРОВСКА("04"), ХАСКОВСКА("05"), КЪРДЖАЛИЙСКА("06"), КЮСТЕНДИЛСКА("07"), ЛОВЕШКА("08"), МОНТАНАНСКА(
			"09"), ПАЗАРДЖИШКА("10"), ПЕРНИШКА("11"), ПЛЕВЕНСКА("12"), ПЛОВДИВСКА("13"), РАЗГРАДСКА("14"), РУСЕНСКА("15"), ШУМЕНСКА("16"), СИЛИСТРЕНСКА(
					"17"), СЛИВЕНСКА("18"), СМОЛЯНСКА("19"), СОФИЯ("20"), СОФИЙСКА("21"), СТАРОЗАГОРСКА("22"), ТЪРГОВИШКА("23"), ВАРНЕНСКА(
							"24"), ВЕЛИКОТЪРНОВСКА("25"), ВИДИНСКА("26"), ВРАЧАНСКА("27"), ЯМБОЛСКА("28");

	private String cod;

	EnumRegiuniBG(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public static String getNumeJudet(int codJudet) {
		for (EnumRegiuniBG enumJ : EnumRegiuniBG.values()) {
			if (Integer.valueOf(enumJ.cod) == codJudet)
				return enumJ.toString();

		}
		return "";
	}

}
