package kariminf.langpi.basic.turkish;

import kariminf.langpi.basic.BasicInfo;

public class TrInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "tr";
	}

	@Override
	public String getLangEnglishName() {
		return "Turkish";
	}

	@Override
	public String getLangName() {
		return "Türkçe";
	}

	@Override
	public String getClassPrefix() {
		return "Tr";
	}

}
