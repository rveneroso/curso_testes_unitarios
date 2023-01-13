package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
	
	/*
	 * Essa classe é um exemplo de que o JUnit não garante que a ordem de execução dos testes será a mesma em que foram declarados na classe.
	 * Aqui era de se esperar que o método inicia() fosse executado antes do método verifica(). Eventualmente isso pode acontecer mas não se pode ter certeza disso.
	 * Nos casos em que o método verifica() for executado antes do método inicia(), o teste presente em verifica() irá falhar pois a variável contador ainda não terá recebido o valor 1.
	 * 
	 * Uma forma de se resolver esse problema é através da anotação @FixMethodOrder. Essa anotação aceita diversos valores para definir a ordem de execução dos métodos.
	 * Nesse exemplo utiliza-se o valor MethodSorters.NAME_ASCENDING que, em outras palavras, indica que os métodos serão executados em ordem alfabética crescente. Aqui essa solução
	 * funcionará pois i vem antes de v. Porém, em classes de testes do mundo real, raramente isso acontecerá. Uma solução para isso é prefixar os nomes dos métodos que faça com que
	 * a ordem alfabética seja exatamente a ordem de execução desejada. Por exemplo t1_method..., t2_method..., t3_method... e assim por diante.
	 */
	
	private static int contador = 0;
	
	@Test
	public void inicia() {
		contador = 1;
	}

	@Test
	public void verifica() {
		Assert.assertEquals(1, contador);
	}
}
