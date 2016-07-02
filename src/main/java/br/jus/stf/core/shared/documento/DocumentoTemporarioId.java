package br.jus.stf.core.shared.documento;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael.Alencar
 * @version 1.0
 * @since 29-set-2015 18:14:46
 */
public class DocumentoTemporarioId extends ValueObjectSupport<DocumentoTemporarioId> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;

	DocumentoTemporarioId() {

	}

	public DocumentoTemporarioId(final String id){
		Validate.notBlank(id, "documentoTemporarioId.id.required");
		
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id;
	}
	
}