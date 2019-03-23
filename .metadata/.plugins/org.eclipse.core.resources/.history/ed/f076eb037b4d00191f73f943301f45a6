
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Parade;

@Component
@Transactional
public class ParadeToStringConverter implements Converter<Parade, String> {

	@Override
	public String convert(final Parade parade) {
		String res;

		if (procession == null)
			res = null;
		else
			res = String.valueOf(parade.getId());

		return res;
	}
}
