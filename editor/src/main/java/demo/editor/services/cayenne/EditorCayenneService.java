package demo.editor.services.cayenne;

import org.apache.cayenne.lifecycle.audit.AuditableFilter;
import org.apache.cayenne.map.EntityResolver;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

import demo.editor.audit.ContentAuditor;
import demo.services.cayenne.CayenneService;

public class EditorCayenneService extends CayenneService {

	public EditorCayenneService(RegistryShutdownHub shutdownHub) {
		super(shutdownHub);

		EntityResolver resolver = sharedContext().getEntityResolver();
		AuditableFilter auditableFilter = new AuditableFilter(resolver,
				new ContentAuditor());

		runtime.getDataDomain().addFilter(auditableFilter);
	}

}
