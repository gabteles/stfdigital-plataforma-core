package br.jus.stf.core.shared.eventos;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;

/**
 * @author Lucas Rodrigues
 * 
 * @since 1.0.0
 * @since 27.02.2016
 */
public class ProcessoDistribuido implements DomainEvent<ProcessoDistribuido> {
	
	private static final long serialVersionUID = 1L;
	
	public static final String EVENT_KEY = "processo.distribuido";

	private String processoId;
	
	private Long relatorId;
	
	private String relator;
	
	private Date data;
	
	public ProcessoDistribuido() {
    	// Usado apenas pelo Jackson durante a conversação de Json para uma nova instância.
	}

	public ProcessoDistribuido(String processoId, Long relatorId, String relator, Date data) {
		this.processoId = processoId;
		this.relatorId = relatorId;
		this.relator = relator;
		this.data = data;
	}
	
	public String getProcessoId() {
		return processoId;
	}
	
	public Long getRelatorId() {
		return relatorId;
	}
	
	public String getRelator() {
		return relator;
	}
	
	public Date getData() {
		return data;
	}

	@Override
	public boolean sameEventAs(ProcessoDistribuido other) {
		return new EqualsBuilder()
				.append(getProcessoId(), other.getProcessoId())
				.append(getRelatorId(), other.getRelatorId())
				.append(getData(), other.getData())
				.isEquals();
	}

	@Override
	public String eventKey() {
		return EVENT_KEY;
	}
	
}
