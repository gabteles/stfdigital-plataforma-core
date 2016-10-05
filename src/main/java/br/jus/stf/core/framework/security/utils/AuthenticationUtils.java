package br.jus.stf.core.framework.security.utils;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Classe utilitária para recuperar informações do contexto de segurança
 * 
 * @author lucas.rodrigues
 *
 */
public class AuthenticationUtils {

	/**
	 * Retorna a autenticação OAuth2
	 * 
	 * @return OAuth2Authentication
	 */
	public static OAuth2Authentication getAuthentication() {
		return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication(); 
	}
	
	/**
	 * Retorna a autenticação do usuário
	 * 
	 * @return Authentication
	 */
	public static Authentication getUserAuthentication() {
		return Optional.ofNullable(getAuthentication())
				.map(auth -> auth.getUserAuthentication())
				.orElse(null);
	}
	
	/**
	 * Retorna um detalhe da autenticação do usuário
	 * 
	 * @param <T> o tipo do detalhe
	 * @param detail O detalhe
	 * @param detailClass A classe do detalhe
	 * @return um Optional do detalhe especificado
	 */
	public static <T> Optional<T> getUserDetail(String detail, Class<T> detailClass) {
		return Optional.ofNullable(getUserAuthentication())
				.flatMap(user -> Optional.ofNullable(user.getDetails()))
				.map(Map.class::cast)
				.map(details -> details.get(detail))
				.map(detailClass::cast);
	}
	
}
