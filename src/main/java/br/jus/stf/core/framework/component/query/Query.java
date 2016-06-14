package br.jus.stf.core.framework.component.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.stf.core.framework.component.Component;

/**
 * Anotação que permite marcar um método de um serviço como o responsável
 * por tratar uma pesquisa
 * 
 * @author lucas.rodrigues
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
@Component(Query.class)
public @interface Query {

	/**
	 * Identificador da pesquisa para verificar o acesso,
	 * caso não seja informado será extraído da classe da
	 * pesquisa. Ex: PesquisarProcessoQuery -> pesquisar-processo
	 * 
	 * @return identificador da pesquisa
	 */
	String value() default "";
	
	String description();
}
