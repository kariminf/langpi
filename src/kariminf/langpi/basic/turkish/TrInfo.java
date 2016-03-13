package kariminf.langpi.basic.turkish;

import kariminf.langpi.basic.PreProcessInfo;

public class TrInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "tr";
	}

	@Override
	public String getLangEnglishName() {
		return "Turkish";
	}

	@Override
	public String getLangName() {
		return "Türkçe";
	}

	@Override
	public String getPrefix() {
		return "Tr";
	}

}
