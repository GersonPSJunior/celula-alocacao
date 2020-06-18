package br.com.duosdevelop.vb.igrejaalocacao.config;

import br.com.duosdevelop.vb.igrejaalocacao.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Autowired
    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase(){
        dbService.instantiateTestDatabase();
        return true;
    }
}
