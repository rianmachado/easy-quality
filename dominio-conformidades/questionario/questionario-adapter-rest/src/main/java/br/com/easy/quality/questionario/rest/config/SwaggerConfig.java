/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API questionario").description("API questionario")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licesen.html")
				.termsOfServiceUrl("api.stelo/transacional").version("1.0")
				.contact(new Contact("", "", "bitcoin@stelo.com")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.easy.quality.form.rest.api")).build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class).apiInfo(apiInfo());
	}
}