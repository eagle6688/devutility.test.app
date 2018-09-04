package devutility.test.app.springmvc.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import devutility.internal.text.format.DateFormatUtils;

public class DateJsonSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		SimpleDateFormat simpleDateFormat = DateFormatUtils.getSimpleDateFormat(DateFormatUtils.STANDARDDATEFORMAT);
		String text = simpleDateFormat.format(value);
		gen.writeString(text);
	}
}