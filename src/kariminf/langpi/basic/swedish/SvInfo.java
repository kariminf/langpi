package kariminf.langpi.basic.swedish;

import kariminf.langpi.basic.BasicInfo;

public class SvInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "sv";
	}

	@Override
	public String getLangEnglishName() {
		return "Swedish";
	}

	@Override
	public String getLangName() {
		return "svenska";
	}

	@Override
	public String getClassPrefix() {
		return "Sv";
	}

}
