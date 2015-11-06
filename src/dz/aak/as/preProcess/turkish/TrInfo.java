package dz.aak.as.preProcess.turkish;

import aak.as.preProcess.lang.PreProcessInfo;

public class TrInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "tr";
	}

	@Override
	public String getLangEnglishName() {
		return "Turkish";
	}

	@Override
	public String getLangName() {
		return "Türkçe";
	}

	@Override
	public String getPrefix() {
		return "Tr";
	}

}
