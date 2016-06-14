package br.jus.stf.core.framework.component.command;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.stf.core.framework.component.Component;

/**
 * Anotação que permite marcar um método de um serviço (camada de aplicação) como o responsável
 * por tratar um caso de uso
 * 
 * @author lucas.rodrigues
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
@Component(Command.class)
public @interface Command {

	/**
	 * Identificador do comando para verificar o acesso,
	 * caso não seja informado será extraído da classe do
	 * comando. Ex: RegitrarOficioCommand -> regitrar-oficio
	 * 
	 * @return identificador do comando
	 */
	String value() default "";
	
	String description() default "";
	
	String targetType() default "";
	
	boolean listable() default true;
	
	boolean startProcess() default false;
}
