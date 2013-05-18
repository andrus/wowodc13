package demo.editor.audit;

import org.apache.cayenne.DataObject;
import org.apache.cayenne.PersistenceState;
import org.apache.cayenne.annotation.PrePersist;
import org.apache.cayenne.annotation.PreRemove;
import org.apache.cayenne.annotation.PreUpdate;

import demo.cayenne.CustomAudit;

public class ContentAuditor {

	@PrePersist(entityAnnotations = CustomAudit.class)
	@PreRemove(entityAnnotations = CustomAudit.class)
	@PreUpdate(entityAnnotations = CustomAudit.class)
	public void audit(DataObject object) {

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

		System.out.println(String.format("[AUDIT]  [%s] is %s",
				object.getObjectId(), op));
	}

}
