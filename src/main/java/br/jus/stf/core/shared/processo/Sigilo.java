package br.jus.stf.core.shared.processo;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 12.05.2016
 */
public enum Sigilo {
	
	PUBLICO("Público"),
	SEGREDO_JUSTICA("Segredo de Justiça");
	
	private String descricao;
	
	private Sigilo(String descricao) {
		this.descricao = descricao;
	}
	
	public String descricao() {
		return descricao;
	}

}
