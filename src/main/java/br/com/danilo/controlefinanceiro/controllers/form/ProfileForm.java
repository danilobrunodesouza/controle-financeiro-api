package br.com.danilo.controlefinanceiro.controllers.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.danilo.controlefinanceiro.models.Profile;
import br.com.danilo.controlefinanceiro.repository.ProfileRepository;

public class ProfileForm {
	@NotNull @NotEmpty @Length(min=3,max=20)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile convert() {
		return new Profile(name);
	}

	public Profile update(Long id, ProfileRepository profileRepository) {
		Optional<Profile> optional = profileRepository.findById(id);
		if(optional.isPresent()) {
			Profile profile = optional.get();
			profile.setName(name);

			return profile;
		}
		return null;
	}
	
}
