package br.jus.stf.core.framework.monitoring;

import static net.logstash.logback.argument.StructuredArguments.fields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Reporta o processamento de uma requisição, com informações de identificação, erros e performance. Registra
 * as informações no Elasticsearch, para posterior pesquisa via Kibana.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0.M4
 * @since 16.10.2015
 */
@Component
public class RequestTraceReporter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTraceReporter.class);
	
	/**
	 * Registra as informações de tracing encapuladas no objeto do tipo {@link RequestTrace} no Elasticsearch. Utiliza a mesma
	 * infraestrutura utiliza por outros mecanismos, como o mecanismo de pesquisa, por exemplo.
	 *
	 * @param requestTrace a {@link RequestTrace} da requisição corrente
	 */
	public void reportRequestTrace(RequestTrace requestTrace) {
		LOGGER.trace("{}", fields(requestTrace));
	}

}
