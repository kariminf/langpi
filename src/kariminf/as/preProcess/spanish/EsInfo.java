package kariminf.as.preProcess.spanish;

import kariminf.as.preProcess.lang.PreProcessInfo;

public class EsInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "es";
	}

	@Override
	public String getLangEnglishName() {
		return "Spanish";
	}

	@Override
	public String getLangName() {
		return "español";
	}

	@Override
	public String getPrefix() {
		return "Es";
	}

}
