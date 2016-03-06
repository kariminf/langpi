package kariminf.as.preProcess.japanese;

import dz.aak.as.preProcess.lang.PreProcessInfo;

public class JaInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "ja";
	}

	@Override
	public String getLangEnglishName() {
		return "Japanese";
	}

	@Override
	public String getLangName() {
		return "日本語";
	}

	@Override
	public String getPrefix() {
		return "Ja";
	}

}
