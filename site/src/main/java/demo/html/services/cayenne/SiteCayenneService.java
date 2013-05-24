package demo.html.services.cayenne;

import org.apache.cayenne.cache.EhCacheQueryCache;
import org.apache.cayenne.cache.QueryCache;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

import demo.services.cayenne.CayenneService;

public class SiteCayenneService extends CayenneService {

	public SiteCayenneService(RegistryShutdownHub shutdownHub) {
		super(shutdownHub);
	}

	@Override
	protected ServerRuntime createRuntime(String project) {
		Module module = new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(QueryCache.class).to(EhCacheQueryCache.class);
			}
		};

		return new ServerRuntime(project, module);
	}

}
