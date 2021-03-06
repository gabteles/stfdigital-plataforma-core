package br.jus.stf.core.shared.documento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 19.04.2016
 */
@Embeddable
public class TextoId extends ValueObjectSupport<TextoId> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "SEQ_TEXTO")
    private Long id;
    
    public TextoId() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public TextoId(Long id) {
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
