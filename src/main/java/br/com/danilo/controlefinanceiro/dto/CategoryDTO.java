package br.com.danilo.controlefinanceiro.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.danilo.controlefinanceiro.models.Category;

public class CategoryDTO {
	
	private Long id;
	private String title;
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
	}

	public static List<CategoryDTO> convert(List<Category> categories) {
		return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
