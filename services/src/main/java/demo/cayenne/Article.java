package demo.cayenne;

import java.util.Date;

import org.apache.cayenne.lifecycle.audit.Auditable;
import org.apache.cayenne.lifecycle.cache.CacheGroups;

import demo.cayenne.auto._Article;

@CacheGroups("recent_news")
@Auditable
public class Article extends _Article {

	@Override
	protected void onPostAdd() {
		setPublishedOn(new Date());
	}
}
