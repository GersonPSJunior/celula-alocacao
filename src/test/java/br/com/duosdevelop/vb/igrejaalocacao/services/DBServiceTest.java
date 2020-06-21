package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DBServiceTest {

    @InjectMocks
    private DBService service;
    @Mock
    private BCryptPasswordEncoder be;
    @Mock
    private CidadeRepository cidadeRepository;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private EstadoRepository estadoRepository;
    @Mock
    private MembroRepository membroRepository;
    @Mock
    private CelulaRepository celulaRepository;
    @Mock
    private PermissaoRepository permissaoRepository;

    @Test
    public void instantiateTestDatabase() {
        service.instantiateTestDatabase();
        verify(be, times(3)).encode(anyString());
        verify(cidadeRepository).saveAll(any());
        verify(pessoaRepository).saveAll(any());
        verify(enderecoRepository).saveAll(any());
        verify(estadoRepository).saveAll(any());
        verify(membroRepository).saveAll(any());
        verify(celulaRepository).saveAll(any());
        verify(permissaoRepository).saveAll(any());
    }
}