package com.hutter.front.site.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class FastJsonAutoConfiguration {

	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		messageConverter.setSupportedMediaTypes(supportedMediaTypes);
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.QuoteFieldNames,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.SortField,
				SerializerFeature.WriteDateUseDateFormat);
		messageConverter.setFastJsonConfig(fastJsonConfig);
		return messageConverter;
	}
	
	@Bean
	public HttpMessageConverters messageConverters() {
		HttpMessageConverter<?> fastjson = fastJsonHttpMessageConverter();
		return new HttpMessageConverters(fastjson);
	}
}
