package kariminf.langpi.basic.french;

import kariminf.langpi.basic.PreProcessInfo;

public class FrInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "fr";
	}

	@Override
	public String getLangEnglishName() {
		return "French";
	}

	@Override
	public String getLangName() {
		return "français";
	}

	@Override
	public String getPrefix() {
		return "Fr";
	}

}
