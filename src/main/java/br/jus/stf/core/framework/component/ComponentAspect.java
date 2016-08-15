package br.jus.stf.core.framework.component;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import br.jus.stf.core.framework.security.AccessException;
import br.jus.stf.core.framework.security.SecurityChecker;

public abstract class ComponentAspect<Registry extends ComponentRegistry<?>> {
	
	@Autowired
	private SecurityChecker securityChecker;

	/**
	 * Método que deve ser anotado para processar o aspecto.
	 * 
	 * @param joinPoint O JoinPoint a ser processado.
	 * @return o objeto
	 * @throws Throwable Exceção a ser relançada
	 */
	public abstract Object process(ProceedingJoinPoint joinPoint) throws Throwable ;
	
	/**
	 * Método que pode ser invocado para testar se o recurso é acessível e realizar a auditoria
	 * 
	 * @param joinPoint O JoinPoint a ser verificado.
	 * @throws Throwable Exceção a ser relançada
	 */
	protected void verify(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		
		if (!isAccessible(signature.getMethod())) {
			AccessException ae = new AccessException("Usuário não possui permissões para execução!");
			audit(joinPoint, ae);
			throw ae;
		}
		audit(joinPoint);
	}
	
	/**
	 * @return a registry
	 */
	protected abstract Registry getRegistry();

	/**
	 * TODO Rodrigo Barreiros: Revisitar a autorização de componente quando do design
	 * da segurança de dados.
	 * 
	 * Verifica se o usuário tem acesso ao comando
	 * 
	 * @param method O método do comando
	 * @return True se tem acesso, False se não
	 */
	private boolean isAccessible(Method method) {
		String id = getRegistry().extractId(method);
		ComponentConfig config = getRegistry().find(id);
		
		return securityChecker.hasPermission(config);
	}

	/**
	 * Auditoria sobre a execução do método
	 * 
	 * @param joinPoint O JoinPoint a ser auditado.
	 */
	private void audit(ProceedingJoinPoint joinPoint) {
		//TODO Enviar mensagem para mecanismo de auditoria
	}

	/**
	 * Auditoria sobre a execução do método quando ocorre exceção
	 * 
	 * @param joinPoint O JoinPoint a ser auditado.
	 * @param throwable Exceção capturada
	 */
	private void audit(ProceedingJoinPoint joinPoint, Throwable throwable) {
		//TODO Enviar mensagem para mecanismo de auditoria
	}

}