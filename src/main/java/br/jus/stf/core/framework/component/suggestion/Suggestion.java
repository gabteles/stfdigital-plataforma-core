package br.jus.stf.core.framework.component.suggestion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.stf.core.framework.component.Component;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
@Component(Suggestion.class)
public @interface Suggestion {

	/**
	 * Identificador da pesquisa para verificar o acesso,
	 * caso não seja informado será extraído da classe da
	 * pesquisa. Ex: PesquisarProcessoSuggestion -&gt; pesquisar-processo
	 * 
	 * @return identificador da suggestion
	 */
	String value() default "";
	
}
