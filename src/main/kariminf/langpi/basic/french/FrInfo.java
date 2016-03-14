package kariminf.langpi.basic.french;

import kariminf.langpi.basic.BasicInfo;

public class FrInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "fr";
	}

	@Override
	public String getLangEnglishName() {
		return "French";
	}

	@Override
	public String getLangName() {
		return "français";
	}

	@Override
	public String getClassPrefix() {
		return "Fr";
	}

}
