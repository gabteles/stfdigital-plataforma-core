package br.jus.stf.core.shared;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.eventos.AutuacaoFinalizada;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class AutuacaoFinalizadaUnitTests {
	
	@Test
	public void devemSerConsideradosIguais() {
		AutuacaoFinalizada evento1 = new AutuacaoFinalizada(1L);
		AutuacaoFinalizada evento2 = new AutuacaoFinalizada(1L);
		
		Assert.assertTrue(evento1.sameEventAs(evento2));
	}
	

	@Test
	public void devemSerConsideradosDiferentes() {
		AutuacaoFinalizada evento1 = new AutuacaoFinalizada(1L);
		AutuacaoFinalizada evento2 = new AutuacaoFinalizada(2L);
		
		Assert.assertFalse(evento1.sameEventAs(evento2));
	}
	
}
