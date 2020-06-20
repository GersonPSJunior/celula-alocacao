package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Permissao;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PermissaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PermissaoServiceTest {

    @Mock
    private PermissaoRepository repository;

    @InjectMocks
    private PermissaoService service;

    @Test
    public void shouldReturnPermissionList() {
        List<Permissao> permissaoList = Arrays.asList(
                new Permissao(1L, "teste1"),
                new Permissao(2L, "teste2")
        );
        when(repository.findAll()).thenReturn(permissaoList);
        List<Permissao> listResult = service.findAll();
        assertEquals(permissaoList, listResult);
    }
}