package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import junit.framework.Assert;

public class LocacaoServiceTest {
	
	private LocacaoService servico;
	
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
	
	/*
	 * As anotações @Before e @After do JUnit permitem definir métodos a serem executados antes e depois que cada teste é realizado. É possível ter mais de um método anotado com 
	 * @Before ou @After mas pelos testes que fiz, não se pode garantir a sequência em que serão executados.
	 */
	@Before
	public void beforeMethod() {
		servico = new LocacaoService();
	}
	
	/**
	 * Quando se adiciona a cláusula throws a um método de testes gerenciado pelo JUnit, é o próprio JUnit que vai tratar a exceção.
	 * Aqui temos o caso de um filme sem estoque. Como o métofdo alugarFilme irá lançar a exceção, os testes irão falhar. Porém, em vez de reportar o problema como uma falha, o JUnit irá reportá-lo como um erro.
	 * Isso pode ser visto na console do JUnit.
	 */
	@Test
	public void testeLocacao() throws Exception {
		
		// Cenário
		servico = new LocacaoService();
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
	@Test(expected = Exception.class)
	public void testeLocacao_filmeSemEstoque_elegante() throws Exception {
		
		// Cenário
		servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 0, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		servico.alugarFilme(usuario, filme);
	}
	
	/**
	 * Segunda forma de testar situações nas quais exceções são esperadas. 
	 * Essa forma é chamada de robusta. Ela permite controlar o tratamento das exceções de uma maneira que a forma elegante não permite.
	 * @throws Exception 
	 */
	@Test()
	public void testeLocacao_filmeSemEstoque_robusta()  {
		
		// Cenário
		servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 0, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Ação
		try {
			servico.alugarFilme(usuario, filme);
			// Uma forma de alertar para o caso em que era esperada uma exceção e ela não ocorreu é forçar uma condição de erro.
			// Nesse ponto da execução, deveria ter ocorrido uma exceção pelo fato do estoque do filme ser zero. Então, uma condição de erro deve ser forçada.
			Assert.fail("Deveria ter sido lançada uma exceção: filme sem estoque para locação");
		} catch (Exception e) {
			// Ao ocorrer exceção verifica-se se a mensagem da exceção é aquela esperada. Se for, significa que o teste funcionou corretamente.
			assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque"));
		}
	}

	/**
	 * Terceira forma de testar situações nas quais exceções são esperadas. 
	 * O instrutor do curso deu a essa forma o nome de nova.
	 * @throws Exception 
	 */
	@Test()
	public void testeLocacao_filmeSemEstoque_nova() throws Exception {
		
		// Cenário
		servico = new LocacaoService();
		Filme filme = new Filme("The Omen", 0, 5.0);
		Usuario usuario = new Usuario("Renato Veneroso");
		
		// Essas declarações precisam estar antes do ponto onde a exceção irá ocorrer. Caso contrário, a execução não chegará até onde elas se encontram e o teste terminará com erro.
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		// Ação
		servico.alugarFilme(usuario, filme);

	}

}
