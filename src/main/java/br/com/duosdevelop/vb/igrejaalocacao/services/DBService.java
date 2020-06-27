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

    private final BCryptPasswordEncoder be;
    private final CidadeRepository cidadeRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final EstadoRepository estadoRepository;
    private final MembroRepository membroRepository;
    private final CelulaRepository celulaRepository;
    private final PermissaoRepository permissaoRepository;

    @Autowired
    public DBService(BCryptPasswordEncoder be, CidadeRepository cidadeRepository, PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository, EstadoRepository estadoRepository, MembroRepository membroRepository, CelulaRepository celulaRepository, PermissaoRepository permissaoRepository) {
        this.be = be;
        this.cidadeRepository = cidadeRepository;
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
        this.estadoRepository = estadoRepository;
        this.membroRepository = membroRepository;
        this.celulaRepository = celulaRepository;
        this.permissaoRepository = permissaoRepository;
    }

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
        Permissao roleInserirPermissaoPessoa = new Permissao(15L, "ROLE_INSERIR_PERMISSAO_PESSOA");
        Permissao rolePesquisarPermissao = new Permissao(16L, "ROLE_PESQUISAR_PERMISSAO");
        Estado es1 = new Estado("São Paulo");

        Cidade cid1 = new Cidade("São Paulo", es1);

        Pessoa admin = new Pessoa("admin", "12/12/2010", "16523721537", "admin@gmail.com", be.encode("admin"));

        admin.setPermissoes(Arrays.asList(roleCadastroCelula, roleAtualizaCelula, roleAssociarMembroCelula,
                roleDeletaCelula, roleCadastroMembro, rolePesquisarMembro, roleAtualizarMembro, roleDeletarMembro,
                roleAssociarCelulaMembro, roleInserirCelulaMembro, roleAssociarCidadeEstado, rolePesquisaEstado,
                roleDeletarEstado, rolePesquisarPessoa, roleInserirPermissaoPessoa, rolePesquisarPermissao));

        Membro mem1 = new Membro(admin, false, false);
        mem1.getPessoa().getTelefone().addAll(Arrays.asList("976678758"));

        Endereco end1 = new Endereco("Rua 1", "1", "Casa", "Vila 1", "63218763", cid1);

        Celula celula1 = new Celula("celula 1", "Fulano", DiasSemana.SEXTA, new Date(System.currentTimeMillis()), end1);


        // Associações de instancias

        es1.getCidades().addAll(Arrays.asList(cid1));

        mem1.getPessoa().getEnderecos().addAll(Arrays.asList(end1));

        celula1.getMembros().addAll(Arrays.asList(mem1));

        // cadastro

        permissaoRepository.saveAll(Arrays.asList(roleCadastroCelula, roleAtualizaCelula, roleAssociarMembroCelula,
                roleDeletaCelula, roleCadastroMembro, rolePesquisarMembro, roleAtualizarMembro, roleDeletarMembro,
                roleAssociarCelulaMembro, roleInserirCelulaMembro, roleAssociarCidadeEstado, rolePesquisaEstado,
                roleDeletarEstado, rolePesquisarPessoa, roleInserirPermissaoPessoa, rolePesquisarPermissao));
        estadoRepository.saveAll(Arrays.asList(es1));
        cidadeRepository.saveAll(Arrays.asList(cid1));
        pessoaRepository.saveAll(Arrays.asList(mem1.getPessoa()));
        membroRepository.saveAll(Arrays.asList(mem1));
        enderecoRepository.saveAll(Arrays.asList(end1));
        celulaRepository.saveAll(Arrays.asList(celula1));
    }
}
