package demo.services;

import org.apache.tapestry5.ioc.ServiceBinder;

import demo.services.cayenne.CayenneService;
import demo.services.cayenne.ICayenneService;
import demo.services.cluster.IClusterService;
import demo.services.cluster.JmsClusterService;
import demo.services.domain.IDomainService;
import demo.services.domain.VhostDomainService;
import demo.services.news.INewsService;
import demo.services.news.NewsService;

public class ServicesModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ICayenneService.class, CayenneService.class);
		binder.bind(IClusterService.class, JmsClusterService.class);

		binder.bind(IDomainService.class, VhostDomainService.class);
		binder.bind(INewsService.class, NewsService.class);
	}
}
