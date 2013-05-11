package demo.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import demo.html.services.news.INewsService;

@Path("news")
public class NewsResource {

	@Inject
	private INewsService newsService;

	@GET
	public Object get() {
		return newsService.getRecentNews().toString();
	}
}
