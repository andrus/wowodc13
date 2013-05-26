package demo.services.cayenne;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.cache.EhCacheQueryCache;
import org.apache.cayenne.cache.QueryCache;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

import demo.services.cluster.IClusterService;

public class CayenneService implements ICayenneService {

	protected ServerRuntime runtime;
	private ObjectContext sharedContext;

	public CayenneService(RegistryShutdownHub shutdownHub, final IClusterService clusterService) {

		Module module = new Module() {
			@Override
			public void configure(Binder binder) {
				QueryCache queryCache = new ClusteredQueryCache(new EhCacheQueryCache(), clusterService);
				binder.bind(QueryCache.class).toInstance(queryCache);
			}
		};

		this.runtime = new ServerRuntime("cayenne-project.xml", module);
		this.sharedContext = runtime.newContext();

		shutdownHub.addRegistryShutdownListener(new Runnable() {

			public void run() {
				runtime.shutdown();
			}
		});
	}

	public ObjectContext sharedContext() {
		return sharedContext;
	}

	public ObjectContext newContext() {
		return runtime.newContext();
	}

}
