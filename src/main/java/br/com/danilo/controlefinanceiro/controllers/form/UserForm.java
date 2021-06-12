package br.com.danilo.controlefinanceiro.controllers.form;

import java.util.Optional;

import javax.transaction.Transactional;

import br.com.danilo.controlefinanceiro.models.User;
import br.com.danilo.controlefinanceiro.repository.UserRepository;

public class UserForm {

	private String username;
	private String name;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User convert() {
		return new User(username, name, password);
	}
	
	@Transactional
	public User update(Long id, UserRepository userRepository) {
		Optional<User> optional = userRepository.findById(id);
				
		if(optional.isPresent()) {
			User user = getUserUpdated(optional);
			return user;
		} else {
			return null;
		}
		
	}
	private User getUserUpdated(Optional<User> optional) {
		User user = optional.get();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

}
