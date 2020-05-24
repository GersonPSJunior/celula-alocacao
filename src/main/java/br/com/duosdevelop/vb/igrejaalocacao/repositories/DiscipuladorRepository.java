package br.com.duosdevelop.vb.igrejaalocacao.repositories;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscipuladorRepository extends JpaRepository<Discipulador, Long> {
}
