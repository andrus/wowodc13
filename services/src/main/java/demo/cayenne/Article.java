package demo.cayenne;

import java.util.Date;

import demo.cayenne.auto._Article;

public class Article extends _Article {

	@Override
	protected void onPostAdd() {
		setPublishedOn(new Date());
	}
}
