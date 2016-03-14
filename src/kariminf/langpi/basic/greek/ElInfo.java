package kariminf.langpi.basic.greek;

import kariminf.langpi.basic.BasicInfo;

public class ElInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "el";
	}

	@Override
	public String getLangEnglishName() {
		return "Greek";
	}

	@Override
	public String getLangName() {
		return "ελληνικά";
	}

	@Override
	public String getClassPrefix() {
		return "El";
	}

}
