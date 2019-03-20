
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ParadeRepository;
import domain.Parade;

@Component
@Transactional
public class StringToParadeConverter implements Converter<String, Parade> {

	@Autowired
	ParadeRepository	paradeRepository;


	@Override
	public Parade convert(final String text) {
		Parade res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.paradeRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
