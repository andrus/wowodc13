package demo.html.services;

import org.apache.tapestry5.ioc.ServiceBinder;


import demo.html.services.cayenne.CayenneService;
import demo.html.services.cayenne.ICayenneService;
import demo.html.services.domain.IDomainService;
import demo.html.services.domain.VhostDomainService;

public class HtmlModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ICayenneService.class, CayenneService.class);
		binder.bind(IDomainService.class, VhostDomainService.class);
	}
}
