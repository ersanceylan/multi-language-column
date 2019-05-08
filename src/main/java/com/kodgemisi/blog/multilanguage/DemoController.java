package com.kodgemisi.blog.multilanguage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
class DemoController {

	private final ProductRepository productRepository;

	@GetMapping
	public String getProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("product", new Product());
		model.addAttribute("defaultLanguage", Languages.getDefaultLanguage());
		model.addAttribute("languages", Languages.values());
		return "products";
	}

	@PostMapping
	public String createProduct(Product product) {
		productRepository.save(product);
		return "redirect:/";
	}
}