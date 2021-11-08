package app.keepcash.srpago.client.srpago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import app.keepcash.srpago.client.FeignCustomErrorDecoder;
import app.keepcash.srpago.config.SecretsInit;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientSrPagoConfig {

	@Autowired
	private SecretsInit secretsInit;

	public FeignClientSrPagoConfig() {
		super();
	}

	@Bean
	Logger feignLoggerLevel() {
		return new Slf4jLogger();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(this.secretsInit.getSrPagoSecrets().getUsername(), 
			this.secretsInit.getSrPagoSecrets().getPassword());
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