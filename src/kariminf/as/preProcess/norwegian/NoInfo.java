package kariminf.as.preProcess.norwegian;

import dz.aak.as.preProcess.lang.PreProcessInfo;

public class NoInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "no";
	}

	@Override
	public String getLangEnglishName() {
		return "Norwegian";
	}

	@Override
	public String getLangName() {
		return "Norsk";
	}

	@Override
	public String getPrefix() {
		return "No";
	}

}
