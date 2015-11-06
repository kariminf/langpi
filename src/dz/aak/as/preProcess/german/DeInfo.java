package dz.aak.as.preProcess.german;

import aak.as.preProcess.lang.PreProcessInfo;

public class DeInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "de";
	}

	@Override
	public String getLangEnglishName() {
		return "German";
	}

	@Override
	public String getLangName() {
		return "Deutsch";
	}

	@Override
	public String getPrefix() {
		return "De";
	}

}
