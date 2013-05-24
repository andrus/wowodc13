package demo.services.cayenne;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

public class CayenneService implements ICayenneService {

	protected ServerRuntime runtime;
	private ObjectContext sharedContext;

	public CayenneService(RegistryShutdownHub shutdownHub) {
		this.runtime = createRuntime("cayenne-project.xml");
		this.sharedContext = runtime.getContext();

		shutdownHub.addRegistryShutdownListener(new Runnable() {

			public void run() {
				runtime.shutdown();
			}
		});
	}

	protected ServerRuntime createRuntime(String project) {
		return new ServerRuntime(project);
	}

	public ObjectContext sharedContext() {
		return sharedContext;
	}

	public ObjectContext newContext() {
		return runtime.getContext();
	}

}
