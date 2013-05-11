package demo.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import demo.cayenne.Article;
import demo.rest.model.JaxbArticle;
import demo.rest.model.JaxbArticles;
import demo.services.news.INewsService;

@Path("news")
public class NewsResource {

	@Inject
	private INewsService newsService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object get() {
		List<Article> articles = newsService.getRecentNews();

		JaxbArticles response = new JaxbArticles();
		for (Article a : articles) {
			response.addArticle(new JaxbArticle(a));
		}

		return response;
	}
}
