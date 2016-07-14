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
 * Registro de dashboards do contexto.
 * 
 * @author Tomas.Godoi
 *
 */
@Component
public class DashboardRegistry extends ComponentRegistry<DashboardConfig> {

	private static String DASHBOARDS_FILE = "/dashboards.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Override
	protected void configure() throws Exception {
		InputStream input = getClass().getResourceAsStream(DASHBOARDS_FILE);
		if (input != null) {
			List<DashboardConfig> dashboards = mapper.readValue(input, new TypeReference<List<DashboardConfig>>(){});
			dashboards.forEach(dashboard -> components.add(dashboard));
			
			String dashboardsString = mapper.writer().writeValueAsString(components);
			discoveryClient.getLocalServiceInstance().getMetadata().put("dashboards", dashboardsString);
		}
	}

}
