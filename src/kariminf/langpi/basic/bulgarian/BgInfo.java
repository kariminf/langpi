package kariminf.langpi.basic.bulgarian;

import kariminf.langpi.basic.BasicInfo;

public class BgInfo implements BasicInfo {

	@Override
	public String getIndicator() {
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
	public String getClassPrefix() {
		return "Bg";
	}

}
