package br.jus.stf.core.shared.identidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Esdras
 * 
 * @since 1.0.0
 * @since 06.05.2016
 */
@Embeddable
public class MinistroId extends ValueObjectSupport<MinistroId> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "COD_MINISTRO")
    private Long codigo;
    
    public MinistroId() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public MinistroId(Long codigo) {
        this.codigo = codigo;
    }
    
    public Long toLong() {
        return codigo;
    }

     @Override
    public String toString(){
        return codigo.toString();
    }
    
}
