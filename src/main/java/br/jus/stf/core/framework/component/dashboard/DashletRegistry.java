package br.jus.stf.core.framework.component.dashboard;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.component.ComponentRegistry;

/**
 * Registro de dashlets.
 * 
 * @author Tomas.Godoi
 *
 */
@Component
public class DashletRegistry extends ComponentRegistry<DashletConfig> {

	private static String DASHLETS_FILE = "/dashlets.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Override
	protected void configure() throws Exception {
		InputStream input = getClass().getResourceAsStream(DASHLETS_FILE);
		if (input != null) {
			List<DashletConfig> dashlets = mapper.readValue(input, new TypeReference<List<DashletConfig>>(){});
			dashlets.forEach(dashlet -> components.add(dashlet));
			
			String dashletsString = mapper.writer().writeValueAsString(components);
			discoveryClient.getLocalServiceInstance().getMetadata().put("dashlets", dashletsString);
		}
	}
	
}
