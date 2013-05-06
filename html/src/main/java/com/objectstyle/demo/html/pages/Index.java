package com.objectstyle.demo.html.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Index {

	@Property
	@Persist
	private int clickCounter;

	public String getText() {
		return "Hi! clicked " + clickCounter + " time(s)";
	}

	public void onActionFromClick() {
		clickCounter++;
	}
}
