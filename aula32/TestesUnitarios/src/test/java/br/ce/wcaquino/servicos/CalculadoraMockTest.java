package br.ce.wcaquino.servicos;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class CalculadoraMockTest {

	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		// A verificação abaixo diz que, toda vez que o método somar da classe Calculadora for chamado passando-se os argumentos 1 e 2, deve ser retornado o valor 5.
		// when(calc.somar(1, 2)).thenReturn(5);
		// Quando se usa Matchers na passagem de parâmetros em chamadas de métodos mockados, todos os parâmetros precisam ser chamados através de Matchers.
		// A linha abaixo, por exemplo, causa um erro na execução do teste.
		// when(calc.somar(1, Mockito.anyInt())).thenReturn(5);
		// A solução para o problema acima é a seguinte:
		when(calc.somar(eq(1), Mockito.anyInt())).thenReturn(5);
		
		// System.out.println(calc.somar(1, 8));
	}
}
