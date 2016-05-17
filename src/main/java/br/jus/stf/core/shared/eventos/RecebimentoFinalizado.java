package br.jus.stf.core.shared.eventos;

import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class RecebimentoFinalizado implements DomainEvent<RecebimentoFinalizado> {

	private static final long serialVersionUID = 1L;
	
	private Long protocoloId;
	
	private String classeId;
	
	private String tipoProcesso;
	
	public RecebimentoFinalizado() {
    	// Usado apenas pelo Jackson durante a conversação de Json para uma nova instância.
	}
	
	public RecebimentoFinalizado(Long protocoloId, String classeId, String tipoProcesso) {
		this.protocoloId = protocoloId;
		this.classeId = classeId;
		this.tipoProcesso = tipoProcesso;
	}
	
	public Long getProtocoloId() {
		return protocoloId;
	}

	public String getClasseId() {
		return classeId;
	}
	
	public String getTipoProcesso() {
		return tipoProcesso;
	}

	@Override
	public boolean sameEventAs(RecebimentoFinalizado other) {
		return getProtocoloId().equals(other.getProtocoloId());
	}

}
