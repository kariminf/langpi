package dz.aak.as.preProcess.nynorsk;

import aak.as.preProcess.lang.PreProcessInfo;

public class NnInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "nn";
	}

	@Override
	public String getLangEnglishName() {
		return "Norwegian Nynorsk";
	}

	@Override
	public String getLangName() {
		return "Norsk nynorsk";
	}

	@Override
	public String getPrefix() {
		return "Nn";
	}

}
