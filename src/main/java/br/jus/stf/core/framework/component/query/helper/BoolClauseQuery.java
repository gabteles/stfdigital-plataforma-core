package br.jus.stf.core.framework.component.query.helper;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe que converte um Critério em uma claúsula booleana
 * 
 * @author lucas.rodrigues
 *
 */
class BoolClauseQuery {
	
	private Criteria criteria;
	private JSONObject clauseObject = new JSONObject();

	public BoolClauseQuery(Criteria criteria) {
		this.criteria = criteria;
	}
	
	public void appendQueryTo(JSONObject boolQuery) {
		String key = criteria.getLogicalOperator().toString();
		boolQuery.put(key, clauseObject);
		appendComparisionOperator();
	}
	
	private void appendComparisionOperator() {
		
		switch(criteria.getComparisonOperator()) {
			case EQUALS: 		appendEquals(); break;
			case CONTAINS:  	appendContains(); break;
			case BETWEEN: 		appendBetween(); break;
			case LESS_THAN: 	appendLessThan(); break;
			case GREATER_THAN:  appendGreaterThan(); break;
			case EXISTS:  		appendExists(); break;
			default: throw new IllegalArgumentException("Operador de comparação desconhecido!");
		}
	}
	
	private void appendEquals() {
		JSONObject queryObject = createQueryObject("term");
		queryObject.put(criteria.getField(), getTypedValue());
	}
	
	private void appendContains() {
		JSONObject queryObject = createQueryObject("match_phrase");
		queryObject.put(criteria.getField(), getTypedValue());
	}
	
	private void appendBetween() {
		JSONArray values = new JSONArray(criteria.getValue());
		JSONObject rangeObject = createRangeObject();
		rangeObject.put("gt", getTypedValue(values.get(0).toString()));
		rangeObject.put("lt", getTypedValue(values.get(1).toString()));
	}
	
	private void appendLessThan() {
		JSONObject rangeObject = createRangeObject();
		rangeObject.put("lt", getTypedValue());
	}
	
	private void appendGreaterThan() {
		JSONObject rangeObject = createRangeObject();
		rangeObject.put("gt", getTypedValue());
	}
	
	private void appendExists() {
		JSONObject queryObject = createQueryObject("exists");
		queryObject.put("field", criteria.getValue());
	}
	
	private Object getTypedValue() {
		return getTypedValue(criteria.getValue());
	}
	
	private Object getTypedValue(String value) {
		return "number".equals(criteria.getDataType()) ? Long.parseLong(value) : value;
	}
	
	private JSONObject createQueryObject(String queryType) {
		JSONObject queryObject = new JSONObject();
		clauseObject.put(queryType, queryObject);
		return queryObject;
	}
	
	private JSONObject createRangeObject() {
		JSONObject queryObject = createQueryObject("range");
		JSONObject rangeObject = new JSONObject();
		queryObject.put(criteria.getField(), rangeObject);
		return rangeObject;
	}
}