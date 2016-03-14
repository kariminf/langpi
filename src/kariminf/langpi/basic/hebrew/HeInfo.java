package kariminf.langpi.basic.hebrew;

import kariminf.langpi.basic.BasicInfo;

public class HeInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "he";
	}

	@Override
	public String getLangEnglishName() {
		return "Hebrew";
	}

	@Override
	public String getLangName() {
		return "עברית";
	}

	@Override
	public String getClassPrefix() {
		return "He";
	}

}
