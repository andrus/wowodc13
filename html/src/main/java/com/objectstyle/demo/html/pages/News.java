package com.objectstyle.demo.html.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.query.SelectQuery;
import org.apache.tapestry5.annotations.Property;

import com.objectstyle.demo.cayenne.Article;
import com.objectstyle.demo.html.services.cayenne.ICayenneService;

public class News {

	@Property
	private Article article;

	@Inject
	private ICayenneService cayenneService;

	public List<Article> getNewsList() {
		SelectQuery<Article> query = new SelectQuery<Article>(Article.class);
		return cayenneService.sharedContext().select(query);
	}
}
