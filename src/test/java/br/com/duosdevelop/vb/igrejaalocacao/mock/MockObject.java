package br.com.duosdevelop.vb.igrejaalocacao.mock;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.dto.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class MockObject {

    public static Pessoa getPessoa(){
        Pessoa pessoa = new Pessoa("teste mock", "12/12/2010", "122.122.122-12", "teste@teste.com", "123");
        pessoa.setEnderecos(Arrays.asList(getEndereco()));
        pessoa.setTelefone(new HashSet<>(Arrays.asList("123234314","213214132")));
        return pessoa;
    }

    public static Celula getCelula(){
        return new Celula("celula", "Fulano", DiasSemana.toEnum(1), new Date(System.currentTimeMillis()), getEndereco());
    }

    public static Endereco getEndereco(){
        return new Endereco("rua", "numero", "complemento",
                "bairro", "cep", new Cidade("cidade", new Estado("estado")));
    }

    public static CelulaDTO getCelulaDTO(){
        return new CelulaDTO(getCelula());
    }

    public static NewCelulaDTO getNewCelulaDTO(){
        NewCelulaDTO newCelulaDTO = new NewCelulaDTO();
        newCelulaDTO.setNome("Teste");
        newCelulaDTO.setDia(2);
        newCelulaDTO.setHorario("12:00");
        newCelulaDTO.setLider("Teste");
        newCelulaDTO.setEndereco(getEnderecoDTO());
        return newCelulaDTO;
    }

    public static NewMembroDTO getNewMembroDTO(){
        NewMembroDTO newMembroDTO = new NewMembroDTO();
        newMembroDTO.setAtivo(true);
        newMembroDTO.setBatizado(true);
        newMembroDTO.setCpf("132435342");
        newMembroDTO.setEmail("teste@teste.com");
        newMembroDTO.setEndereco(getEnderecoDTO());
        newMembroDTO.setNascimento("12/12/2010");
        newMembroDTO.setNome("teste");
        newMembroDTO.setSenha("123");
        newMembroDTO.setTelefone1("132532124");
        newMembroDTO.setTelefone2("751735123");
        newMembroDTO.setTelefone3("263452342");
        return newMembroDTO;
    }

    public static UpdateMembroDTO getUpdateMembroDTO(){
        UpdateMembroDTO updateMembroDTO = new UpdateMembroDTO();
        updateMembroDTO.setAtivo(true);
        updateMembroDTO.setBatizado(true);
        updateMembroDTO.setCpf("132435342");
        updateMembroDTO.setEmail("teste@teste.com");
        updateMembroDTO.setEndereco(getEnderecoDTO());
        updateMembroDTO.setNascimento("12/12/2010");
        updateMembroDTO.setNome("teste");
        updateMembroDTO.setSenha("123");
        updateMembroDTO.setTelefone1("132532124");
        updateMembroDTO.setTelefone2("751735123");
        updateMembroDTO.setTelefone3("263452342");
        return updateMembroDTO;
    }

    private static EnderecoDTO getEnderecoDTO() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1);
        enderecoDTO.setBairro("teste");
        enderecoDTO.setCep("13456234");
        enderecoDTO.setCidade(1L);
        enderecoDTO.setComplemento("casa");
        enderecoDTO.setEstado(1L);
        enderecoDTO.setNumero("245");
        enderecoDTO.setRua("teste");
        return enderecoDTO;
    }
}
