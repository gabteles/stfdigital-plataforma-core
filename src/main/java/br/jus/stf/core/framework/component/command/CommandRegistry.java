package br.jus.stf.core.framework.component.command;

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
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;

/**
 * Registro dos comandos da aplicação
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class CommandRegistry extends ComponentRegistry<CommandConfig> {
		
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private RouteRegistry routeRegistry;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void configure() throws Exception {
		appContext.getBeansWithAnnotation(ApplicationService.class).values()
			.forEach(appService -> {
				Method[] methods = AopUtils.getTargetClass(appService).getDeclaredMethods();
				Arrays.asList(methods).stream()
					.filter(method -> method.isAnnotationPresent(Command.class))
					.forEach(method ->components.add(getCommand(method)));
			});
		String commands = mapper.writer().writeValueAsString(components);
		discoveryClient.getLocalServiceInstance().getMetadata().put("commands", commands);
	}
	
	/**
	 * @param routes
	 * @param method
	 * @return
	 */
	private CommandConfig getCommand(Method method) {
		CommandConfig command = new CommandConfig();
		Command anno = method.getAnnotation(Command.class);
		String id = extractId(method);
		CommandTarget target = new CommandTarget(anno.targetType(), anno.targetMode());
		
		command.setId(id);
		command.setDescription(anno.description());
		command.setTarget(target);
		command.setListable(anno.listable());
		command.setStartProcess(anno.startProcess());
		command.setRoute(routeRegistry.find(id));
		return command;
	}
	
}
