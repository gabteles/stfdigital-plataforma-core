package br.jus.stf.core.framework.domaindrivendesign;

import java.io.Serializable;

/**
 * Um evento de domínio é algo único, mas que não tem um ciclo de vida. A identidade pode ser explicita, como o número
 * sequencial de uma petição, ou pode ser derivada de vários aspectos do evento, como onde, quando e o que
 * aconteceu.
 * 
 * <p>Os eventos de domínio devem atender aos seguintes critérios:</p>
 * <ul>
 * 	<li> Implementar a interface {@link DomainEvent} </li>
 *  <li> Possuir Getters e Setters na notação Java Beans </li>
 *  <li> Possuir o construtor default </li>
 * </ul>
 * 
 * @author Rafael Esdras
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 15.12.2015
 */
public interface DomainEvent<T> extends Serializable {

    /**
     * Verifica se um dado evento de domínio e este evento são considerados como sendo o mesmo evento.
     * 
     * @param other um outro evento de domínio, do mesmo tipo
     * @return <code>true</code> se forem considerados iguais, <code>false</code> caso contrário 
     */
    boolean sameEventAs(T other);
    
    /**
     * Retorna a chave de identificação do evento, que será utilizada principalmente
     * para implementação do mecanismo de integração assíncrona.
     * 
     * @return a chave de identificação do evento
     */
    String eventKey();
    
}