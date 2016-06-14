package br.jus.stf.core.framework.component;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.component.command.CommandRegistry;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * @author lucas.rodrigues
 *
 */
@SpringApplicationConfiguration(ContextInitializer.class)
public class CommandIntegrationTests extends IntegrationTestsSupport {
	
	@Autowired
	private DumbApplicationService dumbService;
	
	@Autowired
	private CommandRegistry registry;
	
	@Test
	public void commands() {
		Assert.assertTrue(registry.list().size() == 1);
		Assert.assertNotNull(registry.find("do-dumb"));
		Assert.assertNotNull(registry.list().get(0).getRoute());
	}
	
	@Test
	public void extractId() {
		Assert.assertEquals(registry.extractId(DumbApplicationService.class.getMethods()[0]), "do-dumb");
	}
	
	@Test
	public void execute() {
		dumbService.handle(new DoDumbCommand());
	}
	
	@ApplicationService
	static class DumbApplicationService {
		
		@Command(description = "Do Dumb")
		public void handle(DoDumbCommand command) { }
		
	}
	
	class DoDumbCommand {	}
	
}
