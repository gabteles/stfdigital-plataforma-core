package br.jus.stf.core.shared.identidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Embeddable
public class RecursoId extends ValueObjectSupport<RecursoId> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SEQ_RECURSO")
	private Long id;

	public RecursoId() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public RecursoId(Long id){
		this.id = id;
	}
	
	public Long toLong(){
		return id;
	}

	@Override
	public String toString() {
		return id.toString();
	}
	
}