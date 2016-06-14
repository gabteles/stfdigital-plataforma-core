package br.jus.stf.core.framework.component.navigation;

import br.jus.stf.core.framework.component.ComponentConfig;

/**
 * @author lucas.rodrigues
 *
 */
public class RouteConfig implements ComponentConfig {

	private String id;
    private String stateName;
    private String navigationItem;
    private String translation;
    private String urlPrefix;
    private String src;
    
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
    
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getUrlPrefix() {
		return urlPrefix;
	}
	
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
	public String getSrc() {
		return src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getNavigationItem() {
		return navigationItem;
	}
	
	public void setNavigationItem(String navigationItem) {
		this.navigationItem = navigationItem;
	}
	
	public String getTranslation() {
		return translation;
	}
	
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
}
