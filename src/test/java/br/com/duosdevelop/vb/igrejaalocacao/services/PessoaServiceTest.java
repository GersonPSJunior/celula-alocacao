package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @InjectMocks
    private PessoaService service;

    private String cpf = "123.123.123-12";

    @Test
    public void shouldReturnPerson() {
        Pessoa pessoa = MockObject.getPessoa();
        pessoa.setCpf(cpf);
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(pessoa));
        Pessoa pessoaResult = service.findCpf(this.cpf);
        assertNotNull(pessoaResult);
        assertEquals(pessoa.getNome(), pessoaResult.getNome());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldReturnException() {
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());
        service.findCpf(this.cpf);
    }

    @Test
    public void shouldInsertPermission() {
        Pessoa pessoa = MockObject.getPessoa();
        pessoa.setCpf(cpf);
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(pessoa));
        service.insertPermissoes(cpf, Arrays.asList(1L,2L));
        verify(repository).save(pessoa);
    }
}