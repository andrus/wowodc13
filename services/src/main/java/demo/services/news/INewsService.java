package demo.services.news;

import java.util.List;

import demo.cayenne.Article;

public interface INewsService {

	List<Article> getRecentNews();

	Article getArticle(int id);
}
