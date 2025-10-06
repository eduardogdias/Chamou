package br.com.chamou.chamou.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.chamou.chamou.model.entity.Chamada;

import java.util.List;

@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long> {
    // Busca as últimas 3 chamadas finalizadas (atual = false), ordenadas por id desc
    List<Chamada> findTop3ByAtualFalseOrderByIdDesc();
}
