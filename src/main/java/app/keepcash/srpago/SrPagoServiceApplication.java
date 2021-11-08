package app.keepcash.srpago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SrPagoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrPagoServiceApplication.class, args);
	}

}
