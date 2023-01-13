package br.ce.wcaquino.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {
	
	private Integer diasDiferenca;
	 
	public DataDiferencaDiasMatcher(Integer diasDiferenca) {
		this.diasDiferenca = diasDiferenca;
	}

	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected boolean matchesSafely(Date data) {
		return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(diasDiferenca));
	}
}
