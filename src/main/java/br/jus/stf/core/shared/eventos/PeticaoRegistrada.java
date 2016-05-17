package br.jus.stf.core.shared.eventos;

import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 03.01.2016
 */
public class PeticaoRegistrada implements DomainEvent<PeticaoRegistrada> {

	private static final long serialVersionUID = 1L;
	
	private Long protocoloId;

	private String protocolo;
	
	private String classeId;
	
	private String tipoProcesso;
	
	private String sigilo;

	public PeticaoRegistrada() {
    	// Usado apenas pelo Jackson durante a conversação de Json para uma nova instância.
	}
	
	public PeticaoRegistrada(Long protocoloId, String protocolo, String classeId, String tipoProcesso, String sigilo) {
		this.protocoloId = protocoloId;
		this.protocolo = protocolo;
		this.classeId = classeId;
		this.tipoProcesso = tipoProcesso;
		this.sigilo = sigilo;
	}
	
	public Long getProtocoloId() {
		return protocoloId;
	}
	
	public String getProtocolo() {
		return protocolo;
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

	@Override
	public boolean sameEventAs(PeticaoRegistrada other) {
		return getProtocoloId().equals(other.getProtocoloId());
	}
	
}
