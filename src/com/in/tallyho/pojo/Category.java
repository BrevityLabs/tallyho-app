package com.in.tallyho.pojo;

public class Category {

	private final int categoryId;
	private final String categoryDescription;
	private final int categoryCode;

	public Category(int categoryId, String categoryDescription, int categoryCode) {
		this.categoryId = categoryId;
		this.categoryDescription = categoryDescription;
		this.categoryCode = categoryCode;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public int getCateGoryCode() {
		return categoryCode;
	}
}
