package kariminf.langpi.basic.norwegian;

import kariminf.langpi.basic.BasicInfo;

public class NoInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "no";
	}

	@Override
	public String getLangEnglishName() {
		return "Norwegian";
	}

	@Override
	public String getLangName() {
		return "Norsk";
	}

	@Override
	public String getClassPrefix() {
		return "No";
	}

}
