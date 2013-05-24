package demo.services.cluster;

import java.io.Serializable;

public interface IClusterService {

	void send(String topic, Serializable event);

	void addListener(String topic, ClusterEventListener listener);
}
