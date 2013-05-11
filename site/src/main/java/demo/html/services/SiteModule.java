package demo.html.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;

import demo.html.services.cayenne.CayenneService;
import demo.html.services.cayenne.ICayenneService;
import demo.html.services.domain.IDomainService;
import demo.html.services.domain.VhostDomainService;
import demo.html.services.news.INewsService;
import demo.html.services.news.NewsService;

public class SiteModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ICayenneService.class, CayenneService.class);
		binder.bind(IDomainService.class, VhostDomainService.class);
		binder.bind(INewsService.class, NewsService.class);
	}

	public static void contributeIgnoredPathsFilter(
			Configuration<String> configuration) {
		configuration.add("/rest/.*");
	}
}
