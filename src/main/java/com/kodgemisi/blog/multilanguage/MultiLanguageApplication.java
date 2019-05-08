package com.kodgemisi.blog.multilanguage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class MultiLanguageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiLanguageApplication.class, args);
	}

	@Bean
	LocaleResolver localeResolver() {
		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		var lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return (args) -> {
			final var product = new Product();

			var name = new MultiLanguageText(Map.of(Languages.EN.toString(), "Fish", Languages.TR.toString(), "Balık", Languages.FR.toString(), "Poisson"));
			var description = new MultiLanguageText(Map.of(Languages.EN.toString(), "A delicious fish", Languages.TR.toString(), "Lezzetli bir balık", Languages.FR.name(), "Un poisson délicieux"));

			product.setName(name);
			product.setDescription(description);

			productRepository.save(product);

			productRepository.findAll().forEach(p -> {
				log.info("name: {} , description: {}", p.getName(), p.getDescription());
			});
		};
	}

}