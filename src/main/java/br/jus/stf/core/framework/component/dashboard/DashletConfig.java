package br.jus.stf.core.framework.component.dashboard;

import br.jus.stf.core.framework.component.ComponentConfig;

/**
 * Configuração de dashlet.
 * 
 * @author Tomas.Godoi
 *
 */
public class DashletConfig implements ComponentConfig {

	private String id;
	private String nome;
	private String src;

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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
