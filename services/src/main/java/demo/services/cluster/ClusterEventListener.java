package demo.services.cluster;

import java.io.Serializable;

public interface ClusterEventListener {

	void receive(Serializable event);
}
