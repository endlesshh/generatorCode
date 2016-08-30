package com.wifuns.generate.enums;

public enum CodeType {
	serviceImpl("ServiceImpl"), dao("Dao"), service("ServiceI"), controller("Controller"), page("Page"), entity(
			"Entity"), jsp(
					""), jsp_add(""), jsp_update(""), js(""), jsList("List"), jspList("List"), jspSubList("SubList");

	private String type;

	private CodeType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.type;
	}
}