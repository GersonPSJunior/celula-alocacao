package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CidadeRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CidadeServiceTest {

    @InjectMocks
    private CidadeService service;
    @Mock
    private CidadeRepository repository;

    private Cidade cidade = new Cidade("Teste Mock", new Estado("Teste Mock"));

    @Test
    public void shouldFindAllCidade() {
        when(repository.findAll()).thenReturn(Arrays.asList(cidade));
        assertNotNull(service.findAll());
    }

    @Test
    public void shouldFindIdCidade() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(cidade));
        assertEquals(cidade, service.findId(id));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldFindIdCidadeException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        service.findId(id);
    }

    @Test
    public void shouldInsertCidade() {
        when(repository.save(cidade)).thenReturn(cidade);
        assertEquals(cidade, service.insert(cidade));
    }
}