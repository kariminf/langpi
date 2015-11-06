package dz.aak.as.preProcess.thai;

import aak.as.preProcess.lang.PreProcessInfo;

public class ThInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "th";
	}

	@Override
	public String getLangEnglishName() {
		return "Thai";
	}

	@Override
	public String getLangName() {
		return "";
	}

	@Override
	public String getPrefix() {
		return "Th";
	}

}
