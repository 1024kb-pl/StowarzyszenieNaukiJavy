package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Product;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.ProductRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
	private ProductRepository productRepository;

	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> allProducts()
	{
		List<Product> allProducts = productRepository.findAll();
		return ResponseEntity.ok(allProducts);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable Long id)
	{
		Product product = productRepository.findById(id).get();
		System.out.println(product);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProduct(@RequestBody Product product)
	{
		Product save = productRepository.save(product);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(save.getId())
						.toUri();
		return ResponseEntity.created(location).body(save);
	}
}
