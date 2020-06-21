package br.com.duosdevelop.vb.igrejaalocacao.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationResourceTest {

    @InjectMocks
    private ApplicationResource resource;

    @Test
    public void home() {
        assertEquals(200, resource.home().getStatusCode().value());
    }
}