
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.EnrolementRepository;
import domain.Enrolement;

@Component
@Transactional
public class StringToEnrolementConverter implements Converter<String, Enrolement> {

	@Autowired
	EnrolementRepository	enrolementRepository;


	@Override
	public Enrolement convert(final String text) {
		Enrolement res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.enrolementRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}
