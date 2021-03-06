package br.jus.stf.core.framework.component;

import static org.mockito.BDDMockito.given;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.component.command.CommandRegistry;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;
import br.jus.stf.core.framework.security.SecurityChecker;

/**
 * @author lucas.rodrigues
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = ContextInitializer.class)
public class CommandIntegrationTests {
	
	@Autowired
	private DumbApplicationService dumbService;
	
	@Autowired
	private CommandRegistry registry;
	
    @MockBean
    private SecurityChecker securityChecker;
	
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
		given(this.securityChecker.hasPermission(registry.find("do-dumb"))).willReturn(true);
		dumbService.handle(new DoDumbCommand());
	}
	
	@ApplicationService
	static class DumbApplicationService {
		
		@Command(description = "Do Dumb")
		public void handle(DoDumbCommand command) { }
		
	}
	
	class DoDumbCommand {	}
	
}
