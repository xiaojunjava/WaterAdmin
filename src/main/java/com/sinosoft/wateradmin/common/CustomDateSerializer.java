package com.sinosoft.wateradmin.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 为了使用注解@JsonSerialize(using = CustomDateSerializer.class)
 *
 * @author lkj.
 * @create 2017-11-06 17:22
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value,
						  JsonGenerator jsonGenerator,
						  SerializerProvider provider)
			throws IOException {
		jsonGenerator.writeString(CommonUtil.SDF_DATE.format(value));
	}
}
