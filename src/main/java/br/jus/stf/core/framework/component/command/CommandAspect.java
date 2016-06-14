package br.jus.stf.core.framework.component.command;

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
public class CommandAspect extends ComponentAspect<CommandRegistry> {
	
	@Autowired
	private CommandRegistry registry;

	@Override
	protected CommandRegistry getRegistry() {
		return registry;
	}

	@Override
	@Around("execution(public * *(..)) && @annotation(br.jus.stf.core.framework.component.command.Command)")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		verify(joinPoint);
		//
		return joinPoint.proceed();
	}
	
}
