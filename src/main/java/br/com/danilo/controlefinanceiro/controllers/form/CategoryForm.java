package br.com.danilo.controlefinanceiro.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.danilo.controlefinanceiro.models.Category;

public class CategoryForm {

	@NotNull @NotEmpty
	private String title;

	@NotNull @NotEmpty
	private String icon;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Category convert() {
		return new Category(title, getIcon());
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	


		
}
