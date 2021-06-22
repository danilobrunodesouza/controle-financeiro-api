package br.com.danilo.controlefinanceiro.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.danilo.controlefinanceiro.controllers.form.CategoryForm;

@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	public Category() {}
	
	public Category(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void getValuesFrom(@Valid CategoryForm form) {
		this.title = form.getTitle();
	}
	
	
}
