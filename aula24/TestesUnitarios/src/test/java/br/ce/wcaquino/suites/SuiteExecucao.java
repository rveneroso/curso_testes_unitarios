package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest;

@RunWith(Suite.class)
// Define quais classes de testes devem ter métodos executados por essa suite.
@SuiteClasses({
	CalculadoraTest.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTest.class
})
public class SuiteExecucao {
	// Declaração obrigatória do Java.
	
	/*
	 * Se houver métodos anotados com @BeforeClass, eles serão executados antes de quaisquer testes da bateria.
	 * Da mesma forma, se houver métodos anotados com @AfterClass, eles serão executados somente quanto todos os testes da bateria tiverem sido executados.
	 */
}
