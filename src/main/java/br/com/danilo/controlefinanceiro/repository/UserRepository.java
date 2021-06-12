package br.com.danilo.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.controlefinanceiro.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
