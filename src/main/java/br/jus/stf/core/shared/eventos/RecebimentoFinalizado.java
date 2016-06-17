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
	
	private String sigilo;
	
	private Boolean criminalEleitoral;
	
	public RecebimentoFinalizado() {
    	// Usado apenas pelo Jackson durante a conversação de Json para uma nova instância.
	}
	
	public RecebimentoFinalizado(Long protocoloId, String classeId, String tipoProcesso, String sigilo, Boolean criminalEleitoral) {
		this.protocoloId = protocoloId;
		this.classeId = classeId;
		this.tipoProcesso = tipoProcesso;
		this.sigilo = sigilo;
		this.criminalEleitoral = criminalEleitoral;
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
	
	public String getSigilo() {
		return sigilo;
	}
	
	public Boolean isCriminalEleitoral() {
		return criminalEleitoral;
	}

	@Override
	public boolean sameEventAs(RecebimentoFinalizado other) {
		return getProtocoloId().equals(other.getProtocoloId());
	}

}
