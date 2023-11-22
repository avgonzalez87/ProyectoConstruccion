package com.ucatolica.easyevent.easyevent;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
@SpringBootApplication
public class EasyeventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyeventApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.info(new Info()
						.title("Prueba de swagger spring boot 3 API")
						.version("1.0.0")
						.description("Ejemplo swagger construccion de software")
						.termsOfService("https://swagger.io/terms")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}