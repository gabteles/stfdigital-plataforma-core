package br.jus.stf.core.framework.stream;

import static java.lang.Class.forName;
import static java.lang.String.format;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;

/**
 * Base Class para implementação, nos serviços de negócio, do mecanismo de integração assíncrona. A cada agregado de négocio
 * uma tabela para EVENTOS é associada. Sempre que se desejar publicar um evento, um novo registro deve ser inserido nessa tabela.
 * Após inserção, um poller listará periodicamente os eventos pendentes de publicação, enviando cada evento pelo canal
 * adequado. Incialmente o poller ({@link #load()}) recupera um evento pendente de publicação. Após, um 
 * transformer ({@link #transform(List)}) recupera os detalhes do evento instanciando a classe apropriada. Por fim,
 * um router ({@link #route(DomainEvent)}) envia o evento pelo canal adequado, que será determinado de acordo
 * com sua chave de identificação ({@link DomainEvent#eventKey()}).
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.08.2016
 */
public abstract class StreamConfigurerSupport {
	
	/**
	 * Consulta para listar os eventos que ainda não foram publicados (status = 1). Lista a sequence que será usada para
	 * atualização do registro para publicação (seq_evento), o JSon que será usado para recuperar os detalhes do 
	 * evento (bin_detalhe) e o nome da classes do evento (nom_evento).
	 */
	private static final String EVENTOS_QUERY = "SELECT seq_evento, bin_detalhe, nom_evento FROM %s.evento WHERE tip_status = 1";

	/**
	 * Comando para atualização do evento após publicação. O registro será identificado para o valor da sequence
	 * recuperada pelo select acima e o status será atualizada para o valor "2" (publicado). 
	 */
	private static final String EVENTOS_UPDATE = "UPDATE %s.evento SET tip_status = 2 WHERE seq_evento IN (:seq_evento)";
	
	@Autowired
	private DataSource dataSource;

	/**
	 * Carrega os eventos armazenados na base de dados que ainda não foram publicados (status = 1). 
	 * Após a publicação, o status é alterado para publicado (status = 2). Apenas um evento é carregado a cada
	 * pool, que ocorre a cada segundo. 
	 * 
	 * @return o InboundChannelAdapter para carregamento dos eventos
	 * @throws SQLException em caso de problemas são consultar a tabela de eventos
	 */
	@Bean
	@InboundChannelAdapter(value = "eventos", poller = @Poller(fixedDelay = "1000"))
	public MessageSource<Object> load() throws SQLException {
		JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, format(EVENTOS_QUERY, serviceSchema()));
		adapter.setUpdateSql(format(EVENTOS_UPDATE, serviceSchema()));
		adapter.setMaxRowsPerPoll(1);
		return adapter;
	}
	
	/**
	 * Transforma uma lista de mapas (cada mapa possui armazena as propriedades de um evento) em um
	 * {@link DomainEvent}. Essa lista sempre vai possuir apenas um evento, já que o poller defindo
	 * em {@link #load()} sempre lê um evento por vez. 
	 * 
	 * @param eventos a lista com os eventos selecionado da base (apenas um de cada vez)
	 * @return o evento de domínio que será publicado
	 * 
	 * @throws IOException em caso de erro no parse do json do evento para a classe do evento
	 * @throws ClassNotFoundException em caso de erro ao carregar a classe do evento 
	 */
	@Transformer(inputChannel = "eventos", outputChannel = "roteamento")
	public Object transform(List<Map<String, String>> eventos) throws IOException, ClassNotFoundException {
		return new ObjectMapper().readValue(eventos.get(0).get("BIN_DETALHE"), forName(eventos.get(0).get("NOM_EVENTO")));
	}
	
	/**
	 * Rotea a mensagem (o evento) para o canal apropriado, que nesse caso possui o mesmo nome 
	 * da chave de identificação do evento.
	 * 
	 * @param domainEvent o evento de domínio que será publicado
	 * 
	 * @return o nome do canal para envio do evento
	 */
	@Router(inputChannel = "roteamento")
	public String route(DomainEvent<?> domainEvent) {
		return domainEvent.eventKey();
	}
	
	/**
	 * Retorna o nome do esquema da tabela de EVENTOS, para o serviço que utilizará o mecanismo
	 * 
	 * @return o nome do esquema do serviço
	 */
	protected abstract String serviceSchema();

}
