package kariminf.langpi.basic.japanese;

import kariminf.langpi.basic.BasicInfo;

public class JaInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "ja";
	}

	@Override
	public String getLangEnglishName() {
		return "Japanese";
	}

	@Override
	public String getLangName() {
		return "日本語";
	}

	@Override
	public String getClassPrefix() {
		return "Ja";
	}

}
