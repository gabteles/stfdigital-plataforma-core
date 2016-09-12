package br.jus.stf.core.framework.monitoring;

import static java.util.Optional.ofNullable;
import static org.springframework.web.servlet.DispatcherServlet.EXCEPTION_ATTRIBUTE;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Representa a requisição que será monitorada. Encapsula os objetos HttpServlet (request, response e filterChain) para
 * viabilizar a extração de informações por todo o ciclo de vida da requisição.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0.M4
 * @since 16.10.2015
 */
public class MonitoredRequest {
	
	private HttpServletRequest request;

	private HttpServletResponse response;
	
	private FilterChain filterChain;
	
	private long startTime;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredRequest.class);

	/**
	 * Cria uma nova instância para uma requisição que deverá ser monitorada.
	 * 
	 * @param request a requisição recebida pelo sistema
	 * @param response a resposta gerada como resultado do processamento da requisição
	 * @param filterChain a cadeia de filtros do sistema
	 */
	public MonitoredRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		this.startTime = System.currentTimeMillis();
		this.request = request;
		this.response = response;
		this.filterChain = filterChain;
	}

	/**
	 * Promove o processamento da requisitação, populando um objeto de tracing com informações
	 * do resultado do processamento.
	 * 
	 * @param requestTrace o objeto com informações de tracing
	 * @return o objeto de tracing populado
	 */
	public RequestTrace execute(RequestTrace requestTrace) {
		try {
			filterChain.doFilter(request, response);
		} catch (IOException | ServletException e) {
			LOGGER.error(String.format("Problemas ao tentar processar: %s", requestTrace), e);
		}
		return populate(requestTrace);
	}

	/**
	 * Helper Method para popular o objeto de tracing
	 * 
	 * @param requestTrace o objeto com informações de tracing
	 * @return o objeto de tracing populado
	 */
	private RequestTrace populate(RequestTrace requestTrace) {
		requestTrace.setPerformance(System.currentTimeMillis() - startTime);
		requestTrace.setStatus(response.getStatus());
		return requestTrace;
	}

	/**
	 * Verifica se a requisição falhou, se ocorreu algum problema durante o processamento
	 * 
	 * @return true, se falhou, false, caso contrário
	 */
	public boolean hasFailed() {
		return response.getStatus() >= 400;
	}
	
	/**
	 * Extrai a exceção que deu causa ao problema detectado ao processar a requisição
	 * 
	 * @return stachtrace completa da exceção em questão
	 */
	public String exception() {
        Optional<Throwable> causa = ofNullable((Throwable) request.getAttribute(EXCEPTION_ATTRIBUTE));
        
        return causa.isPresent()? ExceptionUtils.getFullStackTrace(causa.get()):"";
	}

	/**
	 * @return o usuário logado na aplicação
	 */
	public String username() {
		if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
			@SuppressWarnings("unchecked")
			Map<String, Object> principal = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
			return principal.get("login").toString();
		} else if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return "anonimo";
		} else {
			return null;
		}
	}

	/**
	 * @return a URL da requisição
	 */
	public String url() {
		return request.getRequestURI();
	}

	/**
	 * @return o método da requisição (POST, GET e etc.)
	 */
	public String method() {
		return request.getMethod();
	}

}
