
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ProcessionRepository;
import domain.Procession;

@Component
@Transactional
public class StringToProcessionConverter implements Converter<String, Procession> {

	@Autowired
	ProcessionRepository	processionRepository;


	@Override
	public Procession convert(final String text) {
		Procession res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.processionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
