package br.jus.stf.core.framework.component.command;

import org.apache.commons.lang.Validate;

/**
 * @author lucas.rodrigues
 *
 */
public class CommandTarget {

	private String type;
	private Mode mode;
	
	public CommandTarget(Class<?> type, Mode mode) {
		Validate.notNull(type);
		Validate.notNull(mode);
		
		this.type = type.getSimpleName();
		this.mode = mode;
	}
	
	public String getType() {
		return type;
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public enum Mode {

		None, One, OneOrMany, Many;
		
	}
	
}
