package dz.aak.as.preProcess.french;

import dz.aak.as.preProcess.lang.PreProcessInfo;

public class FrInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "fr";
	}

	@Override
	public String getLangEnglishName() {
		return "French";
	}

	@Override
	public String getLangName() {
		return "français";
	}

	@Override
	public String getPrefix() {
		return "Fr";
	}

}
