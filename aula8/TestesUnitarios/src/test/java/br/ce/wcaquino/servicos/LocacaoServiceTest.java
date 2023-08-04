package br.ce.wcaquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	/**
	 * Usando ErrorCollector altera-se o padrão do JUnit que é o de interromper os testes tão logo o primeiro teste falhe. Nesse comportamento padrão, se houver mais de um erro nos testes, somente um será
	 * identificado a cada execução.
	 * Com ErrorCollector todos os testes serão realizados e a coleção final conterá todos os testes que falharam. Essa técnica é útil para os casos onde não se deseja separar cada teste em um método próprio
	 * obrigando a se repetir as instruções para se construir o mesmo cenário em cada método.
	 */
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void teste() {
		
		// Cenário
		LocacaoService servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 1, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		Locacao locacao = servico.alugarFilme(usuario, filme);
		
		// Verifiçação
		// Usando checkThat e fazendo import estático das classes.
		error.checkThat(locacao.getValor(), CoreMatchers.is(7.0)); // Pode ser lido como 'verifique que locacao.getValor() é 5.0'
		// Mesmo teste acima escrito de outra forma
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0))); 
		
		// Testando negativas com CoreMatchers.
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0))); 
		
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(false));
		
	}

}
