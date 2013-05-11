package demo.rest.jersey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.tapestry5.TapestryFilter;
import org.apache.tapestry5.ioc.AnnotationProvider;
import org.apache.tapestry5.ioc.Registry;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

@Provider
public class JerseyTapestryBridge implements InjectableProvider<Inject, Type> {

	@Context
	private ServletContext servletContext;

	@Override
	public ComponentScope getScope() {
		return ComponentScope.Singleton;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Injectable getInjectable(ComponentContext ic, final Inject a,
			final Type c) {
		return new Injectable<Object>() {

			@SuppressWarnings("unchecked")
			public Object getValue() {

				if (servletContext == null) {
					throw new IllegalStateException(
							"Provider is not yet initialized. Servlet context is null.");
				}

				Registry registry = (Registry) servletContext
						.getAttribute(TapestryFilter.REGISTRY_CONTEXT_NAME);
				if (registry == null) {
					throw new IllegalStateException(
							"Filter is not yet initialized. Tapestry registry is null");
				}

				Class aClass = (Class) c;

				return registry.getObject(aClass, new AnnotationProvider() {

					@Override
					public <T extends Annotation> T getAnnotation(
							Class<T> annotationClass) {

						if (Inject.class.isAssignableFrom(annotationClass)) {
							return (T) a;
						}

						return null;
					}
				});
			}
		};
	}

}
