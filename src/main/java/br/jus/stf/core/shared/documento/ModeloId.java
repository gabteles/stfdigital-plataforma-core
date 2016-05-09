package br.jus.stf.core.shared.documento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * Id do Modelo.
 * 
 * @author Tomas.Godoi
 *
 */
@Embeddable
public class ModeloId extends ValueObjectSupport<ModeloId> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SEQ_MODELO", nullable = false)
	private Long id;

	ModeloId() {

	}

	public ModeloId(final Long sequencial) {
		Validate.notNull(sequencial, "modeloId.sequencial.required");

		this.id = sequencial;
	}

	public Long toLong() {
		return id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
