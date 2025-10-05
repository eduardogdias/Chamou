package br.com.chamou.chamou.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.chamou.chamou.model.entity.Senha;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {
}
