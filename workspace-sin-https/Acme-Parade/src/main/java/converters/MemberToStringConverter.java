
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Member;

@Component
@Transactional
public class MemberToStringConverter implements Converter<Member, String> {

	@Override
	public String convert(final Member member) {
		String res;

		if (member == null)
			res = null;
		else
			res = String.valueOf(member.getId());

		return res;
	}
}
