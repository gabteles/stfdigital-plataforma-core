package br.jus.stf.core.framework.security;

import java.util.List;

import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.component.ComponentConfig;
import br.jus.stf.core.framework.security.utils.AuthenticationUtils;

/**
 * Classe responsável por verificar se o usuário possui as permissões necessárias
 * para acessar um componente.
 * 
 * @author Lucas Rodrigues
 * @author Rodrigo Barreiros
 *
 * @since 1.0.0
 * @since 25.11.2015
 */
@Component("securityChecker")
public class SecurityChecker {
	
	/**
	 * Verifica se o usuário corrente possui permissão para acesso a 
	 * um dado componente.
	 * 
	 * @param componente o componente a ser avalidado
	 * @return true, se o usuário possui permissão de acesso
	 * de um componente, false, caso contrário
	 */
	public boolean hasPermission(ComponentConfig componente) {
		return AuthenticationUtils.getUserDetail("componentes", List.class)
			.map(componentes -> componentes.contains(componente.getId()))
			.orElse(false);
	}
	
}
