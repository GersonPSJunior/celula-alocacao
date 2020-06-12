package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder be;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private CelulaRepository celulaRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;

    public void instantiateTestDatabase(){
        // Instancias mocadas

        Permissao roleCadastroCelula = new Permissao(1L, "ROLE_CADASTRAR_CELULA");
        Permissao roleAtualizaCelula = new Permissao(2L, "ROLE_ATUALIZAR_CELULA");
        Permissao roleAssociarMembroCelula = new Permissao(3L, "ROLE_ASSOCIAR_MEMBRO_CELULA");
        Permissao roleDeletaCelula = new Permissao(4L, "ROLE_DELETAR_CELULA");
        Permissao roleCadastroMembro = new Permissao(5L, "ROLE_CADASTRAR_MEMBRO");
        Permissao rolePesquisarMembro = new Permissao(6L, "ROLE_PESQUISAR_MEMBRO");
        Permissao roleAtualizarMembro = new Permissao(7L, "ROLE_ATUALIZAR_MEMBRO");
        Permissao roleDeletarMembro = new Permissao(8L, "ROLE_DELETAR_MEMBRO");
        Permissao roleAssociarCelulaMembro = new Permissao(9L, "ROLE_ASSOCIAR_CELULA_MEMBRO");
        Permissao roleInserirCelulaMembro = new Permissao(10L, "ROLE_INSERIR_ESTADO");
        Permissao roleAssociarCidadeEstado = new Permissao(11L, "ROLE_ASSOCIAR_CIDADE_ESTADO");
        Permissao rolePesquisaEstado = new Permissao(12L, "ROLE_PESQUISA_ESTADO");
        Permissao roleDeletarEstado = new Permissao(13L, "ROLE_DELETAR_ESTADO");
        Permissao rolePesquisarPessoa = new Permissao(14L, "ROLE_PESQUISAR_PESSOA");
        Estado es1 = new Estado("São Paulo");
        Estado es2 = new Estado("Rio de Janeiro");
        Estado es3 = new Estado("Minas Gerais");

        Cidade cid1 = new Cidade("São Paulo", es1);
        Cidade cid2 = new Cidade("Cabo Frio", es2);
        Cidade cid3 = new Cidade("Belo Horizonte", es3);

        Pessoa maria = new Pessoa("Maria", LocalDate.now(), "16523721537", "maria@gmail.com", be.encode("maria"));
        Pessoa joao = new Pessoa("João", LocalDate.now(), "6278647836", "joao@gmail.com", be.encode("joao"));
        Pessoa joaquim = new Pessoa("Joaquim", LocalDate.now(), "768468646", "joaquim@gmail.com", be.encode("joaquim"));

        maria.setPermissoes(Arrays.asList(roleCadastroCelula, roleAtualizaCelula, roleAssociarMembroCelula,
                roleDeletaCelula, roleCadastroMembro, rolePesquisarMembro, roleAtualizarMembro, roleDeletarMembro,
                roleAssociarCelulaMembro, roleInserirCelulaMembro, roleAssociarCidadeEstado, rolePesquisaEstado,
                roleDeletarEstado, rolePesquisarPessoa));
        joaquim.setPermissoes(Arrays.asList(roleCadastroMembro, rolePesquisarMembro, roleAtualizarMembro, roleDeletarMembro,
                roleAssociarCelulaMembro, roleInserirCelulaMembro, roleAssociarCidadeEstado, rolePesquisaEstado,
                roleDeletarEstado, rolePesquisarPessoa));
        joao.setPermissoes(Arrays.asList(roleCadastroCelula, roleAtualizaCelula, roleAssociarMembroCelula,
                roleDeletaCelula));

        Membro mem1 = new Membro(maria, false, false);
        mem1.getPessoa().getTelefone().addAll(Arrays.asList("976678758"));
        Membro mem2 = new Membro(joao, true, false);
        mem2.getPessoa().getTelefone().addAll(Arrays.asList("93846274", "648376646"));
        Membro mem3 = new Membro(joaquim, false, true);
        mem3.getPessoa().getTelefone().addAll(Arrays.asList("93487287", "92346274"));

        Endereco end1 = new Endereco("Rua 1", "1", "Casa", "Vila 1", "63218763", cid1);
        Endereco end2 = new Endereco("Rua 2", "2", "APT", "Vila 2", "34543544", cid2);
        Endereco end3 = new Endereco("Rua 3", "3", "Casa", "Vila 3", "45324234", cid1);
        Endereco end4 = new Endereco("Rua 4", "4", "APT", "Vila 4", "42342554", cid3);
        Endereco end5 = new Endereco("Rua 5", "5", "Casa", "Vila 5", "43565244", cid2);
        Endereco end6 = new Endereco("Rua 6", "6", "Predio", "Vila 6", "43565244", cid2);

        Celula celula1 = new Celula("celula 1", "Ana", DiasSemana.SABADO, new Date(System.currentTimeMillis()), end3);
        Celula celula2 = new Celula("celula 2", "Maria", DiasSemana.QUARTA, new Date(System.currentTimeMillis()), end2);
        Celula celula3 = new Celula("celula 3", "João", DiasSemana.QUINTA, new Date(System.currentTimeMillis()), end5);
        Celula celula4 = new Celula("celula 4", "Tata", DiasSemana.SABADO, new Date(System.currentTimeMillis()), end4);
        Celula celula5 = new Celula("celula 5", "Fulano", DiasSemana.SEXTA, new Date(System.currentTimeMillis()), end1);


        // Associações de instancias

        es1.getCidades().addAll(Arrays.asList(cid1));
        es2.getCidades().addAll(Arrays.asList(cid2));
        es3.getCidades().addAll(Arrays.asList(cid3));

        mem1.getPessoa().getEnderecos().addAll(Arrays.asList(end5));
        mem2.getPessoa().getEnderecos().addAll(Arrays.asList(end2, end4));
        mem3.getPessoa().getEnderecos().addAll(Arrays.asList(end1, end3));

        celula1.getMembros().addAll(Arrays.asList(mem1, mem2));
        celula2.getMembros().addAll(Arrays.asList(mem3, mem2));
        celula3.getMembros().addAll(Arrays.asList(mem1));
        celula4.getMembros().addAll(Arrays.asList(mem3));
        celula5.getMembros().addAll(Arrays.asList(mem1, mem3));

        // cadastro

        permissaoRepository.saveAll(Arrays.asList(roleCadastroCelula, roleAtualizaCelula, roleAssociarMembroCelula,
                roleDeletaCelula, roleCadastroMembro, rolePesquisarMembro, roleAtualizarMembro, roleDeletarMembro,
                roleAssociarCelulaMembro, roleInserirCelulaMembro, roleAssociarCidadeEstado, rolePesquisaEstado,
                roleDeletarEstado, rolePesquisarPessoa));
        estadoRepository.saveAll(Arrays.asList(es1, es2, es3));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
        pessoaRepository.saveAll(Arrays.asList(mem1.getPessoa(), mem2.getPessoa(), mem3.getPessoa()));
        membroRepository.saveAll(Arrays.asList(mem1, mem2, mem3));
        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5));
        celulaRepository.saveAll(Arrays.asList(celula1, celula2, celula3, celula4, celula5));
    }
}
