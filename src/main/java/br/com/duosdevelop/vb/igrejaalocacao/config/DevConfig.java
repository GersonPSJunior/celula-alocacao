package br.com.duosdevelop.vb.igrejaalocacao.config;

import br.com.duosdevelop.vb.igrejaalocacao.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    private ApplicationYAMLConfig applicationYAMLConfig;

    @Autowired
    public DevConfig(DBService dbService, ApplicationYAMLConfig applicationYAMLConfig) {
        this.dbService = dbService;
        this.applicationYAMLConfig = applicationYAMLConfig;
    }

    @Bean
    public boolean instantiateDatabase() {

        if (!"create".equals(applicationYAMLConfig.getDbStrategy())) return false;

        dbService.instantiateTestDatabase();
        return true;
    }
}
