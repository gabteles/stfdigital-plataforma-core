package br.jus.stf.core.framework.component;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import br.jus.stf.core.framework.security.AccessException;

public abstract class ComponentAspect<Registry extends ComponentRegistry<?>> {

	/**
	 * Método que deve ser anotado para processar o aspecto.
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public abstract Object process(ProceedingJoinPoint joinPoint) throws Throwable ;
	
	/**
	 * Método que pode ser invocado para testar se o recurso é acessível e realizar a auditoria
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
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
	 * Verifica se o usuário tem acesso ao comando
	 * 
	 * @param method O método do comando
	 * @return True se tem acesso, False se não
	 */
	private boolean isAccessible(Method method) {
		String id = getRegistry().extractId(method);
		return getRegistry().find(id) != null;
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