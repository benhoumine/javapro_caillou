package org.isima.caillou.Components;

import java.util.List;

public class NutritionProperties {

	private String attr;
	private List<String> listnutritionnegative;
	private List<String> listnutritionpositive;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public List<String> getListnutritionnegative() {
		return listnutritionnegative;
	}

	public void setListnutritionnegative(List<String> listnutritionnegative) {
		this.listnutritionnegative = listnutritionnegative;
	}

	public List<String> getListnutritionpositive() {
		return listnutritionpositive;
	}

	public void setListnutritionpositive(List<String> listnutritionpositive) {
		this.listnutritionpositive = listnutritionpositive;
	}

}
