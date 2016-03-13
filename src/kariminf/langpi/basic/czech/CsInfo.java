package kariminf.langpi.basic.czech;

import kariminf.langpi.basic.PreProcessInfo;

public class CsInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
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
	public String getPrefix() {
		return "Cs";
	}

}
