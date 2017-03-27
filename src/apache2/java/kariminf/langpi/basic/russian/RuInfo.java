package kariminf.langpi.basic.russian;

import kariminf.langpi.basic.BasicInfo;

public class RuInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "ru";
	}

	@Override
	public String getLangEnglishName() {
		return "Russian";
	}

	@Override
	public String getLangName() {
		return "Русский";
	}

	@Override
	public String getClassPrefix() {
		return "Ru";
	}

}
