package br.jus.stf.core.shared.processo;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 12.05.2016
 */
public enum MeioTramitacao {
	
	ELETRONICO("Eletrônico"),
	FISICO("Físico");
	
	private String descricao;
	
	private MeioTramitacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String descricao() {
		return descricao;
	}

}
