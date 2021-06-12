package br.com.danilo.controlefinanceiro.controllers.form;

import br.com.danilo.controlefinanceiro.models.User;

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

}
