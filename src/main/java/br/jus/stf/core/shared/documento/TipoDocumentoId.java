package br.jus.stf.core.shared.documento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 07.04.2016
 */
@Embeddable
public class TipoDocumentoId extends ValueObjectSupport<TipoDocumentoId> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "SEQ_TIPO_DOCUMENTO")
    private Long id;
    
    public TipoDocumentoId() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public TipoDocumentoId(Long id) {
        this.id = id;
    }
    
    public Long toLong() {
        return id;
    }

     @Override
    public String toString(){
        return id.toString();
    }
    
}
