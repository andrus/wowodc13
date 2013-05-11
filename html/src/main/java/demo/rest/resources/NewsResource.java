package demo.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import demo.cayenne.Article;
import demo.html.services.news.INewsService;
import demo.rest.model.JaxbArticle;
import demo.rest.model.JaxbArticles;

@Path("news")
public class NewsResource {

	@Inject
	private INewsService newsService;

	@GET
	public Object get() {
		List<Article> articles = newsService.getRecentNews();

		JaxbArticles response = new JaxbArticles();
		for (Article a : articles) {
			response.addArticle(new JaxbArticle(a));
		}

		return response;
	}
}
