package br.jus.stf.core.framework.component.navigation;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.component.ComponentRegistry;

/**
 * @author lucas.rodrigues
 *
 */
@Component
public class RouteRegistry extends ComponentRegistry<RouteConfig> {

	private static String ROUTES_FILE = "/routes.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void configure() throws Exception {

		InputStream input = getClass().getResourceAsStream(ROUTES_FILE);
		if (input != null) {
			List<RouteConfig> routes = mapper.readValue(input, new TypeReference<List<RouteConfig>>(){});
			routes.forEach(route -> components.add((RouteConfig)route));
		}	
	}

}
