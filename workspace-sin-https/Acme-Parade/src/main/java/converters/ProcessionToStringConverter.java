
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Procession;

@Component
@Transactional
public class ProcessionToStringConverter implements Converter<Procession, String> {

	@Override
	public String convert(final Procession procession) {
		String res;

		if (procession == null)
			res = null;
		else
			res = String.valueOf(procession.getId());

		return res;
	}
}
