package br.jus.stf.core.framework.component.suggestion;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.component.ComponentRegistry;

/**
 * Registro dos comandos da aplicação
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class SuggestionRegistry extends ComponentRegistry<SuggestionConfig> {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void configure() throws Exception {
		appContext.getBeansWithAnnotation(Component.class).values()
		.forEach(component -> {
			Method[] methods = AopUtils.getTargetClass(component).getDeclaredMethods();
			Arrays.asList(methods).stream()
				.filter(method -> method.isAnnotationPresent(Suggestion.class))
				.forEach(method ->components.add(getSuggestion(method)));
		});
		String suggestions = mapper.writer().writeValueAsString(components);
		discoveryClient.getLocalServiceInstance().getMetadata().put("suggestions", suggestions);
	}
	
	/**
	 * @param method
	 * @return
	 */
	private SuggestionConfig getSuggestion(Method method) {
		SuggestionConfig suggestion = new SuggestionConfig();
		String id = extractId(method);
		
		suggestion.setId(id);
		return suggestion;
	}
	
}
