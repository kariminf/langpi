package aak.as.preProcess.basque;

import aak.as.preProcess.lang.PreProcessInfo;

public class EuInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
		return "eu";
	}

	@Override
	public String getLangEnglishName() {
		return "Basque";
	}

	@Override
	public String getLangName() {
		return "euskara";
	}

	@Override
	public String getPrefix() {
		return "Eu";
	}

}
