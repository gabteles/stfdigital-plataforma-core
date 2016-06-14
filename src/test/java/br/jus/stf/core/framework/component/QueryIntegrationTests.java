package br.jus.stf.core.framework.component;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.component.query.Query;
import br.jus.stf.core.framework.component.query.QueryRegistry;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * @author lucas.rodrigues
 *
 */
@SpringApplicationConfiguration(ContextInitializer.class)
public class QueryIntegrationTests extends IntegrationTestsSupport {
	
	@Autowired
	private QueryRegistry registry;
	
	@Test
	public void querys() {
		Assert.assertTrue(registry.list().size() == 1);
		Assert.assertNotNull(registry.find("search-dumb"));
		Assert.assertNotNull(registry.list().get(0).getRoute());
	}
	
	
	
	@Component
	static class DumbFinder {
		
		@Query(description = "Search Dumb")
		public void execute(SearchDumbQuery query) { }
		
	}
	
	class SearchDumbQuery {	}
	
}
