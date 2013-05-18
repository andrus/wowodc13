package demo.editor.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;

import demo.cayenne.Article;
import demo.services.news.INewsService;

public class Index {

	@Property
	private Article article;

	@Inject
	private INewsService newsService;

	@InjectPage
	private NewsEditor editor;

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
		editor.startEditing(articleId);
		return editor;
	}

}
