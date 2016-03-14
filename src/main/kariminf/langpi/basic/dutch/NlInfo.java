package kariminf.langpi.basic.dutch;

import kariminf.langpi.basic.BasicInfo;

public class NlInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "nl";
	}

	@Override
	public String getLangEnglishName() {
		return "Dutch";
	}

	@Override
	public String getLangName() {
		return "Nederlands";
	}

	@Override
	public String getClassPrefix() {
		return "Nl";
	}

}
