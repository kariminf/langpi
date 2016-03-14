package kariminf.langpi.basic.czech;

import kariminf.langpi.basic.BasicInfo;

public class CsInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "cs";
	}

	@Override
	public String getLangEnglishName() {
		return "Czech";
	}

	@Override
	public String getLangName() {
		return "čeština";
	}

	@Override
	public String getClassPrefix() {
		return "Cs";
	}

}
