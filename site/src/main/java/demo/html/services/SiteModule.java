package demo.html.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;
import org.apache.tapestry5.ioc.services.ServiceOverride;

import demo.html.services.cayenne.SiteCayenneService;
import demo.services.ServicesModule;
import demo.services.cayenne.ICayenneService;

@SubModule(ServicesModule.class)
public class SiteModule {

	public static void contributeIgnoredPathsFilter(
			Configuration<String> configuration) {
		configuration.add("/rest/.*");
	}

	public ICayenneService buildCayenneService(RegistryShutdownHub shutdownHub) {
		return new SiteCayenneService(shutdownHub);
	}

	@Contribute(ServiceOverride.class)
	public void contributeServiceOverrides(
			MappedConfiguration<Class<?>, Object> config,
			@Local ICayenneService cayenneService) {

		config.add(ICayenneService.class, cayenneService);
	}
}
