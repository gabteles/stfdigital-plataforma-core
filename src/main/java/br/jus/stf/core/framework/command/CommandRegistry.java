package br.jus.stf.core.framework.command;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.base.CaseFormat;

import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;

/**
 * Registro dos comandos da aplicação
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class CommandRegistry {
	
	@Autowired
	private ApplicationContext appContext;
	
	private List<String> commands = new LinkedList<String>();
	
	/**
	 * Inicializa o registro dos comandos procurando por serviços
	 * da camada de aplicação que estejam anotados com {@link ApplicationService}
	 */
	@PostConstruct
	public void init() {
		
		appContext.getBeansWithAnnotation(ApplicationService.class).values()
			.forEach(appService -> {
				Method[] methods = AopUtils.getTargetClass(appService).getDeclaredMethods();
				Arrays.asList(methods).stream()
					.filter(method -> method.isAnnotationPresent(Command.class))
					.forEach(method -> commands.add(getId(method)));
			});	
	}
	
	/**
	 * @return mapa de comandos filtrado pelas permissões do usuário
	 */
	//TODO aplicar o filtro de segurança (@SecuredResource) que verifica o acesso ao recurso
	public List<String> list() {
		return commands;
	}
	
	/**
	 * Verifica se o usuário possui permissão sobre o comando
	 * associado ao método
	 * 
	 * @param method
	 * @return o identificador do comando, ou null se não possuir o comando
	 */
	//TODO aplicar o filtro de segurança (@SecuredResource) que verifica o acesso ao recurso
	public String verify(Method method) {
		String id = getId(method);
		if (commands.contains(id)) {
			return id;
		}
		return null;
	}
	
	/**
	 * Retorna o identificador do comando
	 * 
	 * @param method
	 * @return o identificador formatado
	 */
	private String getId(Method method) {
		Command annotation = method.getAnnotation(Command.class);
		
		if (annotation != null) {
			if (StringUtils.isEmpty(annotation.value())) {
				String commandClazz = method.getParameterTypes()[0].getSimpleName();
				return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, commandClazz.replace("Command", ""));
			
			} else {
				return annotation.value();
			}
		}
		return null;
	}
	
}
