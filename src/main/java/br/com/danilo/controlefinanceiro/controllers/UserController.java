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

import br.com.danilo.controlefinanceiro.controllers.form.UserForm;
import br.com.danilo.controlefinanceiro.dto.UserDTO;
import br.com.danilo.controlefinanceiro.models.User;
import br.com.danilo.controlefinanceiro.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return UserDTO.convert(users);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new UserDTO(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDTO> insertUser(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.convert();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {
		User user = userForm.update(id, userRepository);
		
		if(user != null) {			
			return ResponseEntity.ok(new UserDTO(user));
		} else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> removeEntry(@PathVariable Long id){
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
