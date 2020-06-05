package br.com.duosdevelop.vb.igrejaalocacao.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(
                        PathSelectors.regex("/pessoas.*"),
                        PathSelectors.regex("/celulas.*"),
                        PathSelectors.regex("/membros.*"),
                        PathSelectors.regex("/estados.*")
                ))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Celula Alocação REST Webservice",
                "<p>Projeto MVP para disponibilizar informações de alocação de celulas</p>",
                null,
                null,
                null,
                null,
                null,
                Collections.emptyList()
        );
    }
}
