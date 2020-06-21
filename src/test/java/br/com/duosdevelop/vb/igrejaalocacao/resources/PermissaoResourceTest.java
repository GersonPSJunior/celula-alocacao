package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Permissao;
import br.com.duosdevelop.vb.igrejaalocacao.services.PermissaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PermissaoResourceTest {

    @InjectMocks
    private PermissaoResource resource;
    @Mock
    private PermissaoService service;

    @Test
    public void shouldFindAllPermissao() {
        Permissao permissao = new Permissao(1L, "teste");
        when(service.findAll()).thenReturn(Arrays.asList(permissao));
        ResponseEntity<List<Permissao>> permissaoResult = resource.findAll();
        assertEquals(200, permissaoResult.getStatusCodeValue());
        assertNotNull(permissaoResult.getBody().get(0));
    }
}