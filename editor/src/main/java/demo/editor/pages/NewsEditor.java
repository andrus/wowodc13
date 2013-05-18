package demo.editor.pages;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import demo.cayenne.Article;
import demo.services.cayenne.ICayenneService;

public class NewsEditor {

	@Persist
	@Property
	private Article article;

	@Inject
	private ICayenneService cayenneService;

	public int getArticleId() {
		return Cayenne.intPKForObject(article);
	}

	public void startEditing(int articleId) {
		ObjectContext context = cayenneService.newContext();
		this.article = Cayenne.objectForPK(context, Article.class, articleId);
	}
}
