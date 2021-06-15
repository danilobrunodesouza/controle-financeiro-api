package br.com.danilo.controlefinanceiro.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.danilo.controlefinanceiro.models.Profile;

public class ProfileDTO {

	private Long id;
	private String name;

	public ProfileDTO(Profile profile) {
		id = profile.getId();
		name = profile.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<ProfileDTO> convert(List<Profile> profiles) {
		return profiles.stream().map(ProfileDTO::new).collect(Collectors.toList());
	}

}
