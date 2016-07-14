package br.jus.stf.core.framework.component.dashboard;

import java.util.List;

import br.jus.stf.core.framework.component.ComponentConfig;

/**
 * Configuração de dashboard.
 * 
 * @author Tomas.Godoi
 *
 */
public class DashboardConfig implements ComponentConfig {

	private String id;
	private String nome;
	private List<String> dashletsIds;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getDashletsIds() {
		return dashletsIds;
	}

	public void setDashletsIds(List<String> dashletsIds) {
		this.dashletsIds = dashletsIds;
	}

}
