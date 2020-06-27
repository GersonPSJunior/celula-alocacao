package br.com.duosdevelop.vb.igrejaalocacao.config;

import br.com.duosdevelop.vb.igrejaalocacao.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    private final DBService dbService;

    @Autowired
    private ApplicationYAMLConfig applicationYAMLConfig;

    @Autowired
    public ProdConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() {

        if (!"create".equals(applicationYAMLConfig.getDbStrategy())) return false;

        dbService.instantiateTestDatabase();
        return true;
    }
}
