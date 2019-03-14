
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ProcessionRepository;
import domain.Parade;

@Component
@Transactional
public class StringToProcessionConverter implements Converter<String, Parade> {

	@Autowired
	ProcessionRepository	processionRepository;


	@Override
	public Parade convert(final String text) {
		Parade res;
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
