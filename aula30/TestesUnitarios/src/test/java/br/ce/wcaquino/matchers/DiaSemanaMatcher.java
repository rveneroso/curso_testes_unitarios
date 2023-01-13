package br.ce.wcaquino.matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

// O generics informado abaixo correponde ao primeiro parâmetro que o matcher irá receber. Nesse caso, será a data de retorno do filme alugado. Portanto, Date.
public class DiaSemanaMatcher extends TypeSafeMatcher<Date>{
	
	private Integer diaSemana;
	
	// Nesse construtor o parâmetro é um Integer porque, ao se utilizar o matcher em alguma classe de testes, será utilizado alguma das constantes de Calendar para se validar o dia da semana. Por exemplo, Calendar.MONDAY é um inteiro.
	// Portanto, essa versão do construtor recebe um inteiro.
	public DiaSemanaMatcher(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}

	// Customiza a mensagem de erro a ser exibida quando um teste realizado por esse Matcher falhar.
	@Override
	public void describeTo(Description description) {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_WEEK, diaSemana);
		// Extrai o nome do dia da semana por extenso.
		String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt","BR"));
		description.appendText(dataExtenso);
		
	}

	@Override
	// O tipo do argumento desse método deve ser o mesmo que foi definido no TypeSafeMatcher na definição da classe. Nesse caso, Date.
	protected boolean matchesSafely(Date data) {
		return DataUtils.verificarDiaSemana(data, diaSemana);
	}

}
