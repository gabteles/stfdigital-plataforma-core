package br.jus.stf.core.framework.command;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.security.AccessException;

/**
 * @author lucas.rodrigues
 *
 */
@Aspect
@Component
public class CommandHandler {
	
	@Autowired
	private CommandRegistry registry;
	
	@Around("execution(public * *(..)) && @annotation(br.jus.stf.core.framework.command.Command)")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Object val = null;
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		
		if (isAccessible(signature.getMethod())) {
			try {
				val = joinPoint.proceed();
			} catch(Throwable t) {
				audit(joinPoint, t);
			}
		} else {
			AccessException ae = new AccessException("Usuário não possui permissões para executar o comando!");
			audit(joinPoint, ae);
			throw ae;
		}
		audit(joinPoint);
		return val;
	}
	
	/**
	 * Verifica se o usuário tem acesso ao comando
	 * 
	 * @param method O método do comando
	 * @return True se tem acesso, False se não
	 */
	private boolean isAccessible(Method method) {
		return registry.verify(method) != null;
	}
	
	/**
	 * Auditoria sobre a execução do método
	 * 
	 * @param joinPoint
	 */
	private void audit(ProceedingJoinPoint joinPoint) {
		//TODO Enviar mensagem para mecanismo de auditoria
	}
	
	/**
	 * Auditoria sobre a execução do método quando ocorre exceção
	 * 
	 * @param joinPoint
	 * @param throwable
	 */
	private void audit(ProceedingJoinPoint joinPoint, Throwable throwable) {
		//TODO Enviar mensagem para mecanismo de auditoria
	}
	

	
}
