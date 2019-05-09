package com.kodgemisi.blog.multilanguage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created on May, 2019
 *
 * @author ersan
 */
@AllArgsConstructor
@Controller
public class ProductController {

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
