package kariminf.as.preProcess.bulgarian;

import kariminf.as.preProcess.lang.PreProcessInfo;

public class BgInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "bg";
	}

	@Override
	public String getLangEnglishName() {
		return "Bulgarian";
	}

	@Override
	public String getLangName() {
		return "български език";
	}

	@Override
	public String getPrefix() {
		return "Bg";
	}

}
