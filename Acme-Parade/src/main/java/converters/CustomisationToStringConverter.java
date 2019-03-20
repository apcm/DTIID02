package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import domain.Customisation;

@Component
@Transactional
public class CustomisationToStringConverter implements Converter<Customisation, String>{

	@Override
	public String convert(Customisation referee) {
        String result;
		
		if(referee == null){
			result = null;
		}else{
			result = String.valueOf(referee.getId());
		}
		
		return result;
	}
	
	

}