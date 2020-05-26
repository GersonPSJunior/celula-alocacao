package br.com.duosdevelop.vb.igrejaalocacao.repositories;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {

    List<Membro> findAllByAtivoTrueAndBatizadoTrue();

    List<Membro> findAllByAtivoTrue();

    List<Membro> findAllByBatizadoTrue();

    List<Membro> findAllByAtivoTrueAndBatizadoFalse();

    List<Membro> findAllByAtivoFalseAndBatizadoTrue();
}
