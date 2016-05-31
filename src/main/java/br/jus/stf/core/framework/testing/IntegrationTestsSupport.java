package br.jus.stf.core.framework.testing;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Centraliza o setup básico para os testes de integração.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
public abstract class IntegrationTestsSupport {
 
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;
    
    @Autowired
	private DataSource dataSource;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    protected void loadDataTests(String... scriptsSql) throws SQLException {
    	Connection connection = null;
    	ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
    	for(String scriptSql : scriptsSql) {
    		populator.addScript(new ClassPathResource("/db/tests/" + scriptSql));
    	}
		
		try {
			connection = DataSourceUtils.getConnection(dataSource);
			populator.populate(connection);
		} finally {
			if (connection != null) {
				DataSourceUtils.releaseConnection(connection, dataSource);
			}
		}
	}
    
}
