package br.jus.stf.core.framework.component.query.helper;

/**
 * @author lucas.rodrigues
 *
 */
public enum LogicalOperator {
	
	MUST, SHOULD, MUST_NOT;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
	
}
