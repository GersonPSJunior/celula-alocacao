package br.com.duosdevelop.vb.igrejaalocacao;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Endereco;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CidadeRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EstadoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class IgrejaAlocacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private MembroRepository membroRepository;
	public static void main(String[] args) {
		SpringApplication.run(IgrejaAlocacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Instancias mocadas

		Estado es1 = new Estado(null, "São Paulo");
		Estado es2 = new Estado(null, "Rio de Janeiro");
		Estado es3 = new Estado(null, "Minas Gerais");

		Cidade cid1 = new Cidade(null, "São Paulo", es1);
		Cidade cid2 = new Cidade(null, "Cabo Frio", es2);
		Cidade cid3 = new Cidade(null, "Belo Horizonte", es3);

		Membro mem1 = new Membro(null, "Maria", new Date(System.currentTimeMillis()), "16523721537", null);
		mem1.getTelefone().addAll(Arrays.asList("976678758"));
		Membro mem2 = new Membro(null, "João", new Date(System.currentTimeMillis()), "6278647836", null);
		mem2.getTelefone().addAll(Arrays.asList("93846274", "648376646"));
		Membro mem3 = new Membro(null, "Joaquim", new Date(System.currentTimeMillis()), "768468646", null);
		mem3.getTelefone().addAll(Arrays.asList("93487287", "92346274"));

		Endereco end1 = new Endereco(null, "Rua 1", "1", "Casa", "Vila 1", "63218763", cid1, mem3);
		Endereco end2 = new Endereco(null, "Rua 2", "2", "APT", "Vila 2", "34543544", cid2, mem2);
		Endereco end3 = new Endereco(null, "Rua 3", "3", "Casa", "Vila 3", "45324234", cid1, mem3);
		Endereco end4 = new Endereco(null, "Rua 4", "4", "APT", "Vila 4", "42342554", cid3, mem2);
		Endereco end5 = new Endereco(null, "Rua 5", "5", "Casa", "Vila 5", "43565244", cid2, mem1);

		// Associações de instancias

		es1.getCidades().addAll(Arrays.asList(cid1));
		es2.getCidades().addAll(Arrays.asList(cid2));
		es3.getCidades().addAll(Arrays.asList(cid3));

		mem1.getEnderecos().addAll(Arrays.asList(end5));
		mem2.getEnderecos().addAll(Arrays.asList(end2, end4));
		mem3.getEnderecos().addAll(Arrays.asList(end1, end3));

		// cadastro

		estadoRepository.saveAll(Arrays.asList(es1, es2, es3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		membroRepository.saveAll(Arrays.asList(mem1, mem2, mem3));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5));
	}
}
