package kariminf.langpi.basic.chinese;

import kariminf.langpi.basic.PreProcessInfo;

public class ZhInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
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
	public String getPrefix() {
		return "Zh";
	}

}
