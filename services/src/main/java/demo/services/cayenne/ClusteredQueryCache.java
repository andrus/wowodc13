package demo.services.cayenne;

import java.io.Serializable;
import java.util.List;

import org.apache.cayenne.cache.QueryCache;
import org.apache.cayenne.cache.QueryCacheEntryFactory;
import org.apache.cayenne.query.QueryMetadata;

import demo.services.cluster.ClusterEventListener;
import demo.services.cluster.IClusterService;

public class ClusteredQueryCache implements QueryCache {

	private static final String CACHE_REFRESH_TOPIC = "cayenne.cache.group.refresh";

	private QueryCache delegate;
	private IClusterService clusterService;

	public ClusteredQueryCache(QueryCache delegate, IClusterService clusterService) {
		this.delegate = delegate;
		this.clusterService = clusterService;

		clusterService.addListener(CACHE_REFRESH_TOPIC, new ClusterEventListener() {
			@Override
			public void receive(Serializable event) {
				System.out.println(String.format("[CACHE REFRESH] received request to refresh cache group '%s'", event));
				ClusteredQueryCache.this.delegate.removeGroup(event.toString());
			}
		});
	}

	public void removeGroup(String groupKey) {
		delegate.removeGroup(groupKey);
		clusterService.send(CACHE_REFRESH_TOPIC, groupKey);
		System.out.println(String.format("[CACHE REFRESH] sent request to refresh cache group '%s'", groupKey));
	}

	public List get(QueryMetadata metadata) {
		return delegate.get(metadata);
	}

	public List get(QueryMetadata metadata, QueryCacheEntryFactory factory) {
		return delegate.get(metadata, factory);
	}

	public void put(QueryMetadata metadata, List results) {
		delegate.put(metadata, results);
	}

	public void remove(String key) {
		delegate.remove(key);
	}

	public void clear() {
		delegate.clear();
	}

	public int size() {
		return delegate.size();
	}
}
