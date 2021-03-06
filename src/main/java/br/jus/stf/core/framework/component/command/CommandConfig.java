package br.jus.stf.core.framework.component.command;

import br.jus.stf.core.framework.component.ComponentConfig;
import br.jus.stf.core.framework.component.navigation.RouteConfig;

/**
 * @author lucas.rodrigues
 *
 */
public class CommandConfig implements ComponentConfig {
	
	private String id;
    private String description;
    private RouteConfig route;
    private CommandTarget target;
    private boolean listable;
    private boolean startProcess;
    
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

	public CommandTarget getTarget() {
		return target;
	}

	public void setTarget(CommandTarget target) {
		this.target = target;
	}

	public boolean isListable() {
		return listable;
	}

	public void setListable(boolean listable) {
		this.listable = listable;
	}

	public boolean isStartProcess() {
		return startProcess;
	}
	
	public void setStartProcess(boolean startProcess) {
		this.startProcess = startProcess;
	}
	
}
