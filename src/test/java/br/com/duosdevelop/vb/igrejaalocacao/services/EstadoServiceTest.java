package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EstadoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstadoServiceTest {

    @InjectMocks
    private EstadoService service;
    @Mock
    private EstadoRepository repository;

    private Estado estado = new Estado("Teste Mock");

    @Test
    public void shouldFindAllEstado() {
        List<Estado> estados = Arrays.asList(estado);
        when(repository.findAll()).thenReturn(estados);
        assertNotNull(service.findAll().get(0));
    }

    @Test
    public void shouldFindIdEstado() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(estado));
        assertEquals(estado, service.findId(id));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldFindIdEstadoException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        service.findId(id);
    }

    @Test
    public void shouldInsertEstado() {
        when(repository.save(estado)).thenReturn(estado);
        assertNotNull(service.insert(estado));
    }

    @Test
    public void shouldDeleteEstado() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(estado));
        service.delete(id);
        verify(repository).findById(id);
        verify(repository).delete(estado);
    }

    @Test
    public void shouldDeleteAllEstado() {
        service.deleteAll();
        verify(repository).deleteAll();
    }
}