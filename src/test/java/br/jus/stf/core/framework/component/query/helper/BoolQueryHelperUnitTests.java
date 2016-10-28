package br.jus.stf.core.framework.component.query.helper;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lucas.rodrigues
 *
 */
public class BoolQueryHelperUnitTests {

	@Test
	public void geraQueryComClausulaMustEqualsNumber() {
		String query = "{\"bool\":{\"must\":{\"term\":{\"numero\":10}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST);
		criteria.setComparisonOperator(ComparisonOperator.EQUALS);
		Trait trait = new Trait();
		trait.setDataType("number");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("10");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(query, json);
	}
	
	@Test
	public void geraQueryComClausulaMustNotExists() {
		String query = "{\"bool\":{\"must_not\":{\"exists\":{\"field\":\"numero\"}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST_NOT);
		criteria.setComparisonOperator(ComparisonOperator.EXISTS);
		Trait trait = new Trait();
		trait.setDataType("constant");
		trait.setField("field");
		criteria.setTrait(trait);
		criteria.setValue("numero");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(query, json);
	}
	
	@Test
	public void geraQueryComClausulaMustEqualsString() {
		String query = "{\"bool\":{\"must\":{\"term\":{\"numero\":\"10\"}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST);
		criteria.setComparisonOperator(ComparisonOperator.EQUALS);
		Trait trait = new Trait();
		trait.setDataType("string");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("10");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(query, json);
	}
	
	@Test
	public void geraQueryComClausulaShouldContainsString() {
		String query = "{\"bool\":{\"should\":{\"match_phrase\":{\"numero\":\"10\"}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.SHOULD);
		criteria.setComparisonOperator(ComparisonOperator.CONTAINS);
		Trait trait = new Trait();
		trait.setDataType("string");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("10");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(query, json);
	}
	
	@Test
	public void geraQueryComClausulaMustBetweenNumber() {
		String constPart = "{\"bool\":{\"must\":{\"range\":{\"numero\":";
		String ltPart = "\"lt\":20";
		String gtPart = "\"gt\":10";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST);
		criteria.setComparisonOperator(ComparisonOperator.BETWEEN);
		Trait trait = new Trait();
		trait.setDataType("number");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("[10,20]");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertTrue(json.indexOf(constPart) == 0);
		Assert.assertTrue(json.indexOf(ltPart) > 35);
		Assert.assertTrue(json.indexOf(gtPart) > 35);
	}
	
	@Test
	public void geraQueryComClausulaShouldBetweenDate() {
		String constPart = "{\"bool\":{\"should\":{\"range\":{\"data\":";
		String ltPart = "\"lt\":\"20/01/2016\"";
		String gtPart = "\"gt\":\"10/01/2016\"";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.SHOULD);
		criteria.setComparisonOperator(ComparisonOperator.BETWEEN);
		Trait trait = new Trait();
		trait.setDataType("date");
		trait.setField("data");
		criteria.setTrait(trait);
		criteria.setValue("[\"10/01/2016\",\"20/01/2016\"]");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertTrue(json.indexOf(constPart) == 0);
		Assert.assertTrue(json.indexOf(ltPart) > 35);
		Assert.assertTrue(json.indexOf(gtPart) > 35);
	}
	
	@Test
	public void geraQueryComClausulaMustLessThanNumber() {
		String query = "{\"bool\":{\"must\":{\"range\":{\"numero\":{\"lt\":10}}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST);
		criteria.setComparisonOperator(ComparisonOperator.LESS_THAN);
		Trait trait = new Trait();
		trait.setDataType("number");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("10");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(json, query);
	}
	
	@Test
	public void geraQueryComClausulaMustGreaterThanNumber() {
		String query = "{\"bool\":{\"must\":{\"range\":{\"numero\":{\"gt\":10}}}}}";
		
		Criteria criteria = new Criteria();
		criteria.setLogicalOperator(LogicalOperator.MUST);
		criteria.setComparisonOperator(ComparisonOperator.GREATER_THAN);
		Trait trait = new Trait();
		trait.setDataType("number");
		trait.setField("numero");
		criteria.setTrait(trait);
		criteria.setValue("10");
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertEquals(json, query);
	}
	
	@Test
	public void geraQueryComMultiplasClausulas() {
		String constPart = "{\"bool\":";
		String clausePart1 = "\"must\":{\"term\":{\"numero\":10}}";
		String clausePart2 = "\"must_not\":{\"exists\":{\"field\":\"data\"}}";
		
		Criteria criteria1 = new Criteria();
		criteria1.setLogicalOperator(LogicalOperator.MUST);
		criteria1.setComparisonOperator(ComparisonOperator.EQUALS);
		Trait trait1 = new Trait();
		trait1.setDataType("number");
		trait1.setField("numero");
		criteria1.setTrait(trait1);
		criteria1.setValue("10");
		
		Criteria criteria2 = new Criteria();
		criteria2.setLogicalOperator(LogicalOperator.MUST_NOT);
		criteria2.setComparisonOperator(ComparisonOperator.EXISTS);
		Trait trait2 = new Trait();
		trait2.setDataType("constant");
		trait2.setField("field");
		criteria2.setTrait(trait2);
		criteria2.setValue("data");
		
		ArrayList<Criteria> criterias = new ArrayList<>();
		criterias.add(criteria1);
		criterias.add(criteria2);
		
		String json = BoolQueryHelper.toJSONQuery(criterias);
		Assert.assertTrue(json.indexOf(constPart) == 0);
		Assert.assertTrue(json.indexOf(clausePart1) > 0);
		Assert.assertTrue(json.indexOf(clausePart2) > 0);
	}
	
}
