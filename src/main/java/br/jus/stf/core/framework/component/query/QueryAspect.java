package br.jus.stf.core.framework.component.query;

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
public class QueryAspect extends ComponentAspect<QueryRegistry> {
	
	@Autowired
	private QueryRegistry registry;

	@Override
	protected QueryRegistry getRegistry() {
		return registry;
	}

	@Override
	@Around("execution(public * *(..)) && @annotation(br.jus.stf.core.framework.component.query.Query)")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		verify(joinPoint);
		//TODO filtrar a pesquisa do usuário
		return joinPoint.proceed();
	}
	
}
