package demo.html.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

import demo.cayenne.Article;
import demo.services.cayenne.ICayenneService;
import demo.services.news.INewsService;

public class Index {
	@Property
	private Article article;

	@Property
	private Article clickedArticle;

	@Inject
	private ICayenneService cayenneService;

	@Inject
	private INewsService newsService;

	@InjectComponent
	private Zone articleZone;

	public List<Article> getNewsList() {
		return newsService.getRecentNews();
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
