package kariminf.langpi.basic.korean;

import kariminf.langpi.basic.BasicInfo;;

public class KoInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "ko";
	}

	@Override
	public String getLangEnglishName() {
		return "Korean";
	}

	@Override
	public String getLangName() {
		return "한국어";
	}

	@Override
	public String getClassPrefix() {
		return "Ko";
	}

}
