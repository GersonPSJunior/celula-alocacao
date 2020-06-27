package br.com.duosdevelop.vb.igrejaalocacao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring")
public class ApplicationYAMLConfig {

    private Security security = new Security();
    private String dbStrategy;

    public String getDbStrategy() {
        return dbStrategy;
    }

    public void setDbStrategy(String dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public static class Security{
        private String clientId;
        private String clientSecret;
        private String[] scope;
        private String[] authorizationGrantType;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String[] getScope() {
            return scope;
        }

        public void setScope(String[] scope) {
            this.scope = scope;
        }

        public String[] getAuthorizationGrantType() {
            return authorizationGrantType;
        }

        public void setAuthorizationGrantType(String[] authorizationGrantType) {
            this.authorizationGrantType = authorizationGrantType;
        }
    }
}
