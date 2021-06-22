package br.com.danilo.controlefinanceiro.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.danilo.controlefinanceiro.controllers.form.CategoryForm;
import br.com.danilo.controlefinanceiro.dto.CategoryDTO;
import br.com.danilo.controlefinanceiro.models.Category;
import br.com.danilo.controlefinanceiro.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		
		return CategoryDTO.convert(categories);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
		Optional<Category> optional = categoryRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new CategoryDTO(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDTO> insertCategory(@RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder) {
		Category category = form.convert();
		categoryRepository.save(category);
		
		URI uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoryDTO(category));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder) {
		Optional<Category> optional = categoryRepository.findById(id);
		
		if(optional.isPresent()) {
			Category category = optional.get();
			category.getValuesFrom(form);
			return ResponseEntity.ok(new CategoryDTO(category));
		} else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeCategory(@PathVariable Long id){
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
		
}
