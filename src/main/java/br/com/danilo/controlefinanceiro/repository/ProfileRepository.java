package br.com.danilo.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.controlefinanceiro.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
