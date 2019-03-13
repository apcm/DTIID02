
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Enrolement;

@Component
@Transactional
public class EnrolementToStringConverter implements Converter<Enrolement, String> {

	@Override
	public String convert(final Enrolement enrolement) {
		String res;

		if (enrolement == null)
			res = null;
		else
			res = String.valueOf(enrolement.getId());

		return res;
	}

}
