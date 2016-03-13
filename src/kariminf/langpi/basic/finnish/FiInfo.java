package kariminf.langpi.basic.finnish;

import kariminf.langpi.basic.PreProcessInfo;

public class FiInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "fi";
	}

	@Override
	public String getLangEnglishName() {
		return "Finnish";
	}

	@Override
	public String getLangName() {
		return "suomi";
	}

	@Override
	public String getPrefix() {
		return "Fi";
	}

}
