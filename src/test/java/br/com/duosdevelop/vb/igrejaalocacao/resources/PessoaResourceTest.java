package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.services.PessoaService;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaResourceTest {

    @InjectMocks
    private PessoaResource resource;
    @Mock
    private PessoaService service;

    @Test
    public void shouldFindCpfPessoa() {
        String cpf = "123.123.123-12";
        when(service.findCpf(cpf)).thenReturn(MockObject.getPessoa());
        ResponseEntity<Pessoa> pessoaResult = resource.findCpf(cpf);
        assertEquals(200, pessoaResult.getStatusCodeValue());
        assertNotNull(pessoaResult.getBody());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldFindCpfPessoaException() {
        String cpf = "123.123.123-12";
        doThrow(new ObjectNotFoundException("")).when(service).findCpf(cpf);
        assertEquals(404, resource.findCpf(cpf));
    }

    @Test
    public void shouldInsertPermissao() {
        List<Long> permissoes = Arrays.asList(1L, 2L);
        String cpf = "123.123.123-12";
        ResponseEntity<Void> pessoaResult = resource.insertPermissao(cpf, permissoes);
        assertEquals(204, pessoaResult.getStatusCodeValue());
    }
}