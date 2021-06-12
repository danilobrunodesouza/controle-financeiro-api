package br.com.danilo.controlefinanceiro.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.danilo.controlefinanceiro.models.Entry;

public class EntryDTO {

	private Long id;
	private String title;
	private String description;
	private BigDecimal value;

	public EntryDTO(Entry entry) {
		this.setId(entry.getId());
		this.title = entry.getTitle();
		this.description = entry.getDescription();
		this.value = entry.getValue();
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static List<EntryDTO> convert(List<Entry> entries) {
		return entries.stream().map(EntryDTO::new).collect(Collectors.toList());
	}

}
