package br.jus.stf.core.shared.controletese;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;
import br.jus.stf.core.shared.classe.ClasseId;

/**
 * @author Rafael Esdras
 * 
 * @since 1.0.0
 * @since 13.05.2016
 */
@Embeddable
public class AssuntoId extends ValueObjectSupport<ClasseId> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_ASSUNTO")
	private String codigo;

	public AssuntoId() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}

	public AssuntoId(String codigo){
		this.codigo = codigo;
	}

	@Override
	public String toString(){
		return codigo;
	}
	
}