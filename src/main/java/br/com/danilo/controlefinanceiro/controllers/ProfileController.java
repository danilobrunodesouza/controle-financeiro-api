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

import br.com.danilo.controlefinanceiro.controllers.form.ProfileForm;
import br.com.danilo.controlefinanceiro.dto.ProfileDTO;
import br.com.danilo.controlefinanceiro.models.Profile;
import br.com.danilo.controlefinanceiro.repository.ProfileRepository;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@GetMapping
	public List<ProfileDTO> getAllProfiles() {
		List<Profile> profiles = profileRepository.findAll();
		
		return ProfileDTO.convert(profiles);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
		Optional<Profile> optional = profileRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new ProfileDTO(optional.get())); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProfileDTO> insertProfile(@RequestBody @Valid ProfileForm form, UriComponentsBuilder uriBuilder){
		Profile profile = form.convert();
		profileRepository.save(profile);
		URI uri = uriBuilder.path("/profile/{id}").buildAndExpand(profile.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProfileDTO(profile));
	}
	

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody @Valid ProfileForm profileForm, UriComponentsBuilder uriBuilder) {
		Profile profile = profileForm.update(id, profileRepository);
		
		if(profile != null) {			
			return ResponseEntity.ok(new ProfileDTO(profile));
		} else {
			return ResponseEntity.notFound().build();
		
		}
			
	}
	
	
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> deleteProfile(@PathVariable Long id){
		profileRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
}
