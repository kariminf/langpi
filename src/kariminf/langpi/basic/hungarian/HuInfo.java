package kariminf.langpi.basic.hungarian;

import kariminf.langpi.basic.PreProcessInfo;

public class HuInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "hu";
	}

	@Override
	public String getLangEnglishName() {
		return "Hungarian";
	}

	@Override
	public String getLangName() {
		return "magyar";
	}

	@Override
	public String getPrefix() {
		return "Hu";
	}

}
