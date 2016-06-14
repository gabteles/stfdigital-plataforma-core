package br.jus.stf.core.framework.component.query;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.component.ComponentRegistry;
import br.jus.stf.core.framework.component.navigation.RouteRegistry;

/**
 * Registro dos comandos da aplicação
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class QueryRegistry extends ComponentRegistry<QueryConfig> {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private RouteRegistry routeRegistry;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void configure() throws Exception {
		appContext.getBeansWithAnnotation(Component.class).values()
		.forEach(component -> {
			Method[] methods = AopUtils.getTargetClass(component).getDeclaredMethods();
			Arrays.asList(methods).stream()
				.filter(method -> method.isAnnotationPresent(Query.class))
				.forEach(method ->components.add(getQuery(method)));
		});
		String queries = mapper.writer().writeValueAsString(components);
		discoveryClient.getLocalServiceInstance().getMetadata().put("queries", queries);
	}
	
	/**
	 * @param routes
	 * @param method
	 * @return
	 */
	private QueryConfig getQuery(Method method) {
		QueryConfig query = new QueryConfig();
		Query anno = method.getAnnotation(Query.class);
		String id = extractId(method);
		
		query.setId(id);
		query.setDescription(anno.description());
		query.setRoute(routeRegistry.find(id));
		return query;
	}
	
}
