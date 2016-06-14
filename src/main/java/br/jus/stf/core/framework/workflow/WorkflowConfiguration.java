package br.jus.stf.core.framework.workflow;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author lucas.rodrigues
 *
 */
@Configuration
@ConditionalOnClass(org.activiti.spring.SpringProcessEngineConfiguration.class)
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WorkflowConfiguration {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@PostConstruct
	public void init() {
		
		Set<RequestMappingInfo> mappings = handlerMapping.getHandlerMethods().keySet();
		boolean hasApiTarefas = false;
		
		for (RequestMappingInfo mapping : mappings) {
		    if("[/api/tarefas]".equals(mapping.getPatternsCondition().toString())) {
		    	hasApiTarefas = true;
		    	break;
		    }
		}
		discoveryClient.getLocalServiceInstance().getMetadata().put("hasApiTarefas", Boolean.toString(hasApiTarefas));
	}
	
}
