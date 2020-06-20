package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CelulaServiceTest {

    @InjectMocks
    private CelulaService service;
    @Mock
    private CelulaRepository repository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private MembroRepository membroRepository;

    private Celula celula = MockObject.getCelula();

    @Test
    public void shouldFindAllCelula() {
        when(repository.findAll()).thenReturn(Arrays.asList(celula));
        assertNotNull(service.findAll());
    }

    @Test
    public void shouldFindCelula() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(celula));
        assertEquals(celula, service.find(id));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldFindCelulaException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        service.find(id);
    }

    @Test
    public void insert() {
        when(repository.save(celula)).thenReturn(celula);
        Celula celulaResult = service.insert(celula);
        verify(enderecoRepository).save(celula.getEndereco());
        assertEquals(celula, celulaResult);
    }

    @Test
    public void shouldUpdateCelula() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(celula));
        when(repository.save(celula)).thenReturn(celula);
        Celula celulaResult = service.update(id, celula);
        verify(enderecoRepository).save(celula.getEndereco());
        assertEquals(celula, celulaResult);
    }

    @Test
    public void shouldInsertMembroEmCelula() {
        Long idCelula = 1L;
        List<Long> idMembro = Arrays.asList(1L);
        List<Membro> membros = Arrays.asList(new Membro(MockObject.getPessoa(), true, true));
        when(repository.findById(idCelula)).thenReturn(Optional.of(celula));
        when(membroRepository.findAllById(idMembro)).thenReturn(membros);
        when(membroRepository.saveAll(membros)).thenReturn(membros);
        Celula celulaResult = service.insertMembro(idCelula, idMembro);
        assertEquals(celulaResult.getMembros(), membros);
    }

    @Test
    public void shouldDeleteCelula() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(celula));
        service.delete(id);
        verify(repository).delete(celula);
    }
}