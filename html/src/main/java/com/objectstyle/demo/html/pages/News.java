package com.objectstyle.demo.html.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.query.SelectQuery;
import org.apache.tapestry5.annotations.Property;

import com.objectstyle.demo.cayenne.Article;
import com.objectstyle.demo.html.services.cayenne.ICayenneService;
import com.objectstyle.demo.html.services.domain.IDomainService;

public class News {

	@Property
	private Article article;

	@Inject
	private ICayenneService cayenneService;

	@Inject
	private IDomainService domainService;

	public List<Article> getNewsList() {
		SelectQuery<Article> query = new SelectQuery<Article>(Article.class);
		query.andQualifier(Article.DOMAIN.eq(domainService.currentDomain()));
		query.addOrdering(Article.PUBLISHED_ON.desc());

		return cayenneService.sharedContext().select(query);
	}

	public Format getPublishDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}
}
