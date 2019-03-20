
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Area;

@Component
@Transactional
public class AreaToStringConverter implements Converter<Area, String> {

	@Override
	public String convert(final Area area) {
		String res;

		if (area == null)
			res = null;
		else
			//res = String.valueOf(Area);
			res = String.valueOf(area.getId());
		return res;
	}

}
