package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Test
	public void teste() {
		
		// Cenário
		LocacaoService servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 1, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		Locacao locacao = servico.alugarFilme(usuario, filme);
		
		// Verifiçação
		// Usando assertThat e fazendo import estáticos das classes.
		assertThat(locacao.getValor(), CoreMatchers.is(5.0)); // Pode ser lido como 'verifique que locacao.getValor() é 5.0'
		// Mesmo teste acima escrito de outra forma
		assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0))); 
		
		// Testando negativas com CoreMatchers.
		assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0))); 
		
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
	}

}
