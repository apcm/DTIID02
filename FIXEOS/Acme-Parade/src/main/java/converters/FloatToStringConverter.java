
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FloatToStringConverter implements Converter<domain.Float, String> {

	@Override
	public String convert(final domain.Float flo) {
		String res;

		if (flo == null)
			res = null;
		else
			//res = String.valueOf(Float);
			res = String.valueOf(flo.getId());
		return res;
	}

}
