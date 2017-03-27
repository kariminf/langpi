package kariminf.langpi.basic.chinese;

import kariminf.langpi.basic.BasicInfo;

public class ZhInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "zh";
	}

	@Override
	public String getLangEnglishName() {
		return "Chinese";
	}

	@Override
	public String getLangName() {
		return "中文";
	}

	@Override
	public String getClassPrefix() {
		return "Zh";
	}

}
