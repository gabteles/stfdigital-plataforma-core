package br.jus.stf.core.framework.command;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.framework.integrationtest.ContextInitializer;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * @author lucas.rodrigues
 *
 */
@SpringApplicationConfiguration(ContextInitializer.class)
public class CommandIntegrationTests extends IntegrationTestsSupport {
	
	@Test
	public void commands() throws Exception {
		ResultActions result = mockMvc.perform(get("/api/commands"));
		result.andExpect(jsonPath("$[0]").value("dumb"));
	}
	
	@ApplicationService
	static class DumbApplicationService {
		
		@Command
		public void handle(DumbCommand command) { }
		
	}
	
	class DumbCommand {	}
	
}
