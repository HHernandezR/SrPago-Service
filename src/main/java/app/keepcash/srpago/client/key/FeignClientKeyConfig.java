package app.keepcash.srpago.client.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import app.keepcash.srpago.client.FeignCustomErrorDecoder;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientKeyConfig {

	@Value("${key.username}")
	private String userName;

	@Value("${key.password}")
	private String password;

	public FeignClientKeyConfig() {
		super();
	}

	@Bean
	Logger feignLoggerLevel() {
		return new Slf4jLogger();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(this.userName, this.password);
	}

	@Bean
	public RequestInterceptor requestLoggingInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				template.header("Content-Type", "application/json");
			}
		};
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignCustomErrorDecoder();
	}

}
