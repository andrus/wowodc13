package com.objectstyle.demo.html.services.cayenne;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

public class CayenneService implements ICayenneService {

	private ServerRuntime runtime;
	private ObjectContext sharedContext;

	public CayenneService(RegistryShutdownHub shutdownHub) {
		this.runtime = new ServerRuntime("cayenne-project.xml");
		this.sharedContext = runtime.getContext();

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
		return runtime.getContext();
	}

}
