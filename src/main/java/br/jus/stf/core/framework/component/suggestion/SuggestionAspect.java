package br.jus.stf.core.framework.component.suggestion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.component.ComponentAspect;

/**
 * @author lucas.rodrigues
 *
 */
@Aspect
@Component
public class SuggestionAspect extends ComponentAspect<SuggestionRegistry> {
	
	@Autowired
	private SuggestionRegistry registry;

	@Override
	protected SuggestionRegistry getRegistry() {
		return registry;
	}

	@Override
	@Around("execution(public * *(..)) && @annotation(br.jus.stf.core.framework.component.suggestion.Suggestion)")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		verify(joinPoint);
		//TODO filtrar a pesquisa do usuário
		return joinPoint.proceed();
	}
	
}
