package br.com.duosdevelop.vb.igrejaalocacao.config;

import br.com.duosdevelop.vb.igrejaalocacao.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() {

        if (!"create".equals(strategy)) return false;

        dbService.instantiateTestDatabase();
        return true;
    }
}