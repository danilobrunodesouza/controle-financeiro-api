package br.com.danilo.controlefinanceiro.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.danilo.controlefinanceiro.models.User;

public class UserDTO {

	private String username;
	private String name;
	private String password;

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.password = user.getPassword();
	}

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

	public static List<UserDTO> convert(List<User> users) {
		return users.stream().map(UserDTO::new).collect(Collectors.toList());
	}

}
