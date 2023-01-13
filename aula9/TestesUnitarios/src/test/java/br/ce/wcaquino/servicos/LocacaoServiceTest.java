package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import junit.framework.Assert;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoServiceTest {
	
	/**
	 * Usando ErrorCollector altera-se o padrão do JUnit que é o de interromper os testes tão logo o primeiro teste falhe. Nesse comportamento padrão, se houver mais de um erro nos testes, somente um será
	 * identificado a cada execução.
	 * Com ErrorCollector todos os testes serão realizados e a coleção final conterá todos os testes que falharam. Essa técnica é útil para os casos onde não se deseja separar cada teste em um método próprio
	 * obrigando a se repetir as instruções para se construir o mesmo cenário em cada método.
	 */
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Quando se adiciona a cláusula throws a um método de testes gerenciado pelo JUnit, é o próprio JUnit que vai tratar a exceção.
	 * Aqui temos o caso de um filme sem estoque. Como o métofdo alugarFilme irá lançar a exceção, os testes irão falhar. Porém, em vez de reportar o problema como uma falha, o JUnit irá reportá-lo como um erro.
	 * Isso pode ser visto na console do JUnit.
	 */
	@Test
	public void testeLocacao() throws Exception {
		
		// Cenário
		LocacaoService servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 2, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		Locacao locacao = servico.alugarFilme(usuario, filme);
		
		// Verifiçação
		// Usando assertThat e fazendo import estáticos das classes.
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0)); // Pode ser lido como 'verifique que locacao.getValor() é 5.0'
		// Mesmo teste acima escrito de outra forma
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0))); 
		
		// Testando negativas com CoreMatchers.
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0))); 
		
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
	}
	
	/**
	 * Primeira forma de testar situações nas quais exceções são esperadas. 
	 * Essa forma é chamada de elegante.
	 * @throws Exception 
	 */
	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacao_filmeSemEstoque_elegante() throws Exception {
		// Cenário
		LocacaoService servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 0, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		servico.alugarFilme(usuario, filme);
	}
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		// Cenário
		LocacaoService servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 1, 5.0);
		
		// Ação
		try {
			servico.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
		}
		
		// Essa linha será impressa porque o fluxo de execução segue mesmo após o JUnit ter feito a validação. 
		System.out.println("Forma robusta");
	}
	
	@Test
	public void testeLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// Cenário
		LocacaoService servico = new LocacaoService();
		Usuario usuario = new Usuario("Renato Veneroso");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		// Ação
		servico.alugarFilme(usuario, null);
		
		// Essa linha não será executada porque, como o JUnit foi informado sobre qual exceção esperar, uma vez que ela ocorra O JUnit a captura e assume o fluxo de execução.
		System.out.println("Forma nova");
	}

}
