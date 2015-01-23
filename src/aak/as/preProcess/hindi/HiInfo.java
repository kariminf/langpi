package aak.as.preProcess.hindi;

import aak.as.preProcess.lang.PreProcessInfo;

public class HiInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "hi";
	}

	@Override
	public String getLangEnglishName() {
		return "Hindi";
	}

	@Override
	public String getLangName() {
		return "िह दी";
	}

	@Override
	public String getPrefix() {
		return "Hi";
	}

}
