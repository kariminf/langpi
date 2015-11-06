package dz.aak.as.preProcess.russian;

import dz.aak.as.preProcess.lang.PreProcessInfo;

public class RuInfo implements PreProcessInfo {

	@Override
	public String getISO639_1() {
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
	public String getPrefix() {
		return "Ru";
	}

}
