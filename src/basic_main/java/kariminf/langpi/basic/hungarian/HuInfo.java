package kariminf.langpi.basic.hungarian;

import kariminf.langpi.basic.BasicInfo;

public class HuInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "hu";
	}

	@Override
	public String getLangEnglishName() {
		return "Hungarian";
	}

	@Override
	public String getLangName() {
		return "magyar";
	}

	@Override
	public String getClassPrefix() {
		return "Hu";
	}

}
