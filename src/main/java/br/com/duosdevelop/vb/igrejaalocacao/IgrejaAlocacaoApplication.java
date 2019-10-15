package br.com.duosdevelop.vb.igrejaalocacao;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.*;
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
	@Autowired
	private CultoRepository cultoRepository;
	@Autowired
	private IgrejaRepository igrejaRepository;
	@Autowired
	private RedeRepository redeRepository;
	@Autowired
	private DiscipuladoRepository discipuladoRepository;
	@Autowired
	private CelulaRepository celulaRepository;

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

		Endereco end1 = new Endereco(null, "Rua 1", "1", "Casa", "Vila 1", "63218763", cid1);
		Endereco end2 = new Endereco(null, "Rua 2", "2", "APT", "Vila 2", "34543544", cid2);
		Endereco end3 = new Endereco(null, "Rua 3", "3", "Casa", "Vila 3", "45324234", cid1);
		Endereco end4 = new Endereco(null, "Rua 4", "4", "APT", "Vila 4", "42342554", cid3);
		Endereco end5 = new Endereco(null, "Rua 5", "5", "Casa", "Vila 5", "43565244", cid2);
		Endereco end6 = new Endereco(null, "Rua 6", "6", "Predio", "Vila 6", "43565244", cid2);

		Igreja igreja = new Igreja(null, "Batista", end6);

		Culto culto1 = new Culto(null, "Adoração", DiasSemana.DOMINGO, new Date(System.currentTimeMillis()), igreja);
		Culto culto2 = new Culto(null, "Jovens", DiasSemana.SABADO, new Date(System.currentTimeMillis()), igreja);

		Rede rede1 = new Rede(null, "rede 1", mem2, igreja);
		Rede rede2 = new Rede(null, "rede 2", mem1, igreja);

		Discipulado discipulado1 = new Discipulado(null, "Discipulado 1", rede1, mem3);
		Discipulado discipulado2 = new Discipulado(null, "Discipulado 2", rede1, mem1);
		Discipulado discipulado3 = new Discipulado(null, "Discipulado 3", rede2, mem2);

		Celula celula1 = new Celula(null, "celula 1", "Ana", DiasSemana.SABADO, new Date(System.currentTimeMillis()), end3, discipulado1);
		Celula celula2 = new Celula(null, "celula 2", "Maria", DiasSemana.QUARTA, new Date(System.currentTimeMillis()), end2, discipulado2);
		Celula celula3 = new Celula(null, "celula 3", "João", DiasSemana.QUINTA, new Date(System.currentTimeMillis()), end5, discipulado1);
		Celula celula4 = new Celula(null, "celula 4", "Tata", DiasSemana.SABADO, new Date(System.currentTimeMillis()), end4, discipulado3);
		Celula celula5 = new Celula(null, "celula 5", "Fulano", DiasSemana.SEXTA, new Date(System.currentTimeMillis()), end1, discipulado3);


		// Associações de instancias

		es1.getCidades().addAll(Arrays.asList(cid1));
		es2.getCidades().addAll(Arrays.asList(cid2));
		es3.getCidades().addAll(Arrays.asList(cid3));

		end1.setMembro(mem3);
		end2.setMembro(mem2);
		end3.setMembro(mem3);
		end4.setMembro(mem2);
		end5.setMembro(mem1);

		mem1.getEnderecos().addAll(Arrays.asList(end5));
		mem2.getEnderecos().addAll(Arrays.asList(end2, end4));
		mem3.getEnderecos().addAll(Arrays.asList(end1, end3));


		igreja.getCultos().addAll(Arrays.asList(culto1, culto2));
		igreja.getRedes().addAll(Arrays.asList(rede1,rede2));

		rede1.getDiscipulados().addAll(Arrays.asList(discipulado1, discipulado2));
		rede1.getDiscipulados().addAll(Arrays.asList(discipulado3));

		celula1.getMembros().addAll(Arrays.asList(mem1, mem2));
		celula2.getMembros().addAll(Arrays.asList(mem3, mem2));
		celula3.getMembros().addAll(Arrays.asList(mem1));
		celula4.getMembros().addAll(Arrays.asList(mem3));
		celula5.getMembros().addAll(Arrays.asList(mem1, mem3));

		// cadastro

		estadoRepository.saveAll(Arrays.asList(es1, es2, es3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		igrejaRepository.saveAll(Arrays.asList(igreja));
		cultoRepository.saveAll(Arrays.asList(culto1, culto2));
		membroRepository.saveAll(Arrays.asList(mem1, mem2, mem3));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5));
		redeRepository.saveAll(Arrays.asList(rede1, rede2));
		discipuladoRepository.saveAll(Arrays.asList(discipulado1, discipulado2, discipulado3));
		celulaRepository.saveAll(Arrays.asList(celula1, celula2, celula3, celula4, celula5));
	}
}
