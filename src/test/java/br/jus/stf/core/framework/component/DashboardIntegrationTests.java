package br.jus.stf.core.framework.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.jus.stf.core.framework.component.dashboard.DashboardRegistry;
import br.jus.stf.core.framework.component.dashboard.DashletRegistry;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = ContextInitializer.class)
public class DashboardIntegrationTests {

	@Autowired
	private DashletRegistry dashletRegistry;
	
	@Autowired
	private DashboardRegistry dashboardRegistry;
	
	@Test
	public void testDashlets() {
		Assert.assertEquals(dashletRegistry.list().size(), 2);
		
		Assert.assertNotNull(dashletRegistry.find("dashlet-01"));
		
		Assert.assertEquals(dashletRegistry.list().get(1).getNome(), "Dashlet 02");
		Assert.assertEquals(dashletRegistry.list().get(1).getSrc(), "contexto-02/dashlets");
	}
	
	@Test
	public void testDashboards() {
		Assert.assertEquals(dashboardRegistry.list().size(), 2);
		
		Assert.assertNotNull(dashboardRegistry.find("dashboard-01"));
		
		Assert.assertEquals(dashboardRegistry.list().get(0).getDashletsIds().size(), 2);
		Assert.assertEquals(dashboardRegistry.list().get(1).getDashletsIds().size(), 1);
		
		Assert.assertEquals(dashboardRegistry.list().get(0).getNome(), "Dashboard 01");
		Assert.assertEquals(dashboardRegistry.list().get(1).getNome(), "Dashboard 02");
	}
	
}
