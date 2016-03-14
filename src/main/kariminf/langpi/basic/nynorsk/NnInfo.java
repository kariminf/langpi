package kariminf.langpi.basic.nynorsk;

import kariminf.langpi.basic.BasicInfo;

public class NnInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "nn";
	}

	@Override
	public String getLangEnglishName() {
		return "Norwegian Nynorsk";
	}

	@Override
	public String getLangName() {
		return "Norsk nynorsk";
	}

	@Override
	public String getClassPrefix() {
		return "Nn";
	}

}
