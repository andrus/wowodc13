package demo.html.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.query.SelectQuery;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

import demo.cayenne.Article;
import demo.html.services.cayenne.ICayenneService;
import demo.html.services.domain.IDomainService;

public class News {

	@Property
	private Article article;

	@Property
	private Article clickedArticle;

	@Inject
	private ICayenneService cayenneService;

	@Inject
	private IDomainService domainService;

	@InjectComponent
	private Zone articleZone;

	public List<Article> getNewsList() {
		SelectQuery<Article> query = new SelectQuery<Article>(Article.class);
		query.andQualifier(Article.DOMAIN.eq(domainService.currentDomain()));
		query.addOrdering(Article.PUBLISHED_ON.desc());

		return cayenneService.sharedContext().select(query);
	}

	public Format getPublishDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}
	
	public int getArticleId() {
		return Cayenne.intPKForObject(article);
	}

	public Object onActionFromArticleLink(int articleId) {
		clickedArticle = Cayenne.objectForPK(cayenneService.sharedContext(),
				Article.class, articleId);
		return articleZone.getBody();
	}
}
