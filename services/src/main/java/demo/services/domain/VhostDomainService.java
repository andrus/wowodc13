package demo.services.domain;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.cayenne.query.SelectQuery;

import demo.cayenne.Domain;
import demo.services.cayenne.ICayenneService;

public class VhostDomainService implements IDomainService {

	@Inject
	private HttpServletRequest request;

	@Inject
	private ICayenneService cayenneService;

	public Domain currentDomain() {
		String vhost = request.getServerName() + ":" + request.getServerPort();

		SelectQuery<Domain> query = new SelectQuery<Domain>(Domain.class);
		query.andQualifier(Domain.VHOST.eq(vhost));
		query.useLocalCache();

		Domain domain = cayenneService.sharedContext().selectOne(query);

		if (domain == null) {
			throw new RuntimeException("Domain not found: " + vhost);
		}

		return domain;
	}

}
