package br.jus.stf.core.framework.component.suggestion;

import br.jus.stf.core.framework.component.ComponentConfig;

/**
 * @author lucas.rodrigues
 *
 */
public class SuggestionConfig implements ComponentConfig {
	
	private String id;
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
	
}
