package br.jus.stf.core.framework.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author lucas.rodrigues
 *
 */
@RestController
@RequestMapping("/api")
public class CommandRestResource {
	
	@Autowired
	private CommandRegistry registry;

	@ApiOperation("Retorna os comandos do contexto.")
	@RequestMapping("/commands")
	public List<String> getCommands() {
		return registry.list();		
	}
	
}
