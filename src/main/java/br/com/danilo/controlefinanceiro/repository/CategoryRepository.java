package br.com.danilo.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.controlefinanceiro.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{

}
