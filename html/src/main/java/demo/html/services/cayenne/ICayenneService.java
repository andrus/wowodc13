package demo.html.services.cayenne;

import org.apache.cayenne.ObjectContext;

public interface ICayenneService {

	ObjectContext sharedContext();

	ObjectContext newContext();
}
