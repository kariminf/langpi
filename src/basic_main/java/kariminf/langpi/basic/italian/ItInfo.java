package kariminf.langpi.basic.italian;

import kariminf.langpi.basic.BasicInfo;

public class ItInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "it";
	}

	@Override
	public String getLangEnglishName() {
		return "Italian";
	}

	@Override
	public String getLangName() {
		return "italiano";
	}

	@Override
	public String getClassPrefix() {
		return "It";
	}

}
