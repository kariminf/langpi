package aak.as.preProcess.english;

import aak.as.preProcess.lang.PreProcessInfo;;

public class EnInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "en";
	}

	@Override
	public String getLangEnglishName() {
		return "English";
	}

	@Override
	public String getLangName() {
		return "English";
	}

	@Override
	public String getPrefix() {
		return "Porter";
	}

}
