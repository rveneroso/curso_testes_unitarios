package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;



/*
 * Classe criada apenas para apresentar os diferentes tipos de assertivas disponíveis.
 */
public class AssertTest {
	
	@Test
	public void assertTypes() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		// assertEquals tem comportamentos distintos dependendo do tipo sendo testado.
		// No exemplo apresentado no curso o professor colocou um terceiro parâmetro, um delta indicando qual é a margem de erro
		// aceita na comparação.
		// O teste abaixo não falha.
		// Assert.assertEquals(0.512, 0.51, 0.01);
		
		// O teste abaixo falha.
		// Assert.assertEquals(0.512, 0.51, 0.001);
		
		// Na comparação de Strings, será usado o método equals da classe.
		// O teste abaixo não falha.
		Assert.assertEquals("codex gigas", "codex gigas");
		
		// O teste abaixo não falha apesar de serem objetos diferentes. Sendo o conteúdo o mesmo em ambas as Strings,
		// o resultado é considerado igual.
		Assert.assertEquals("codex gigas", new String("codex gigas"));
		
		// Outros objetos também serão comparados pelo resultado de seus métodos equals.
		// O teste abaixo falha desde que a classe Usuario não tenha o método equals que utiliza o atributo nome como
		// parâmetro de comparação. Como eu implementei o método equals em Usuario baseado no atributo nome, o teste NÃO IRÁ FALHAR.
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Assert.assertEquals(u1,u2);
		
		// Se for necessário verificar se duas instâncias são a mesma, deve-se utilizar assertSame.
		// O teste abaixo não falha.
		Assert.assertSame(u1, u1);
		
		// Já o teste abaixo irá falhar pois trata-se de instâncias distintas
		// Assert.assertSame(u1, u2);
		
		// O teste abaixo não falha pois trata-se de duas instâncias que apontam para o mesmo objeto.
		Usuario u3 = u2;
		Assert.assertSame(u3, u2);
		
		// Para testar se um objeto é nulo
		Usuario u4 = null;
		Assert.assertNull(u4);
		
		// Existem também as assertivas assertNotEquals, assertNotSqme e assertNotNull
		Assert.assertNotEquals("codex gigas", "Leviathan II");
		Assert.assertNotSame(u1,u2);
		Assert.assertNotNull(u3);
		
		// Os métodos da classe Assert possuem uma versão que permite definir uma String que será exibida caso o teste falhe.
		// Por exemplo:
		Assert.assertEquals("As músicas não são as mesmas", "Codex Gigas", "Midnight Star");
	}
}
