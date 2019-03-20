package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.CustomisationRepository;



import domain.Customisation;

@Component
@Transactional
public class StringToCustomisationConverter implements Converter<String, Customisation>{
	
	@Autowired
	private CustomisationRepository customisationRepository;

	@Override
	public Customisation convert(String text) {
		Customisation result;
		int id;
		
		try {
			if (StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = this.customisationRepository.findOne(id);
			}
		} catch (Throwable oops){
			throw new IllegalArgumentException(oops);
			
		}
		
		return result;
	}

}
