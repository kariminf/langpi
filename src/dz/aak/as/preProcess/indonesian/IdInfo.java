package dz.aak.as.preProcess.indonesian;

import aak.as.preProcess.lang.PreProcessInfo;

public class IdInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "id";
	}

	@Override
	public String getLangEnglishName() {
		return "Indonesian";
	}

	@Override
	public String getLangName() {
		return "Bahasa Indonesia";
	}

	@Override
	public String getPrefix() {
		return "Id";
	}

}
