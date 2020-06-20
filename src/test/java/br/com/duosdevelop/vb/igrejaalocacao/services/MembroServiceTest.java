package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MembroServiceTest {

    @InjectMocks
    private MembroService service;
    @Mock
    private MembroRepository repository;
    @Mock
    private CelulaRepository celulaRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private BCryptPasswordEncoder be;

    @Test
    public void shouldFindAllMembroBatizadoAtivo() {
        String ativo = "sim";
        String batizado = "sim";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), true, true)
        );
        when(repository.findAllByAtivoTrueAndBatizadoTrue()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAllByAtivoTrueAndBatizadoTrue();
        assertEquals(true, listResult.get(0).getAtivo());
        assertEquals(true, listResult.get(0).getBatizado());

    }

    @Test
    public void shouldFindAllMembroBatizadoNaoAtivo() {
        String ativo = "nao";
        String batizado = "sim";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), true, false)
        );
        when(repository.findAllByAtivoFalseAndBatizadoTrue()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAllByAtivoFalseAndBatizadoTrue();
        assertEquals(false, listResult.get(0).getAtivo());
        assertEquals(true, listResult.get(0).getBatizado());
    }
    @Test
    public void shouldFindAllMembroNaoBatizadoAtivo() {
        String ativo = "sim";
        String batizado = "nao";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), false, true)
        );
        when(repository.findAllByAtivoTrueAndBatizadoFalse()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAllByAtivoTrueAndBatizadoFalse();
        assertEquals(true, listResult.get(0).getAtivo());
        assertEquals(false, listResult.get(0).getBatizado());
    }
    @Test
    public void shouldFindAllMembroBatizado() {
        String ativo = "";
        String batizado = "sim";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), true, true)
        );
        when(repository.findAllByBatizadoTrue()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAllByBatizadoTrue();
        assertEquals(true, listResult.get(0).getBatizado());
    }
    @Test
    public void shouldFindAllMembroAtivo() {
        String ativo = "sim";
        String batizado = "";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), true, true)
        );
        when(repository.findAllByAtivoTrue()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAllByAtivoTrue();
        assertEquals(true, listResult.get(0).getAtivo());
    }
    @Test
    public void shouldFindAllMembro() {
        String ativo = "";
        String batizado = "";
        List<Membro> membros = Arrays.asList(
                new Membro(MockObject.getPessoa(), true, true)
        );
        when(repository.findAll()).thenReturn(membros);
        List<Membro> listResult = service.findAll(ativo, batizado);
        verify(repository).findAll();
        assertNotNull(listResult.get(0));
    }

    @Test
    public void shouldInsertMembro() {
        Membro membro = new Membro(MockObject.getPessoa(), true, true);
        when(pessoaRepository.findByCpf(membro.getPessoa().getCpf())).thenReturn(Optional.of(membro.getPessoa()));
        when(repository.save(membro)).thenReturn(membro);
        when(be.encode(membro.getPessoa().getSenha())).thenReturn(membro.getPessoa().getSenha());
        Membro membroResult = service.insert(membro);
        verify(be).encode(membro.getPessoa().getSenha());
        verify(pessoaRepository).findByCpf(membro.getPessoa().getCpf());
        verify(pessoaRepository).save(membro.getPessoa());
        verify(enderecoRepository).saveAll(membro.getPessoa().getEnderecos());
        assertEquals(membro, membroResult);
    }

    @Test
    public void shouldFindById() {
        Long id = 1L;
        Membro membro = new Membro(MockObject.getPessoa(), true, true);
        when(repository.findById(id)).thenReturn(Optional.of(membro));
        Membro membroResult = service.find(id);
        assertNotNull(membroResult);
        assertEquals(membro, membroResult);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldFindByIdException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        service.find(id);
    }

    @Test
    public void shouldUpdateMembro() {
        Long id = 1L;
        Membro membro = new Membro(MockObject.getPessoa(), true, true);
        when(repository.findById(id)).thenReturn(Optional.of(membro));
        when(be.encode(membro.getPessoa().getSenha())).thenReturn(membro.getPessoa().getSenha());
        when(repository.save(membro)).thenReturn(membro);
        Membro membroResult = service.update(id, membro);
        verify(be).encode(membro.getPessoa().getSenha());
        assertEquals(membro, membroResult);
    }

    @Test
    public void shouldDeleteMembro() {
        Long id = 1L;
        Membro membro = new Membro(MockObject.getPessoa(), true, true);
        when(repository.findById(id)).thenReturn(Optional.of(membro));
        service.delete(id);
        verify(repository).deleteById(id);
    }

    @Test
    public void shouldUpdateCelulaDoMembro() {
        Long idCelula = 1L;
        Long idMembro = 1L;
        Celula celula = MockObject.getCelula();
        Membro membro = new Membro(MockObject.getPessoa(), true, true);
        when(celulaRepository.findById(idCelula)).thenReturn(Optional.of(celula));
        when(repository.findById(idMembro)).thenReturn(Optional.of(membro));
        when(repository.save(membro)).thenReturn(membro);
        Membro membroResult = service.updateCelula(idMembro, idCelula);
        verify(celulaRepository).save(celula);
        assertEquals(membro, membroResult);
    }
}