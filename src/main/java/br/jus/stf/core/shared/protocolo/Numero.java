package br.jus.stf.core.shared.protocolo;

import javax.persistence.Embeddable;

/**
 * @author Rafael Esdras
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.02.2016
 */
@Embeddable
public class Numero {
	
	private Long numero;
	
	private Integer ano;
	
	public Numero() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}

	public Numero(Long numero, Integer ano) {
		this.numero = numero;
		this.ano = ano;
	}

	public Long numero() {
		return numero;
	}
	
	public Integer ano() {
		return ano;
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s", this.numero(), this.ano());
	}
	
}
