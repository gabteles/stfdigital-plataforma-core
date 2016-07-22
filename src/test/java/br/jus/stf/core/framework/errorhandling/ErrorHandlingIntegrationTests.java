package br.jus.stf.core.framework.errorhandling;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.integrationtest.ContextInitializer;

/**
 * Executa os testes necessários para validação do mecanismo de tratamento de exceções implementado 
 * pelo {@link GlobalControllerExceptionHandler}.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 21.12.2015
 * 
 * @see ErrorMockRestResource
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = ContextInitializer.class)
public class ErrorHandlingIntegrationTests {
    
	@Autowired
	private MockMvc mockMvc;
	
    @Test
    public void deveRetornarUmErroDeServidor() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/errors/500"));
        
        result.andExpect(status().is5xxServerError());
    }
    
    @Test
    public void deveRetorarUmErroDeRequisicaoInvalida() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/errors/400"));
        
        result.andExpect(status().isBadRequest());
    }
    
    @Test
    public void naoDeveRetornarErros() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/errors/200"));
        
        result.andExpect(status().isOk());
    }
    
}
