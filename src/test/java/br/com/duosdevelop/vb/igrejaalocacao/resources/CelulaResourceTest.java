package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.dto.CelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.services.CelulaService;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CelulaResourceTest {

    @InjectMocks
    private CelulaResource resource;
    @Mock
    private CelulaService service;
    @Mock
    private ApplicationEventPublisher publisher;

    private Celula celula = MockObject.getCelula();

    @Test
    public void shouldFindAllCelula() {
        when(service.findAll()).thenReturn(Arrays.asList(celula));
        ResponseEntity<List<CelulaDTO>> celulaResult = resource.findAll();
        assertEquals(200, celulaResult.getStatusCodeValue());
        assertNotNull(celulaResult.getBody().get(0));
        assertEquals(CelulaDTO.class.getName(), celulaResult.getBody().get(0).getClass().getName());
    }

    @Test
    public void shouldFindIdCelula() {
        Long id = 1L;
        when(service.find(id)).thenReturn(celula);
        ResponseEntity<Celula> celulaResponseEntity = resource.find(id);
        assertEquals(200, celulaResponseEntity.getStatusCodeValue());
        assertNotNull(celulaResponseEntity.getBody());
    }

    @Test
    public void shouldInsertCelula() throws Exception {
        when(service.insert(MockObject.getNewCelulaDTO().toDomain())).thenReturn(celula);
        ResponseEntity<Celula> celulaResult = resource.insert(MockObject.getNewCelulaDTO(), new MockHttpServletResponse());
        assertEquals(201, celulaResult.getStatusCodeValue());
        assertNotNull(celulaResult.getBody());
    }

    @Test
    public void shouldUpdateCelula() throws Exception {
        Long id = 1L;
        when(service.update(id, MockObject.getNewCelulaDTO().toDomain())).thenReturn(celula);
        ResponseEntity<Void> result = resource.update(MockObject.getNewCelulaDTO(), id);
        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void shouldInsertMembroEmCelula() {
        ResponseEntity<Void> result = resource.insertMembro(Arrays.asList(1L, 2L), 1L);
        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteCelula() {
        ResponseEntity<Void> result = resource.delete(1L);
        assertEquals(204, result.getStatusCodeValue());
    }
}