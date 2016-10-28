package br.jus.stf.core.framework.component.query.helper;

import javax.validation.constraints.NotNull;

/**
 * @author lucas.rodrigues
 *
 */
public class Trait {
	
	@NotNull
    private String field;
	@NotNull
    private String dataType;
    
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}
