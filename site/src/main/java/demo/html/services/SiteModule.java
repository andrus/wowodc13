package demo.html.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.SubModule;

import demo.services.ServicesModule;

@SubModule(ServicesModule.class)
public class SiteModule {

	public static void contributeIgnoredPathsFilter(
			Configuration<String> configuration) {
		configuration.add("/rest/.*");
	}
}
