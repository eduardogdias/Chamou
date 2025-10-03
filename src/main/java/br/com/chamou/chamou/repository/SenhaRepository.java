package br.com.chamou.chamou.repository;

import br.com.chamou.chamou.entity.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {
}
