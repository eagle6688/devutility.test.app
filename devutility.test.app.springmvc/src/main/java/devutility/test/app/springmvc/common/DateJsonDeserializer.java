package devutility.test.app.springmvc.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import devutility.internal.lang.StringUtils;
import devutility.internal.text.format.DateFormatUtils;

public class DateJsonDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat simpleDateFormat = DateFormatUtils.getSimpleDateFormat(DateFormatUtils.STANDARDDATEFORMAT);
		String text = jsonParser.getText();

		if (StringUtils.isNullOrEmpty(text)) {
			return null;
		}

		Date value = null;

		try {
			value = simpleDateFormat.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (value == null) {
			return null;
		}

		return value;
	}
}