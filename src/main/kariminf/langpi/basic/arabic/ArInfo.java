package kariminf.langpi.basic.arabic;

import kariminf.langpi.basic.BasicInfo;

public class ArInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "ar";
	}

	@Override
	public String getLangEnglishName() {
		return "Arabic";
	}

	@Override
	public String getLangName() {
		return "العربية";
	}

	@Override
	public String getClassPrefix() {
		return "Ar";
	}
	

}
