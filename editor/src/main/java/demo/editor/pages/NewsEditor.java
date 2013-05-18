package demo.editor.pages;

import java.util.Date;

import javax.inject.Inject;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import demo.cayenne.Article;
import demo.services.cayenne.ICayenneService;
import demo.services.domain.IDomainService;

public class NewsEditor {

	@Persist
	@Property
	private Article article;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private String message;

	@Inject
	private ICayenneService cayenneService;

	@Inject
	private IDomainService domainService;

	public String getArticleId() {
		return article.getObjectId().isTemporary() ? "(new)" : ""
				+ Cayenne.intPKForObject(article);
	}

	public void startEditing(int articleId) {
		ObjectContext context = cayenneService.newContext();
		this.article = Cayenne.objectForPK(context, Article.class, articleId);
	}

	public void startEditing() {
		ObjectContext context = cayenneService.newContext();
		this.article = context.newObject(Article.class);
		this.article.setDomain(context.localObject(domainService
				.currentDomain()));
	}

	public void onSuccessFromForm() {
		article.getObjectContext().commitChanges();
		message = "Saved!";
	}
}
