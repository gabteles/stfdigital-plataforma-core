package br.jus.stf.core.framework.component.query;

import br.jus.stf.core.framework.component.ComponentConfig;
import br.jus.stf.core.framework.component.navigation.RouteConfig;

/**
 * @author lucas.rodrigues
 *
 */
public class QueryConfig implements ComponentConfig {
	
	private String id;
    private String description;
    private RouteConfig route;
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
	
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
    	this.description = description;
    }
	
	public RouteConfig getRoute() {
		return route;
	}

	public void setRoute(RouteConfig route) {
		this.route = route;
	}
	
}
