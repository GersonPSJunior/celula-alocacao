package br.com.duosdevelop.vb.igrejaalocacao.mock;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class MockObject {

    public static Pessoa getPessoa(){
        Pessoa pessoa = new Pessoa("teste mock", LocalDate.now(), "122.122.122-12", "teste@teste.com", "123");
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
}
