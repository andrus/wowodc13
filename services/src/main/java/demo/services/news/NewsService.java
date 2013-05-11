package demo.services.news;

import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.query.SelectQuery;

import demo.cayenne.Article;
import demo.services.cayenne.ICayenneService;
import demo.services.domain.IDomainService;

public class NewsService implements INewsService {

	@Inject
	private ICayenneService cayenneService;

	@Inject
	private IDomainService domainService;

	@Override
	public List<Article> getRecentNews() {
		SelectQuery<Article> query = new SelectQuery<Article>(Article.class);
		query.andQualifier(Article.DOMAIN.eq(domainService.currentDomain()));
		query.addOrdering(Article.PUBLISHED_ON.desc());

		return cayenneService.sharedContext().select(query);
	}

	@Override
	public Article getArticle(int id) {
		return Cayenne.objectForPK(cayenneService.sharedContext(),
				Article.class, id);
	}
}
