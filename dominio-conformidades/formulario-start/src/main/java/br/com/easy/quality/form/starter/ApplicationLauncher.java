package br.com.easy.quality.form.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "br.com.easy.quality.form.*" })
@EnableMongoRepositories(basePackages = "br.com.easy.quality.form.adapter.mongo.repository")
@EnableAsync
public class ApplicationLauncher {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class);
	}
}
