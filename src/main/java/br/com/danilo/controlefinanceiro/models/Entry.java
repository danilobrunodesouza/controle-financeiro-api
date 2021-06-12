package br.com.danilo.controlefinanceiro.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.danilo.controlefinanceiro.controllers.form.EntryForm;

@Entity
@Table(name = "ENTRY")
public class Entry {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private BigDecimal value;
	
	public Entry() {}
	
	public Entry(String title, String description, BigDecimal value) {
		this.title = title;
		this.description = description;
		this.value = value;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getValue() {
		return value;
	}

	public void getValuesFrom(EntryForm entryForm) {
		this.title = entryForm.getTitle();
		this.description = entryForm.getDescription();
		this.value = entryForm.getValue();
	}
	
	
}
