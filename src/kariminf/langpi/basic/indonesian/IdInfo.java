package kariminf.langpi.basic.indonesian;

import kariminf.langpi.basic.BasicInfo;

public class IdInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "id";
	}

	@Override
	public String getLangEnglishName() {
		return "Indonesian";
	}

	@Override
	public String getLangName() {
		return "Bahasa Indonesia";
	}

	@Override
	public String getClassPrefix() {
		return "Id";
	}

}
