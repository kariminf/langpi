package kariminf.langpi.basic.dutch;

import kariminf.langpi.basic.PreProcessInfo;

public class NlInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
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
	public String getPrefix() {
		return "Nl";
	}

}
