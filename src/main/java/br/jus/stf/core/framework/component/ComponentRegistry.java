package br.jus.stf.core.framework.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import com.google.common.base.CaseFormat;

/**
 * @author lucas.rodrigues
 *
 */
public abstract class ComponentRegistry<Config extends ComponentConfig> implements InitializingBean {

	protected List<Config> components = new ArrayList<Config>();
		
	@Override
	public void afterPropertiesSet() throws Exception {
		configure();
	}
	
	/**
	 * @return mapa de comandos filtrado pelas permissões do usuário
	 */
	//TODO aplicar o filtro de segurança (@SecuredResource) que verifica o acesso ao recurso
	public List<Config> list() {
		return components;
	}
	
	/**
	 * Procura por um componente registrado com o id especificado
	 *  
	 * @param method
	 * @return o identificador do comando, ou null se não possuir o comando
	 */
	//TODO aplicar o filtro de segurança (@SecuredResource) que verifica o acesso ao recurso
	public Config find(String id) {
		return components.stream()
				.filter(cmd -> cmd.getId().equals(id))
				.findFirst()
				.orElse(null);
	};
	
	/**
	 * Inicializa o registro dos componentes procurando nos serviços por operações
	 * que sejam anotados com o componente
	 * @throws Exception  
	 */
	protected abstract void configure() throws Exception;
	
	/**
	 * Extrai o id do método anotado, o id pode vir do value da anotação
	 * ou do nome do primeiro parâmetro do método, retirando o sufixo representado
	 * pelo nome da classe da anotação
	 * <pre>&#64;Command
	 * handle(TestarRegistroCommand) // extrai o id testar-registro
	 * </pre> 
	 * 
	 * @param method
	 * @return o id extraído do método
	 */
	protected String extractId(Method method) {
		Component component = AnnotationUtils.getAnnotation(method, Component.class);
		
		if (component != null) {
			Annotation annotation = AnnotationUtils.getAnnotation(method, component.value());
			String value = (String) AnnotationUtils.getDefaultValue(annotation);
			
			if (StringUtils.isEmpty(value)) {
				String suffix = annotation.annotationType().getSimpleName();
				String commandClazz = method.getParameterTypes()[0].getSimpleName();
				return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, commandClazz.replace(suffix, ""));
			
			} else {
				return value;
			}
		}
		throw new IllegalArgumentException("O método informado não está anotado com um componente.");
	};
	
}
