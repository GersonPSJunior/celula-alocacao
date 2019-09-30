package br.com.duosdevelop.vb.igrejaalocacao.repositories;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
