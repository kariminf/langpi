package kariminf.langpi.basic.portuguese;

import kariminf.langpi.basic.PreProcessInfo;

public class PtInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "pt";
	}

	@Override
	public String getLangEnglishName() {
		return "Portuguese";
	}

	@Override
	public String getLangName() {
		return " português";
	}

	@Override
	public String getPrefix() {
		return "Pt";
	}

}
