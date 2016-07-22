package br.jus.stf.core.framework.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.jus.stf.core.framework.component.query.Query;
import br.jus.stf.core.framework.component.query.QueryRegistry;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;

/**
 * @author lucas.rodrigues
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = ContextInitializer.class)
public class QueryIntegrationTests {
	
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
