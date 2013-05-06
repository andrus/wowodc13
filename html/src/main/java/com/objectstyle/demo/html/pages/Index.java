package com.objectstyle.demo.html.pages;

import org.apache.tapestry5.annotations.Persist;

public class Index {

	@Persist
	private int clickCounter;

	public String getText() {
		return "Hi! Clicked " + clickCounter + " time(s)";
	}

	public void onActionFromClick() {
		clickCounter++;
	}
}
