package br.com.duosdevelop.vb.igrejaalocacao.repositories;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
