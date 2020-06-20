package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Permissao;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.mock.MockObject;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserDetailsServiceTest {

    @InjectMocks
    private AppUserDetailsService service;
    @Mock
    private PessoaRepository pessoaRepository;

    private Pessoa pessoa = MockObject.getPessoa();
    @Test
    public void shouldLoadUserByUsername() {
        Permissao permissao = new Permissao();
        permissao.setId(1L);
        permissao.setDescricao("Mock");
        pessoa.setPermissoes(Arrays.asList(permissao));
        when(pessoaRepository.findByEmail(pessoa.getEmail())).thenReturn(Optional.of(pessoa));
        UserDetails userDetails = service.loadUserByUsername(pessoa.getEmail());
        assertNotNull(userDetails);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldLoadUserByUsernameException() {
        when(pessoaRepository.findByEmail(pessoa.getEmail())).thenReturn(Optional.empty());
        service.loadUserByUsername(pessoa.getEmail());
    }
}