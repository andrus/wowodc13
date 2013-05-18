package demo.editor.audit;

import org.apache.cayenne.DataObject;
import org.apache.cayenne.lifecycle.audit.AuditableOperation;
import org.apache.cayenne.lifecycle.audit.AuditableProcessor;

public class ContentAuditor implements AuditableProcessor {

	@Override
	public void audit(Object object, AuditableOperation operation) {

		DataObject dataObject = (DataObject) object;

		String op = "?";

		switch (operation) {
		case INSERT:
			op = "created";
			break;
		case UPDATE:
			op = "modified";
			break;
		case DELETE:
			op = "deleted";
			break;
		}

		System.out.println(String.format("[AUDIT]  [%s] is %s",
				dataObject.getObjectId(), op));
	}

}
