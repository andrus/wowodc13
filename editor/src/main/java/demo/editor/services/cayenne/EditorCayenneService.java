package demo.editor.services.cayenne;

import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

import demo.editor.audit.ContentAuditor;
import demo.services.cayenne.CayenneService;

public class EditorCayenneService extends CayenneService {

	public EditorCayenneService(RegistryShutdownHub shutdownHub) {
		super(shutdownHub);

		sharedContext().getEntityResolver().getCallbackRegistry()
				.addListener(new ContentAuditor());
	}

}
