package br.jus.stf.core.framework.component.query.helper;

import java.util.List;

import org.json.JSONObject;

/**
 * Helper para montagem de uma bool query baseada no mecanismo de pesquisa avançada
 * 
 * @author lucas.rodrigues
 *
 */
public class BoolQueryHelper {
	
	public static String toJSONQuery(List<Criteria> criterias) {
		JSONObject query = new JSONObject();
		JSONObject boolQuery = new JSONObject();
		query.put("bool", boolQuery);
		criterias.stream().map(BoolClauseQuery::new).forEach(cq -> cq.appendQueryTo(boolQuery));
		return query.toString();
	}
}
