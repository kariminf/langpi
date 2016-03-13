package kariminf.langpi.basic.arabic;

import kariminf.langpi.basic.PreProcessInfo;

public class ArInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "ar";
	}

	@Override
	public String getLangEnglishName() {
		return "Arabic";
	}

	@Override
	public String getLangName() {
		return "العربية";
	}

	@Override
	public String getPrefix() {
		return "Ar";
	}
	

}
