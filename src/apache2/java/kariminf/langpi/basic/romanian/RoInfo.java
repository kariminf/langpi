package kariminf.langpi.basic.romanian;

import kariminf.langpi.basic.BasicInfo;

public class RoInfo implements BasicInfo {

	@Override
	public String getIndicator() {
		return "ro";
	}

	@Override
	public String getLangEnglishName() {
		return "Romanian";
	}

	@Override
	public String getLangName() {
		return "limba română";
	}

	@Override
	public String getClassPrefix() {
		return "Ro";
	}

}
