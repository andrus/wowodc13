package demo.editor.audit;

import org.apache.cayenne.PersistenceState;
import org.apache.cayenne.annotation.PrePersist;
import org.apache.cayenne.annotation.PreRemove;
import org.apache.cayenne.annotation.PreUpdate;

import demo.cayenne.Article;

public class ContentAuditor {

	@PrePersist(Article.class)
	@PreRemove(Article.class)
	@PreUpdate(Article.class)
	public void audit(Article object) {

		String op = "?";

		switch (object.getPersistenceState()) {
		case PersistenceState.NEW:
			op = "created";
			break;
		case PersistenceState.MODIFIED:
			op = "modified";
			break;
		case PersistenceState.DELETED:
			op = "deleted";
			break;
		}

		System.out.println(String.format("[AUDIT] object [%s] is %s",
				object.getObjectId(), op));
	}

}
