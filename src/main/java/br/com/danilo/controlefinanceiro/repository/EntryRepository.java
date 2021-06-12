package br.com.danilo.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.controlefinanceiro.models.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long>{

}
