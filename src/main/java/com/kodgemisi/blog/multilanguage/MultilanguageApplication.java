package com.kodgemisi.blog.multilanguage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Controller
@SpringBootApplication
public class MultilanguageApplication {

	private final ProductRepository productRepository;

	public MultilanguageApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MultilanguageApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return (args) -> {
			Product product = new Product();

			var name = new MultiLanguageText(Map.of(Languages.EN.toString(), "fish", Languages.TR.toString(), "balik"));
			var description = new MultiLanguageText(Map.of(Languages.EN.toString(), "a delicious fish", Languages.TR.toString(), "cok guzel balik"));

			product.setName(name);
			product.setDescription(description);

			productRepository.save(product);

			productRepository.findAll().forEach(p -> {
				log.info("name: {} , description: {}", p.getName(), p.getDescription());
			});
		};
	}

}