package br.com.danilo.controlefinanceiro.controllers.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.danilo.controlefinanceiro.models.Entry;

public class EntryForm {

	@NotNull @NotEmpty
	private String title;
	@NotNull @NotEmpty
	private String description;
	@NotNull @PositiveOrZero
	private BigDecimal value;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public Entry convert() {
		return new Entry(title, description, value);
	}


		
}
