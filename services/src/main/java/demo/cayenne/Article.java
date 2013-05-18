package demo.cayenne;

import java.util.Date;

import org.apache.cayenne.lifecycle.audit.Auditable;

import demo.cayenne.auto._Article;

@Auditable
public class Article extends _Article {

	@Override
	protected void onPostAdd() {
		setPublishedOn(new Date());
	}
}
