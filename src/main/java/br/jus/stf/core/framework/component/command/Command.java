package br.jus.stf.core.framework.component.command;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.stf.core.framework.component.Component;
import br.jus.stf.core.framework.component.command.CommandTarget.Mode;

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
	 * comando. Ex: RegitrarOficioCommand -&gt; regitrar-oficio.
	 * <br><i>Default:</i> ""
	 * 
	 * @return identificador do comando
	 */
	String value() default "";
	
	/**
	 * Descrição do comando
	 * <br><i>Default:</i> ""
	 * 
	 * @return descrição
	 */
	String description() default "";
	
	/**
	 * O tipo do objeto de domínio alvo do comando 
	 * <br><i>Default:</i> Default.class
	 * 
	 * @return a classe do alvo
	 */
	Class<?> targetType() default Default.class;
	
	/**
	 * Indica a multiplicidade dos objetos alvo
	 * para que seja possível a execução do comando
	 * <br><i>Default:</i> One
	 * 
	 * @return o modo
	 */
	Mode targetMode() default Mode.One;
	
	/**
	 * Indica se o comando pode ser listado
	 * <br><i>Default:</i> true
	 * 
	 * @return boleano
	 */
	boolean listable() default true;
	
	/**
	 * Indica se o comando inicia um processo no workflow
	 * <br><i>Default:</i> false
	 * 
	 * @return boleano
	 */
	boolean startProcess() default false;
	
	static class Default {};
}
