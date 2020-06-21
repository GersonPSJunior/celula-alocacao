package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.services.CidadeService;
import br.com.duosdevelop.vb.igrejaalocacao.services.EstadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstadoResourceTest {

    @InjectMocks
    private EstadoResource resource;
    @Mock
    private EstadoService service;
    @Mock
    private CidadeService cidadeService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    private Estado estado = new Estado("teste");
    @Test
    public void shouldFindAllEstado() {
        when(service.findAll()).thenReturn(Arrays.asList(estado));
        ResponseEntity<List<Estado>> estadoResult = resource.findAll();
        assertEquals(200, estadoResult.getStatusCodeValue());
        assertNotNull(estadoResult.getBody().get(0));
    }

    @Test
    public void shouldFindIdEstado() {
        Long id = 1L;
        when(service.findId(id)).thenReturn(estado);
        ResponseEntity<Estado> estadoResult = resource.find(id);
        assertEquals(200, estadoResult.getStatusCodeValue());
        assertNotNull(estadoResult.getBody());
    }

    @Test
    public void shouldInsertEstado() {
        when(service.insert(estado)).thenReturn(estado);
        ResponseEntity<Estado> estadoResult = resource.insert(estado, new MockHttpServletResponse());
        assertNotNull(estadoResult.getBody());
        assertEquals(201, estadoResult.getStatusCodeValue());
    }

    @Test
    public void shouldInsertCidadeEmEstado() {
        Long id = 1L;
        when(service.findId(id)).thenReturn(estado);
        ResponseEntity<Void> estadoResult = resource.insertCidade("teste", id);
        verify(cidadeService).insert(new Cidade("teste", estado));
        assertEquals(204, estadoResult.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteEstado() {
        assertEquals(204, resource.delete(1L).getStatusCodeValue());
    }

    @Test
    public void shouldDeleteAllEstado() {
        assertEquals(204, resource.deleteAll().getStatusCodeValue());
    }
}