package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.MembroOutputDTO;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;
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
public class MembroResourceTest {

    @InjectMocks
    private MembroResource resource;
    @Mock
    private MembroService service;
    @Mock
    private ApplicationEventPublisher publisher;

    private Membro membro = new Membro(MockObject.getPessoa(), true, true);

    @Test
    public void shouldFindAllMembro() {
        String ativo = "sim";
        String batizado = "sim";
        when(service.findAll(ativo, batizado)).thenReturn(Arrays.asList(membro));
        ResponseEntity<List<Membro>> membroResult = resource.findAll(ativo, batizado);
        assertEquals(200, membroResult.getStatusCodeValue());
        assertNotNull(membroResult.getBody().get(0));
    }

    @Test
    public void shouldFindIdMembro() {
        Long id = 1L;
        when(service.find(id)).thenReturn(membro);
        ResponseEntity<MembroOutputDTO> membroResult = resource.find(id);
        assertEquals(200, membroResult.getStatusCodeValue());
        assertNotNull(membroResult.getBody());
    }

    @Test
    public void shouldInsertMembro() throws Exception {
        when(service.insert(MockObject.getNewMembroDTO().toDomain())).thenReturn(membro);
        ResponseEntity<Membro> membroResult = resource.insert(MockObject.getNewMembroDTO(), new MockHttpServletResponse());
        assertEquals(201, membroResult.getStatusCodeValue());
        assertNotNull(membroResult.getBody());
    }

    @Test
    public void shouldUpdateMembro() throws Exception {
        Long id = 1L;
        when(service.update(id, MockObject.getUpdateMembroDTO().toDomain())).thenReturn(membro);
        ResponseEntity<Void> membroResult = resource.update(MockObject.getUpdateMembroDTO(), id, new MockHttpServletResponse());
        assertEquals(204, membroResult.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteMembro() {
        assertEquals(204, resource.delete(1L).getStatusCodeValue());
    }

    @Test
    public void shouldInsertCelula() {
        Long idMembro = 1L;
        Long idCelula = 1L;
        when(service.updateCelula(idMembro, idCelula)).thenReturn(membro);
        assertEquals(204, resource.insertCelula(idMembro, idCelula, new MockHttpServletResponse()).getStatusCodeValue());
    }
}