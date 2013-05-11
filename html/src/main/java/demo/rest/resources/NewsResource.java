package demo.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import demo.html.services.domain.IDomainService;

@Path("/")
public class NewsResource {

	@Inject
	private IDomainService domainService;

	@GET
	public String get() {
		return "Hello, world @" + domainService.currentDomain().getName();
	}
}
