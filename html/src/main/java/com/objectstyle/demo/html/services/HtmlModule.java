package com.objectstyle.demo.html.services;

import org.apache.tapestry5.ioc.ServiceBinder;

import com.objectstyle.demo.html.services.cayenne.CayenneService;
import com.objectstyle.demo.html.services.cayenne.ICayenneService;

public class HtmlModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ICayenneService.class, CayenneService.class)
				.preventReloading();
	}
}
