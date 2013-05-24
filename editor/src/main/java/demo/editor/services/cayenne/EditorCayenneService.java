package demo.editor.services.cayenne;

import org.apache.cayenne.lifecycle.audit.AuditableFilter;
import org.apache.cayenne.lifecycle.cache.CacheInvalidationFilter;
import org.apache.cayenne.map.EntityResolver;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;

import demo.editor.audit.ContentAuditor;
import demo.services.cayenne.CayenneService;
import demo.services.cluster.IClusterService;

public class EditorCayenneService extends CayenneService {

	public EditorCayenneService(RegistryShutdownHub shutdownHub, IClusterService clusterService) {
		super(shutdownHub, clusterService);

		EntityResolver resolver = sharedContext().getEntityResolver();
		AuditableFilter auditableFilter = new AuditableFilter(resolver, new ContentAuditor());

		runtime.getDataDomain().addFilter(auditableFilter);

		CacheInvalidationFilter cacheInvalidationFilter = new CacheInvalidationFilter();
		runtime.getDataDomain().addFilter(cacheInvalidationFilter);
	}

}
