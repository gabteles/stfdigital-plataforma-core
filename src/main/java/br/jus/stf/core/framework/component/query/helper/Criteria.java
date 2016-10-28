package br.jus.stf.core.framework.component.query.helper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

/**
 * @author lucas.rodrigues
 *
 */
public class Criteria {
	@NotNull
	private LogicalOperator logicalOperator;
	@NotNull
	private ComparisonOperator comparisonOperator;
	@NotNull
	private Trait trait;
	private String value;
	
	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}
	
	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}
	
	public ComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}
	
	public void setComparisonOperator(ComparisonOperator comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}
	
	public void setTrait(Trait trait) {
		this.trait = trait;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDataType() {
		return Optional.ofNullable(trait).map(t -> t.getDataType()).orElse(null);
	}
	
	public String getField() {
		return Optional.ofNullable(trait).map(t -> t.getField()).orElse(null);
	}
	
}
