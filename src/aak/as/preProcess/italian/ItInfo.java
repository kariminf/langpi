package aak.as.preProcess.italian;

import aak.as.preProcess.lang.PreProcessInfo;

public class ItInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "it";
	}

	@Override
	public String getLangEnglishName() {
		return "Italian";
	}

	@Override
	public String getLangName() {
		return "italiano";
	}

	@Override
	public String getPrefix() {
		return "It";
	}

}
